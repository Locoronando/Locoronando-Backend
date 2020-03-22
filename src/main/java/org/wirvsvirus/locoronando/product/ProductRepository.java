package org.wirvsvirus.locoronando.product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
  Product findProductByIdAndCategoryIdAndDealerId(
    int id,
    int categoryId,
    int dealerId);
}
