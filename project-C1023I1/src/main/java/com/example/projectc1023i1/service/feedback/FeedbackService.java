package com.example.projectc1023i1.service.feedback;

import com.example.projectc1023i1.model.feedback.Feedback;
import com.example.projectc1023i1.repository.feedback.IFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class FeedbackService implements IFeedbackService {

    @Autowired
    private IFeedbackRepository feedbackRepository;
    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback getFeedbackById(Integer feedbackId) {
        return feedbackRepository.findById(feedbackId).orElse(null);
    }

    @Override
    public List<Feedback> getFeedbackByDayCreate(Date dayCreate) {
        return feedbackRepository.findByDayCreate(dayCreate);
    }


}
