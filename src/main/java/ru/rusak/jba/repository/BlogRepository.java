package ru.rusak.jba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.rusak.jba.entity.Blog;
import ru.rusak.jba.entity.User;

public interface BlogRepository extends JpaRepository<Blog, Integer>{
	
	List<Blog> findByUser(User user);
}
