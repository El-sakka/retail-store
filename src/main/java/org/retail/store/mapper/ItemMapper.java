package org.retail.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.retail.store.model.dto.ItemDto;
import org.retail.store.model.entity.Item;
import org.retail.store.model.request.ItemRequest;

@Mapper
public interface ItemMapper {

    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    Item itemRequestToItem(ItemRequest itemRequest);
    ItemDto toItemDto(Item item);
}
