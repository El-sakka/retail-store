package org.retail.store.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "bills")
public class Bill extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "User is mandatory")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotEmpty(message = "Items list cannot be empty")
    @OneToMany(cascade = CascadeType.ALL)
    @Transient
    private List<Item> items;


    private Double totalAmountBeforeDiscount;
    private Double totalAmountAfterDiscount;

    public double getTotalAmount() {
        return items.stream().mapToDouble(Item::getPrice).sum();
    }

    public double getTotalNonGroceryAmount() {
        return items.stream().filter(item -> !item.isGrocery()).mapToDouble(Item::getPrice).sum();
    }
}
