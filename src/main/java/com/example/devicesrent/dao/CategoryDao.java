package com.example.devicesrent.dao;

import com.example.devicesrent.data.Category;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class CategoryDao {
    @PersistenceContext
    private final EntityManager entityManager;

    public CategoryDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void save(Category category) {
        entityManager.persist(category);
    }

    public Optional<Category> readById(Long id) {
        return Optional.ofNullable(entityManager.find(Category.class, id));
    }

    @Transactional
    public void update(Category category) {
        entityManager.merge(category);
    }

    @Transactional
    public void delete(Category category) {
        readById(category.getId()).ifPresent(entityManager::remove);
    }
}
