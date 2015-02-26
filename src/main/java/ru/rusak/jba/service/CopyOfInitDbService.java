package ru.rusak.jba.service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.rusak.jba.entity.Role;

import ru.rusak.jba.repository.RoleRepository;

@Transactional
@Service
public class CopyOfInitDbService {
	@Autowired
	private RoleRepository roleRepository;
	
	@PostConstruct
	public void init(){
		Role roleBlogger	=	new Role();
		roleBlogger.setName("ROLE_BLOGGER");
		roleRepository.save(roleBlogger);
	}
}
