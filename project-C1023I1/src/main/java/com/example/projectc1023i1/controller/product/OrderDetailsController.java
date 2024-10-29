package com.example.projectc1023i1.controller.product;

import com.example.projectc1023i1.Dto.product.CallOrderRequestDto;
import com.example.projectc1023i1.Dto.product.CallServiceRequestDto;
import com.example.projectc1023i1.Dto.product.OrderDetailsDto;
import com.example.projectc1023i1.model.Users;
import com.example.projectc1023i1.model.product.*;
import com.example.projectc1023i1.repository.product.NotificationRepository;
import com.example.projectc1023i1.service.product.CallOrderRequestService;
import com.example.projectc1023i1.service.product.OrderDetailsService;
import com.example.projectc1023i1.service.product.TableService;
import com.example.projectc1023i1.service.product.impl.IProductService;
import com.example.projectc1023i1.service.product.impl.ProductService;
import com.example.projectc1023i1.service.user.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderDetailsController {

    @Autowired
    private OrderDetailsService orderDetailsService;
    @Autowired
    private ProductService productService; // Sử dụng service để lấy sản phẩm từ DB
    @Autowired
    private TableService tableService;
    @Autowired
    private IProductService service;
    @Autowired
    private UserService userService;
    @Autowired
    private CallOrderRequestService callOrderRequestService;
    @Autowired
    private NotificationRepository notificationRepository;

    // Lấy danh sách tất cả đơn đặt hàng
    @GetMapping("")
    public ResponseEntity<List<OrderDetails>> getAllOrderDetails() {
        List<OrderDetails> orderDetailsList = orderDetailsService.findAll();
        return ResponseEntity.ok(orderDetailsList);
    }

    // Lấy danh sách đơn hàng theo ID bàn
    @GetMapping("/table/{tableId}")
    public ResponseEntity<List<OrderDetails>> getOrdersByTableId(@PathVariable int tableId) {
        List<OrderDetails> ordersByTable = orderDetailsService.findOrdersByTableId(tableId);
        System.out.println("Orders retrieved for table " + tableId + ": " + ordersByTable);
        if (ordersByTable.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ordersByTable, HttpStatus.OK);
    }

    // Thêm một đơn đặt hàng
    @PostMapping("")
    public ResponseEntity<OrderDetails> createOrderDetails(@RequestBody OrderDetailsDto orderDetailsDto) {
        System.out.println("OrderDetailsDto: " + orderDetailsDto);
        if (orderDetailsDto.getTableId() == null) {
            throw new IllegalArgumentException("Table ID is missing.");
        }

        Table table = tableService.findById(orderDetailsDto.getTableId());
        if (table == null) {
            throw new IllegalArgumentException("Invalid Table ID.");
        }

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setTable(table);
        orderDetails.setQuantity(orderDetailsDto.getQuantity());
        orderDetails.setShippingDay(orderDetailsDto.getShippingDay());
        orderDetails.setDayCreate(LocalDateTime.now());
        orderDetails.setDayUpdate(LocalDateTime.now());
        orderDetails.setTotalMoneyOrder(orderDetailsDto.getTotalMoneyOrder());
        orderDetails.setStatus(orderDetailsDto.getStatus());
        orderDetails.setCallOrderTime(orderDetailsDto.getCallOrderTime());
        orderDetails.setCallServiceTime(orderDetailsDto.getCallServiceTime());

        orderDetailsService.save(orderDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Xóa một đơn đặt hàng theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderDetails(@PathVariable int id) {
        System.out.println("Deleting order with ID: " + id);
        OrderDetails orderDetails = orderDetailsService.findById(id);
        if (orderDetails == null) {
            System.out.println("Order not found for ID: " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        orderDetailsService.delete(orderDetails);
        System.out.println("Order deleted successfully for ID: " + id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Lấy thông tin đơn đặt hàng theo ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderDetails> getOrderDetailsById(@PathVariable int id) {
        OrderDetails orderDetails = orderDetailsService.findById(id);
        if (orderDetails == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    // Gọi món
    @PostMapping("/call-order")
    public ResponseEntity<String> callOrder(@RequestBody CallOrderRequestDto callOrderRequestDto) {
        Table table = tableService.findById(callOrderRequestDto.getTableId());
        if (table == null) {
            return new ResponseEntity<>("Table không tồn tại", HttpStatus.BAD_REQUEST);
        }

        Users user = userService.findById(callOrderRequestDto.getUserId());
        if (user == null) {
            return new ResponseEntity<>("Người dùng không tồn tại", HttpStatus.BAD_REQUEST);
        }

        CallOrderRequest callOrderRequest = new CallOrderRequest();
        callOrderRequest.setTable(table);
        callOrderRequest.setUser(user);

        List<OrderDetails> orderDetailsList = new ArrayList<>();
        for (OrderDetailsDto dto : callOrderRequestDto.getOrderDetails()) {
            OrderDetails orderDetails = new OrderDetails();
            Product product = productService.findProductById(dto.getProductId());
            if (product == null) {
                return new ResponseEntity<>("Product không tồn tại", HttpStatus.BAD_REQUEST);
            }

            orderDetails.setProduct(product);
            orderDetails.setQuantity(dto.getQuantity());
            orderDetails.setTotalMoneyOrder(dto.getTotalMoneyOrder());
            orderDetails.setShippingDay(dto.getShippingDay());
            orderDetails.setStatus(dto.getStatus());
            orderDetails.setCallServiceTime(dto.getCallServiceTime());
            orderDetails.setCallOrderRequest(callOrderRequest);
            orderDetailsList.add(orderDetails);
        }

        callOrderRequest.setOrderDetailsList(orderDetailsList);
        callOrderRequestService.save(callOrderRequest);

        // Tạo và lưu thông báo
        Notification notification = new Notification();
        notification.setMessage("Bàn " + table.getCode() + " gọi món.");
        notification.setCreatedAt(LocalDateTime.now());
        notificationRepository.save(notification);

        return ResponseEntity.ok("Thông báo gọi món đã được lưu.");
    }


    // Gọi phục vụ
    @PostMapping("/call-service")
    public ResponseEntity<String> callService(@RequestBody CallServiceRequestDto callServiceRequest) {
        Table table = tableService.findById(callServiceRequest.getTableId());
        if (table == null) {
            return new ResponseEntity<>("Table không tồn tại", HttpStatus.BAD_REQUEST);
        }

        // Tạo và lưu thông báo
        Notification notification = new Notification();
        notification.setMessage("Bàn " + table.getCode() + " yêu cầu phục vụ.");
        notification.setCreatedAt(LocalDateTime.now());
        notificationRepository.save(notification);

        return ResponseEntity.ok("Thông báo gọi phục vụ đã được lưu.");
    }


    @PostMapping("/hello")
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok("Hello World");
    }

    // Lấy danh sách sản phẩm theo categoryId
    @GetMapping("/products/category/{categoryId}")
    public ResponseEntity<Page<Product>> getProductsByCategoryId(
            @PathVariable int categoryId,
            Pageable pageable) {
        Page<Product> products = service.findProductByCategory(categoryId, pageable);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}   