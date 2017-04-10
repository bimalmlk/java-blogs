package org.bimal.jba.repository;

import java.util.List;

import org.bimal.jba.entities.Blog;
import org.bimal.jba.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Integer>{
	List<Blog> findByUser(User user);

}
