package com.miltech.deliveryservice.controller;

import com.miltech.deliveryservice.domain.Box;
import com.miltech.deliveryservice.domain.Item;
import com.miltech.deliveryservice.service.BoxDaoService;
import com.miltech.deliveryservice.service.BoxService;
import com.miltech.deliveryservice.service.ItemDaoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/delivery")
public class DeliveryController {
    private final BoxDaoService boxDaoService;
    private final ItemDaoService itemDaoService;
    private final BoxService boxService;

    @Operation(summary = "creating a box")
    @PostMapping("/box")
    public ResponseEntity<Box> createBox(@Validated @RequestBody Box box) {
        return ResponseEntity.ok().body(boxDaoService.createBox(box));
    }

    @Operation(summary = "loading a box with items")
    @PutMapping("/box/load")
    public ResponseEntity<Box> loadBoxWithItem(@Validated @RequestBody Item item) {
        return ResponseEntity.ok().body(boxService.loadBoxWithItem(item));
    }

    @Operation(summary = "checking loaded items for a given box")
    @GetMapping("/items/{boxCode}")
    public ResponseEntity<List<Item>> checkLoadedItemsByBoxCode(@Validated @PathVariable String boxCode) {
        return ResponseEntity.ok().body(itemDaoService.findByBoxCode(boxCode));
    }

    @Operation(summary = "checking available boxes for loading")
    @GetMapping("/box/available")
    public ResponseEntity<List<Box>> availableBoxesForLoading() {
        return ResponseEntity.ok().body(boxDaoService.findByWeightLessThan());
    }

    @Operation(summary = "check battery level for a given box;")
    @GetMapping("/battery-level/{boxCode}")
    public ResponseEntity<Double> findBatteryLevelByBoxCode(@PathVariable String boxCode) {
        return ResponseEntity.ok().body(boxDaoService.findBatteryLevelByBoxCode(boxCode));
    }
}
