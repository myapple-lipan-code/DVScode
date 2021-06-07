package com.ljs.callray.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljs.callray.dao.ScconfigRepository;
import com.ljs.callray.domain.PageForm;
import com.ljs.callray.domain.PageInfoForm;
import com.ljs.callray.entity.Scconfig;
import com.ljs.callray.service.ScconfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author administrator
 * @date 2021/6/4 16:05
 */
@Service
public class ScconfigServiceImpl implements ScconfigService {

    @Autowired
    ScconfigRepository scconfigRepository;

    @Override
    public PageForm getListByPage(String ext, PageInfoForm pageInfoForm) {

        IPage<Scconfig> page = new Page<>(pageInfoForm.getCurrent(),pageInfoForm.getSize());
        QueryWrapper<Scconfig> queryWrapper = new QueryWrapper<>();

        if(ext != null && !"".equals(ext)){
            queryWrapper.and(wrapper -> wrapper.like("ext",ext));
        }

        IPage<Scconfig> accountIPage = scconfigRepository.selectPage(page, queryWrapper);
        PageForm<Scconfig> ret = new PageForm<>();
        ret.setCurrent(pageInfoForm.getCurrent());
        ret.setSize(pageInfoForm.getSize());
        ret.setTotal(accountIPage.getTotal());
        List<Scconfig> records = accountIPage.getRecords();

        ret.setDataList(records);
        return ret;
    }
}
