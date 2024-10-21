package com.example.projectc1023i1.controller.bill;

import com.example.projectc1023i1.Dto.bill.OrderDTO;
import com.example.projectc1023i1.service.bill.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Lấy danh sách tất cả các hóa đơn
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    // Tìm hóa đơn theo ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Integer id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    // Tìm hóa đơn theo khoảng ngày
    @GetMapping("/search-by-date")
    public ResponseEntity<List<OrderDTO>> getOrdersByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate date) {
        return ResponseEntity.ok(orderService.getOrdersByDate(date));
    }
}
