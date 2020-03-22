package org.wirvsvirus.locoronando.category;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.wirvsvirus.locoronando.product.Product;

@Data
public class CategoryFindResponse {
  private String name;
  private String description;
  private int id;
  private List<Product> products = new ArrayList<>();
}