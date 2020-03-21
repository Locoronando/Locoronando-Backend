package org.wirvsvirus.locoronando.product;

import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, UUID> {
  void deleteProductByUniqueId(UUID uniqueId);

  List<Product> findAllByHandlerId(int handlerId);

  List<Product> findAllByHandlerIdAndCategoryId(int handlerId, UUID uniqueId);
}
