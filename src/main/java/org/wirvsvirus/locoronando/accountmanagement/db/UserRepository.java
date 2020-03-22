package org.wirvsvirus.locoronando.accountmanagement.db;

import org.springframework.data.repository.CrudRepository;
import org.wirvsvirus.locoronando.accountmanagement.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

  User findByUsername(String username);
  User findByEmail(String email);
}
