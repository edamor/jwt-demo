package org.edamor.springjwt.repositories;

import org.edamor.springjwt.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
}
