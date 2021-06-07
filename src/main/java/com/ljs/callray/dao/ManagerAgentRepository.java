package com.ljs.callray.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljs.callray.entity.ManagerAgent;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author administrator
 * @date 2021/5/19 17:36
 */
@Repository
public interface ManagerAgentRepository extends BaseMapper<ManagerAgent> {

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    ManagerAgent getById(Long id);

    /**
     * 根据班长id删除
     *
     * @param mgrId
     */
    void deleteByManagerId(Long mgrId);

    /**
     * 根据话务员id删除
     *
     * @param agentId
     */
    void deleteByAgentId(Long agentId);


    void batchAdd(List<ManagerAgent> agents);
}
