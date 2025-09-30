package com.miltech.deliveryservice.service;

import com.miltech.deliveryservice.domain.Box;
import com.miltech.deliveryservice.domain.Item;
import com.miltech.deliveryservice.interfaces.BoxCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoxService {
    private final BoxDaoService boxDaoService;
    private final ItemDaoService itemDaoService;

    public Box loadBoxWithItem(Item item){
        double weight = 500;
        Box box = boxDaoService.findTopByWeightLessThanOrderByWeightAsc(weight);
        if(ObjectUtils.isEmpty(box)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Box available for item to be store");
        if(box.getBatteryLevel() < 0.25) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Battery level too low to load an item");
        if((box.getWeight() + item.getWeight()) > 500) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Box weight exceeded");
        item.setBoxCode(box.getBoxCode());
        item.setCode(itemDaoService.generateItemCode());
        box.setWeight(box.getWeight() + item.getWeight());
        Box response = boxDaoService.save(box);
        itemDaoService.save(item);
        return response;
    }

    public Box findByCode(String code) {
        BoxCode boxCode = itemDaoService.findByCode(code);
        if(ObjectUtils.isEmpty(boxCode) || !StringUtils.hasText(boxCode.getBoxCode())) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No record found for Box");
        return boxDaoService.findByBoxCode(boxCode.getBoxCode());
    }
}
