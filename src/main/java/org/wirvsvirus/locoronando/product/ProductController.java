package org.wirvsvirus.locoronando.product;

import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/product")
public final class ProductController {
  private ProductRepository productRepository;

  @Autowired
  private ProductController(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public void addProduct(@RequestBody Product product) {
    productRepository.save(product);
  }

  @GetMapping(path = "findOne")
  public Product findOneProduct(
    @PathParam("productId") int productId,
    @PathParam("dealerId") int dealerId,
    @PathParam("categoryId") int categoryId
    ) {
    return productRepository.findProductByIdAndCategoryIdAndDealerId(
      productId,
      dealerId,
      categoryId
    );
  }

  @GetMapping(path = "findAll")
  public Iterable<Product> findAll() {
    return productRepository.findAll();
  }
}