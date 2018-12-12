package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.Admin;
import com.ssm.pojo.BorrowApply;
import com.ssm.utils.Constants;

public class BorrowApplyDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.BORROWAPPLYTABLE);
			if(params.get("contract")!=null){
				BorrowApply contract = (BorrowApply) params.get("contract");
				if(contract.getApplyName()!=null && !contract.getApplyName().equals("")){
					WHERE(" apply_name LIKE CONCAT ('%',#{contract.applyName},'%')");
				}
				if(contract.getAdminName()!=null && !contract.getAdminName().equals("")){
					WHERE(" admin_name LIKE CONCAT ('%',#{contract.adminName},'%')");
				}
				if(contract.getContractId()!=null && !contract.getContractId().equals("")){
					WHERE(" contract_id LIKE CONCAT ('%',#{contract.contractId},'%')");
				}
				if(contract.getContractName()!=null && !contract.getContractName().equals("")){
					WHERE(" contract_name LIKE CONCAT ('%',#{contract.contractName},'%')");
				}
				if(contract.getState()!=null){
					WHERE(" state LIKE CONCAT ('%',#{contract.state},'%')");
				}
				if(contract.getStartTime()!=null ){
					WHERE(" entry_time >=#{contract.startTime} ");
				}
				if(contract.getEndTime()!=null ){
					WHERE(" entry_time >=#{contract.endTime} ");
				}
			}
			}
		}.toString();
		if(params.get("pageModel")!=null){
			sql +=" limit #{pageModel.startIndex},#{pageModel.pageSize} ";
		}
		return sql;
	}
	
	public String count( final Map<String,Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(Constants.BORROWAPPLYTABLE);
				if(params.get("contract")!=null){
					BorrowApply contract = (BorrowApply) params.get("contract");
					if(contract.getAdminName()!=null && !contract.getAdminName().equals("")){
						WHERE(" admin_name LIKE CONCAT ('%',#{contract.adminName},'%')");
					}
					if(contract.getApplyName()!=null && !contract.getApplyName().equals("")){
						WHERE(" apply_name LIKE CONCAT ('%',#{contract.applyName},'%')");
					}
					if(contract.getContractId()!=null && !contract.getContractId().equals("")){
						WHERE(" contract_id LIKE CONCAT ('%',#{contract.contractId},'%')");
					}
					if(contract.getContractName()!=null && !contract.getContractName().equals("")){
						WHERE(" contract_name LIKE CONCAT ('%',#{contract.contractName},'%')");
					}
					if(contract.getState()!=null){
						WHERE(" state LIKE CONCAT ('%',#{contract.state},'%')");
					}
					if(contract.getStartTime()!=null ){
						WHERE(" entry_time >=#{contract.startTime} ");
					}
					if(contract.getEndTime()!=null ){
						WHERE(" entry_time >=#{contract.endTime} ");
					}
				}
				
			}
		}.toString();
	}
	
	public String insert(final BorrowApply contract){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.BORROWAPPLYTABLE);
				if(contract.getApplyId()!=null&& !contract.getApplyId().equals("")){
					VALUES("apply_id","#{applyId}");
				}
				if(contract.getApplyName()!=null&& !contract.getApplyName().equals("")){
					VALUES("apply_name","#{applyName}");
				}
				if(contract.getAdminName()!=null&& !contract.getAdminName().equals("")){
					VALUES("admin_name","#{adminName}");
				}
				if(contract.getContractId()!=null&& !contract.getContractId().equals("")){
					VALUES("contract_id","#{contractId}");
				}
				if(contract.getContractName()!=null&& !contract.getContractName().equals("")){
					VALUES("contract_name","#{contractName}");
				}
				if(contract.getCode()!=null & !contract.getCode().equals("")){
					VALUES("code","#{code}");
				}
				if(contract.getState()!=null){
					VALUES("state","#{state}");
				}
				if(contract.getStartTime()!=null){
					VALUES("start_time","#{startTime}");
				}
				if(contract.getEndTime()!=null){
					VALUES("end_time","#{endTime}");
				}
				if(contract.getEntryTime()!=null){
					VALUES("entry_time","#{entryTime}");
				}
				
			}
		}.toString();
	}
	
	public String update(final BorrowApply contract){
		String sql ="update "+Constants.BORROWAPPLYTABLE +" set ";
		String str="";
			
				if(contract.getAdminName()!=null&& !contract.getAdminName().equals("")){
					if(str.equals(""))
					 str+=" admin_name='"+contract.getAdminName()+"'";
					else str+=",admin_name='"+contract.getAdminName()+"'";
				}
				if(contract.getContractId()!=null&& !contract.getContractId().equals("")){
					if(str.equals(""))
						 str+=" contract_id='"+contract.getContractId()+"'";
						else str+=",contract_id='"+contract.getContractId()+"'";
					
				}
				if(contract.getContractName()!=null&& !contract.getContractName().equals("")){
					if(str.equals(""))
						 str+=" contract_name='"+contract.getContractName()+"'";
						else str+=",contract_name='"+contract.getContractName()+"'";
				}
				if(contract.getCode()!=null){
					if(str.equals(""))
						 str+=" code='"+contract.getCode()+"'";
						else str+=",code='"+contract.getCode()+"'";
				}
				if(contract.getState()!=null){
					if(str.equals(""))
						 str+=" state='"+contract.getState()+"'";
						else str+=",state='"+contract.getState()+"'";
				}
				if(contract.getReviewName()!=null){
					if(str.equals(""))
						 str+=" review_name='"+contract.getReviewName()+"'";
						else str+=",review_name='"+contract.getReviewName()+"'";
				}
				if(contract.getConfirmName()!=null){
					if(str.equals(""))
						 str+=" confirm_name='"+contract.getConfirmName()+"'";
						else str+=",confirm_name='"+contract.getConfirmName()+"'";
				}
				if(contract.getEndName()!=null){
					if(str.equals(""))
						 str+=" end_name='"+contract.getEndName()+"'";
						else str+=",end_name='"+contract.getEndName()+"'";
				}
				if(contract.getConfirmTime()!=null){
					if(str.equals(""))
						 str+=" confirm_time='"+contract.getConfirmTime()+"'";
						else str+=",confirm_time='"+contract.getConfirmTime()+"'";
				}
				if(contract.getStartTime()!=null){
					if(str.equals(""))
						 str+=" start_time='"+contract.getStartTime()+"'";
						else str+=",start_time='"+contract.getStartTime()+"'";
				}
				if(contract.getEndTime()!=null){
					if(str.equals(""))
						 str+=" end_time='"+contract.getEndTime()+"'";
						else str+=",end_time='"+contract.getEndTime()+"'";
				}
				if(contract.getEntryTime()!=null){
					if(str.equals(""))
						 str+=" entry_time='"+contract.getEntryTime()+"'";
						else str+=",entry_time='"+contract.getEntryTime()+"'";
					
				}
				sql = sql+str+" where id='"+contract.getId()+"'";
				return sql;
	}
	
	

}
