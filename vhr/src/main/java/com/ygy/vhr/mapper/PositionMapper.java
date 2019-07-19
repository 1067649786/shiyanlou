package com.ygy.vhr.mapper;

import com.ygy.vhr.bean.Position;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionMapper {

    List<Position> getAllPos();
}
