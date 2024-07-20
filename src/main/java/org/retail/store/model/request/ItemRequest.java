package org.retail.store.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemRequest {


    @NotBlank(message = "Item name is mandatory")
    private String name;

    @Min(value = 0, message = "Price must be non-negative")
    private double price;

    @NotNull(message = "Grocery status is mandatory")
    private boolean isGrocery = false;
}
