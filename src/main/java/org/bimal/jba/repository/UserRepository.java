package org.bimal.jba.repository;

import org.bimal.jba.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByName(String name);

}
