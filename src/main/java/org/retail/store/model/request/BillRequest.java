package org.retail.store.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillRequest {

    private UserRequest user;

    private List<ItemRequest> items;

    public double getTotalAmount() {
        return items.stream().mapToDouble(ItemRequest::getPrice).sum();
    }

    public double getTotalNonGroceryAmount() {
        return items.stream().filter(item -> !item.isGrocery()).mapToDouble(ItemRequest::getPrice).sum();
    }
}
