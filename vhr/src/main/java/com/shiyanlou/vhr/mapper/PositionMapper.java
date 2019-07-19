package com.shiyanlou.vhr.mapper;

import com.shiyanlou.vhr.bean.Position;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionMapper {

    List<Position> getAllPos();
}
