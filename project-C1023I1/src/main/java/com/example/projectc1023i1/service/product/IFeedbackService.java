package com.example.projectc1023i1.service.product;

import com.example.projectc1023i1.model.product.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface IFeedbackService {
    Page<Feedback> getAllFeedback(Pageable pageable);


    public Feedback getFeedbackById(Integer feedbackId);

    public Page<Feedback> getFeedbackByDayCreate(Date date, int page, int size);
    void save(Feedback feedback);
}
