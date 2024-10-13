package codegym.vn.iposproduct.repository;

import codegym.vn.iposproduct.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
}
