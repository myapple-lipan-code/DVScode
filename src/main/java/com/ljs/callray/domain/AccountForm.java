package com.ljs.callray.domain;


import com.ljs.callray.entity.Account;
import com.ljs.callray.utils.RightItemUitls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author administrator
 * @date 2021/5/17 11:03
 */
public class AccountForm {

    private Long id; // 帐号id
    private Boolean batchAdd; // 是否批量添加
    private Integer property; // 帐号类型
    private String prefix; // 帐号前缀
    private String beginAccount; // 开始帐号
    private String endAccount; // 结束帐号
    private String password = ""; // 帐号密码
    private String relPassword = ""; // 重复密码
    private String alias; // 帐号别名
    private String departmentIds; // 所属部门
    private String rights; // 权限

    public Account toAccount()
    {
        Account account = new Account();
        account.setId(id);
        account.setName(beginAccount);
        if (alias == null || alias.trim().isEmpty())
        {
            account.setAlias(beginAccount);
        } else
        {
            account.setAlias(alias);
        }
        account.setPassword(password);
        account.setProperty(property);
        account.setDesp(departmentIds);
        String rights = this.rights;
        if (rights != null && !"".equals(rights.trim()))
        {
            String[] rightsArr = rights.split(",");
            short[] rightShort = new short[] {};
            for (String str : rightsArr)
            {
                rightShort = RightItemUitls.GrantRight(rightShort, Integer.parseInt(str));
            }
            account.setRights(RightItemUitls.ByteArrayRightToStringRight(rightShort));
        }
        return account;
    }

    private int pos = Integer.MIN_VALUE;

    /**
     * 批量添加时，每次返回20个待添加的帐号。防止过多对象造成内存不足
     *
     * @param accounts
     * @return true还存在待添加的帐号
     */
    public boolean toAccount(List<Account> accounts)
    {
        int begin = Integer.parseInt(beginAccount);
        int end = Integer.parseInt(endAccount);
        if (pos == Integer.MIN_VALUE)
        {
            pos = begin;
        } else
        {
            pos++;
        }
        String relRights = null;
        if (rights != null && !"".equals(rights.trim()))
        {
            String[] rightsArr = rights.split(",");
            short[] rightShort = new short[] {};
            for (String str : rightsArr)
            {
                rightShort = RightItemUitls.GrantRight(rightShort, Integer.parseInt(str));
            }
            relRights = RightItemUitls.ByteArrayRightToStringRight(rightShort);
        }
        for (int i = pos; i <= end; i++, pos++)
        {
            Account account = new Account();
            account.setName(prefix + i);
            if (alias == null || alias.trim().isEmpty())
            {
                account.setAlias(account.getName());
            } else
            {
                account.setAlias(alias);
            }
            account.setPassword(password);
            account.setDesp(departmentIds);
            account.setProperty(property);
            account.setRights(relRights);
            accounts.add(account);
            if (accounts.size() >= 1000 && i != end)
            {
                return false;
            }
        }
        return true;
    }

    public Map<String, Object> validate()
    {
        Map<String, Object> map = new HashMap<String, Object>();
        if (beginAccount == null || "".equals(beginAccount.trim()))
        {
            map.put("empty", "Start account cannot be empty");
        }
        if (!password.trim().equals(relPassword.trim()))
        {
            map.put("password", "The two input password is not consistent, please re-enter");
        }
        if (batchAdd != null && batchAdd)
        {
            if (!(Account.AGENT == property || Account.EXTENSION == property))
            {
                map.put("batchAdd","Batch must be added to the extension or agent");
            }
            try
            {
                int begin = Integer.parseInt(beginAccount);
                int end = Integer.parseInt(endAccount);
                if (begin > end)
                {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e)
            {
                map.put("endAccount"," Batch add, start account and end account must be an integer, and the end account must be greater than the start account");
                return map;
            }
        }
        return map;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Boolean isBatchAdd()
    {
        return batchAdd;
    }

    public void setBatchAdd(Boolean batchAdd)
    {
        this.batchAdd = batchAdd;
    }

    public Integer getProperty()
    {
        return property;
    }

    public void setProperty(Integer property)
    {
        this.property = property;
    }

    public String getPrefix()
    {
        return prefix;
    }

    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
    }

    public String getBeginAccount()
    {
        return beginAccount;
    }

    public void setBeginAccount(String beginAccount)
    {
        this.beginAccount = beginAccount;
    }

    public String getEndAccount()
    {
        return endAccount;
    }

    public void setEndAccount(String endAccount)
    {
        this.endAccount = endAccount;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getRelPassword()
    {
        return relPassword;
    }

    public void setRelPassword(String relPassword)
    {
        this.relPassword = relPassword;
    }

    public String getAlias()
    {
        return alias;
    }

    public void setAlias(String alias)
    {
        this.alias = alias;
    }

    public String getDepartmentIds()
    {
        return departmentIds;
    }

    public void setDepartmentIds(String departmentIds)
    {
        this.departmentIds = departmentIds;
    }

    public String getRights()
    {
        return rights;
    }

    public void setRights(String rights)
    {
        this.rights = rights;
    }

    @Override
    public String toString() {
        return "AccountForm{" +
                "id=" + id +
                ", batchAdd=" + batchAdd +
                ", property=" + property +
                ", prefix='" + prefix + '\'' +
                ", beginAccount='" + beginAccount + '\'' +
                ", endAccount='" + endAccount + '\'' +
                ", password='" + password + '\'' +
                ", relPassword='" + relPassword + '\'' +
                ", alias='" + alias + '\'' +
                ", departmentIds='" + departmentIds + '\'' +
                ", rights='" + rights + '\'' +
                ", pos=" + pos +
                '}';
    }
}
