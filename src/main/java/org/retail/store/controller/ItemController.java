package org.retail.store.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.retail.store.model.dto.ItemDto;
import org.retail.store.model.request.ItemRequest;
import org.retail.store.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
@Api(tags = "Item Management")
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    @ApiOperation(value = "Create a new Item", notes = "Endpoint to add a new item")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Item added successfully", response = ItemDto.class),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<ItemDto> addItem(
            @ApiParam(value = "Item request", required = true)
            @Valid @RequestBody ItemRequest request) {
        return new ResponseEntity<>(itemService.addItem(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ItemDto> getItem(@RequestParam Long itemId) {
        return new ResponseEntity<>(itemService.getItem(itemId), HttpStatus.OK);
    }
}
