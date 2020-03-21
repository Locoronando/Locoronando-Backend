package org.wirvsvirus.locoronando.product;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wirvsvirus.locoronando.category.Category;
import org.wirvsvirus.locoronando.category.CategoryRepository;

@Service
public final class ProductService {
  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;

  @Autowired
  private ProductService(
    CategoryRepository categoryRepository,
    ProductRepository productRepository) {
    this.categoryRepository = categoryRepository;
    this.productRepository = productRepository;
  }

  public List<Product> findAllProductsFromCategory(UUID categoryId) {
    Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
    if (!optionalCategory.isPresent()) {
      return Collections.emptyList();
    }
    Category category = optionalCategory.get();
    return productRepository.findAllByHandlerIdAndCategoryId(
      category.handlerId(), categoryId);
  }
}
