package org.wirvsvirus.locoronando.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/product")
public final class ProductController {
  private final ProductRepository productRepository;

  @Autowired
  public ProductController(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @PostMapping(path = "add")
  @ResponseStatus(HttpStatus.OK)
  public void addProduct(Product product) {
    productRepository.save(product);
  }

  @GetMapping(path = "find")
  public Iterable<Product> findAll() {
    return productRepository.findAll();
  }

  @GetMapping(path = "find/{brandId}")
  public Iterable<Product> findAll(@PathVariable("brandId") int brandId) {
    return productRepository.findAllByBrandId(brandId);
  }
}