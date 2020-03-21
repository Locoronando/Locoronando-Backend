package org.wirvsvirus.locoronando.category;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wirvsvirus.locoronando.product.Product;
import org.wirvsvirus.locoronando.product.ProductRepository;

@Service
public final class CategoryService {
  private final ProductRepository productRepository;

  @Autowired
  public CategoryService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> findAllProductsFromCategory(Category category) {
    return productRepository.findAllByHandlerIdAndCategoryId(
      category.handlerId(), category.uniqueId());
  }
}