package com.example.projectc1023i1.controller.feedback;

import com.example.projectc1023i1.model.feedback.Feedback;
import com.example.projectc1023i1.service.feedback.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")

public class FeedbackControllers {
    @Autowired
    private IFeedbackService feedbackService;

    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedback() {
        List<Feedback> feedbackList = feedbackService.getAllFeedback();
        return ResponseEntity.ok(feedbackList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable("id") Integer feedbackId) {
        Feedback feedback = feedbackService.getFeedbackById(feedbackId);
        if (feedback != null) {
            return ResponseEntity.ok(feedback);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/search/day-create/{date}")
    public ResponseEntity<List<Feedback>> searchFeedbackByDayCreate(
            @PathVariable("date") String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateStr);
            List<Feedback> feedbacks = feedbackService.getFeedbackByDayCreate(date);
            if (feedbacks.isEmpty()) {
                return ResponseEntity.ok(feedbacks);
            }
            return ResponseEntity.ok(feedbacks);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
