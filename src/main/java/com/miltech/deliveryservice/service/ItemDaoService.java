package com.miltech.deliveryservice.service;

import com.miltech.deliveryservice.domain.Item;
import com.miltech.deliveryservice.interfaces.BoxCode;
import com.miltech.deliveryservice.interfaces.Id;
import com.miltech.deliveryservice.model.ItemEntity;
import com.miltech.deliveryservice.repository.ItemRepository;
import com.miltech.deliveryservice.util.CodeGeneratorUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemDaoService {
    private final ItemRepository repository;
    private final ModelMapper modelMapper;

    public Item save(Item item){
        ItemEntity itemEntity = modelMapper.map(item, ItemEntity.class);
        return modelMapper.map(repository.save(itemEntity), Item.class);
    }

    public String generateItemCode(){
        Id id = repository.findTopByOrderByIdDesc().orElse(null);
        if(ObjectUtils.isEmpty(id)){
            return CodeGeneratorUtils.generateCode("ITEM", 0);
        }
        return CodeGeneratorUtils.generateCode("ITEM", id.getId());
    }

    public List<Item> findByBoxCode(String code){
        return repository.findByBoxCode(code).stream().map(itemEntity -> modelMapper.map(itemEntity, Item.class)).toList();
    }

    public BoxCode findByCode(String code){
        return repository.findByCode(code).orElse(null);
    }
}
