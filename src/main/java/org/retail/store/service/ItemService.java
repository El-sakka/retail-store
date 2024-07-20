package org.retail.store.service;

import org.retail.store.model.dto.ItemDto;
import org.retail.store.model.request.ItemRequest;

public interface ItemService {

    ItemDto addItem(ItemRequest itemRequest);

    ItemDto getItem(Long id);
}
