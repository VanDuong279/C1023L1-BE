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
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private TableRepository tableRepository;

    public List<OrderDTO> getAllOrders() {
        // Lấy tất cả OrderDetails từ repository
        List<OrderDetails> allOrderDetails = orderDetailsRepository.findAll();

        // Gom nhóm OrderDetails theo Order và tạo danh sách OrderDTO
        return allOrderDetails.stream()
                .collect(Collectors.groupingBy(OrderDetails::getOrder)) // Nhóm theo Order
                .entrySet().stream()
                .map(entry -> convertToDTO(entry.getKey(), entry.getValue())) // Chuyển đổi mỗi nhóm sang OrderDTO
                .collect(Collectors.toList());
    }

    public OrderDTO getOrderById(Integer id) {
        // Lấy danh sách OrderDetails của Order có id yêu cầu
        List<OrderDetails> orderDetails = orderDetailsRepository.findByOrderId(id);

        if (orderDetails.isEmpty()) {
            throw new RuntimeException("Order not found");
        }

        // Lấy Order từ OrderDetails đầu tiên (do các OrderDetails cùng thuộc về một Order)
        Order order = orderDetails.get(0).getOrder();

        return convertToDTO(order, orderDetails); // Chuyển đổi sang OrderDTO
    }
    public List<OrderDTO> getOrdersByDate(LocalDateTime dateCreate) {
        List<OrderDetails> orders = orderRepository.findByDayCreate(dateCreate);
        return orders.stream()
                .collect(Collectors.groupingBy(OrderDetails::getOrder))
                .entrySet().stream()
                .map(entry -> convertToDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    private OrderDTO convertToDTO(Order order, List<OrderDetails> orderDetails) {
        OrderDTO dto = new OrderDTO();

        // Tính tổng tiền và số lượng từ danh sách OrderDetails
        double totalMoneyOrder = orderDetails.stream()
                .mapToDouble(OrderDetails::getTotalMoneyOrder)
                .sum();

        int totalQuantity = orderDetails.stream()
                .mapToInt(OrderDetails::getQuantity)
                .sum();

        dto.setOrderId(order.getOrderId());
        dto.setTableName(order.getTable().getCode());
        dto.setDayCreate(orderDetails.get(0).getDayCreate()); // Sử dụng dayCreate từ OrderDetails
        dto.setTotalMoneyOrder(totalMoneyOrder); // Tổng tiền từ OrderDetails
        dto.setCreatorName(order.getUser().getFullName());
        dto.setQuantily(totalQuantity); // Tổng số lượng từ OrderDetails

        // Thêm thông tin chi tiết sản phẩm
        List<String> productDetails = orderDetails.stream()
                .map(detail -> detail.getProduct().getProductName() + " x " +
                        detail.getProduct().getProductPrice() + " x " +
                        detail.getQuantity() + " = " +
                        (detail.getQuantity() * detail.getProduct().getProductPrice()))
                .collect(Collectors.toList());

        dto.setProductDetails(productDetails); // Giả sử bạn có trường productDetails trong OrderDTO

        return dto;
    }
}