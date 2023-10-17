package alten.demo.shop.controller;

import alten.demo.shop.dto.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductController {

    private ProductControllerService controllerService;

    @PostMapping
    ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(this.controllerService.createProduct(product), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(this.controllerService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    ResponseEntity<Product> getProductById(@PathVariable long id) {
        Product product = this.controllerService.getProductById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new Product(), HttpStatus.OK);
    }

    @PatchMapping("/{productId}")
    ResponseEntity<Product> updateProductById(@PathVariable long id, @RequestBody Product product) {
        Product updatedProduct = this.controllerService.updateProductById(id, product);
        if (updatedProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new Product(), HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    ResponseEntity<Void> deleteProductById(@PathVariable long id) {
        if (this.controllerService.deleteProductById(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
