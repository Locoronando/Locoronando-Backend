package org.wirvsvirus.locoronando.product;

import java.util.Collections;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/product")
public final class ProductController {

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public void addProduct(Product product) {
  }

  @DeleteMapping(path = "delete/{uniqueId}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteProduct(@PathVariable("uniqueId") UUID uniqueId) {
    //TODO: Remove product from dealer
  }

  @GetMapping(path = "find")
  public Iterable<Product> findAll() {
    return Collections.emptyList();
  }
}