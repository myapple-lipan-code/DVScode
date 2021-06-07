package com.ljs.callray.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@TableName("t_channels")
public class Channel implements Serializable
{
	private static final long serialVersionUID = 1L;

	@TableId(value = "lsh")
	private Long id;

	@TableField(value = "computer")
	private String computer;

	@TableField(value = "extension")
	private String extension;

	@TableField(value = "enable")
	private int enable;

	@TableField(value = "CardType")
	private String cardType;

	@TableField(value = "ChNoInCard")
	private int chNoInCard;

	@TableField(value = "ChannelType")
	private String channelType;

	@TableField(value = "ChannelIPAddr")
	private String channelIPAddr;

	@TableField(value = "ChannelMAC")
	private String channelMac;

	@TableField("ChannelPacketSize")
	private int channelPacketSize;

	@TableField("ChannelVirtualExt")
	private String channelVirtualExt;

	@TableField("ChannelSBCServer")
	private String channelSBCServer;

	@TableField("CTIAppIPAddr")
	private String ctiAppIPAddr;

	@TableField(exist = false)
	private String[] departmentIds;

	@TableField(exist = false)
	private List<Account> departments; // 部门

	public String[] getDepartmentIds()
	{
		return departmentIds;
	}

	public void setDepartmentIds(String[] departmentIds)
	{
		this.departmentIds = departmentIds;
	}

	public List<Account> getDepartments()
	{
		return departments;
	}

	public void setDepartments(List<Account> departments)
	{
		this.departments = departments;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getComputer()
	{
		return computer;
	}

	public void setComputer(String computer)
	{
		this.computer = computer;
	}

	public String getExtension()
	{
		return extension;
	}

	public void setExtension(String extension)
	{
		this.extension = extension;
	}

	public int getEnable()
	{
		return enable;
	}

	public void setEnable(int enable)
	{
		this.enable = enable;
	}

	public String getCardType()
	{
		return cardType;
	}

	public void setCardType(String cardType)
	{
		this.cardType = cardType;
	}

	public int getChNoInCard()
	{
		return chNoInCard;
	}

	public void setChNoInCard(int chNoInCard)
	{
		this.chNoInCard = chNoInCard;
	}

	public String getChannelType()
	{
		return channelType;
	}

	public void setChannelType(String channelType)
	{
		this.channelType = channelType;
	}

	public String getChannelIPAddr()
	{
		return channelIPAddr;
	}

	public void setChannelIPAddr(String channelIPAddr)
	{
		this.channelIPAddr = channelIPAddr;
	}

	public String getChannelMac()
	{
		return channelMac;
	}

	public void setChannelMac(String channelMac)
	{
		this.channelMac = channelMac;
	}

	public int getChannelPacketSize()
	{
		return channelPacketSize;
	}

	public void setChannelPacketSize(int channelPacketSize)
	{
		this.channelPacketSize = channelPacketSize;
	}

	public String getChannelVirtualExt()
	{
		return channelVirtualExt;
	}

	public void setChannelVirtualExt(String channelVirtualExt)
	{
		this.channelVirtualExt = channelVirtualExt;
	}

	public String getChannelSBCServer()
	{
		return channelSBCServer;
	}

	public void setChannelSBCServer(String channelSBCServer)
	{
		this.channelSBCServer = channelSBCServer;
	}

	public String getCtiAppIPAddr()
	{
		return ctiAppIPAddr;
	}

	public void setCtiAppIPAddr(String ctiAppIPAddr)
	{
		this.ctiAppIPAddr = ctiAppIPAddr;
	}

	@Override
	public String toString() {
		return "Channel{" +
				"id=" + id +
				", computer='" + computer + '\'' +
				", extension='" + extension + '\'' +
				", enable=" + enable +
				", cardType='" + cardType + '\'' +
				", chNoInCard=" + chNoInCard +
				", channelType='" + channelType + '\'' +
				", channelIPAddr='" + channelIPAddr + '\'' +
				", channelMac='" + channelMac + '\'' +
				", channelPacketSize=" + channelPacketSize +
				", channelVirtualExt='" + channelVirtualExt + '\'' +
				", channelSBCServer='" + channelSBCServer + '\'' +
				", ctiAppIPAddr='" + ctiAppIPAddr + '\'' +
				", departmentIds=" + Arrays.toString(departmentIds) +
				", departments=" + departments +
				'}';
	}
}
