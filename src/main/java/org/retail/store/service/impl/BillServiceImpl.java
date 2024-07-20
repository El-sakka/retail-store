package org.retail.store.service.impl;

import lombok.RequiredArgsConstructor;
import org.retail.store.exception.BillNotFoundException;
import org.retail.store.mapper.BillMapper;
import org.retail.store.model.dto.BillDto;
import org.retail.store.model.entity.Bill;
import org.retail.store.model.entity.User;
import org.retail.store.model.enums.UserTypeEnum;
import org.retail.store.model.request.BillRequest;
import org.retail.store.repository.BillRepository;
import org.retail.store.service.BillService;
import org.retail.store.service.UserService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class BillServiceImpl
        implements BillService {

    private static final BillMapper billMapper = BillMapper.INSTANCE;
    private final UserService userService;
    private final BillRepository billRepository;

    @Override
    public BillDto addBill(BillRequest billRequest) {

        User user = userService.getUser(billRequest.getUser());

        double totalAmount = billRequest.getTotalAmount();

        double itemsTotalDiscount = calcuteOverAllItemsDiscount(totalAmount);
        double percentageDiscount = calculateDiscountPercentage(user,
                                                                billRequest.getTotalNonGroceryAmount());

        double discount = Math.max(itemsTotalDiscount,
                                   percentageDiscount);

        Bill bill = billMapper.billRequestToBill(billRequest);
        bill.setTotalAmountBeforeDiscount(totalAmount);
        bill.setTotalAmountAfterDiscount(totalAmount - discount);
        bill.setUser(user);

        return billMapper.toDTO(billRepository.save(bill));
    }

    @Override
    public Bill getBill(Long billId) {
        return billRepository.findById(billId)
                             .orElseThrow(BillNotFoundException::new);
    }

    @Override
    public double calculateDiscountPercentage(User user,
                                              double totalAmountNonGrocery) {

        UserTypeEnum userType = user.getType();
        double discount = 0;

        if (userType == UserTypeEnum.EMPLOYEE) {
            discount = totalAmountNonGrocery * 0.30;
        } else if (userType == UserTypeEnum.AFFILIATE) {
            discount = totalAmountNonGrocery * 0.10;
        } else if (isTwoYearsOldCustomer(user)) {
            discount = totalAmountNonGrocery * 0.05;
        }

        return discount;
    }

    private int calcuteOverAllItemsDiscount(double totalAmount) {
        return (int) (totalAmount / 100) * 5;
    }

    private boolean isTwoYearsOldCustomer(User user) {
        LocalDate registrationDate = Instant.ofEpochMilli(user.getCreatedAt()
                                                              .toEpochMilli())
                                            .atZone(ZoneId.systemDefault())
                                            .toLocalDate();

        long yearsBetween = ChronoUnit.YEARS.between(registrationDate,
                                                     LocalDate.now());

        return yearsBetween > 2;
    }

}
