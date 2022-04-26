package com.example.devicesrent.data.category;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final Scanner scanner;

    public CategoryService(CategoryRepository categoryRepository, Scanner scanner) {
        this.categoryRepository = categoryRepository;
        this.scanner = scanner;
    }
    @Transactional
    public void addNewCategory() {
        System.out.print("Nazwa kategorii: ");
        String name = scanner.nextLine();
        System.out.print("Opis kategorii: ");
        String description = scanner.nextLine();
        Category category = new Category(name, description);

        if (!categoryRepository.existsCategoryByName(name)) {
            categoryRepository.save(category);
            System.out.println("Dodano kategorię: " + category);
        } else {
            System.out.println("Kategoria " + name + " już istnieje!");
        }
    }

    @Transactional
    public void deleteCategory() {
        try {
            Category categoryToRemove = choseCategory();
            System.out.println("Usunięto kategorię " + categoryToRemove.getName());
            categoryRepository.deleteById(categoryToRemove.getId());
        } catch (CategoryException e) {
            System.out.println(e.getMessage());
        }
    }

    public void findCategory() {
        System.out.print("Podaj nazwę poszukiwanej kategorii: ");
        String name = scanner.nextLine();
        categoryRepository.findCategoryByNameIgnoreCase(name)
                .ifPresentOrElse(c -> System.out.println("Kategoria " + c.getName() + " istnieje i ma ID-" + c.getId()),
                        ()-> System.out.println("Kategoria " + name + " nie istnieje."));
    }

    private Category choseCategory() throws CategoryException {
        if (categoryRepository.count() > 0) {
            Optional<Category> category = Optional.empty();
            while (category.isEmpty()) {
                System.out.println("Podaj numer kategorii do usunięcia: ");
                printCategoryList();
                try {
                    category = categoryRepository.findById(scanner.nextLong());
                } catch (InputMismatchException e) {
                    category = Optional.empty();
                    scanner.nextLine();
                }
            }
            return category.get();
        } else {
            throw new CategoryException ("Nie zapisano żadnej kategorii!");
        }

    }

    private void printCategoryList() {
        for (Category category : categoryRepository.findAll()) {
            System.out.println(category.getId() + " - " + category);
        }
    }
}
