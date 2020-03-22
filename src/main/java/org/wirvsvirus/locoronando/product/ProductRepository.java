package org.wirvsvirus.locoronando.product;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
  Product findProductByIdAndCategoryIdAndDealerId(
    int id,
    int categoryId,
    int dealerId);

  List<Product> findProductByCategoryIdAndAndDealerId(int categoryId, int dealerId);
}
