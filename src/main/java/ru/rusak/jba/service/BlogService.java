package ru.rusak.jba.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import ru.rusak.jba.entity.Blog;
import ru.rusak.jba.entity.Item;
import ru.rusak.jba.entity.User;
import ru.rusak.jba.exception.RssException;
import ru.rusak.jba.repository.BlogRepository;
import ru.rusak.jba.repository.ItemRepository;
import ru.rusak.jba.repository.UserRepository;

@Service
@Transactional
public class BlogService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private RssService rssService;
	
	@Autowired
	ItemRepository itemRepository;
	
	public void saveItems(Blog blog) {
		try {
			List<Item> items = rssService.getItems(blog.getUrl());
			for (Item item : items) {
				Item saveItem	=	itemRepository.findByBlogAndLink(blog, item.getLink());
				if (saveItem == null){
					item.setBlog(blog);
					itemRepository.save(item);
				}
			}
		} catch (RssException e) {
			e.printStackTrace();
		}
	}
	
	// 1 hour = 60 seconds * 60 minutes * 1000
	@Scheduled(fixedDelay=3600000)
	public void reloadBlog() {
		List<Blog> blogs	=	blogRepository.findAll();
		for (Blog blog : blogs) {
			saveItems(blog);
		}
	}
	
	public void save(Blog blog, String name) {
		User user	=	userRepository.findByName(name);
		blog.setUser(user);
		blogRepository.save(blog);
		
		saveItems(blog);
	}

	@PreAuthorize("#blog.user.name == authentication.name OR hasRole('ROLE_ADMIN')")
	public void delete(@P("blog") Blog blog) {
		blogRepository.delete(blog);
	}

	public Blog findOne(int id) {
		return blogRepository.findOne(id);
	}
}
