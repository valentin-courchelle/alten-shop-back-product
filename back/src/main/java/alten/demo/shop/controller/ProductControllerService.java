package alten.demo.shop.controller;

import alten.demo.shop.dto.Product;
import alten.demo.shop.entity.ProductEntity;
import alten.demo.shop.exception.BadRequestException;
import alten.demo.shop.exception.NotFoundException;
import alten.demo.shop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductControllerService {

    public static final String NOT_FOUND_EXCEPTION_MESSAGE_FORMAT = "No product found with  id %d";
    private final ProductRepository repository;

    public ProductControllerService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product createProduct(Product product) {
        ProductEntity newEntity = ProductEntity.builder()
                .image(product.getImage())
                .name(product.getName())
                .price(product.getPrice())
                .code(product.getCode())
                .category(product.getCategory())
                .description(product.getDescription())
                .inventoryStatus(product.getInventoryStatus())
                .rating(product.getRating())
                .quantity(product.getQuantity())
                .build();

        newEntity = this.repository.save(newEntity);
        return toDto(newEntity);
    }

    public List<Product> getAllProducts() {
        return this.repository.findAll().stream().map(this::toDto).toList();
    }

    public Product getProductById(long id) throws NotFoundException {
        Optional<ProductEntity> entity = this.repository.findById(id);
        if (entity.isEmpty()) {
            throw new NotFoundException(String.format(NOT_FOUND_EXCEPTION_MESSAGE_FORMAT, id));
        }
        return this.toDto(entity.get());
    }

    public Product updateProductById(long id, Product patch) throws BadRequestException, NotFoundException {
        if (patch.getId() != id) {
            throw new BadRequestException("Given path variable id and poriduct id are not equal");
        }
        Optional<ProductEntity> entityOpt = this.repository.findById(id);
        if (entityOpt.isEmpty()) {
            throw new NotFoundException(String.format(NOT_FOUND_EXCEPTION_MESSAGE_FORMAT, id));
        }
        ProductEntity updatedEntity = this.repository.save(this.toEntity(patch));
        return toDto(updatedEntity);
    }

    public void deleteProductById(long id) throws NotFoundException {
        Optional<ProductEntity> entityOpt = this.repository.findById(id);
        if (entityOpt.isEmpty()) {
            throw new NotFoundException(String.format(NOT_FOUND_EXCEPTION_MESSAGE_FORMAT, id));
        }
        this.repository.deleteById(id);
    }

    private Product toDto(ProductEntity entity) {
        return Product.builder()
                .id(entity.getId())
                .image(entity.getImage())
                .name(entity.getName())
                .price(entity.getPrice())
                .code(entity.getCode())
                .category(entity.getCategory())
                .description(entity.getDescription())
                .inventoryStatus(entity.getInventoryStatus())
                .rating(entity.getRating())
                .quantity(entity.getQuantity())
                .build();
    }

    private ProductEntity toEntity(Product product) {
        return ProductEntity.builder()
                .id(product.getId())
                .image(product.getImage())
                .name(product.getName())
                .price(product.getPrice())
                .code(product.getCode())
                .category(product.getCategory())
                .description(product.getDescription())
                .inventoryStatus(product.getInventoryStatus())
                .rating(product.getRating())
                .quantity(product.getQuantity())
                .build();
    }
}
