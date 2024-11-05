package com.example.projectc1023i1.service.product.impl;

import com.example.projectc1023i1.Dto.product.bill.OrderDTO;
import com.example.projectc1023i1.Dto.product.income.IncomeDTO;

import com.example.projectc1023i1.model.product.Order;
import com.example.projectc1023i1.model.product.OrderDetails;
import com.example.projectc1023i1.repository.IUserRepository;
import com.example.projectc1023i1.repository.product.OrderDetailsRepository;
import com.example.projectc1023i1.repository.product.OrderRepository;
import com.example.projectc1023i1.repository.product.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll(); // Lấy tất cả các đơn hàng từ OrderRepository
        return orders.stream()
                .map(this::convertToDTO) // Chuyển đổi từng đơn hàng sang OrderDTO
                .collect(Collectors.toList());
    }

    public OrderDTO getOrderById(Integer id) {
        return orderRepository.findById(id)
                .map(this::convertToDTO) // Chuyển đổi sang OrderDTO
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<OrderDTO> getOrdersByDate(LocalDateTime dateCreate) {
        List<Order> orders = orderRepository.findByDayCreate(dateCreate);
        return orders.stream()
                .map(this::convertToDTO) // Chuyển đổi sang OrderDTO
                .collect(Collectors.toList());
    }

    private OrderDTO convertToDTO(Order order) {
        OrderDTO dto = new OrderDTO();

        // Tính tổng moneyOrder và quantity từ OrderDetails
        List<OrderDetails> orderDetails = orderDetailsRepository.findByOrder(order);

        double totalMoneyOrder = orderDetails.stream()
                .mapToDouble(OrderDetails::getTotalMoneyOrder)
                .sum();

        int totalQuantity = orderDetails.stream()
                .mapToInt(OrderDetails::getQuantity)
                .sum();

        dto.setOrderId(order.getOrderId());
        dto.setTableName(order.getTable().getCode());
        dto.setDayCreate(order.getDayCreate());
        dto.setTotalMoneyOrder(totalMoneyOrder); // Tổng tiền từ OrderDetails
        dto.setCreatorName(order.getUser().getFullName());
        dto.setQuantily(totalQuantity); // Tổng số lượng từ OrderDetails

        // Thêm thông tin chi tiết sản phẩm
        List<String> productNames = orderDetails.stream()
                .map(detail -> detail.getProduct().getProductName()+ " x " +detail.getProduct().getProductPrice()+ " x " + detail.getQuantity()+" x " + detail.getQuantity()*detail.getProduct().getProductPrice())
                .collect(Collectors.toList());

        dto.setProductDetails(productNames); // Giả sử bạn có trường productDetails trong OrderDTO

        return dto;
    }
}