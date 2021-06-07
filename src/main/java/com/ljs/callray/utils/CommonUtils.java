package com.ljs.callray.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class CommonUtils
{
	/**
	 * 返回唯一标识
	 * 
	 * @return
	 */
	public static String getUUID()
	{
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 判断2个字符串是否相同，null与""任务是相同的
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean sameStrIgnoreNull(String s1, String s2)
	{
		if ((s1 == null && s2 == null) || (s1 == null && s2 != null && "".equals(s2.trim())) || (s2 == null && s1 != null && "".equals(s1.trim())) || (s1 != null && s2 != null && s1.trim().equals(s2.trim())))
		{
			return true;
		}
		return false;
	}

	/**
	 * 安静的关闭socket
	 * 
	 * @param socket
	 */
	public static void closeSocketQuietly(Socket socket)
	{
		if (socket != null)
		{
			if (socket.isConnected() && !socket.isClosed())
			{
				try
				{
					socket.shutdownInput();
					socket.shutdownOutput();
					try
					{
						Thread.sleep(5000);
					}
					catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					socket.close();
				}
				catch (IOException e)
				{
				}
			}
		}
	}

	public static String toString(String[] args, String separator)
	{
		if (args == null || args.length == 0)
		{
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < args.length; i++)
		{
			sb.append(args[i]);
			if (i + 1 != args.length)
			{
				sb.append(",");
			}
		}
		return sb.toString();
	}

	public static List<Long> StrArrayToLongList(String[] args)
	{
		List<Long> ids = new ArrayList<Long>();
		for (String arg : args)
		{
			try
			{
				Long id = Long.parseLong(arg);
				ids.add(id);
			}
			catch (NumberFormatException e)
			{
				e.printStackTrace();
			}
		}
		return ids;
	}

	public static String LongArrayToString(Long[] args, String ch)
	{
		if (args == null || args.length < 1)
		{
			return "";
		}
		StringBuilder result = new StringBuilder();
		for (Long l : args)
		{
			result.append(l + ch);
		}
		if (result.length() > 0)
		{
			result.deleteCharAt(result.length() - 1);
		}
		return result.toString();
	}

	public static <T> String listToString(Collection<T> args, String ch)
	{
		if (args == null || args.size() < 1)
		{
			return "";
		}
		StringBuilder result = new StringBuilder();
		for (T l : args)
		{
			result.append(l.toString() + ch);
		}
		if (result.length() > 0)
		{
			result.deleteCharAt(result.length() - 1);
		}
		return result.toString();
	}

	/**
	 * 把inorout转换成实际意义
	 * 
	 * @param inorout
	 * @return
	 */
//	public static String inorout2meaning(Integer inorout)
//	{
//		if (inorout == 1)
//		{
//			return GlobalizationUtil.GetMessage("select.callin");
//		}
//		else if (inorout == 0)
//		{
//			return GlobalizationUtil.GetMessage("select.callout");
//		}
//		return GlobalizationUtil.GetMessage("select.callunknown");
//	}

	/**
	 * 把ifback转换成实际意义
	 * 
	 * @param ifback
	 * @return
	 */
//	public static String ifback2meaning(int ifback)
//	{
//		String meaning = "";
//		switch (ifback) {
//			case 0:
//				meaning = GlobalizationUtil.GetMessage("select.nobaknodel");
//				break;
//			case 1:
//				meaning = GlobalizationUtil.GetMessage("select.baknodel");
//				break;
//			case 2:
//				meaning = GlobalizationUtil.GetMessage("select.bakdel");
//				break;
//			case 3:
//				meaning = GlobalizationUtil.GetMessage("select.nobakdel");
//				break;
//			default:
//				break;
//		}
//		return meaning;
//	}

	/**
	 * 把howtime数字类型转换成00:00:00的时间格式
	 * 
	 * @param howtime
	 * @return
	 */
	public static String howtime2str(Integer howtime)
	{
		if (howtime == null)
		{
			return "00:00:00";
		}
		int h = howtime / 3600;
		int m = (howtime % 3600) / 60;
		int s = (howtime % 3600) % 60;
		String hh = h >= 10 ? (h + "") : ("0" + h);
		String mm = m >= 10 ? (m + "") : ("0" + m);
		String ss = s >= 10 ? (s + "") : ("0" + s);
		return hh + ":" + mm + ":" + ss;
	}

	/**
	 * 判断时间是否在指定的时间范围内
	 * 
	 * @param date
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean timeInRange(Date date, Date min, Date max)
	{
		Calendar cDate = Calendar.getInstance();
		cDate.setTime(date);
		Calendar cMin = Calendar.getInstance();
		cMin.setTime(min);
		Calendar cMax = Calendar.getInstance();
		cMax.setTime(max);
		cDate.set(2016, 1, 1);
		cMin.set(2016, 1, 1);
		cMax.set(2016, 1, 1);
		if (cDate.after(cMin) && cDate.before(cMax))
		{
			return true;
		}
		return false;
	}

	/**
	 * 比较2个时间（不比较日期）
	 * 
	 * @param first
	 * @param second
	 * @return -1 后者大于前者，0相等 ， 1前者大于后者
	 */
	public static int compareTime(Date first, Date second)
	{
		Calendar cFirst = Calendar.getInstance();
		cFirst.setTime(first);
		Calendar cSecond = Calendar.getInstance();
		cSecond.setTime(second);
		cFirst.set(2016, 1, 1);
		cSecond.set(2016, 1, 1);
		return cFirst.compareTo(cSecond);
	}

	/**
	 * 把Date对象的日期至为当前日期
	 * 
	 * @param time
	 * @return
	 */
	public static Date timeAddNowDate(Date time)
	{
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		Calendar cTime = Calendar.getInstance();
		cTime.setTime(time);
		cTime.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
		return cTime.getTime();
	}

	/**
	 * 计算2个时间之间相差多少分钟,后一个时间减去前一个时间
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	public static long dateIntervalMinute(Date first, Date second)
	{
		long interval = second.getTime() - first.getTime();
		if (interval == 0)
		{
			return 0;
		}
		return interval / 60 / 1000;
	}
	/*
	 * 判断是否为整数
	 * 
	 * @param str 传入的字符串
	 * 
	 * @return 是整数返回true,否则返回false
	 */

	public static boolean isInteger(String str)
	{
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
	 * 
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNullOrEmpty(Object obj)
	{
		if (obj == null)
			return true;

		if (obj instanceof CharSequence)
			return ((CharSequence) obj).length() == 0;

		if (obj instanceof Collection)
			return ((Collection) obj).isEmpty();

		if (obj instanceof Map)
			return ((Map) obj).isEmpty();

		if (obj instanceof Object[])
		{
			Object[] object = (Object[]) obj;
			if (object.length == 0)
			{
				return true;
			}
			boolean empty = true;
			for (int i = 0; i < object.length; i++)
			{
				if (!isNullOrEmpty(object[i]))
				{
					empty = false;
					break;
				}
			}
			return empty;
		}
		return false;
	}
	
	public static String parseDate(String longStr)
	{
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(new Date(Long.parseLong(longStr)));
		}
		catch (Exception e)
		{
			return longStr;
		}
	}

	public static String parseDate(long longVal)
	{
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(new Date(longVal));
		}
		catch (Exception e)
		{
			return longVal + "";
		}
	}
	
	public static void deleteFile(Path file)
	{
		try
		{
			Files.delete(file);
		}
		catch (Exception e)
		{
		}
	}
	
	public static void deleteFilesBefore(Path pDir, int seconds, String suffix)
	{
		try
		{
			Date date = new Date(System.currentTimeMillis() - 1000 * seconds);
			File[] files = pDir.toFile().listFiles();
			for (File file : files)
			{
				if (file.isDirectory())
					continue;
				if (!file.getName().toLowerCase().endsWith(suffix))
					continue;
				if (new Date(file.lastModified()).before(date))
					file.delete();
			}
		}
		catch (Exception e)
		{
		}
	}
	public static String getJsonValue(String JsonString, String JsonId) {
        String JsonValue ="";
        if (JsonString == null || JsonString.trim().length() < 1) {
            return null;
        }
        try {
            JSONObject obj = JSONObject.parseObject(JsonString);
            JsonValue = obj.getString(JsonId);
        } catch (Exception e) {
           // e.printStackTrace();
        	JsonValue = "";
        }
        return JsonValue;
    }

	public static void main(String[] args)
	{
//		System.out.println(LongArrayToString(new Long[] { 1L, 2L }, ","));
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		try
//		{
//			Date date = sdf.parse("2016-01-02 11:00:00");
//			Date min = sdf.parse("2016-01-02 08:00:00");
//			Date max = sdf.parse("2015-12-25 18:00:00");
//			System.out.println(timeInRange(date, min, max));
//			System.out.println(compareTime(date, max));
//			System.out.println(sdf.format(timeAddNowDate(date)));
//			System.out.println(dateIntervalMinute(min, date));
//		}
//		catch (ParseException e)
//		{
//			e.printStackTrace();
//		}
		//String jString = "{\"agent\":\""+null+"\"}";
		//System.out.println(getJsonValue(jString, "agent"));
		System.out.println(System.getenv("SystemRoot"));
	}
}
