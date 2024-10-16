package com.example.projectc1023i1.repository.product;

import com.example.projectc1023i1.model.product.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
}
