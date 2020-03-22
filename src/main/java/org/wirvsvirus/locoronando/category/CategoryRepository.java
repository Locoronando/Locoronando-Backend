package org.wirvsvirus.locoronando.category;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
  void deleteCategoriesByDealerId(int dealerId);

  void deleteCategoryByIdAndDealerId(int id, int dealerId);

  Category findCategoryByIdAndDealerId(int id, int dealerId);

  List<Category> findCategoriesByDealerId(int dealerId);
}
