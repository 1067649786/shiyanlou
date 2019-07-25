package com.shiyanlou.vhr.mapper;

import com.shiyanlou.vhr.bean.Hr;
import com.shiyanlou.vhr.bean.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HrMapper {

    Hr loadUserByUsername(String username);

    List<Role> getRolesByHrId(Long id);

    Hr getHrById(Long hrId);

    int deleteHr(Long hrId);

    int updateHr(Hr hr);

    int deleteRoleByHrId(Long hrId);

    int addRolesForHr(@Param("hrId") Long hrId, @Param("rids") Long[] rids);

    List<Hr> getHrsByKeywords(@Param("keywords") String keywords);

    int hrReg(@Param("username") String username, @Param("password") String password);

    List<Hr> getAllHr(@Param("currentId") Long currentId);
}
