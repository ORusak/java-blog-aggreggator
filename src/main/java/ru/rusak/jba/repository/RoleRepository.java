package ru.rusak.jba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.rusak.jba.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

}
