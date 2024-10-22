package com.example.projectc1023i1.model.product;

import com.example.projectc1023i1.model.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@jakarta.persistence.Table(name = "order_details")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer order_details_Id;

    @Column(nullable = false)
    private LocalDateTime dayCreate;

    @Column(nullable = false)
    private LocalDateTime dayUpdate;

    private int quantity;

    @Column(nullable = false)
    private LocalDateTime shippingDay;

    @Column(nullable = false)
    private double totalMoneyOrder;

    @Column(nullable = false)
    private String status; // Trạng thái đơn hàng

    @ManyToOne
    @JoinColumn(name = "call_order_request_id")
    private CallOrderRequest callOrderRequest;
    @Column(nullable = false)
    private LocalDateTime callOrderTime;
    @Column(nullable = false)
    private LocalDateTime callServiceTime; // Thời gian gọi phục vụ

    @ManyToOne
    @JoinColumn(name = "table_id") // Chỉ rõ khóa ngoại
    private Table table;

    public LocalDateTime getCallOrderTime() {
        return callOrderTime;
    }

    public void setCallOrderTime(LocalDateTime callOrderTime) {
        this.callOrderTime = callOrderTime;
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private Users user;

    // Thêm mối quan hệ tới Product
    @ManyToOne
    @JoinColumn(name = "product_id") // Chỉ rõ khóa ngoại của Product
    private Product product; // Liên kết tới bảng Product

    public OrderDetails() {
    }

    // Getters và Setters cho tất cả các thuộc tính

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getOrder_details_Id() {
        return order_details_Id;
    }

    public void setOrder_details_Id(Integer order_details_Id) {
        this.order_details_Id = order_details_Id;
    }

    public LocalDateTime getDayCreate() {
        return dayCreate;
    }

    public void setDayCreate(LocalDateTime dayCreate) {
        this.dayCreate = dayCreate;
    }

    public LocalDateTime getDayUpdate() {
        return dayUpdate;
    }

    public void setDayUpdate(LocalDateTime dayUpdate) {
        this.dayUpdate = dayUpdate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getShippingDay() {
        return shippingDay;
    }

    public void setShippingDay(LocalDateTime shippingDay) {
        this.shippingDay = shippingDay;
    }

    public double getTotalMoneyOrder() {
        return totalMoneyOrder;
    }

    public void setTotalMoneyOrder(double totalMoneyOrder) {
        this.totalMoneyOrder = totalMoneyOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCallServiceTime() {
        return callServiceTime;
    }

    public void setCallServiceTime(LocalDateTime callServiceTime) {
        this.callServiceTime = callServiceTime;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public CallOrderRequest getCallOrderRequest() {
        return callOrderRequest;
    }

    public void setCallOrderRequest(CallOrderRequest callOrderRequest) {
        this.callOrderRequest = callOrderRequest;
    }
    // Các getter và setter khác...
}
