package org.wirvsvirus.locoronando.category;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/category")
public final class CategoryController {
  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public void addCategory(@RequestBody Category category) {
  }

  @DeleteMapping(path = "delete/{handlerId}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteCategories(@PathVariable("handlerId") int handlerId) {
  }

  @DeleteMapping("delete/{uniqueId}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteCategory(@PathVariable("uniqueId") UUID uniqueId) {
  }
}
