package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import com.ssm.dao.provider.BankAccountDynaSqlProvider;
import com.ssm.pojo.BankAccount;
import com.ssm.utils.Constants;

public interface BankAccountMapper {
		
	@SelectProvider(type=BankAccountDynaSqlProvider.class,method="selectWhitParam")
	List<BankAccount> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=BankAccountDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.BANKACCOUNTTABLE+" where state=1 ")
	List<BankAccount> selectAllBankAccount();
	
	
	@Select("select * from "+Constants.BANKACCOUNTTABLE+" where id=#{id}")
	BankAccount selectById(Integer id);
	
	
	@Select("select * from "+Constants.BANKACCOUNTTABLE+" where userId=#{userId}")
	List<BankAccount> selectByUserId(String userId);
	
	@Delete("delete from "+Constants.BANKACCOUNTTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@SelectProvider(type=BankAccountDynaSqlProvider.class,method="updateBankAccount")
	void update(BankAccount adr);
	
	@InsertProvider(type=BankAccountDynaSqlProvider.class,method="insertBankAccount")
	Integer save(BankAccount adr);


}
