package org.bimal.jba.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.bimal.jba.entities.Blog;
import org.bimal.jba.entities.Item;
import org.bimal.jba.entities.Role;
import org.bimal.jba.entities.User;
import org.bimal.jba.repository.BlogRepository;
import org.bimal.jba.repository.ItemRepository;
import org.bimal.jba.repository.RoleRepository;
import org.bimal.jba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public List<User> findAll(){
		return userRepository.findAll();
	}


	public User findOne(int id) {
		return userRepository.findOne(id);
	}

	@Transactional
	public User findOneWithBlogs(int id) {
		User user = findOne(id);
		List<Blog> blogs = blogRepository.findByUser(user);
			for (Blog blog:blogs) {
				List<Item> items = itemRepository.findByBlog(blog, new PageRequest(0, 10, Direction.DESC
						, "publishedDate"));
				blog.setItems(items);
			}
			user.setBlogs(blogs);
		return user;
	}


	public void save(User user) {
		user.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByName("ROLE_USER"));
		
		
		user.setRoles(roles);
		userRepository.save(user);
	}


	public User findOneWithBlogs(String name) {
		// TODO Auto-generated method stub
		User user = userRepository.findByName(name);
		return findOneWithBlogs(user.getId());
	}
}
