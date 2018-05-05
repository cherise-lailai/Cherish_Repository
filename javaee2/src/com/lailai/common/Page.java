package com.lailai.common;

import java.util.List;

public class Page<T> {

	private int pageNo;
	
	private List<T> list;
	
	private int pageSize = 2;
	

	private long totalItemNumber;
	

	public long getTotalItemNumber() {
		return totalItemNumber;
	}

	public void setTotalItemNumber(long totalItemNumber) {
		this.totalItemNumber = totalItemNumber;
	}

	public Page(int pageNo) {
		super();
		this.pageNo = pageNo;
	}
	
	public int getPageNo() {
		if(pageNo > getTotalPageNumber())
			pageNo = getTotalPageNumber();
		if(pageNo <= 0)
			pageNo = 1;
		return pageNo;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setList(List<T> list) {
		this.list = list;
	}
	
	public List<T> getList() {
		return list;
	}
	
	public int getTotalPageNumber() {
		int totalPageNumber = (int) (totalItemNumber / pageSize);
		if(totalItemNumber % pageSize != 0)
			totalPageNumber++;
		return totalPageNumber;
	}
	
	public void setTotalItemNumber(int totalItemNumber) {
		this.totalItemNumber = totalItemNumber;
	}
	
	public boolean isHasNext() {
		if(getPageNo() < getTotalPageNumber())
			return true;
		return false;
	}
	
	public boolean isHasPrev() {
		if(getPageNo() ==1)
			return false;
		return true;
	}
	
	public int getPrevPage() {
		if(isHasPrev())
			return getPageNo()-1;
		return getPageNo();
	}
	
	public int getNextPage() {
		if(isHasNext())
			return getPageNo()+1;
		return getPageNo();
	}
}
