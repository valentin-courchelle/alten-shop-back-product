package alten.demo.shop.entity;

import alten.demo.shop.dto.InventoryStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    private String description;

    private int price;

    private int quantity;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "inventory_status")
    private InventoryStatus inventoryStatus;

    private String category;

    private String image;

    private int rating;
}
