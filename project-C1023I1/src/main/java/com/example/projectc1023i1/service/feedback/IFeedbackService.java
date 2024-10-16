package com.example.projectc1023i1.service.feedback;

import com.example.projectc1023i1.model.feedback.Feedback;

import java.util.Date;
import java.util.List;

public interface IFeedbackService {

    public List<Feedback> getAllFeedback();


    public Feedback getFeedbackById(Integer feedbackId);

    public List<Feedback> getFeedbackByDayCreate(Date dayCreate);
}
