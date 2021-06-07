package com.ljs.callray.domain;

import com.ljs.callray.entity.Channel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class ChannelForm
{

	private Long id;
	private String beginExt;
	private String endExt;
	private String computer;
	private String channelIPAddr;
	private String channelMac;
	private String ctiAppIPAddr;
	private String cardType;
	private String channelType;
	private Integer channelPacketSize;
	private String channelVirtualExt;
	private String channelSBCServer;
	private boolean enable;
	private String[] departmentIds;

	public Map<String, Object> validate()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		if (beginExt == null || beginExt.trim().isEmpty())
		{
			map.put("beginExt", true);
		}
		if (endExt != null && !endExt.trim().isEmpty())
		{
			int begin = 0;
			int end = 0;
			try
			{
				begin = Integer.parseInt(beginExt);
				end = Integer.parseInt(endExt);
				if (begin > end)
				{
					map.put("endExt", true);
				}
			}
			catch (NumberFormatException e)
			{
				map.put("endExt", true);
			}
		}
		if (computer == null || computer.trim().isEmpty())
		{
			map.put("computer", true);
		}
		return map;
	}

	public Channel toChannel()
	{
		Channel channel = new Channel();
		channel.setId(id);
		channel.setComputer(this.computer);
		channel.setExtension(beginExt);
		channel.setEnable(this.enable ? 1 : 0);
		channel.setCardType(this.cardType);
		channel.setChannelType(this.channelType);
		channel.setChannelIPAddr(this.channelIPAddr);
		channel.setChannelMac(this.channelMac);
		channel.setChannelPacketSize(this.channelPacketSize);
		channel.setChannelVirtualExt(this.channelVirtualExt);
		channel.setChannelSBCServer(channelSBCServer);
		channel.setCtiAppIPAddr(ctiAppIPAddr);
		channel.setDepartmentIds(departmentIds);
		return channel;
	}

	private int pos = Integer.MIN_VALUE;

	/**
	 * 把表单转换成Channel对象，为了防止内存溢出，转换5000就直接返回true
	 *
	 * @param channels
	 * @return true 还有剩余
	 */
	public boolean toChannelList(List<Channel> channels)
	{
		String str = "";
		if (beginExt.startsWith("0"))
		{
			str = "0";
		}

		int begin = Integer.parseInt(beginExt);
		int end = Integer.parseInt(endExt);
		if (pos == Integer.MIN_VALUE)
		{
			pos = begin;
		}
		else
		{
			pos++;
		}
		for (int i = pos; i <= end; i++)
		{
			Channel channel = new Channel();
			channel.setExtension(str + i + "");
			channel.setComputer(this.computer);
			channel.setEnable(this.enable ? 1 : 0);
			channel.setCardType(this.cardType);
			channel.setChannelType(this.channelType);
			channel.setChannelIPAddr(this.channelIPAddr);
			channel.setChannelMac(this.channelMac);
			channel.setChannelPacketSize(this.channelPacketSize);
			channel.setChannelVirtualExt(this.channelVirtualExt);
			channel.setChannelSBCServer(channelSBCServer);
			channel.setCtiAppIPAddr(ctiAppIPAddr);
			channel.setDepartmentIds(departmentIds);
			channels.add(channel);
			pos++;
			if (channels.size() > 5000)
			{
				if (i != end)
				{
					return true;
				}
			}
		}
		return false;
	}

	// ....
	public String getComputer()
	{
		return computer;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getBeginExt()
	{
		return beginExt;
	}

	public void setBeginExt(String beginExt)
	{
		this.beginExt = beginExt;
	}

	public String getEndExt()
	{
		return endExt;
	}

	public void setEndExt(String endExt)
	{
		this.endExt = endExt;
	}

	public void setComputer(String computer)
	{
		this.computer = computer;
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

	public String getCtiAppIPAddr()
	{
		return ctiAppIPAddr;
	}

	public void setCtiAppIPAddr(String ctiAppIPAddr)
	{
		this.ctiAppIPAddr = ctiAppIPAddr;
	}

	public String getCardType()
	{
		return cardType;
	}

	public void setCardType(String cardType)
	{
		this.cardType = cardType;
	}

	public String getChannelType()
	{
		return channelType;
	}

	public void setChannelType(String channelType)
	{
		this.channelType = channelType;
	}

	public Integer getChannelPacketSize()
	{
		return channelPacketSize;
	}

	public void setChannelPacketSize(Integer channelPacketSize)
	{
		this.channelPacketSize = channelPacketSize;
	}

	public String getChannelSBCServer()
	{
		return channelSBCServer;
	}

	public void setChannelSBCServer(String channelSBCServer)
	{
		this.channelSBCServer = channelSBCServer;
	}

	public boolean getEnable()
	{
		return enable;
	}

	public void setEnable(boolean enable)
	{
		this.enable = enable;
	}

	public String getChannelVirtualExt()
	{
		return channelVirtualExt;
	}

	public void setChannelVirtualExt(String channelVirtualExt)
	{
		this.channelVirtualExt = channelVirtualExt;
	}

	public String[] getDepartmentIds()
	{
		return departmentIds;
	}

	public void setDepartmentIds(String[] departmentIds)
	{
		this.departmentIds = departmentIds;
	}


	@Override
	public String toString() {
		return "ChannelForm{" +
				"id=" + id +
				", beginExt='" + beginExt + '\'' +
				", endExt='" + endExt + '\'' +
				", computer='" + computer + '\'' +
				", channelIPAddr='" + channelIPAddr + '\'' +
				", channelMac='" + channelMac + '\'' +
				", ctiAppIPAddr='" + ctiAppIPAddr + '\'' +
				", cardType='" + cardType + '\'' +
				", channelType='" + channelType + '\'' +
				", channelPacketSize=" + channelPacketSize +
				", channelVirtualExt='" + channelVirtualExt + '\'' +
				", channelSBCServer='" + channelSBCServer + '\'' +
				", enable=" + enable +
				", departmentIds=" + Arrays.toString(departmentIds) +
				", pos=" + pos +
				'}';
	}
}
