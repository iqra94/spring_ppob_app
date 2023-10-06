package programmerzamannow.restful.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import programmerzamannow.restful.exception.ProductException;
import programmerzamannow.restful.model.Category;
import programmerzamannow.restful.model.Product;
import programmerzamannow.restful.repository.CategoryRepository;
import programmerzamannow.restful.repository.ProductRepository;
import programmerzamannow.restful.request.CreateProductRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService{

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private CategoryRepository categoryRepository;

  @Override
  public Product createProduct(CreateProductRequest req) {
    Category topLevel = categoryRepository.findByName(req.getTopLevelCategory());

    if (topLevel == null) {
      Category topLevelCategory = new Category();
      topLevelCategory.setName(req.getTopLevelCategory());
      topLevelCategory.setLevel(1);

      topLevel = categoryRepository.save(topLevelCategory);
    }

    Category secondLevel = categoryRepository.findByNameAndParent(req.getSecondLevelCategory(), topLevel.getName());

    if (secondLevel == null) {
      Category secondLevelCategory = new Category();
      secondLevelCategory.setName(req.getSecondLevelCategory());
      secondLevelCategory.setParentCategory(topLevel);
      secondLevelCategory.setLevel(2);

      secondLevel = categoryRepository.save(secondLevelCategory);
    }

    Category thridLevel = categoryRepository.findByNameAndParent(req.getThirdLevelCategory(), topLevel.getName());

    if (thridLevel == null) {
      Category thridLevelCategory = new Category();
      thridLevelCategory.setName(req.getThirdLevelCategory());
      thridLevelCategory.setParentCategory(secondLevel);
      thridLevelCategory.setLevel(3);

      thridLevel = categoryRepository.save(thridLevelCategory);
    }

    Product product = new Product();
    product.setTitle(req.getTitle());
    product.setColor(req.getColor());
    product.setDescription(req.getDescription());
    product.setDiscountedPrice(req.getDiscountedPrice());
    product.setDiscountPercent(req.getDiscountPercent());
    product.setImageUrl(req.getImageUrl());
    product.setBrand(req.getBrand());
    product.setPrice(req.getPrice());
    product.setSizes(req.getSizes());
    product.setQuantity(req.getQuantity());
    product.setCategory(thridLevel);
    product.setCreatedAt(LocalDateTime.now());

    Product saveProduct = productRepository.save(product);

    return saveProduct;
  }

  @Override
  public String deleteProduct(Long productId) throws ProductException {
    Product product = findProductById(productId);
    product.getSizes().clear();
    productRepository.delete(product);
    return "product deleted successfully";
  }

  @Override
  public Product updateProduct(Long productId, Product req) throws ProductException {
    Product product = findProductById(productId);

    if (req.getQuantity() != 0) {
      product.setQuantity(req.getQuantity());
    }
    return productRepository.save(product);
  }

  @Override
  public Product findProductById(Long id) throws ProductException {
    Optional<Product> opt = productRepository.findById(id);

    if (opt.isPresent()) {
      return opt.get();
    }

    throw new ProductException("product not found with id - " +id);
  }

  @Override
  public List<Product> findProductByCategory(String category) {
    return null;
  }

  @Override
  public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);

    List<Product> products = productRepository.filterProducts(category, minPrice, maxPrice, minDiscount, sort);

    // p1 red | p2 white | p3 yellow
    // [blue, white, black, teal]
    if (!colors.isEmpty()) {
      products = products.stream().filter(p ->
                  colors.stream().anyMatch(c ->
                  c.equalsIgnoreCase(p.getColor())))
                .collect(Collectors.toList());
    }

    if (stock != null) {
      if(stock.equals("in_stock")) {
        products = products.stream().filter(p ->
                    p.getQuantity() > 0)
                  .collect(Collectors.toList());
      } else if (stock.equals("out_of_stock")) {
        products = products.stream().filter(p ->
                    p.getQuantity() < 1)
                  .collect(Collectors.toList());
      }
    }

    //2 => 10 products
    //11 + 10 => 21
    int startIndex = (int) pageable.getOffset();
    int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());

    List<Product> pageContent = products.subList(startIndex, endIndex);
    Page<Product> filteredProducts = new PageImpl<>(pageContent, pageable, products.size());

    return filteredProducts;
  }
}
