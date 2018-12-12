package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.Admin;
import com.ssm.pojo.Contract;
import com.ssm.utils.Constants;

public class ContractDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.CONTRACTTABLE);
			if(params.get("contract")!=null){
				Contract contract = (Contract) params.get("contract");
				if(contract.getAdminName()!=null && !contract.getAdminName().equals("")){
					WHERE(" admin_name LIKE CONCAT ('%',#{contract.adminName},'%')");
				}
				if(contract.getContractId()!=null && !contract.getContractId().equals("")){
					WHERE(" contract_id LIKE CONCAT ('%',#{contract.contractId},'%')");
				}
				if(contract.getContractName()!=null && !contract.getContractName().equals("")){
					WHERE(" contract_name LIKE CONCAT ('%',#{contract.contractName},'%')");
				}
				if(contract.getRank()!=null){
					WHERE(" rank LIKE CONCAT ('%',#{contract.rank},'%')");
				}
				if(contract.getDept()!=null && !contract.getDept().equals("")){
					WHERE(" dept LIKE CONCAT ('%',#{contract.dept},'%')");
				}
				if(contract.getLevel()!=null){
					WHERE(" level LIKE CONCAT ('%',#{contract.level},'%')");
				}
				if(contract.getState()!=null){
					WHERE(" state LIKE CONCAT ('%',#{contract.state},'%')");
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
				FROM(Constants.CONTRACTTABLE);
				if(params.get("contract")!=null){
					Contract contract = (Contract) params.get("contract");
					if(contract.getAdminName()!=null && !contract.getAdminName().equals("")){
						WHERE(" admin_name LIKE CONCAT ('%',#{contract.adminName},'%')");
					}
					if(contract.getContractId()!=null && !contract.getContractId().equals("")){
						WHERE(" contract_id LIKE CONCAT ('%',#{contract.contractId},'%')");
					}
					if(contract.getContractName()!=null && !contract.getContractName().equals("")){
						WHERE(" contract_name LIKE CONCAT ('%',#{contract.contractName},'%')");
					}
					if(contract.getRank()!=null){
						WHERE(" rank LIKE CONCAT ('%',#{contract.rank},'%')");
					}
					if(contract.getLevel()!=null ){
						WHERE(" level LIKE CONCAT ('%',#{contract.level},'%')");
					}
					if(contract.getDept()!=null && !contract.getDept().equals("")){
						WHERE(" dept LIKE CONCAT ('%',#{contract.dept},'%')");
					}
					if(contract.getState()!=null){
						WHERE(" state LIKE CONCAT ('%',#{contract.state},'%')");
					}
				}
				
			}
		}.toString();
	}
	
	public String insert(final Contract contract){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.CONTRACTTABLE);
				if(contract.getAdminId()!=null){
					VALUES("admin_id","#{adminId}");
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
				if(contract.getPartyA()!=null&& !contract.getPartyA().equals("")){
					VALUES("party_a","#{partyA}");
				}
				if(contract.getPartyB()!=null&& !contract.getPartyB().equals("")){
					VALUES("party_b","#{partyB}");
				}
				if(contract.getLevel()!=null){
					VALUES("level","#{level}");
				}
				if(contract.getRank()!=null){
					VALUES("rank","#{rank}");
				}
				if(contract.getBorrowNum()!=null){
					VALUES("borrow_num","#{borrowNum}");
				}
				if(contract.getBorrowTag()!=null){
					VALUES("borrow_tag","#{borrowTag}");
				}
				if(contract.getState()!=null){
					VALUES("state","#{state}");
				}
				if(contract.getDept()!=null){
					VALUES("dept","#{dept}");
				}
				if(contract.getPdfUrl()!=null){
					VALUES("pdf_url","#{pdfUrl}");
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
	
	public String update(final Contract contract){
		String sql =new SQL(){
			{
				UPDATE(Constants.CONTRACTTABLE);
				if(contract.getAdminId()!=null){
					SET("admin_id=#{adminId}");
				}
				if(contract.getAdminName()!=null&& !contract.getAdminName().equals("")){
					SET("admin_name=#{adminName}");
				}
				if(contract.getContractId()!=null&& !contract.getContractId().equals("")){
					SET("contract_id=#{contractId}");
				}
				if(contract.getContractName()!=null&& !contract.getContractName().equals("")){
					SET("contract_name=#{contractName}");
				}
				if(contract.getCode()!=null & !contract.getCode().equals("")){
					SET("code=#{code}");
				}
				if(contract.getPartyA()!=null&& !contract.getPartyA().equals("")){
					SET("party_a=#{partyA}");
				}
				if(contract.getPartyB()!=null&& !contract.getPartyB().equals("")){
					SET("party_b=#{partyB}");
				}
				if(contract.getLevel()!=null){
					SET("level=#{level}");
				}
				if(contract.getRank()!=null){
					SET("rank=#{rank}");
				}
				if(contract.getState()!=null){
					SET("state=#{state}");
				}
				if(contract.getDept()!=null){
					SET("dept=#{dept}");
				}
				if(contract.getBorrowNum()!=null){
					SET("borrow_num=#{borrowNum}");
				}
				if(contract.getBorrowTag()!=null){
					SET("borrow_tag=#{borrowTag}");
				}
				if(contract.getPdfUrl()!=null){
					SET("pdf_url=#{pdfUrl}");
				}
				if(contract.getStartTime()!=null){
					SET("start_time=#{startTime}");
				}
				if(contract.getEndTime()!=null){
					SET("end_time=#{endTime}");
				}
				if(contract.getEntryTime()!=null){
					SET("entry_time=#{entryTime}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
		System.out.println(sql);
		return sql;
	}
	
	

}
