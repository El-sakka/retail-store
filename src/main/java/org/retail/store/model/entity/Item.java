package org.retail.store.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "items")
public class Item
        extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Item name is mandatory")
    @Column(name = "name", nullable = false)
    private String name;

    @Min(value = 0, message = "Price must be non-negative")
    @Column(name = "price", nullable = false)
    private double price;

    @NotNull(message = "Grocery status is mandatory")
    @Column(name = "is_grocery", nullable = false)
    private boolean isGrocery = false;

}

