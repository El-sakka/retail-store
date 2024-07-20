package org.retail.store.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.retail.store.model.dto.BillDto;
import org.retail.store.model.entity.Bill;
import org.retail.store.model.request.BillRequest;
import org.retail.store.service.BillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/bills")
@RequiredArgsConstructor
@Api(tags = "Bill Management")
public class BillController {

    private final BillService billService;

    @PostMapping
    @ApiOperation(value = "Create a new Bill", notes = "Endpoint to add a new Bill")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Bill added successfully", response = BillDto.class),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<BillDto> addBill(
            @ApiParam(value = "Bill request", required = true)
            @Valid @RequestBody BillRequest request) {
        return new ResponseEntity<>(billService.addBill(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Bill> getBill(@RequestParam Long itemId) {
        return new ResponseEntity<>(billService.getBill(itemId), HttpStatus.OK);
    }
}
