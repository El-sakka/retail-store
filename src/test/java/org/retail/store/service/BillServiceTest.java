package org.retail.store.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.retail.store.exception.BillNotFoundException;
import org.retail.store.mapper.BillMapper;
import org.retail.store.model.BillFixture;
import org.retail.store.model.UserFixture;
import org.retail.store.model.dto.BillDto;
import org.retail.store.model.entity.Bill;
import org.retail.store.model.entity.User;
import org.retail.store.model.enums.UserTypeEnum;
import org.retail.store.model.request.BillRequest;
import org.retail.store.repository.BillRepository;
import org.retail.store.service.impl.BillServiceImpl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BillServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private BillRepository billRepository;

    private BillMapper billMapper;

    @InjectMocks
    private BillServiceImpl billService;

    @Test
    void testAddBillWithValidDataEmployeeType() {
        // Given
        User user = UserFixture.getUserFixture(UserTypeEnum.EMPLOYEE);
        BillRequest billRequest = BillFixture.getBillRequest(UserTypeEnum.EMPLOYEE);
        Bill bill = BillFixture.getBill(UserTypeEnum.EMPLOYEE);
        bill.setTotalAmountBeforeDiscount(1780.0);
        bill.setTotalAmountAfterDiscount(1255.0);
        when(userService.getUser(any())).thenReturn(user);
        when(billRepository.save(any(Bill.class))).thenReturn(bill);

        // When
        BillDto result = billService.addBill(billRequest);

        assertEquals(1780.0,
                     result.getTotalAmountBeforeDiscount());

        assertEquals(1255.0,
                     result.getTotalAmountAfterDiscount()
        );
        // Then
        verify(userService,
               times(1)).getUser(any());
        verify(billRepository,
               times(1)).save(any(Bill.class));

    }

    @Test
    void testAddBillWithValidDataAffiliateType() {
        // Given
        User user = UserFixture.getUserFixture(UserTypeEnum.AFFILIATE);
        BillRequest billRequest = BillFixture.getBillRequest(UserTypeEnum.AFFILIATE);
        Bill bill = BillFixture.getBill(UserTypeEnum.AFFILIATE);
        bill.setTotalAmountBeforeDiscount(1780.0);
        bill.setTotalAmountAfterDiscount(1605.0);

        when(userService.getUser(any())).thenReturn(user);
        when(billRepository.save(any(Bill.class))).thenReturn(bill);

        // When
        BillDto result = billService.addBill(billRequest);

        assertEquals(1780.0,
                     result.getTotalAmountBeforeDiscount());
        assertEquals(
                1605.0,
                result.getTotalAmountAfterDiscount());
        // Then
        verify(userService,
               times(1)).getUser(any());
        verify(billRepository,
               times(1)).save(any(Bill.class));

    }

    @Test
    void testAddBillWithTwoYearsOldCustomerDiscount() {
        // Given
        User user = UserFixture.getUserFixture(UserTypeEnum.NORMAL);
        BillRequest billRequest = BillFixture.getBillRequest(UserTypeEnum.NORMAL);
        Bill bill = BillFixture.getBill(UserTypeEnum.NORMAL);
        bill.setTotalAmountBeforeDiscount(1780.0);
        bill.setTotalAmountAfterDiscount(1692.50);

        LocalDate threeYearsAgo = LocalDate.now()
                                           .minus(3,
                                                  ChronoUnit.YEARS);

        Instant createdAt = threeYearsAgo.atStartOfDay(ZoneId.systemDefault())
                                         .toInstant();

        user.setCreatedAt(createdAt);

        when(userService.getUser(any())).thenReturn(user);
        when(billRepository.save(any(Bill.class))).thenReturn(bill);

        // When
        BillDto result = billService.addBill(billRequest);

        assertEquals(1780.0,
                     result.getTotalAmountBeforeDiscount());
        assertEquals(1692.50,
                     result.getTotalAmountAfterDiscount());
        // Then
        verify(userService,
               times(1)).getUser(any());
        verify(billRepository,
               times(1)).save(any(Bill.class));
    }

    @Test
    void testAddBillWithNoDiscount() {
        // Given
        User user = UserFixture.getUserFixture(UserTypeEnum.NORMAL);
        BillRequest billRequest = BillFixture.getBillRequest(UserTypeEnum.NORMAL);
        Bill bill = BillFixture.getBill(UserTypeEnum.NORMAL);
        bill.setTotalAmountBeforeDiscount(1780.0);
        bill.setTotalAmountAfterDiscount(1695.0);

        LocalDate threeYearsAgo = LocalDate.now()
                                           .minus(1,
                                                  ChronoUnit.YEARS);

        Instant createdAt = threeYearsAgo.atStartOfDay(ZoneId.systemDefault())
                                         .toInstant();

        user.setCreatedAt(createdAt);
        when(userService.getUser(any())).thenReturn(user);
        when(billRepository.save(any(Bill.class))).thenReturn(bill);

        // When
        BillDto result = billService.addBill(billRequest);

        assertEquals(1780.0,
                     result.getTotalAmountBeforeDiscount()
        );
        assertEquals(1695.0,
                     result.getTotalAmountAfterDiscount());
        // Then
        verify(userService,
               times(1)).getUser(any());
        verify(billRepository,
               times(1)).save(any(Bill.class));
    }

    @Test
    void testGetBillWhenBillDoesNotExist() {
        when(billRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(BillNotFoundException.class, () -> billService.getBill(1L));
        verify(billRepository, times(1)).findById(anyLong());
    }
}
