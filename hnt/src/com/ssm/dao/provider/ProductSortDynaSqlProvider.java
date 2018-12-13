package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.ProductSort;
import com.ssm.utils.Constants;

public class ProductSortDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.PRODUCTSORTTABLE);
			if(params.get("productSort")!=null){
				ProductSort productSort = (ProductSort) params.get("productSort");
				if(productSort.getName()!=null && !productSort.getName().equals("")){
					WHERE(" name LIKE CONCAT ('%',#{productSort.name},'%')");
				}
			}
			}
		}.toString();
		if(params.get("pageModel")!=null){
			sql +=" limit #{pageModel.startIndex},#{pageModel.pageSize}";
		}
		return sql;
	}
	
	public String count( final Map<String,Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(Constants.PRODUCTSORTTABLE);
				if(params.get("productSort")!=null){
					ProductSort productSort = (ProductSort) params.get("productSort");
					if(productSort.getName()!=null && !productSort.getName().equals("")){
						WHERE(" name LIKE CONCAT ('%',#{productSort.name},'%')");
					}
					
				}
				
			}
		}.toString();
	}
	
	public String insertProductSort(final ProductSort productSort){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.PRODUCTSORTTABLE);
				if(productSort.getName()!=null&& !productSort.getName().equals("")){
					VALUES("name","#{name}");
				}
				if(productSort.getState()!=null&& !productSort.getState().equals("")){
					VALUES("state","#{state}");
				}
				if(productSort.getEntryTime()!=null ){
					VALUES("entryTime","#{entryTime}");
				}
				if(productSort.getEndTime()!=null ){
					VALUES("endTime","#{endTime}");
				}
			}
		}.toString();
	}
	
	public String updateProductSort(final ProductSort productSort){
		return new SQL(){
			{
				UPDATE(Constants.PRODUCTSORTTABLE);
				if(productSort.getName()!=null&& !productSort.getName().equals("")){
					SET("name=#{name}");
				}
				if(productSort.getState()!=null&& !productSort.getState().equals("")){
					SET("state=#{state}");
				}
				if(productSort.getEntryTime()!=null ){
					SET("entryTime=#{entryTime}");
				}
				if(productSort.getEndTime()!=null ){
					SET("endTime=#{endTime}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	

}
