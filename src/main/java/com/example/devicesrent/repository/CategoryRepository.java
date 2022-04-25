package com.example.devicesrent.repository;

import com.example.devicesrent.data.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    boolean existsCategoryByName(String name);
}
