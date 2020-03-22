package org.wirvsvirus.locoronando.category;

import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/category")
public final class CategoryController {
  private CategoryRepository categoryRepository;

  @Autowired
  public CategoryController(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @PostMapping
  public void addCategory(@RequestBody Category category) {
    categoryRepository.save(category);
  }

  @DeleteMapping(path = "deleteAll")
  public void deleteCategories(@PathParam("dealerId") int dealerId) {
    categoryRepository.deleteCategoriesByDealerId(dealerId);
  }

  @DeleteMapping(path = "delete")
  public void deleteCategory(
    @PathParam("categoryId") int categoryId,
    @PathParam("dealerId") int dealerId) {
    categoryRepository.deleteCategoryByIdAndDealerId(categoryId, dealerId);
  }

  @GetMapping(path = "find")
  public Category findCategory(
    @PathParam("categoryId") int categoryId,
    @PathParam("dealerId") int dealerId) {
    return categoryRepository.findCategoryByIdAndDealerId(categoryId, dealerId);
  }

  @GetMapping(path = "findAllByDealer")
  public Iterable<Category> findAll(@PathParam("dealerId") int dealerId) {
    return categoryRepository.findCategoriesByDealerId(dealerId);
  }

  @GetMapping(path = "findAll")
  public Iterable<Category> findAll() {
    return categoryRepository.findAll();
  }
}