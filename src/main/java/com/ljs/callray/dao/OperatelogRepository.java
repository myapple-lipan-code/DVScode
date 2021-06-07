package com.ljs.callray.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljs.callray.domain.OperatelogForm;
import com.ljs.callray.entity.Operatelog;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author administrator
 * @date 2021/5/27 15:27
 */
@Repository
public interface OperatelogRepository extends BaseMapper<Operatelog> {

    List<Operatelog> getByPage(OperatelogForm operatelogForm);

    void add(Operatelog log);

}
