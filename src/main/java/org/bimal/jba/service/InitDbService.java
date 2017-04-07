package org.bimal.jba.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Transactional
@Service
public class InitDbService {
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BlogRepository blogRepository;
	@Autowired
	private ItemRepository itemRepository;
	
	@PostConstruct
	public void initDb(){
		Role roleUser = new Role(); 
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);
				Role roleAdmin = new Role(); 
				roleAdmin.setName("ROLE_ADMIN");
				roleRepository.save(roleAdmin);

				User userAdmin = new User();
				userAdmin.setEnabled(true);
				userAdmin.setName("admin");
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				userAdmin.setPassword(encoder.encode("admin"));
				List<Role> roles = new ArrayList<Role>();
				roles.add(roleAdmin);
				roles.add(roleUser);
				
				userAdmin.setRoles(roles);
				userRepository.save(userAdmin);
				
				Blog javaVidsBlog = new Blog();
				javaVidsBlog.setName("JavaVids");
				javaVidsBlog.setUrl("http://feeds.feedburner.com/javavids?format=xml");
				javaVidsBlog.setUser(userAdmin);
				blogRepository.save(javaVidsBlog);
						Item item1 = new Item();
						item1.setBlog(javaVidsBlog);
						item1.setTitle("first");
						item1.setPublishedDate(new Date());
						item1.setLink("https://www.javavids.com");
				itemRepository.save(item1);
						Item item2 = new Item();
						item2.setBlog(javaVidsBlog);
						item2.setTitle("Second");
						item2.setPublishedDate(new Date());
						item2.setLink("https://www.javavids.com");	
						itemRepository.save(item2);
				
	}
}
