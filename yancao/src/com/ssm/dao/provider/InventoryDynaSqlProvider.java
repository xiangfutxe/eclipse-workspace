package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import com.ssm.pojo.Inventory;
import com.ssm.utils.Constants;

public class InventoryDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.INVENTORYTABLE);
			if(params.get("inventory")!=null){
				Inventory inventory = (Inventory) params.get("inventory");
				if(inventory.getName()!=null && !inventory.getName().equals("")){
					WHERE(" inventoryName LIKE CONCAT ('%',#{inventory.inventoryName},'%')");
				}
				if(inventory.getState()!=null && !inventory.getState().equals("")){
					WHERE(" state LIKE CONCAT ('%',#{inventory.state},'%')");
				}
			
			}
			}
		}.toString();
		if(params.get("pageModel")!=null){
			sql +=" order by id desc limit #{pageModel.startIndex},#{pageModel.pageSize}";
		}
		return sql;
	}
	
	public String count( final Map<String,Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(Constants.INVENTORYTABLE);
				if(params.get("inventory")!=null){
					Inventory inventory = (Inventory) params.get("inventory");
					if(inventory.getName()!=null && !inventory.getName().equals("")){
						WHERE(" name LIKE CONCAT ('%',#{inventory.name},'%')");
					}
					if(inventory.getState()!=null && !inventory.getState().equals("")){
						WHERE(" state LIKE CONCAT ('%',#{inventory.state},'%')");
					}
				
				}
				}
		}.toString();
	}
	
	public String insertInventory(final Inventory inventory){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.INVENTORYTABLE);
				if(inventory.getName()!=null&& !inventory.getName().equals("")){
					VALUES("name","#{name}");
				}
				if(inventory.getAddress()!=null&& !inventory.getAddress().equals("")){
					VALUES("address","#{address}");
				}
				if(inventory.getLinkman()!=null&& !inventory.getLinkman().equals("")){
					VALUES("linkman","#{linkman}");
				}
				if(inventory.getPhone()!=null&& !inventory.getPhone().equals("")){
					VALUES("phone","#{phone}");
				}
				if(inventory.getState()!=null){
					VALUES("state","#{state}");
				}
				if(inventory.getEntryTime()!=null){
					VALUES("entry_time","#{entryTime}");
				}
				if(inventory.getEndTime()!=null){
					VALUES("end_time","#{endTime}");
				}
			}
		}.toString();
	}
	
	public String updateInventory(final Inventory inventory){
		return new SQL(){
			{
				UPDATE(Constants.INVENTORYTABLE);
				if(inventory.getName()!=null&& !inventory.getName().equals("")){
					SET("name=#{name}");
				}
				if(inventory.getAddress()!=null&& !inventory.getAddress().equals("")){
					SET("address=#{address}");
				}
				if(inventory.getLinkman()!=null&& !inventory.getLinkman().equals("")){
					SET("linkman=#{linkman}");
				}
				if(inventory.getPhone()!=null&& !inventory.getPhone().equals("")){
					SET("phone=#{phone}");
				}
				if(inventory.getState()!=null){
					SET("state=#{state}");
				}
				if(inventory.getEndTime()!=null){
					SET("end_time=#{endTime}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	

}
