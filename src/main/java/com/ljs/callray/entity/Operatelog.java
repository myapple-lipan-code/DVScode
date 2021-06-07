package com.ljs.callray.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 操作日志
 */
@TableName("tx_operationlog")
public class Operatelog
{

	public static final int OPERATETYPE_PLAY_LOCAL = 0x0001; // 本地放音
	public static final int OPERATETYPE_PLAY_PHONE = 0x0002; // 电话放音
	public static final int OPERATETYPE_PLAY_TRANS = 0x0003; // 播放
	public static final int OPERATETYPE_PLAY_BUGLE = 0x0004; // 卡上音频口放音
	public static final int OPERATETYPE_LISTEN_PHONE = 0x0011; // 电话监听
	public static final int OPERATETYPE_LISTEN_TRANS = 0x0012; // 监听
	public static final int OPERATETYPE_CONFERENCE = 0x0021; // 强插
	public static final int OPERATETYPE_INTERRUPT = 0x0031; // 强拆
	public static final int OPERATETYPE_QUERY = 0x0040; // 查询
	public static final int OPERATETYPE_STAT = 0x0050; // 统计
	public static final int OPERATETYPE_DISTILL = 0x0060; // 下载
	public static final int OPERATETYPE_REPORT = 0x0070; // 报表
	public static final int OPERATETYPE_LOCAL_LOGIN = 0x0080; // 本地登陆
	public static final int OPERATETYPE_WIZARD_LOGIN = 0x0081; // 向导登陆
	public static final int OPERATETYPE_VALIDATE_LOGIN = 0x0082; // 登陆
	public static final int OPERATETYPE_LOGOUT = 0x0090; // 退录
	public static final int OPERATETYPE_UPDATE_COLUMNS = 0x0100; // 更新业务信息

	@TableId(type = IdType.AUTO,value = "recordlsh")
	private Long recordlsh;
	@TableField("operator")
	private String operator;
	@TableField("operatestarttime")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date operateStarttime;
	@TableField("operateendtime")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date operateEndtime;
	@TableField("operatetype")
	private int operateType;
	@TableField("howtime")
	private int howtime;
	@TableField("operateCount")
	private int operateCount;
	@TableField("starttime")
	private Date starttime;
	@TableField("endtime")
	private Date endtime;
	@TableField("agent")
	private String agent;
	@TableField("extention")
	private String extention;
	@TableField("department")
	private String department;
	@TableField("calltype")
	private int calltype;
	@TableField("callid")
	private String callid;
	@TableField("calledid")
	private String calledid;
	@TableField("howtimemin")
	private int howtimeMin;
	@TableField("howtimemax")
	private int howtimeMax;
	@TableField("recorduniqueid")
	private String recordUniqueid;
	@TableField("ortherInfo")
	private String ortherInfo;

	public Operatelog()
	{
		super();
	}

	public Operatelog(String operator, Date operateStarttime, int operateType, String ortherInfo)
	{
		super();
		this.operator = operator;
		this.operateStarttime = operateStarttime;
		this.operateEndtime = operateStarttime;
		this.operateType = operateType;
		this.ortherInfo = ortherInfo;
	}

	public Long getRecordlsh()
	{
		return recordlsh;
	}

	public void setRecordlsh(Long recordlsh)
	{
		this.recordlsh = recordlsh;
	}

	public String getOperator()
	{
		return operator;
	}

	public void setOperator(String operator)
	{
		this.operator = operator;
	}

	public Date getOperateStarttime()
	{
		return operateStarttime;
	}

	public void setOperateStarttime(Date operateStarttime)
	{
		this.operateStarttime = operateStarttime;
	}

	public Date getOperateEndtime()
	{
		return operateEndtime;
	}

	public void setOperateEndtime(Date operateEndtime)
	{
		this.operateEndtime = operateEndtime;
	}

	public int getOperateType()
	{
		return operateType;
	}

	public void setOperateType(int operateType)
	{
		this.operateType = operateType;
	}

	public int getHowtime()
	{
		return howtime;
	}

	public void setHowtime(int howtime)
	{
		this.howtime = howtime;
	}

	public int getOperateCount()
	{
		return operateCount;
	}

	public void setOperateCount(int operateCount)
	{
		this.operateCount = operateCount;
	}

	public Date getStarttime()
	{
		return starttime;
	}

	public void setStarttime(Date starttime)
	{
		this.starttime = starttime;
	}

	public Date getEndtime()
	{
		return endtime;
	}

	public void setEndtime(Date endtime)
	{
		this.endtime = endtime;
	}

	public String getAgent()
	{
		return agent;
	}

	public void setAgent(String agent)
	{
		this.agent = agent;
	}

	public String getExtention()
	{
		return extention;
	}

	public void setExtention(String extention)
	{
		this.extention = extention;
	}

	public String getDepartment()
	{
		return department;
	}

	public void setDepartment(String department)
	{
		this.department = department;
	}

	public int getCalltype()
	{
		return calltype;
	}

	public void setCalltype(int calltype)
	{
		this.calltype = calltype;
	}

	public String getCallid()
	{
		return callid;
	}

	public void setCallid(String callid)
	{
		this.callid = callid;
	}

	public String getCalledid()
	{
		return calledid;
	}

	public void setCalledid(String calledid)
	{
		this.calledid = calledid;
	}

	public int getHowtimeMin()
	{
		return howtimeMin;
	}

	public void setHowtimeMin(int howtimeMin)
	{
		this.howtimeMin = howtimeMin;
	}

	public int getHowtimeMax()
	{
		return howtimeMax;
	}

	public void setHowtimeMax(int howtimeMax)
	{
		this.howtimeMax = howtimeMax;
	}

	public String getRecordUniqueid()
	{
		return recordUniqueid;
	}

	public void setRecordUniqueid(String recordUniqueid)
	{
		this.recordUniqueid = recordUniqueid;
	}

	public String getOrtherInfo()
	{
		return ortherInfo;
	}

	public void setOrtherInfo(String ortherInfo)
	{
		this.ortherInfo = ortherInfo;
	}
}
