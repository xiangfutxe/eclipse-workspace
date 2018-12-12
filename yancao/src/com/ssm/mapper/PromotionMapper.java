package com.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import com.ssm.dao.provider.PromotionDynaSqlProvider;
import com.ssm.pojo.Promotion;
import com.ssm.utils.Constants;

public interface PromotionMapper {
		
	@Select("select * from "+Constants.PROMOTIONTABLE)
	List<Promotion> selectAllPromotion();
	
	
	@Select("select * from "+Constants.PROMOTIONTABLE+" where id=#{id}")
	Promotion selectById(Integer id);
	
	
	@Select("select * from "+Constants.PROMOTIONTABLE+" where name=#{name} and state=1")
	Promotion selectByName(String name);
	
	@Delete("delete from "+Constants.PROMOTIONTABLE+" where id=#{id}")
	Integer deleteById(Integer id);
	
	@UpdateProvider(type=PromotionDynaSqlProvider.class,method="updatePromotion")
	Integer update(Promotion adr);
	
	@InsertProvider(type=PromotionDynaSqlProvider.class,method="insertPromotion")
	Integer save(Promotion adr);
	
	

}
