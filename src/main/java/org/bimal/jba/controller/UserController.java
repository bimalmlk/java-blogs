package org.bimal.jba.controller;

import java.security.Principal;

import org.bimal.jba.entities.Blog;
import org.bimal.jba.entities.User;
import org.bimal.jba.service.BlogService;
import org.bimal.jba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

	@Autowired
	private BlogService blogService;
	
	@Autowired
	private UserService userService;
	
	
	
	
	@ModelAttribute("user")
	public User constructUser()
	{
		return new User();
	}
	
	@ModelAttribute("blog")
	public Blog constructBlog()
	{
		return new Blog();
	}
	@RequestMapping("/users")
	public String users(Model model){
		model.addAttribute("users", userService.findAll());
		return "users";
	}
	@RequestMapping("/users/{id}")
	public String details(Model model, @PathVariable int id){
		model .addAttribute("user", userService.findOneWithBlogs(id));
		return "user-details";
	}
	@RequestMapping("/register")
	public String showRegister()
	{
		return "user-register";
	}
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String doRegister(@ModelAttribute("user") User user)
	{
		userService.save(user);
		return "redirect:/register.html?success=true";
	}
	@RequestMapping("/account")
	public String account(Model model, Principal principal)
	{
		String name = principal.getName();
		model.addAttribute("user", userService.findOneWithBlogs(name));
		return "user-details";
	}
	@RequestMapping(value="/account", method=RequestMethod.POST)
	public String doAddBlogs(@ModelAttribute("blog") Blog blog, Principal principal)
	{
		String name = principal.getName();
		blogService.save(blog, name);
		return "redirect:/account.html";
	}
	@RequestMapping("/blog/remove/{id}")
	public String removeBlog(@PathVariable int id){
		blogService.delete(id);
		return "redirect:/account.html";
	}
	
}
