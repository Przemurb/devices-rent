package com.example.devicesrent.data.category;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    boolean existsCategoryByName(String name);
    Optional<Category> findCategoryByNameIgnoreCase(String name);
}
