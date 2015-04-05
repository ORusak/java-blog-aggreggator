package ru.rusak.jba.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ru.rusak.jba.entity.Blog;
import ru.rusak.jba.entity.Item;
import ru.rusak.jba.entity.User;
import ru.rusak.jba.repository.BlogRepository;
import ru.rusak.jba.repository.ItemRepository;
import ru.rusak.jba.repository.UserRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	
	public List<User> findAll(){
		return userRepository.findAll();
	}

	public User findOne(int id) {
		return userRepository.findOne(id);
	}

	public User findOneWithBlogs(int id) {
		User user			=	findOne(id);
		List<Blog>	blogs	=	blogRepository.findByUser(user);
		for (Blog blog : blogs){
			List<Item> items	=	itemRepository.findByBlog(blog, new PageRequest(0, 10, Direction.DESC, "publishedDate"));
			blog.setItems(items);
		}
		user.setBlogs(blogs);
		return user;
	}

	public void save(User user) {
		BCryptPasswordEncoder encoder	=	new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		
		userRepository.save(user);
	}

	public User findOneWithBlogs(String name) {
		User user	=	userRepository.findByName(name);
		
		return findOneWithBlogs(user.getId());
	}

	public void delete(int id) {
		userRepository.delete(id);
	}
}
