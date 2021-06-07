package com.ljs.callray.utils;

import com.ljs.callray.domain.TreeNode;
import com.ljs.callray.domain.TreeState;
import com.ljs.callray.entity.RightItem;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**权限解析操作
 * @author administrator
 * @date 2021/6/2 16:05
 */
public class RightItemUitls {

    public  static List<TreeNode> rightItemListToTreeNodeList(List<RightItem> rightItemList){
        List<TreeNode> treeNodes = new ArrayList<>();
        for (RightItem rightItem : rightItemList) {
            TreeNode treeNode = new TreeNode();
            treeNode.setId(rightItem.getId());
            treeNode.setLabel(rightItem.getDescp());
            treeNode.setTreeState(new TreeState());
            treeNodes.add(treeNode);
        }
        //设置所有
        TreeNode treeNode = new TreeNode();
        treeNode.setId(0L);
        treeNode.setLabel("All");
        TreeState rooTreeState = new TreeState();
        rooTreeState.setExpanded(true);
        treeNode.setTreeState(rooTreeState);
        treeNode.setChildren(treeNodes);
        List<TreeNode> roots = new ArrayList<TreeNode>();
        roots.add(treeNode);
        return roots;
    }
    /**
     * 权限解析
     *
     * @param rightArray
     * @param rightItem
     * @return
     */
    public static short[] GrantRight(short[] rightArray, int rightItem)
    {
        int iHigh = rightArray.length - 1;
        int posByte = rightItem / 8;
        int posBit = rightItem % 8;
        if (posByte > iHigh)
        {
            int Result = posByte + 1;
            short[] temprightarray = new short[Result];
            System.arraycopy(rightArray, 0, temprightarray, 0, rightArray.length);
            rightArray = new short[Result];
            System.arraycopy(temprightarray, 0, rightArray, 0, temprightarray.length);
            for (int i = iHigh + 1; i <= posByte; i++)
            {
                rightArray[i] = 0;
            }
        }
        rightArray[posByte] = (short) (rightArray[posByte] | (1 << (posBit)));
        return rightArray;
    }

    public static String ByteArrayRightToStringRight(short[] byteRight)
    {
        String Result = "";
        for (int i = 0; i < byteRight.length; i++)
        {
            Result = Result + byteRight[i] + ",";
        }
        return Result.substring(0, Result.length() - 1);
    }

    public static String showRightsToSaveRights(String showRights)
    {
        if (showRights != null && !"".equals(showRights.trim()))
        {
            String[] rightsArr = showRights.split(",");
            short[] rightShort = new short[] {};
            for (String str : rightsArr)
            {
                rightShort = RightItemUitls.GrantRight(rightShort, Integer.parseInt(str));
            }
            return RightItemUitls.ByteArrayRightToStringRight(rightShort);
        }
        return "";
    }

    /**
     * 是否存在某个权限
     *
     * @param rights
     *            班长的权限
     * @param rightitem
     *            特定权限码
     * @return
     */
    public static boolean isRightGranted(String rights, int rightitem)
    {
        if (rightitem < 0 || rights == null || "".equals(rights.trim()))
            return false;
        StringTokenizer strtokey = new StringTokenizer(rights, ",");
        byte[] rightArray = new byte[strtokey.countTokens()];
        int i = 0;
        while (strtokey.hasMoreTokens())
        {
            String s = strtokey.nextToken();
            int t = Integer.parseInt(s.trim());
            rightArray[i] = (byte) t;
            i++;
        }
        int posByte = rightitem / 8;
        int posBit = rightitem % 8;
        if (posByte > rightArray.length - 1)
        {
            return false;
        }
        return (rightArray[posByte] & (1 << posBit)) != 0;
    }
}
