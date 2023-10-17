package alten.demo.shop.dto;

import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    private Long id;

    private String code;

    private String name;

    private String description;

    private Integer price;

    private Integer quantity;

    private InventoryStatus inventoryStatus;

    private String category;

    private String image;

    private Integer rating;
}
