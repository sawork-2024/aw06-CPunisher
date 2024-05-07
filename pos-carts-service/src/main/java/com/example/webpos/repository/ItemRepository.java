package com.example.webpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webpos.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
