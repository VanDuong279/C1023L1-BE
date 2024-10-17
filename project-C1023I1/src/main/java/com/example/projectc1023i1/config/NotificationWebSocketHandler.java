package com.example.projectc1023i1.config;

import com.example.projectc1023i1.model.product.OrderDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationWebSocketHandler extends TextWebSocketHandler {
    private static List<WebSocketSession> sessions = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        System.out.println("Client kết nối: " + session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        System.out.println("Client ngắt kết nối: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Giả định rằng message chứa thông tin về đơn hàng
        System.out.println("Nhận được thông điệp: " + message.getPayload());

        // Phản hồi lại cho client (nếu cần)
        session.sendMessage(new TextMessage("Bạn đã gửi yêu cầu thành công: " + message.getPayload()));
    }

    public void sendNotification(String message) throws IOException {
        // Gửi thông báo đến tất cả các client đang kết nối
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(message));
            }
        }
    }

    public void sendOrderNotification(OrderDetails orderDetails) throws IOException {
        String message = String.format("Có một đơn gọi món mới từ bàn %s: %s (Trạng thái: %s, Thời gian gọi: %s)",
                orderDetails.getTable().getId(),
                orderDetails.getQuantity(),
                orderDetails.getStatus(),
                orderDetails.getCallOrderTime());
        sendNotification(message);
    }

    public void sendServiceNotification(OrderDetails orderDetails) throws IOException {
        String message = String.format("Có một yêu cầu gọi phục vụ từ bàn %s: Thời gian gọi phục vụ: %s",
                orderDetails.getTable().getId(),
                orderDetails.getCallServiceTime());
        sendNotification(message);
    }
}
