package programmerzamannow.restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import programmerzamannow.restful.exception.ProductException;
import programmerzamannow.restful.model.Product;
import programmerzamannow.restful.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping("/products")
  public ResponseEntity<Page<Product>> findProductByCategoryHandler(@RequestParam String category, @RequestParam List<String> color,
    @RequestParam List<String> size, @RequestParam Integer minPrice,
    @RequestParam Integer maxPrice, @RequestParam Integer minDiscount,
    @RequestParam String sort, @RequestParam String stock,
    @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
    Page<Product> res = productService.getAllProduct(category, color, size, minPrice, maxPrice, minDiscount, sort, stock, pageNumber, pageSize);

    System.out.println("complete products");

    return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
  }

  @GetMapping("/products/id/{productId}")
  public ResponseEntity<Product> findProductByIdHandler(@PathVariable Long productId) throws ProductException {
    Product product = productService.findProductById(productId);

    return new ResponseEntity<Product>(product, HttpStatus.ACCEPTED);
  }

//  @GetMapping("/products/search")
//  public ResponseEntity<List<Product>> searchProductHandler(@RequestParam String q) throws ProductException {
//    List<Product> products = productService.searchProduct(q);
//
//    return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
//  }

}
