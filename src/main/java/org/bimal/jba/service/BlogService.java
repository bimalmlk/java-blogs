package org.bimal.jba.service;

import org.bimal.jba.entities.Blog;
import org.bimal.jba.entities.User;
import org.bimal.jba.repository.BlogRepository;
import org.bimal.jba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
	
	
	
	
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private UserRepository userRepository;
	public void save(Blog blog, String name) {
		User user = userRepository.findByName(name);
		blog.setUser(user);
		blogRepository.save(blog);
		
	}
	public void delete(int id) {
		blogRepository.delete(id);
		
	}

}
