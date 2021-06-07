package com.ljs.callray.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljs.callray.controller.model.Result;
import com.ljs.callray.controller.vo.LoginVo;
import com.ljs.callray.dao.AccountRepository;
import com.ljs.callray.dao.ManagerAgentRepository;
import com.ljs.callray.domain.PageForm;
import com.ljs.callray.domain.PageInfoForm;
import com.ljs.callray.entity.Account;
import com.ljs.callray.entity.ManagerAgent;
import com.ljs.callray.service.AccountService;
import com.ljs.callray.utils.AccountUtils;
import com.ljs.callray.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author administrator
 * @date 2021/5/17 10:44
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ManagerAgentRepository managerAgentRepository;

    @Override
    public Result<LoginVo> login(Account account) {
        if(StringUtils.isBlank(account.getName())){
            return new Result<>(null,400,"账号不能为空!");
        }
        if(StringUtils.isBlank(account.getPassword())){
            return new Result<>(null,400,"密码不能为空!");
        }

        List<Account> users = accountRepository.selectList(new QueryWrapper<Account>().eq("account_name",account.getName()).eq("account_password", account.getPassword()));
        if(null != users && users.size() > 0 && account.getPassword().equals(users.get(0).getPassword())){

            LoginVo loginVo = new LoginVo(users.get(0), UUID.randomUUID().toString().replace("-",""));

            return new Result<LoginVo>(loginVo,200,"登录成功!");
        }
        return new Result<>(null,400,"密码错误!");

    }

    @Override
    public PageForm<Account> getByProperty(int property,String name, PageInfoForm pageForm) {
        IPage<Account> page = new Page<>(pageForm.getCurrent(),pageForm.getSize());
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
       if(property != 1){
           queryWrapper.notIn("ACCOUNT_NAME","Administrator");
       }if(property != 0){
            queryWrapper.eq("ACCOUNT_PROPERTY",property);
        }
       if(name != null && !"".equals(name)){
           queryWrapper.and(wrapper -> wrapper.like("ACCOUNT_NAME",name).or().like("ACCOUNT_ALIAS",name));
       }

        IPage<Account> accountIPage = accountRepository.selectPage(page, queryWrapper);
        PageForm<Account> ret = new PageForm<>();
        ret.setCurrent(pageForm.getCurrent());
        ret.setSize(pageForm.getSize());
        ret.setTotal(accountIPage.getTotal());
        List<Account> records = accountIPage.getRecords();
        if(property != Account.DEPARTMENT){
            setDepartments(records);
        }
        ret.setDataList(records);
        return ret;
    }
    @Override
    public List<Account> getByProperty(int property) {
        List<Account> accounts = accountRepository.getByProperty(property);
        if(property != Account.DEPARTMENT){
            setDepartments(accounts);
        }
        return accounts;
    }

    @Override
    public void batchAdd(List<Account> accounts, List<Account> existAccount) {
        if (accounts.size() > 0)
        {
            // 得到帐号类型
            Account bean = accounts.get(0);
            List<Account> dbAccounts = accountRepository.getByProperty(bean.getProperty());
            // 找出已经存在的帐号
            List<Account> relAddAccount = new ArrayList<Account>();
            if (dbAccounts.size() <= 0)
            {
                relAddAccount = accounts;
            }
            else
            {
                for (Account account : accounts)
                {
                    boolean exist = false;
                    for (Account dbAccount : dbAccounts)
                    {
                        if (account.getName().trim().equalsIgnoreCase(dbAccount.getName().trim()))
                        {
                            accountRepository.insert(account);
                            exist = true;
                            break;
                        }
                    }
                    if (!exist)
                    {
                        relAddAccount.add(account);
                    }
                }
            }
            if (relAddAccount.size() > 0)
            {
                String dpts = bean.getDesp();
                // >>得到所属部门下的班长
                List<Account> monitors = null;
                if (dpts != null && !dpts.trim().isEmpty() && (bean.getProperty() == Account.EXTENSION || bean.getProperty() == Account.AGENT))
                {
                    Map<String, Object> parameters = new HashMap<String, Object>();
                    parameters.put("property", Account.MONITOR);
                    parameters.put("departments", Arrays.asList(dpts.split(",")));
                    monitors = accountRepository.getByPropertyAndDepartments(parameters);
                }
                // >>批量添加时不知道如何获取添加后的注解，所以只能改为单调单调的插入
                for (Account account : relAddAccount)
                {
                    accountRepository.insert(account);
                    if (monitors != null && monitors.size() > 0)
                    {
                        List<ManagerAgent> agents = new ArrayList<ManagerAgent>();
                        for (Account monitor : monitors)
                        {
                            if (account.getId() != null && account.getId() > -1)
                            {
                                agents.add(new ManagerAgent(monitor.getId(), account.getId()));
                            }
                        }
                        if (agents.size() > 0)
                        {
                            managerAgentRepository.batchAdd(agents);
                        }
                    }
                }
            }
        }
    }
    //单调的数据添加操作
    @Override
    public void add(Account account) {
        Account old = accountRepository.getByNameAndProperty(account);
        if (old != null)
        {
            throw new RuntimeException("帐号[" + old.getName() + "]已经存在");
        }
        accountRepository.insert(account);
        // 把分机添加到所属部门的班长的管理表中
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("property", Account.MONITOR);
        if (account.getDesp() != null && !account.getDesp().trim().isEmpty())
        {
            List<String> departmentIds = Arrays.asList(account.getDesp().split(","));
            params.put("departments", departmentIds);
            List<Account> monitors = accountRepository.getByPropertyAndDepartments(params);
            if (monitors.size() > 0)
            {
                List<ManagerAgent> agents = new ArrayList<ManagerAgent>();
                for (Account monitor : monitors)
                {
                    if (account.getId() != null && account.getId() > -1)
                    {
                        agents.add(new ManagerAgent(monitor.getId(), account.getId()));
                    }

                }
                if (agents.size() > 0)
                {
                    System.out.println("agents.size():" + agents.size());
                    managerAgentRepository.batchAdd(agents);
                }
            }
        }

    }

    @Override
    public void update(Account account) {
        Account dbAccount = accountRepository.getByNameAndProperty(account);
        if (dbAccount != null && dbAccount.getId().intValue() != account.getId().intValue())
        {
            throw new RuntimeException("帐号[" + account.getName() + "]已经存在");
        }
        System.out.println(account);
        accountRepository.updateById(account);

    }

    /**
     *  设置班长、话务员、分机等帐号的部门信息
     * @param accounts
     */
    private void setDepartments(List<Account> accounts) {
        //得到所有部门
        List<Account> departments = accountRepository.getByProperty(Account.DEPARTMENT);
        //设置账号所属的部门
        for (Account account : accounts) {
            if(account.getProperty() == Account.DEPARTMENT){
                continue;
            }
            //获取所属部门
            String desp = account.getDesp();
            if(desp == null || desp.trim().isEmpty()){
                desp = "0";//设置默认部门
            }
            if(desp != null && !desp.trim().isEmpty()){

                String[] desps = desp.split(",");
                for (String despStr : desps) {
                    if("0" .equals( despStr)){
                         account.getDepartments().add(AccountUtils.getDefaultDepartment());
                    }else{
                        for (Account dpt : departments) {
                            if (despStr != null && despStr.trim().equalsIgnoreCase(dpt.getId().toString()))
                            {
                                account.getDepartments().add(dpt);
                                break;
                            }
                        }
                        
                    }
                }

            }
        }
    }
    @Override
    public  Account getById(Long id){
        //获取账号信息
        Account account = accountRepository.selectById(id);
        if(account != null && Account.DEPARTMENT != account.getProperty()){////非部门账号
            if(account.getDesp() != null && !account.getDesp().trim().isEmpty()){
                if(account.getDesp().equals("All")){
                    account.setDepartments(this.getAllDeps());
                    account.getDepartments().add(AccountUtils.getDefaultDepartment());
                }else if(account.getDesp().split(",").length > 0){
                    //查询出所有部门
                    String[] split = account.getDesp().split(",");
                    List<Long> ids = CommonUtils.StrArrayToLongList(split);
                    List<Account> allMgrs = accountRepository.getByIds(ids);
                    List<Account> allDepartments = new ArrayList<>();
                    if(allMgrs != null && allMgrs.size()  > 0){
                        for (Account allMgr : allMgrs) {
                            if(allMgr != null){
                                if(allMgr.getProperty() == Account.DEPARTMENT){
                                    allDepartments.add(allMgr);
                                }
                            }
                        }
                    }
                    account.setDepartments(allDepartments);
                    for (Long l : ids) {
                        if(l.intValue() == 0){
                            account.getDepartments().add(AccountUtils.getDefaultDepartment());
                        }
                    }
                }

            }

        }
        return account;
    }

    @Override
    public List<Account> getAllDeps() {
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ACCOUNT_PROPERTY",Account.DEPARTMENT);
        List<Account> accounts = accountRepository.selectList(queryWrapper);
        return accounts;
    }

    //账号删除
    @Override
    public void delete(Long id) {
        Account account = this.getById(id);
        if(account.getProperty() == Account.MONITOR){
            // 如果是班长则要先清除管理的话务员和分机
            managerAgentRepository.deleteByManagerId(account.getId());
        }else if(account.getProperty() == Account.AGENT || account.getProperty() == Account.EXTENSION){
            //分机或者话务员需要清理被管理者信息
            managerAgentRepository.deleteByAgentId(account.getId());
        }else  if(account.getProperty() == Account.DEPARTMENT){
            //删除部门 需要清理所有的部门信息
            List<Account> accounts = accountRepository.getByDepartment(id.toString());
            for (Account a : accounts)
            {
                String newDept = removeDepartmentId(a.getDesp(), id.toString());
                a.setDesp(newDept);
                accountRepository.updateById(a);
            }
        }
        accountRepository.deleteById(id);

    }
    /**
     * 去除所属部门字段中包含的部门id，主要用于部门删除
     *
     * @param desp
     *            所属部门
     * @param departmentId
     *            部门id
     * @return
     */
    private String removeDepartmentId(String desp, String departmentId)
    {
        int index = desp.indexOf(departmentId);
        if (index == 0)
        {
            return desp.substring(0, index) + desp.substring((index + 1) + departmentId.length());
        }
        else
        {
            return desp.substring(0, index - 1) + desp.substring(index + departmentId.length());
        }
    }

    @Override
    public void deletes(List<Long> ids) {
        //进行批量删除
        if(ids != null && ids.size() > 0){
            for (Long id : ids) {
                this.delete(id);
            }
        }
    }

    @Override
    public List<Account> getMgrAccountAgentOrExt(Long id, Integer accountProperty) {
        Map<String, Object> paramters = new HashMap<String, Object>();
        paramters.put("id", id);
        paramters.put("accountProperty", accountProperty);
        List<Account> accounts = accountRepository.getMgrAccountAgentOrExt(paramters);
        setDepartments(accounts);
        return accounts;
    }

    @Override
    public List<Account> getAgentAndExtByDepartments(List<String> departments, Integer accountProperty) {
        Map<String, Object> paramters = new HashMap<String, Object>();
        paramters.put("list", departments);
        paramters.put("accountProperty", accountProperty);
        List<Account> accounts = accountRepository.getAgentAndExtByDepartments(paramters);
        setDepartments(accounts);
        return accounts;
    }

    @Override
    public Account getByNameAndPassword(Account account) {
        Account login = accountRepository.getByNameAndPassword(account);
        //获取到账户信息
        if(login == null){
            return null;
        }
        if (login.getDesp() != null && !login.getDesp().trim().isEmpty())
        {
            // String[] a=login.getDesp().split(",");
            if (login.getDesp().equals("all"))
            {
                login.setDepartments(this.getAllDeps());
                login.getDepartments().add(AccountUtils.getDefaultDepartment());
            }
            else if (login.getDesp().split(",").length > 0)
            {
                List<Long> ids = CommonUtils.StrArrayToLongList(login.getDesp().split(","));
                login.setDepartments(accountRepository.getByIds(ids));
                for (Long id : ids)
                {
                    if (id.intValue() == 0)
                    {
                        login.getDepartments().add(AccountUtils.getDefaultDepartment());
                        break;
                    }
                }
            }

        }
        List<Account> mgrAccounts = new ArrayList<Account>();
        List<Account> mgrAgents = new ArrayList<Account>();
        List<Account> mgrExts = new ArrayList<Account>();
        if (Account.ADMINISTRATOR.equals(login.getName()))
        {
            mgrAgents = accountRepository.getByProperty(Account.AGENT);
            mgrExts = accountRepository.getByProperty(Account.EXTENSION);
            mgrAccounts.addAll(mgrAgents);
            mgrAccounts.addAll(mgrExts);
        }
        else
        {
            mgrAccounts = accountRepository.getMgrAccount(login.getId());
            if (mgrAccounts != null && mgrAccounts.size() > 0)
            {
                login.setMgrAccounts(mgrAccounts);
                for (Account a : mgrAccounts)
                {
                    if (Account.EXTENSION == a.getProperty())
                    {
                        mgrExts.add(a);
                    }
                    else if (Account.AGENT == a.getProperty())
                    {
                        mgrAgents.add(a);
                    }
                }
            }
        }
        login.setMgrAgents(mgrAgents);
        login.setMgrExts(mgrExts);
        login.setMgrAccounts(mgrAccounts);
        return login;
    }

}
