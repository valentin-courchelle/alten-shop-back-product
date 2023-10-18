package alten.demo.shop.controller;

import alten.demo.shop.dto.Product;
import alten.demo.shop.exception.BadRequestException;
import alten.demo.shop.exception.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductController {

    private final ProductControllerService controllerService;

    public ProductController(ProductControllerService controllerService) {
        this.controllerService = controllerService;
    }

    @PostMapping
    ResponseEntity<Product> createProduct(@RequestBody @Valid Product product) {
        return new ResponseEntity<>(this.controllerService.createProduct(product), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(this.controllerService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    ResponseEntity<Product> getProductById(@PathVariable long productId) {
        try {
            Product product = this.controllerService.getProductById(productId);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{productId}")
    ResponseEntity<Product> updateProductById(@PathVariable long productId, @RequestBody @Valid Product patch) {
        try {
            Product updatedProduct = this.controllerService.updateProductById(productId, patch);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (BadRequestException bre) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException nfe) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{productId}")
    ResponseEntity<Void> deleteProductById(@PathVariable long productId) {
        try {
            this.controllerService.deleteProductById(productId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
