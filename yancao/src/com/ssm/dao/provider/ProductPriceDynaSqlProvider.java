package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.Product;
import com.ssm.pojo.ProductPrice;
import com.ssm.utils.Constants;

public class ProductPriceDynaSqlProvider {
	
	String re_sql = " pp.id as id,pp.p_id  as pid,pp.product_id as productId,pt.product_name as productName"
			+ ",pt.product_type as productType,pt.product_sort as product_sort,pt.specification as specification"
			+ ",pt.price as price,pt.num as num,pt.total_num as totalNum,pt.state as state,pt.features as features"
			+ ",pp.price1 as price1,pp.price2 as price2,pp.price3 as price3,pp.price4 as price4,pp.price5 as price5,pp.price6 as price6,pp.price7 as price7"
			+ ",pp.cash1 as cash1,pp.cash2 as cash2,pp.cash3 as cash3,pp.cash4 as cash4,pp.cash5 as cash5,pp.cash6 as cash6,pp.cash7 as cash7"
			+ ",pp.cash_num_1 as cashNum1,pp.cash_num_2 as cashNum2,pp.cash_num_3 as cashNum3,pp.cash_num_4 as cashNum4,pp.cash_num_5 as cashNum5,pp.cash_num_6 as cashNum6,pp.cash_num_7 as cashNum7"
			+ ",pp.integral1 as integral1,pp.integral2 as integral2,pp.integral3 as integral3,pp.integral4 as integral4,pp.integral5 as integral5,pp.integral6 as integral6,pp.integral7 as integral7"
			+ ",pp.entry_time as entryTime,pp.end_time as endTime ";
String sqls = "select "+re_sql+" from "+Constants.PRODUCTTABLE+" pt LEFT JOIN "+Constants.PRODUCTPRICETABLE+" pp inf ON pt.id=pp.p_id ";

	
	public String insert(final ProductPrice product){
		
		String sql = new SQL(){
			{
				INSERT_INTO(Constants.PRODUCTPRICETABLE);
				if(product.getPid()!=null){
					VALUES("p_id","#{pid}");
				}
				if(product.getProductId()!=null&& !product.getProductId().equals("")){
					VALUES("product_id","#{productId}");
				}
				if(product.getEntryTime()!=null ){
					VALUES("entry_time","#{entryTime}");
				}
				if(product.getEndTime()!=null ){
					VALUES("end_time","#{endTime}");
				}
			}
		}.toString();
		return sql;
	}
	
	public String update(final ProductPrice product){
		String sql = new SQL(){
			{
				UPDATE(Constants.PRODUCTPRICETABLE);
				if(product.getPrice1()!=null){
					SET("price1=#{price1}");
				}
				if(product.getPrice2()!=null){
					SET("price2=#{price2}");
				}
				if(product.getPrice3()!=null){
					SET("price3=#{price3}");
				}
				if(product.getPrice4()!=null){
					SET("price4=#{price4}");
				}
				if(product.getPrice5()!=null){
					SET("price5=#{price5}");
				}
				if(product.getPrice6()!=null){
					SET("price6=#{price6}");
				}
				if(product.getPrice7()!=null){
					SET("price7=#{price7}");
				}
				if(product.getCash1()!=null){
					SET("cash1=#{cash1}");
				}
				if(product.getCash2()!=null){
					SET("cash2=#{cash2}");
				}
				if(product.getCash3()!=null){
					SET("cash3=#{cash3}");
				}
				if(product.getCash4()!=null){
					SET("cash4=#{cash4}");
				}
				if(product.getCash5()!=null){
					SET("cash5=#{cash5}");
				}
				if(product.getCash6()!=null){
					SET("cash6=#{cash6}");
				}
				if(product.getCash7()!=null){
					SET("cash7=#{cash7}");
				}
				if(product.getCashNum1()!=null){
					SET("cash_num_1=#{cashNum1}");
				}
				if(product.getCashNum2()!=null){
					SET("cash_num_2=#{cashNum2}");
				}
				if(product.getCashNum3()!=null){
					SET("cash_num_3=#{cashNum3}");
				}
				if(product.getCashNum4()!=null){
					SET("cash_num_4=#{cashNum4}");
				}
				if(product.getCashNum5()!=null){
					SET("cash_num_5=#{cashNum5}");
				}
				if(product.getCashNum6()!=null){
					SET("cash_num_6=#{cashNum6}");
				}
				if(product.getCashNum7()!=null){
					SET("cash_num_7=#{cashNum7}");
				}
				if(product.getIntegral1()!=null){
					SET("integral1=#{integral1}");
				}
				if(product.getIntegral2()!=null){
					SET("integral2=#{integral2}");
				}
				if(product.getIntegral3()!=null){
					SET("integral3=#{integral3}");
				}
				if(product.getIntegral4()!=null){
					SET("integral4=#{integral4}");
				}
				if(product.getIntegral5()!=null){
					SET("integral5=#{integral5}");
				}
				if(product.getIntegral6()!=null){
					SET("integral6=#{integral6}");
				}
				if(product.getIntegral7()!=null){
					SET("integral7=#{integral7}");
				}
				
				if(product.getEndTime()!=null ){
					SET("end_time=#{endTime}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
		return sql;
	}
	
	public String selectAllById(Integer id){
		String sql =sqls+" where pp.id='"+id+"'";
		return sql;
	}
	
	public String selectAllByPid(Integer id){
		String sql =sqls+" where pp.p_id='"+id+"'";
		return sql;
	}
	
	public String selectAllByProductId(String productId){
		String sql =sqls+" where pp.product_id='"+productId+"'";
		return sql;
	}
}
