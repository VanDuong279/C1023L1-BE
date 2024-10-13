package codegym.vn.iposproduct.repository;

import codegym.vn.iposproduct.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
