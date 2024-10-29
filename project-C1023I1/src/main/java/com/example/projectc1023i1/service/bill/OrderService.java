//package com.example.projectc1023i1.service.bill;
//
//
//import com.example.projectc1023i1.Dto.bill.OrderDTO;
//import com.example.projectc1023i1.model.product.Order;
//import com.example.projectc1023i1.model.product.OrderDetails;
//import com.example.projectc1023i1.repository.IUserRepository;
//import com.example.projectc1023i1.repository.bill.IOrderDetailsRepository;
//import com.example.projectc1023i1.repository.bill.IOrderRepository;
//import com.example.projectc1023i1.repository.bill.ITableRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class OrderService {
//
//    @Autowired
//    private IOrderRepository orderRepository;
//
//    @Autowired
//    private IUserRepository userRepository;
//
//    @Autowired
//    private ITableRepository tableRepository;
//
//    @Autowired
//    private IOrderDetailsRepository orderDetailsRepository;
//
//    public List<OrderDTO> getAllOrders() {
//        List<Order> orders = orderRepository.findAll();
//        return orders.stream()
//                .map(order -> convertToDTO(order))
//                .collect(Collectors.toList());
//    }
//
//    public OrderDTO getOrderById(Integer id) {
//        return orderRepository.findById(id)
//                .map(order -> convertToDTO(order))
//                .orElseThrow(() -> new RuntimeException("Order not found"));
//    }
//
//    public List<OrderDTO> getOrdersByDate(LocalDate dateCreate) {
//        List<Order> orders = orderRepository.findByDayCreate(dateCreate);
//        return orders.stream()
//                .map(order -> convertToDTO(order))
//                .collect(Collectors.toList());
//    }
//
//    private OrderDTO convertToDTO(Order order) {
//        OrderDTO dto = new OrderDTO();
//        dto.setOrderId(order.getOrderId());
//        dto.setDayCreate(LocalDate.from(order.getDayCreate()));
//        dto.setCreatorName(order.getUser().getFullName());
//        dto.setTableName(order.getTable().getTableName());
//        dto.setTotalMoneyOrder(order.getTotalMoneyOrder());
//
//        // Truy vấn OrderDetails từ OrderDetailsRepository
//        List<OrderDetails> orderDetailsList = orderDetailsRepository.findByOrder(order);
//
//        // Nếu có OrderDetails, lấy thông tin đầu tiên để gán
//        if (!orderDetailsList.isEmpty()) {
//            OrderDetails orderDetail = orderDetailsList.get(0);
//            dto.setProductName(orderDetail.getProduct().getProductName());
//            dto.setProductPrice(orderDetail.getProduct().getProductPrice());
//            dto.setQuantily(order.getQuantity());
//        }
//        return dto;
//    }
//}
