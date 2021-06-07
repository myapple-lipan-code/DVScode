package com.ljs.callray.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@TableName("tx_account")
public class Account implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public static final String ADMINISTRATOR = "Administrator";

	/**
	 * 管理员
	 */
	public static final int MANAGER = 1;
	/**
	 * 班长
	 */
	public static final int MONITOR = 2;
	/**
	 * 话务员
	 */
	public static final int AGENT = 4;
	/**
	 * 分机
	 */
	public static final int EXTENSION = 8;
	/**
	 * 部门
	 */
	public static final int DEPARTMENT = 16;


	@TableId(type = IdType.AUTO,value = "account_id")
	private Long id;

	@TableField(value = "account_name")
	private String name;

	@TableField(value = "account_password")
	private String password;

	@TableField(value = "account_property")
	private Integer property;

	@TableField(value = "account_rights")
	private String rights;

	@TableField(value = "account_desp")
	private String desp;

	@TableField(value = "account_alias")
	private String alias;

	@TableField(value = "account_despdescribe")
	private Integer despdescribe;

	@TableField(value = "account_dspts")
	private String dspts;

	/**
	 * 解析之后的权限
	 */
	@TableField(exist = false)
	private List<Integer> relRights = new ArrayList<Integer>();

	@TableField(exist = false)
	private List<Account> departments = new ArrayList<Account>();

	@TableField(exist = false)
	private List<Account> mgrAccounts = new ArrayList<Account>();
	@TableField(exist = false)
	private List<Account> mgrAgents = new ArrayList<Account>();
	@TableField(exist = false)
	private List<Account> mgrExts = new ArrayList<Account>();

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		if (name != null)
		{
			return name.trim();
		}
		return name;
	}

	public void setName(String name)
	{
		if(this.name == null){
			this.name = name;
		}
		this.name = name.trim();
	}

	public String getPassword()
	{
		if (this.password == null)
			return null;
		else
			return password.trim();
	}

	public void setPassword(String password)
	{
		if (password == null)
			this.password = password;
		else
			this.password = password.trim();
	}

	public Integer getProperty()
	{
		return property;
	}

	public void setProperty(Integer property)
	{
		this.property = property;
	}

	public String getRights()
	{
		return rights;
	}

	public void setRights(String rights)
	{
		this.rights = rights;
	}

	public String getDesp()
	{
		return desp;
	}

	public void setDesp(String desp)
	{
		this.desp = desp;
	}

	public String getAlias()
	{
		return alias;
	}

	public void setAlias(String alias)
	{
		this.alias = alias;
	}

	public Integer getDespdescribe()
	{
		return despdescribe;
	}

	public void setDespdescribe(Integer despdescribe)
	{
		this.despdescribe = despdescribe;
	}

	public String getDspts()
	{
		return dspts;
	}

	public void setDspts(String dspts)
	{
		this.dspts = dspts;
	}

	public List<Integer> getRelRights()
	{
		return relRights;
	}

	public void setRelRights(List<Integer> relRights)
	{
		this.relRights = relRights;
	}

	public List<Account> getDepartments()
	{
		return departments;
	}

	public void setDepartments(List<Account> departments)
	{
		this.departments = departments;
	}

	public List<Account> getMgrDepartments()
	{
		if (this.property == Account.MONITOR)
		{
			return departments;
		}
		return null;
	}

	public List<Account> getMgrAccounts()
	{
		return mgrAccounts;
	}

	public void setMgrAccounts(List<Account> mgrAccounts)
	{
		this.mgrAccounts = mgrAccounts;
	}

	public List<Account> getMgrAgents()
	{
		return mgrAgents;
	}

	public void setMgrAgents(List<Account> mgrAgents)
	{
		this.mgrAgents = mgrAgents;
	}

	public List<Account> getMgrExts()
	{
		return mgrExts;
	}

	public void setMgrExts(List<Account> mgrExts)
	{
		this.mgrExts = mgrExts;
	}

	@Override
	public String toString()
	{
		return "Account [id=" + id + ", name=" + name + ", password=" + password + ", property=" + property + ", rights=" + rights + ", desp=" + desp + ", alias=" + alias + ", despdescribe=" + despdescribe + ", dspts=" + dspts + "]";
	}
}
