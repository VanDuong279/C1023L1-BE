package codegym.vn.iposproduct.repository;

import codegym.vn.iposproduct.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
}
