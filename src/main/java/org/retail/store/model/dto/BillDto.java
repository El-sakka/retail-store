package org.retail.store.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillDto {

    private Long id;
    private UserDto user;
    private List<ItemDto> items;
    private Double totalAmountBeforeDiscount;
    private Double totalAmountAfterDiscount;
}
