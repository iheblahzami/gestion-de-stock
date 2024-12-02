package org.example.gestion_de_stock.services;

import org.example.gestion_de_stock.entities.Category;
import org.example.gestion_de_stock.entities.Product;
import org.example.gestion_de_stock.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {
   @Autowired
    private CategoryRepository categoryRepository;



    // Create a new category
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Get a category by ID
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    // Get all categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Update an existing category
    public Category updateCategory(Long id, Category updatedCategory) {
        Category category = getCategoryById(id);
        category.setName(updatedCategory.getName());
        category.setDescription(updatedCategory.getDescription());
        return categoryRepository.save(category);
    }

    // Delete a category
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    //Get Products by Category
    public List<Product> getProductsByCategory (long categoryId) {
        Category category = getCategoryById(categoryId);
        return category.getProducts();
    }
    //Find Categories by Name
    public List<Category> findCategoriesByName (String categoryName) {
        return categoryRepository.findByNameContainingIgnoreCase(categoryName);
    }
//Count Products in a Category
    public int countProductsInCategory (long categoryId) {
        Category category = getCategoryById(categoryId);
        return category.getProducts().size();
    }
}
