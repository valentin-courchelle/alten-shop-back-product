package alten.demo.shop.controller;

import alten.demo.shop.dto.Product;
import alten.demo.shop.entity.ProductEntity;
import alten.demo.shop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductControllerService {

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
        return this.repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public Product getProductById(long id){
        Optional<ProductEntity> entity = this.repository.findById(id);
        return entity.map(this::toDto).orElse(null);
    }

    public Product updateProductById(long id, Product patch){
        Optional<ProductEntity> entityOpt = this.repository.findById(id);
        if(entityOpt.isEmpty()){
            return null;
        }

        ProductEntity entity = entityOpt.get();
        if(patch.getCategory() != null){
            entity.setCategory(patch.getCategory());
        }
        if(patch.getCode() != null){
            entity.setCode(patch.getCode());
        }
        if(patch.getName() != null){
            entity.setName(patch.getName());
        }
        if(patch.getImage() != null){
            entity.setImage(patch.getImage());
        }
        if(patch.getDescription() != null){
            entity.setDescription(patch.getDescription());
        }
        if(patch.getPrice() != null){
            entity.setPrice(patch.getPrice());
        }
        if(patch.getRating() != null){
            entity.setRating(patch.getRating());
        }
        if(patch.getQuantity() != null){
            entity.setQuantity(patch.getQuantity());
        }
        if(patch.getInventoryStatus() != null){
            entity.setInventoryStatus(patch.getInventoryStatus());
        }

        ProductEntity updatedEntity = this.repository.save(entity);
        return toDto(updatedEntity);
    }

    public boolean deleteProductById(long id){
        Optional<ProductEntity> entityOpt = this.repository.findById(id);
        if(entityOpt.isEmpty()){
            return false;
        }
        this.repository.deleteById(id);
        return true;
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
}
