package alten.demo.shop.dto;

import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private long id;

    private String code;

    private String name;

    private String description;

    private int price;

    private int quantity;

    private InventoryStatus inventoryStatus;

    private String category;

    private String image;

    private int rating;
}
