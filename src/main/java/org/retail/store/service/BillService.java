package org.retail.store.service;

import org.retail.store.model.dto.BillDto;
import org.retail.store.model.entity.Bill;
import org.retail.store.model.entity.User;
import org.retail.store.model.request.BillRequest;

public interface BillService {

    BillDto addBill(BillRequest newBill);
    Bill getBill(Long billId);

    double calculateDiscountPercentage(User user, double totalAmountNonGrocery);
}
