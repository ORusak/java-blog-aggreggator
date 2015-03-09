package ru.rusak.jba.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ru.rusak.jba.entity.Blog;
import ru.rusak.jba.entity.Item;
import ru.rusak.jba.entity.Role;
import ru.rusak.jba.entity.User;
import ru.rusak.jba.repository.BlogRepository;
import ru.rusak.jba.repository.ItemRepository;
import ru.rusak.jba.repository.RoleRepository;
import ru.rusak.jba.repository.UserRepository;

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
	public void init(){
		Role roleUser	=	new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);
		
		Role roleAdmin	=	new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);
		
		User userAdmin	=	new User();
		userAdmin.setName("admin");
		
		BCryptPasswordEncoder encoder	=	new BCryptPasswordEncoder();
		userAdmin.setPassword(encoder.encode("admin"));
		
		userAdmin.setEnabled(true);
		
		List<Role> roles	=	new ArrayList<Role>();
		roles.add(roleAdmin);
		roles.add(roleUser);
		
		userAdmin.setRoles(roles);
		userRepository.save(userAdmin);
		
		Blog blogJavavids	=	new Blog();
		blogJavavids.setName("JavaVids");
		blogJavavids.setUrl("http://feeds.feedburner.com/javavids?format=xml");
		blogJavavids.setUser(userAdmin);
		blogRepository.save(blogJavavids);
		
		Item item1			=	new Item();
		item1.setBlog(blogJavavids);
		item1.setTitle("first");
		item1.setLink("http://javavids.com");
		item1.setPublishedDate(new Date());
		
		Item item2			=	new Item();
		item2.setBlog(blogJavavids);
		item2.setTitle("second");
		item2.setLink("http://javavids.com");
		item2.setPublishedDate(new Date());
		
		itemRepository.save(item1);
		itemRepository.save(item2);
		
	}
}
