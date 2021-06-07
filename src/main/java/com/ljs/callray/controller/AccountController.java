package com.ljs.callray.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljs.callray.controller.model.Result;
import com.ljs.callray.controller.vo.LoginVo;
import com.ljs.callray.dao.RightItemRepository;
import com.ljs.callray.domain.*;
import com.ljs.callray.entity.Account;
import com.ljs.callray.entity.RightItem;
import com.ljs.callray.service.AccountService;
import com.ljs.callray.service.ManagerAgentService;
import com.ljs.callray.utils.AccountUtils;
import com.ljs.callray.utils.RightItemUitls;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author LiJinSong
 * @date 2021-01-27
 */
@RestController
@RequestMapping("/account")
@Api("用户API")
public class AccountController {

    private final int UserOrPass = 0;
    private final int NoRights = 1;
    private final int Exist = 2;
    private final int NoLicense = 4;
    public static int countnum = 0;

    @Autowired
    private AccountService accountService;

    @Autowired
    private RightItemRepository rightItemRepository;

    @Autowired
    private ManagerAgentService managerAgentService;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public BaseResponse login(@RequestBody Account account, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Account login = accountService.getByNameAndPassword(account);
        if(login == null){
            return BaseResponse.fail("用户名或密码错误");
        }
        //权限 没有权限不让登录
        if(login.getRights() == null || StringUtils.isEmpty(login.getRights())){
            return BaseResponse.fail("权限不足");
        }
        session.setAttribute("login", login);
        return BaseResponse.success("登录成功",login);
    }

    //进行账户信息的查询操作
    @PostMapping("/list")
    public BaseResponse list(@RequestBody JSONObject accountParams) {
        Map<String, Object> params = JSONObject.parseObject(accountParams.toJSONString(), new TypeReference<Map<String, Object>>() {
        });
        String retProperty = (String) params.get("property");
        int property = 0;
        if (retProperty != null && !"".equals(retProperty)) {
            property = Integer.parseInt(retProperty);
        }
        int current = (int) params.get("current");
        int size = (int) params.get("size");
        String name = (String) params.get("name");
        PageInfoForm pageForm = new PageInfoForm(current, size);
        try {
            PageForm page = accountService.getByProperty(property, name, pageForm);
            return BaseResponse.success("查询成功", page);
        } catch (Exception e) {
            return BaseResponse.success("查询失败", null);
        }
    }

    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody AccountForm bean) {
        Map<String, Object> result = bean.validate();
        if (result.size() > 0) {
            result.put("result", false);
            return result;
        }
        result.put("result", true);
        if (bean.isBatchAdd() // 批量添加 ， 只对话务员和分机
                && (Account.AGENT == bean.getProperty() || Account.EXTENSION == bean.getProperty())) {
            List<Account> addAccounts = new ArrayList<Account>(); // 待添加
            List<Account> existAccount = new ArrayList<Account>(); // 已经存在的
            boolean isFinash = false;
            do {
                isFinash = bean.toAccount(addAccounts);
                accountService.batchAdd(addAccounts, existAccount);
                addAccounts.clear();
            }
            while (!isFinash);
            result.put("result", true);
            result.put("batchExist", existAccount);
        } else {
            try {
                accountService.add(bean.toAccount());
                result.put("result", true);
            } catch (Exception e) {
                result.put("result", false);
                result.put("exist", Exist);
            }
        }
        return result;
    }

    @PostMapping("/update")
    public Map<String, Object> update(@RequestBody  AccountForm bean) {
        Map<String, Object> result = bean.validate();
        if (result.size() > 0) {
            result.put("result", false);
            return result;
        }
        try {
            Account account = bean.toAccount();
            accountService.update(account);
            result.put("result", true);
        } catch (RuntimeException e) {
            result.put("result", false);
            result.put("exist", Exist);
        }
        return result;
    }
    @PostMapping("/getById")
    public  BaseResponse get(String id){
       // System.out.println(id);
        try{
            Account account = accountService.getById(Long.parseLong(id));
            List<RightItem> rightItemList =  rightItemRepository.selectList(new QueryWrapper<>());
            List<Integer> rightInts = new ArrayList<Integer>();
            for (RightItem rightItem : rightItemList)
            {
                if (RightItemUitls.isRightGranted(account.getRights(), Integer.parseInt(rightItem.getCode())))
                {
                    rightInts.add(Integer.parseInt(rightItem.getCode()));
                }
            }
            account.setRelRights(rightInts);
            return BaseResponse.success(account);
        }catch (Exception ex){
            ex.getStackTrace();
            return BaseResponse.fail("获取账户失败");
        }
    }
    @PostMapping("/delete")
    public  BaseResponse delete(Long id){
        try{
            accountService.delete(id);
            return BaseResponse.success("删除"+id+"成功");
        }catch (Exception ex){
            return BaseResponse.fail("删除"+id+"失败");
        }
    }
    @PostMapping("/deletes")
    public  BaseResponse deletes(@RequestParam("ids") List<Long> ids){
        try{
            accountService.deletes(ids);
            return BaseResponse.success("删除"+ids+"成功");
        }catch (Exception ex){
            return BaseResponse.fail("删除"+ids+"失败");
        }
    }
    @PostMapping("/departmentTree")
    public List<TreeNode> departmentTree(){
        List<Account> departments = accountService.getByProperty(Account.DEPARTMENT);
        departments.add(AccountUtils.getDefaultDepartment());
        //部门子节点
        List<TreeNode> treeNodes = new ArrayList<>();
        for (Account department : departments) {
            TreeNode node = new TreeNode();
            node.setId(department.getId());
            node.setLabel(department.getName());
            node.setChildren(null);
            node.setTreeState(new TreeState());
            treeNodes.add(node);
        }
        //设置所有
        TreeNode root = new TreeNode();
        root.setId(-1L);
        root.setLabel("所有部门");
        TreeState rootTreeState = new TreeState();
        rootTreeState.setExpanded(true);
        root.setTreeState(rootTreeState);
        root.setChildren(treeNodes);
        List<TreeNode> roots = new ArrayList<TreeNode>();
        roots.add(root);
        return roots;
    }
    /**
     * 获取班长管理的帐号，用于班长管理设置
     *
     * @param id
     *            班长Id
     * @return
     */
    @RequestMapping(value = "/getMgr",method = RequestMethod.POST)
    public List<Account> getMgr(@RequestParam("id") Long id,@RequestParam("accountProperty") Integer accountProperty )
    {
        return accountService.getMgrAccountAgentOrExt(id, accountProperty);
    }
    /**
     * 查找部门下的分机和话务员
     *
     * @param accountProperty
     * @param ids
     * @return
     */
    @RequestMapping(value = "/getAgentAndExtByDepartments",method = RequestMethod.POST)
    @ResponseBody
    public List<Account> listProperty(@RequestParam("ids") List<String> ids,
            @RequestParam("accountProperty") Integer accountProperty)
    {
        if (ids == null || ids.size() <= 0)
        {
            return new ArrayList<Account>();
        }
        return accountService.getAgentAndExtByDepartments(ids,accountProperty);
    }
    @RequestMapping(value = "/saveMgr",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse saveMgr(@RequestParam("id") Long id, @RequestParam(value = "agentIds") List<Long> agentIds){
        try{
            managerAgentService.save(id,agentIds);
            return BaseResponse.success("管理分机成功");
        }catch (Exception ex){
            return BaseResponse.fail("管理分机成功");
        }
    }

}
