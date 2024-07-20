package org.retail.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.retail.store.model.dto.BillDto;
import org.retail.store.model.entity.Bill;
import org.retail.store.model.request.BillRequest;

@Mapper(uses = {UserMapper.class, ItemMapper.class})
public interface BillMapper {

    BillMapper INSTANCE = Mappers.getMapper(BillMapper.class);

    @Mapping(source = "user", target = "user")
    @Mapping(source = "items", target = "items")
    BillDto toDTO(Bill bill);

    @Mapping(source = "user", target = "user")
    @Mapping(source = "items", target = "items")
    Bill billRequestToBill(BillRequest billRequest);
}
