package ru.rusak.jba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.rusak.jba.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
