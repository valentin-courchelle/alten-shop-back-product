package alten.demo.shop.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    private long id;

    @NotNull
    private String code;

    @NotNull
    private String name;

    @NotNull
    private String description;

    private int price;

    private int quantity;

    @NotNull
    private InventoryStatus inventoryStatus;

    @NotNull
    private String category;

    @NotNull
    private String image;

    @NotNull
    private Integer rating;
}
