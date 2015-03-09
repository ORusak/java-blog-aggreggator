package ru.rusak.jba.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.rusak.jba.entity.Blog;
import ru.rusak.jba.entity.User;
import ru.rusak.jba.repository.BlogRepository;
import ru.rusak.jba.repository.UserRepository;

@Service
@Transactional
public class BlogService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	public void save(Blog blog, String name) {
		User user	=	userRepository.findByName(name);
		blog.setUser(user);
		blogRepository.save(blog);
	}
}
