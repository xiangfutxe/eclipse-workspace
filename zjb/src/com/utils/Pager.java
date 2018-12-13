﻿package com.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@SuppressWarnings("rawtypes")
public class Pager {
	protected int[] pageSizeList = {5,10,20,50,100,200,300,500};
	protected int pageSize = 10; //每页显示行数
	protected int pageNo =1;  //当前页
	protected int rowCount =0; //总行数
	protected int pageCount = 0;//总页数
	protected int startIndex=1;
	protected int endIndex =1;
	protected int firstPageNo=1;
	protected int prePageNo = 1;
	protected int nextPageNo =1;
	protected int lastPageNo =1;
	protected Collection resultList;
	protected double[] sum;
	
	public Pager(){
		
	}
	
	public Pager(int pageSize,int pageNo,int rowCount,Collection resultList){
		this.pageSize = pageSize;
		this.pageNo = pageNo;
		this.rowCount = rowCount;
		this.resultList = resultList;
	}
	
	    
	  

	public Object[] getPageSizeIndexs(){
		   List<String> result = new ArrayList<String>(pageSizeList.length);
		   for(int i=0;i<pageSizeList.length;i++){
			   result.add(String.valueOf(pageSizeList[i]));
		   }
		   Object[] indexs = (result.toArray());
		   return indexs;
	   }
	   
	public Object[] getPageNoIndexs(){
		   List<String> result = new ArrayList<String>(pageCount);
		   for(int i = 0;i<pageCount;i++){
			   result.add(String.valueOf(i+1));
		   }
		   Object[] indexs = (result.toArray());
		   return indexs;
	   }

	public Collection getResultList() {
		return resultList;
	}

	public void setResultList(Collection resultList) {
		this.resultList = resultList;
	}

	public int[] getPageSizeList() {
		return pageSizeList;
	}

	public void setPageSizeList(int[] pageSizeList) {
		this.pageSizeList = pageSizeList;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		this.pageNo = this.pageNo<=0?1:this.pageNo;
		this.pageNo = this.pageNo>=this.getPageCount()?this.getPageCount():this.pageNo;
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getRowCount() {
		this.rowCount = this.rowCount<=0?0:this.rowCount;
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getPageCount() {
		if(this.getRowCount()<=0){
			pageCount = 0;
		}else{
			pageCount=(this.getRowCount()-1)/this.getPageSize()+1;
		}
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getStartIndex() {
		return (this.getPageNo()-1)*this.getPageSize();
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public int getFirstPageNo() {
		return firstPageNo;
	}

	public void setFirstPageNo(int firstPageNo) {
		this.firstPageNo = firstPageNo;
	}

	public int getPrePageNo() {
		return prePageNo;
	}

	public void setPrePageNo(int prePageNo) {
		this.prePageNo = prePageNo;
	}

	public int getNextPageNo() {
		return nextPageNo;
	}

	public void setNextPageNo(int nextPageNo) {
		this.nextPageNo = nextPageNo;
	}

	public int getLastPageNo() {
		return lastPageNo;
	}

	public void setLastPageNo(int lastPageNo) {
		this.lastPageNo = lastPageNo;
	}

	public double[] getSum() {
		return sum;
	}

	public void setSum(double[] sum) {
		this.sum = sum;
	}
	

	

}
