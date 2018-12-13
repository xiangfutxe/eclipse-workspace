package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.ProductType;
import com.ssm.utils.Constants;

public class ProductTypeDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.PRODUCTTYPETABLE);
			if(params.get("productType")!=null){
				 ProductType productType = (ProductType) params.get("productType");
				if(productType.getTypeName()!=null && !productType.getTypeName().equals("")){
					WHERE(" typeName LIKE CONCAT ('%',#{productType.typeName},'%')");
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
				FROM(Constants.PRODUCTTYPETABLE);
				if(params.get("productType")!=null){
					 ProductType productType = (ProductType) params.get("productType");
					if(productType.getTypeName()!=null && !productType.getTypeName().equals("")){
						WHERE(" typeName LIKE CONCAT ('%',#{productType.typeName},'%')");
					}
					
				}
				
			}
		}.toString();
	}
	
	public String insertProductType(final ProductType productType){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.PRODUCTTYPETABLE);
				if(productType.getTypeName()!=null&& !productType.getTypeName().equals("")){
					VALUES("typeName","#{typeName}");
				}
				if(productType.getState()!=null&& !productType.getState().equals("")){
					VALUES("state","#{state}");
				}
				if(productType.getEntryTime()!=null ){
					VALUES("entryTime","#{entryTime}");
				}
				if(productType.getEndTime()!=null ){
					VALUES("endTime","#{endTime}");
				}
			}
		}.toString();
	}
	
	public String updateProductType(final ProductType productType){
		return new SQL(){
			{
				UPDATE(Constants.PRODUCTTYPETABLE);
				if(productType.getTypeName()!=null&& !productType.getTypeName().equals("")){
					SET("typeName=#{typeName}");
				}
				if(productType.getState()!=null&& !productType.getState().equals("")){
					SET("state=#{state}");
				}
				if(productType.getEntryTime()!=null ){
					SET("entryTime=#{entryTime}");
				}
				if(productType.getEndTime()!=null ){
					SET("endTime=#{endTime}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	

}
