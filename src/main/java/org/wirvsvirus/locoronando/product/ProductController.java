package org.wirvsvirus.locoronando.product;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
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
  private final ProductRepository productRepository;
  private final ProductService productService;

  @Autowired
  public ProductController(
    ProductRepository productRepository,
    ProductService productService
    ) {
    this.productRepository = productRepository;
    this.productService = productService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public void addProduct(Product product) {
    productRepository.save(product);
  }

  @DeleteMapping(path = "delete/{uniqueId}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteProduct(@PathVariable("uniqueId") UUID uniqueId) {
    productRepository.deleteProductByUniqueId(uniqueId);
  }

  @GetMapping(path = "find")
  public Iterable<Product> findAll() {
    return productRepository.findAll();
  }

  @GetMapping(path = "find/{handlerId}")
  public Iterable<Product> findAll(@PathVariable("handlerId") int handlerId) {
    return productRepository.findAllByHandlerId(handlerId);
  }

  @GetMapping(path = "find/{categoryId}")
  public List<Product> findAll(@PathVariable("categoryId") UUID categoryId) {
    return productService.findAllProductsFromCategory(categoryId);
  }
}