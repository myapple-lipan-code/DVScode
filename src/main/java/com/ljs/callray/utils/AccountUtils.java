package com.ljs.callray.utils;

import com.ljs.callray.entity.Account;

public class AccountUtils
{

	/**
	 * 是否存在默认部门
	 * 
	 * @param desps
	 * @return
	 */
	public static boolean hasDefaultDepartment(String[] desps)
	{
		if (desps == null || desps.length < 1)
		{
			return false;
		}
		for (String s : desps)
		{
			if ("0".equals(s))
			{
				return true;
			}
		}
		return false;
	}

	private static Account defalutDepartment = null;

	/**
	 * (潜规则)默认的部门、在数据库中不存在，但是id为0
	 * 
	 * @return
	 */
	public static Account getDefaultDepartment()
	{
		// if (defalutDepartment == null) {
		synchronized (AccountUtils.class)
		{
			// if (defalutDepartment == null) {
			defalutDepartment = new Account();
			defalutDepartment.setId(0L);
			// defalutDepartment.setName("默认部门");
			// defalutDepartment.setAlias("默认部门");
			defalutDepartment.setName("默认部门");
			defalutDepartment.setAlias("默认部门");
			defalutDepartment.setProperty(Account.DEPARTMENT);
		}
		// }
		// }
		return defalutDepartment;
	}
}
