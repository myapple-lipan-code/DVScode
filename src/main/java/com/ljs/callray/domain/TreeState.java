package com.ljs.callray.domain;

public class TreeState
{
	private boolean checked = false;
	private boolean disabled = false;
	private boolean expanded = false;
	private boolean selected = false;

	public TreeState()
	{

	}

	public TreeState(boolean checked, boolean selected)
	{
		super();
		this.checked = checked;
		this.selected = selected;
	}

	public boolean isChecked()
	{
		return checked;
	}

	public void setChecked(boolean checked)
	{
		this.checked = checked;
	}

	public boolean isDisabled()
	{
		return disabled;
	}

	public void setDisabled(boolean disabled)
	{
		this.disabled = disabled;
	}

	public boolean isExpanded()
	{
		return expanded;
	}

	public void setExpanded(boolean expanded)
	{
		this.expanded = expanded;
	}

	public boolean isSelected()
	{
		return selected;
	}

	public void setSelected(boolean selected)
	{
		this.selected = selected;
	}
}
