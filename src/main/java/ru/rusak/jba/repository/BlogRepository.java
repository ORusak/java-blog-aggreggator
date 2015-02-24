package ru.rusak.jba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.rusak.jba.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer>{

}
