package com.ljs.callray.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("tx_ManagerAgent")
public class ManagerAgent implements Serializable
{
	private static final long serialVersionUID = 1L;

	@TableField(value = "manager_id")
	private Long managerId;
	@TableField(value = "agent_id")
	private Long agentId;

	public ManagerAgent()
	{
		super();
	}

	public ManagerAgent(Long managerId, Long agentId)
	{
		super();
		this.managerId = managerId;
		this.agentId = agentId;
	}

	public Long getManagerId()
	{
		return managerId;
	}

	public void setManagerId(Long managerId)
	{
		this.managerId = managerId;
	}

	public Long getAgentId()
	{
		return agentId;
	}

	public void setAgentId(Long agentId)
	{
		this.agentId = agentId;
	}
}
