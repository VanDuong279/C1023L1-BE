package com.example.projectc1023i1.controller.product;

import com.example.projectc1023i1.Dto.product.FeedbackDto;
import com.example.projectc1023i1.model.product.Feedback;
import com.example.projectc1023i1.service.product.IFeedbackService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/feedbacks")

public class FeedbackController {
    @Autowired
    private IFeedbackService feedbackService;


    @GetMapping
    public ResponseEntity<Page<Feedback>> getAllFeedback(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Feedback> feedbackPage = feedbackService.getAllFeedback(pageable);
        return ResponseEntity.ok(feedbackPage);
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
    public ResponseEntity<?> searchFeedbackByDayCreate(
            @PathVariable("date") String dateStr,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateStr);
            Page<Feedback> feedbackPage = feedbackService.getFeedbackByDayCreate(date, page, size);
            if (feedbackPage.getTotalElements() == 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Không tìm thấy phản hồi cho ngày " + dateStr);
            }

            if (feedbackPage.isEmpty()) {
                return ResponseEntity.ok(Collections.emptyList());
            }

            return ResponseEntity.ok(feedbackPage.getContent());
        } catch (ParseException e) {
            return ResponseEntity.badRequest().body("Định dạng ngày không hợp lệ.");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> addFeedBack(@Valid @RequestBody FeedbackDto feedbackDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        Feedback feedback = new Feedback();
        BeanUtils.copyProperties(feedbackDto, feedback);
        feedbackService.save(feedback);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

