package com.ljs.callray.domain;

import java.util.List;

/** 权限信息码
 * @author administrator
 * @date 2021/6/2 16:01
 */
public class TreeNode {
    private  Long id;
    private  String label;
    private  TreeState treeState;
    private List<TreeNode> children;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public TreeState getTreeState() {
        return treeState;
    }

    public void setTreeState(TreeState treeState) {
        this.treeState = treeState;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
