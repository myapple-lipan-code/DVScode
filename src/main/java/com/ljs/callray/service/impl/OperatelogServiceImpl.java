package com.ljs.callray.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljs.callray.dao.OperatelogRepository;
import com.ljs.callray.domain.OperatelogForm;
import com.ljs.callray.domain.PageForm;
import com.ljs.callray.entity.Operatelog;
import com.ljs.callray.service.OperatelogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author administrator
 * @date 2021/5/27 15:24
 */
@Service
public class OperatelogServiceImpl implements OperatelogService {

    @Autowired
    OperatelogRepository operatelogRepository;

    @Override
    public List<Operatelog> getByPage(OperatelogForm form) {
        return  operatelogRepository.getByPage(form);
    }

    @Override
    public PageForm get(OperatelogForm form, PageForm pageForm) {
        IPage<Operatelog> page = new Page<>(pageForm.getCurrent(),pageForm.getSize());
        QueryWrapper<Operatelog> wrapper = new QueryWrapper<>();
        Date starttime = form.getStarttime();
        Date endtime = form.getEndtime();
        String operator = form.getOperator();
        Integer operateType = form.getOperateType();
        if(starttime != null){
            wrapper.ge("OPERATESTARTTIME",starttime);
        }
        if(endtime != null){
            wrapper.lt("OPERATESTARTTIME",endtime);
        }
        if(operator != null && !"".equals(operator )){
            wrapper.eq("OPERATOR",operator);
        }
        if(operateType != null){
            wrapper.eq("OPERATETYPE",operateType);
        }
        IPage<Operatelog> pagelog = operatelogRepository.selectPage(page, wrapper);
        PageForm pageForm1 = new PageForm();
        pageForm1.setCurrent(pageForm.getCurrent());
        pageForm1.setSize(pageForm.getSize());
        pageForm1.setTotal(pagelog.getTotal());
        pageForm1.setDataList(pagelog.getRecords());
        return pageForm1;
    }
}
