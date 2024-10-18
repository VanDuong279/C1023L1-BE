package com.example.projectc1023i1.service.product.impl;

import com.example.projectc1023i1.model.product.Feedback;
import com.example.projectc1023i1.repository.product.FeedbackRepository;
import com.example.projectc1023i1.service.product.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FeedbackService implements IFeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public Page<Feedback> getAllFeedback(Pageable pageable) {
        return feedbackRepository.findAll(pageable);
    }

    @Override
    public Feedback getFeedbackById(Integer feedbackId) {
        return feedbackRepository.findById(feedbackId).orElse(null);
    }

    @Override
    public Page<Feedback> getFeedbackByDayCreate(Date date, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return feedbackRepository.findByCreationDate(date, pageable);
    }

    @Override
    public void save(Feedback feedback) {
        feedbackRepository.save(feedback);
    }

}
