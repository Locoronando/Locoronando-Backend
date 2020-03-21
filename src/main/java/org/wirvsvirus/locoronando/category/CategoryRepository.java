package org.wirvsvirus.locoronando.category;

import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.wirvsvirus.locoronando.product.Product;

public interface CategoryRepository extends CrudRepository<Category, UUID> {
  void deleteCategoryByUniqueId(UUID uniqueId);

  void deleteCategoriesByHandlerId(int handlerId);

  List<Category> findAllByHandlerId(int handlerId);
}
