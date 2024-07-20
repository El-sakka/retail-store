package org.retail.store.model;

import org.retail.store.model.entity.Bill;
import org.retail.store.model.enums.UserTypeEnum;
import org.retail.store.model.request.BillRequest;

public class BillFixture {

    public static BillRequest getBillRequest(UserTypeEnum userTypeEnum) {

        return BillRequest
                .builder()
                .user(UserFixture.getUserRequest(userTypeEnum))
                .items(ItemFixture.getItemRequests())
                .build();
    }

    public static Bill getBill(UserTypeEnum userTypeEnum) {
        return Bill.builder()
                .id(1L)
                .user(UserFixture.getUserFixture(userTypeEnum))
                .items(ItemFixture.getItemsData())
                .build();
    }

}
