package com.example.projectc1023i1.controller.product;

import com.example.projectc1023i1.Dto.product.bill.OrderDTO;
import com.example.projectc1023i1.Dto.product.income.IncomeDTO;
import com.example.projectc1023i1.model.product.CallOrderRequest;
import com.example.projectc1023i1.model.product.CallServiceRequest;
import com.example.projectc1023i1.config.NotificationWebSocketHandler;
import com.example.projectc1023i1.model.product.OrderDetails;
import com.example.projectc1023i1.model.product.Table;
import com.example.projectc1023i1.service.product.OrderDetailsService;
import com.example.projectc1023i1.service.product.TableService;
import com.example.projectc1023i1.service.product.impl.IncomeService;
import com.example.projectc1023i1.service.product.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
//@CrossOrigin(origins = "*")
public class OrderDetailsController {

    @Autowired
    private OrderDetailsService orderDetailsService;

    @Autowired
    private TableService tableService; // Sử dụng service để lấy Table từ DB

    @Autowired
    private NotificationWebSocketHandler notificationWebSocketHandler;
    @Autowired
    private OrderService orderService;
    @Autowired
    private IncomeService incomeService;

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
        if (ordersByTable.isEmpty()) {
            return new ResponseEntity<>(ordersByTable, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ordersByTable, HttpStatus.OK);
    }

    // Thêm một đơn đặt hàng
    @PostMapping("")
    public ResponseEntity<OrderDetails> createOrderDetails(@RequestBody OrderDetails orderDetails) {
        orderDetailsService.save(orderDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Xóa một đơn đặt hàng theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderDetails(@PathVariable int id) {
        OrderDetails orderDetails = orderDetailsService.findById(id);
        if (orderDetails == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderDetailsService.delete(orderDetails);
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
    public ResponseEntity<String> callOrder(@RequestBody CallOrderRequest callOrderRequest) {
        Table table = callOrderRequest.getTable(); // Lấy đối tượng Table trực tiếp từ request
        if (table == null) {
            return new ResponseEntity<>("Table không tồn tại", HttpStatus.BAD_REQUEST);
        }

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setTable(table); // Liên kết Order với Table
        orderDetails.setQuantity(1); // Hoặc lấy từ thông tin đơn hàng
        orderDetails.setDayCreate(LocalDateTime.now()); // Thời gian tạo đơn
        orderDetails.setTotalMoneyOrder(0); // Bạn cần xác định rõ giá trị này

        // Lưu đơn hàng mới
        orderDetailsService.save(orderDetails);

        try {
            notificationWebSocketHandler.sendNotification("Bàn " + table.getCode() + " gọi món: " + callOrderRequest.getOrderDetails());
            return ResponseEntity.ok("Thông báo gọi món đã được gửi.");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Lỗi khi gửi thông báo.");
        }
    }

    // Gọi phục vụ
    @PostMapping("/call-service")
    public ResponseEntity<String> callService(@RequestBody CallServiceRequest callServiceRequest) {
        Table table = callServiceRequest.getTable(); // Lấy đối tượng Table trực tiếp từ request
        if (table == null) {
            return new ResponseEntity<>("Table không tồn tại", HttpStatus.BAD_REQUEST);
        }

        try {
            notificationWebSocketHandler.sendNotification("Bàn " + table.getCode() + " yêu cầu phục vụ: " + callServiceRequest.getServiceRequest());
            return ResponseEntity.ok("Thông báo gọi phục vụ đã được gửi.");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Lỗi khi gửi thông báo.");
        }
    }





    // Tìm hóa đơn theo khoảng ngày
    @GetMapping("/search-by-date")
    public ResponseEntity<List<OrderDTO>> getOrdersByDate(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS") LocalDateTime date) {

        List<OrderDTO> orders = orderService.getOrdersByDate(date);

        if (orders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(orders);
    }



    @GetMapping("/today")
    public ResponseEntity<IncomeDTO> getTodayIncome() {
        IncomeDTO income = incomeService.getTodayIncome();

        if (income == null ) {
            return ResponseEntity.noContent().build(); // Trả về 204 No Content nếu không có thu nhập hôm nay
        }

        return ResponseEntity.ok(income); // Trả về 200 OK nếu có dữ liệu
    }

    @GetMapping("/week")
    public ResponseEntity<IncomeDTO> getThisWeekIncome() {
        IncomeDTO income = incomeService.getThisWeekIncome();

        if (income == null ) {
            return ResponseEntity.noContent().build(); // Trả về 204 No Content nếu không có thu nhập tuần này
        }

        return ResponseEntity.ok(income); // Trả về 200 OK nếu có dữ liệu
    }

    @GetMapping("/month")
    public ResponseEntity<IncomeDTO> getThisMonthIncome() {
        IncomeDTO income = incomeService.getThisMonthIncome();

        if (income == null ) {
            return ResponseEntity.noContent().build(); // Trả về 204 No Content nếu không có thu nhập tháng này
        }

        return ResponseEntity.ok(income); // Trả về 200 OK nếu có dữ liệu
    }

    @GetMapping("/year")
    public ResponseEntity<IncomeDTO> getThisYearIncome() {
        IncomeDTO income = incomeService.getThisYearIncome();

        if (income == null ) {
            return ResponseEntity.noContent().build(); // Trả về 204 No Content nếu không có thu nhập năm này
        }

        return ResponseEntity.ok(income); // Trả về 200 OK nếu có dữ liệu
    }

    @GetMapping("/custom")
    public ResponseEntity<IncomeDTO> getIncomeByDateRange(
            @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS") LocalDateTime from,
            @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS") LocalDateTime to) {

        IncomeDTO income = incomeService.getIncomeByDateRange(from, to);

        if (income == null ) {
            return ResponseEntity.noContent().build(); // Trả về 204 No Content nếu không có thu nhập trong khoảng ngày
        }

        return ResponseEntity.ok(income); // Trả về 200 OK nếu có dữ liệu
    }

    @PostMapping("/hello")
    public ResponseEntity<?> hello () {
        return ResponseEntity.ok("Hello World");
    }
}

