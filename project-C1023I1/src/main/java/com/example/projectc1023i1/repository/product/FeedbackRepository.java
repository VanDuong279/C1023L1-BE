package com.example.projectc1023i1.repository.product;

import com.example.projectc1023i1.model.product.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    @Query("SELECT f FROM Feedback f WHERE DATE(f.dayCreate) = :date")
    Page<Feedback> findByCreationDate(@Param("date") Date date, Pageable pageable);

}
