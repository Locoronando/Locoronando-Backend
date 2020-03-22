package org.wirvsvirus.locoronando.category;

import java.util.ArrayList;
import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wirvsvirus.locoronando.product.ProductRepository;

@RestController
@RequestMapping("api/v1/category")
public final class CategoryController {
  private CategoryRepository categoryRepository;
  private ProductRepository productRepository;

  @Autowired
  public CategoryController(
    CategoryRepository categoryRepository,
    ProductRepository productRepository
  ) {
    this.categoryRepository = categoryRepository;
    this.productRepository = productRepository;
  }

  @PostMapping
  public void addCategory(@RequestBody Category category) {
    categoryRepository.save(category);
  }

  @DeleteMapping(path = "deleteAll")
  public void deleteCategories(@PathParam("dealerId") int dealerId) {
    categoryRepository.findCategoriesByDealerId(dealerId)
      .forEach(categoryRepository::delete);
  }

  @DeleteMapping(path = "delete")
  public void deleteCategory(
    @PathParam("categoryId") int categoryId,
    @PathParam("dealerId") int dealerId) {
    Category category = categoryRepository.findCategoryByIdAndDealerId(categoryId, dealerId);
    if (category != null) {
      categoryRepository.delete(category);
    }
  }

  @GetMapping(path = "find")
  public CategoryFindResponse findCategory(
    @PathParam("categoryId") int categoryId,
    @PathParam("dealerId") int dealerId) {
    CategoryFindResponse findResponse = new CategoryFindResponse();
    Category category = categoryRepository.findCategoryByIdAndDealerId(categoryId, dealerId);
    if (category != null) {
      findResponse.setId(categoryId);
      findResponse.setName(category.getName());
      findResponse.setDescription(category.getDescription());
      productRepository.findProductByCategoryIdAndAndDealerId(categoryId, dealerId)
        .forEach(findResponse.getProducts()::add);
    }
    return findResponse;
  }

  @GetMapping(path = "findAllByDealer")
  public List<CategoryFindResponse> findAll(@PathParam("dealerId") int dealerId) {
    List<CategoryFindResponse> categories = new ArrayList<>();
    for (Category category : categoryRepository.findCategoriesByDealerId(dealerId)) {
      CategoryFindResponse findResponse = new CategoryFindResponse();
      categories.add(findResponse);
      if (category != null) {
        findResponse.setId(category.getId());
        findResponse.setName(category.getName());
        findResponse.setDescription(category.getDescription());
        productRepository.findProductByCategoryIdAndAndDealerId(category.getId(), dealerId)
          .forEach(findResponse.getProducts()::add);
      }
    }

    return categories;
  }

  @GetMapping(path = "findAll")
  public Iterable<Category> findAll() {
    return categoryRepository.findAll();
  }
}