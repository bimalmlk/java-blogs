package org.bimal.jba.repository;

import java.util.List;

import org.bimal.jba.entities.Blog;
import org.bimal.jba.entities.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer>{
	List<Item> findByBlog(Blog blog, Pageable pageable);
}
