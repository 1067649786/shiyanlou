package com.ygy.vhr.service;

import com.ygy.vhr.bean.Position;
import com.ygy.vhr.mapper.PositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PositionService {

    @Autowired
    PositionMapper positionMapper;

    public List<Position> getAllPos() {
        return positionMapper.getAllPos();
    }
}
