package com.javaguru.shoppinglist.controller;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.service.ProductService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setCategory(productDTO.getCategory());
        product.setDiscount(productDTO.getDiscount());
        product.setDescription(productDTO.getDescription());

        productService.create(product);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/{id}")
    public ProductDTO findProductById(@PathVariable("id") Long id) {
        Product product = productService.findProductById(id);
        return new ProductDTO(product.getId(), product.getName(), product.getPrice(),
                product.getCategory(), product.getDiscount(), product.getDescription());
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProductById(@PathVariable("id") Long id) {
        productService.deleteProductById(id);

        return "Product successfully deleted";
    }

    @PutMapping("/update/{id}")
    public String updateDescription(@PathVariable("id") Long id,
                                    @RequestBody ProductDTO productDTO) {
        String newDescription = productDTO.getDescription();
        productService.updateDescription(id, newDescription);

        return "Product description successfully changed";
    }
}

