package com.example.projectc1023i1.repository.feedback;
import com.example.projectc1023i1.model.feedback.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface IFeedbackRepository extends JpaRepository<Feedback, Integer>  {
    List<Feedback> findByDayCreate(Date dayCreate);

}
