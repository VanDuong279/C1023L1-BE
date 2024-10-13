package codegym.vn.iposproduct.repository;

import codegym.vn.iposproduct.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
