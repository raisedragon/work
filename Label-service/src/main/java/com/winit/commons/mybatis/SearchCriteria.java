package com.winit.commons.mybatis;

public class SearchCriteria
{
	protected Page		page;
	protected Condition	condition;
	protected Order		order;
	public Page getPage()
	{
		return page;
	}
	public void setPage(Page page)
	{
		this.page = page;
	}
	public Condition getCondition()
	{
		return condition;
	}
	public void setCondition(Condition condition)
	{
		this.condition = condition;
	}
	public Order getOrder()
	{
		return order;
	}
	public void setOrder(Order order)
	{
		this.order = order;
	}
	
	
	
}
