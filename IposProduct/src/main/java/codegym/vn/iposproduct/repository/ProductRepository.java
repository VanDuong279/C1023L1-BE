package codegym.vn.iposproduct.repository;

import codegym.vn.iposproduct.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
