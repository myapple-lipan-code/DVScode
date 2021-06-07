package com.ljs.callray.service;

import com.ljs.callray.domain.OperatelogForm;
import com.ljs.callray.domain.PageForm;
import com.ljs.callray.entity.Operatelog;

import java.util.List;

/**
 * @author administrator
 * @date 2021/5/27 15:23
 */
public interface OperatelogService {

    public List<Operatelog> getByPage( OperatelogForm form);

    public PageForm get(OperatelogForm form, PageForm pageForm);
}
