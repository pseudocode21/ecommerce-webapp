package com.app.ecom.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne //means many order items can refer to the same product.
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    private Integer quantity;
    private BigDecimal price;

    //order_id is the foreign key.
    //It references the primary key of the Order entity.
    //nullable = false requires each saved order item to have an associated order -> no null values for order

    @ManyToOne //This means many order items can belong to one order.
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

//1)cascade = CascadeType.ALL: saving, merging, removing, and other supported persistence operations
//on an order can cascade to its items.
//2)orphanRemoval = true: removing an item from the managed order's collection can delete that item from the database.
//3)For example, when creating a new order with new items, saving the order can also save the items because of cascading.
// You still need to set each item's order reference correctly.
//4)Be careful with CascadeType.REMOVE: deleting an order will also delete its order items.
// For an e-commerce system, you generally want to preserve completed order history rather than casually deleting orders.
}
