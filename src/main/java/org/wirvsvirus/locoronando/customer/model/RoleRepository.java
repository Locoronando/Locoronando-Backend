package org.wirvsvirus.locoronando.customer.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wirvsvirus.locoronando.customer.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}