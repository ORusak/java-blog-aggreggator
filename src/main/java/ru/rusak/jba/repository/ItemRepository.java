package ru.rusak.jba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.rusak.jba.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

}
