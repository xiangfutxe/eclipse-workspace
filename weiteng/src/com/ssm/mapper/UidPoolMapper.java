package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;


import com.ssm.dao.provider.UidPoolDynaSqlProvider;
import com.ssm.pojo.Dept;
import com.ssm.pojo.UidPool;
import com.ssm.utils.Constants;

public interface UidPoolMapper {
	
	@Select("select * from "+Constants.UIDPOOLTABLE+" where tag=0 order by id asc limit 3 for update ")
	List<UidPool> getUidList();
	
	@Select("select * from "+Constants.UIDPOOLTABLE+" where tag=0 order by id asc limit 1 for update ")
	UidPool getUid();
	
	@Select("select count(*) from "+Constants.UIDPOOLTABLE+" where tag=0 ")
	Integer count();
	
	@Select("select max(uid) from "+Constants.UIDPOOLTABLE+" ")
	Integer maxId();
	
	@Select("select * from "+Constants.UIDPOOLTABLE+" where id=#{id}")
	UidPool selectById(Integer id);
	
	@Select("select * from "+Constants.UIDPOOLTABLE+" where name=#{name}")
	UidPool selectByName(String name);
	
	@Delete("delete from "+Constants.UIDPOOLTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@Delete("delete from "+Constants.UIDPOOLTABLE+" where tag=#{tag}")
	int deleteByTag(Integer tag);
	
	@Update("update from "+Constants.UIDPOOLTABLE+" set tag=#{tag} where id=#{id}")
	void updateTag(Integer tag,Integer id);
	
	@UpdateProvider(type=UidPoolDynaSqlProvider.class,method="updateUidPool")
	void update(UidPool up);
	
	@InsertProvider(type=UidPoolDynaSqlProvider.class,method="insertAll")
	void insertAll(Map<String,Object> params);
	
	@InsertProvider(type=UidPoolDynaSqlProvider.class,method="insertUidPool")
	void save(UidPool up);

}
