package com.ljs.callray.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljs.callray.dao.RightItemRepository;
import com.ljs.callray.domain.TreeNode;
import com.ljs.callray.entity.RightItem;
import com.ljs.callray.utils.RightItemUitls;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author administrator
 * @date 2021/6/2 16:13
 */
@RestController
@RequestMapping("/right")
@Api("权限API")
public class RightItemController {

    @Autowired
    private RightItemRepository rightItemRepository;

    @PostMapping("/accountTree")
    @ApiOperation("/权限")
    public List<TreeNode>  accountTree(){
        List<RightItem> rightItemList = rightItemRepository.selectList(new QueryWrapper<>());
        List<TreeNode> treeNodes = RightItemUitls.rightItemListToTreeNodeList(rightItemList);
        return treeNodes;
    }
}
