package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.Param;
import com.ssm.utils.Constants;

public class ParamDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.PARAMTABLE);
			if(params.get("param")!=null){
				Param param = (Param) params.get("param");
				if(param.getParamName()!=null && !param.getParamName().equals("")){
					WHERE(" param_name LIKE CONCAT ('%',#{param.paramName},'%'");
				}
				if(param.getState()!=null ){
					WHERE(" state LIKE CONCAT ('%',#{param.state},'%'");
				}
			}
			}
		}.toString();
		if(params.get("pageModel")!=null){
			sql +=" order by id desc limit #{pageModel.startIndex},#{pageModel.pageSize} ";
		}
		return sql;
	}
	
	public String count( final Map<String,Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(Constants.PARAMTABLE);
				if(params.get("param")!=null){
					Param param = (Param) params.get("param");
					if(param.getParamName()!=null && !param.getParamName().equals("")){
						WHERE(" param_name LIKE CONCAT ('%',#{param.paramName},'%'");
					}
					if(param.getState()!=null ){
						WHERE(" state LIKE CONCAT ('%',#{param.state},'%'");
					}
				}
				
			}
		}.toString();
	}
	
	public String insertParam(final Param param){
		return new SQL(){
			{
				INSERT_INTO(Constants.PARAMTABLE);
				if(param.getParamName()!=null&& !param.getParamName().equals("")){
					VALUES("param_name","#{paramName}");
				}
				if(param.getUnit()!=null&& !param.getUnit().equals("")){
					VALUES("unit","#{unit}");
				}
				if(param.getAmount_1()!=null){
					VALUES("amount_1","#{amount_1}");
				}
				if(param.getAmount_2()!=null){
					VALUES("amount_2","#{amount_2}");
				}
				if(param.getAmount_3()!=null){
					VALUES("amount_3","#{amount_3}");
				}
				if(param.getAmount_4()!=null){
					VALUES("amount_4","#{amount_4}");
				}
				if(param.getAmount_5()!=null){
					VALUES("amount_5","#{amount_5}");
				}
				if(param.getAmount_6()!=null){
					VALUES("amount_6","#{amount_6}");
				}
				if(param.getAmount_7()!=null){
					VALUES("amount_7","#{amount_7}");
				}
				if(param.getAmount_8()!=null){
					VALUES("amount_8","#{amount_8}");
				}
				if(param.getAmount_9()!=null){
					VALUES("amount_9","#{amount_9}");
				}
				if(param.getAmount_10()!=null){
					VALUES("amount_10","#{amount_10}");
				}
				if(param.getEntryTime()!=null){
					VALUES("entry_time","#{entryTime}");
				}
				if(param.getEndTime()!=null){
					VALUES("end_time","#{endTime}");
				}
				if(param.getState()!=null){
					VALUES("state","#{state}");
				}
			}
		}.toString();
	}
	
	public String updateParam(final Param param){
		return new SQL(){
			{
				UPDATE(Constants.PARAMTABLE);
				if(param.getParamName()!=null&& !param.getParamName().equals("")){
					SET("param_name=#{paramName}");
				}
				if(param.getUnit()!=null&& !param.getUnit().equals("")){
					SET("unit=#{unit}");
				}
				if(param.getAmount_1()!=null){
					SET("amount_1=#{amount_1}");
				}
				if(param.getAmount_2()!=null){
					SET("amount_2=#{amount_2}");
				}
				if(param.getAmount_3()!=null){
					SET("amount_3=#{amount_3}");
				}
				if(param.getAmount_4()!=null){
					SET("amount_4=#{amount_4}");
				}
				if(param.getAmount_5()!=null){
					SET("amount_5=#{amount_5}");
				}
				if(param.getAmount_6()!=null){
					SET("amount_6=#{amount_6}");
				}
				if(param.getAmount_7()!=null){
					SET("amount_7=#{amount_7}");
				}
				if(param.getAmount_8()!=null){
					SET("amount_8=#{amount_8}");
				}
				if(param.getAmount_9()!=null){
					SET("amount_9=#{amount_9}");
				}
				if(param.getAmount_10()!=null){
					SET("amount_10=#{amount_10}");
				}
				if(param.getEndTime()!=null){
					SET("end_time=#{endTime}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	

}
