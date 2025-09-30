package com.miltech.deliveryservice.service;

import com.miltech.deliveryservice.domain.Box;
import com.miltech.deliveryservice.interfaces.BatteryLevel;
import com.miltech.deliveryservice.interfaces.Id;
import com.miltech.deliveryservice.model.BoxEntity;
import com.miltech.deliveryservice.repository.BoxRepository;
import com.miltech.deliveryservice.util.CodeGeneratorUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoxDaoService {
    private final BoxRepository repository;
    private final ModelMapper modelMapper;

    public Box save(Box box){
        BoxEntity boxEntity = modelMapper.map(box, BoxEntity.class);
        boxEntity.setState(box.getState().getType());
        return modelMapper.map(repository.save(boxEntity), Box.class);
    }

    public Box createBox(Box box){
        box.setBoxCode(generateBoxCode());
        return save(box);
    }

    public Box findTopByWeightLessThanOrderByWeightAsc(Double weight){
        return repository.findTopByWeightLessThanOrderByWeightAsc(weight).map(boxEntity -> modelMapper.map(boxEntity, Box.class)).orElse(null);
    }

    public List<Box> findByWeightLessThan(){
        return repository.findBoxesWithWeightLessThan500().stream().map(boxEntity -> modelMapper.map(boxEntity, Box.class)).toList();
    }

    public String generateBoxCode(){
        Id id = repository.findTopByOrderByIdDesc().orElse(null);
        if(ObjectUtils.isEmpty(id)){
            return CodeGeneratorUtils.generateCode("BOX", 0);
        }
        return CodeGeneratorUtils.generateCode("BOX", id.getId());
    }

    public Double findBatteryLevelByBoxCode(String boxCode){
        BatteryLevel batteryLevel = repository.findBatteryLevelByBoxCode(boxCode).orElse(null);
        if(ObjectUtils.isEmpty(batteryLevel)){
            return null;
        }
        return batteryLevel.getBatteryLevel();
    }

    public Box findByBoxCode(String boxCode){
        return repository.findByBoxCode(boxCode).map(boxEntity -> modelMapper.map(boxEntity, Box.class)).orElse(null);
    }
}
