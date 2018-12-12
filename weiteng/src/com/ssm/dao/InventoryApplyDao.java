package com.ssm.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.InventoryApplyMapper;
import com.ssm.mapper.InventoryDetailMapper;
import com.ssm.mapper.ProductMapper;
import com.ssm.pojo.Admin;
import com.ssm.pojo.Inventory;
import com.ssm.pojo.InventoryApply;
import com.ssm.pojo.InventoryDetail;
import com.ssm.utils.ArithUtil;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;
import com.ssm.utils.StringUtil;

public class InventoryApplyDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
	InventoryApplyMapper inventoryApplyMapper = sqlSession.getMapper(InventoryApplyMapper.class);
	InventoryDetailMapper inventoryDetailMapper = sqlSession.getMapper(InventoryDetailMapper.class);
	ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);

	
    public Pager findByPage(InventoryApply inventoryApply,Pager pager){
		Map<String,Object> params = new HashMap<>();
		params.put("inventoryApply",inventoryApply);
		int recordCount =inventoryApplyMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<InventoryApply> invs =inventoryApplyMapper.selectByPage(params);
		pager.setResultList(invs);
		sqlSession.close();
		return pager;
	}
    
    public String saveApply(Admin admin,String applyId,Inventory inventory,int payType,String type,String[] ids,
    		String[] pids,String[] names,String[] prices,String[] specifications,String[] nums,String remark,Timestamp date){
    	String str="";
    	try{
    		if(ids.length>0){
				int num=0;
				int size = 0;
    			for(int i=0;i<ids.length;i++){
    				if(!(StringUtil.notNull(nums[i]).equals("")||StringUtil.notNull(nums[i]).equals("0"))){
	    				InventoryDetail idl = new InventoryDetail();
	    				idl.setApplyId(applyId);
	    				idl.setProductId(pids[i]);
	    				idl.setProductName(names[i]);
	    				idl.setSpecification(specifications[i]);
	    				idl.setPrice(Double.valueOf(prices[i]));
	    				idl.setNum(Integer.valueOf(nums[i]));
	    				idl.setTotalPrice(ArithUtil.mul(idl.getPrice(), idl.getNum()));
	    				num = num+inventoryDetailMapper.save(idl);
	    				size++;
    				}
    			}
    			if(size>0){
    			if(num-size==0){
    				InventoryApply iay = new InventoryApply();
    				iay.setApplyId(applyId);
    				iay.setState(1);
    				iay.setOperatorId(admin.getAdminName());
    				iay.setInventory(inventory.getName());
    				iay.setInventoryId(inventory.getId());
    				iay.setEntryTime(date);
    				iay.setType(Integer.valueOf(type));
    				iay.setPayType(payType);
    				iay.setRemark(remark);
    				if(inventoryApplyMapper.save(iay)>0){
    					str="yes";
    					sqlSession.commit();
    				}else{
        				str="订单申请保存有误。";
        				sqlSession.rollback();
        			}
    			}else{
    				str="产品清单保存有误。";
    				sqlSession.rollback();
    			}
    			}else{
        			str="未获得出入库的产品信息。";
        			sqlSession.rollback();
        		}
        		
    		}else{
    			str="未获得出入库的产品信息。";
    		}
    		
    	}catch(Exception e){
    		str="系统繁忙中，请稍后再试。";
    		e.printStackTrace();
    		sqlSession.rollback();
    	}
    	finally{
    		sqlSession.close();
    	}
    	return str;
    }
    
    public InventoryApply findByApplyId(String applyId){
    	InventoryApply invs = null;
        	try{
    		invs =inventoryApplyMapper.selectByApplyId(applyId);
        	}finally{
        		sqlSession.close();
        	}
    		return invs;
    	}
    
    public int update(InventoryApply iay){
    	int num = 0;
        	try{
        		Integer up1 = inventoryApplyMapper.update(iay);
        		if(up1!=null){
        			num= up1;
        			sqlSession.commit();
        		}
        	}finally{
        		sqlSession.close();
        	}
    		return num;
    	}
  
    public String reviewYes(Admin admin,String applyId,Timestamp date){
    	String str="";
    	String error ="";
    	try{
    		InventoryApply iay = inventoryApplyMapper.selectByApplyId(applyId);
    		if(iay!=null){
    			if(iay.getState()==1){
    				List<InventoryDetail> ilist = inventoryDetailMapper.selectByApplyId(applyId);
    				for(int i=0;i<ilist.size();i++){
    					int num = ilist.get(i).getNum();
    					if(num>0){
    						Integer up = productMapper.updateNum(ilist.get(i).getProductId(),num);
    						if(up==null||up==0){
    							error+=error+ilist.get(i).getProductId()+"库存数量更新失败;<br>";
    						}
    					}
    				}
    			if(error.equals("")){
    				InventoryApply iay1 = new InventoryApply();
    				iay1.setId(iay.getId());;
    				iay1.setState(2);
    				iay1.setReviewerId(admin.getAdminName());
    				iay1.setReviewTime(date);
    				if(inventoryApplyMapper.update(iay1)>0){
    					str="yes";
    					sqlSession.commit();
    				}else{
        				str="入库申请申请审核流程有误。";
        				sqlSession.rollback();
        			}
    			}else{
    				str=error;
    				sqlSession.rollback();
    			}
    			}else{
        			str="入库单当前状态不能进行审批操作。";
        			sqlSession.rollback();
        		}
        		
    		}else{
    			str="入库单信息获取失败。";
    			sqlSession.rollback();
    		}
    		
    	}catch(Exception e){
    		str="系统繁忙中，请稍后再试。";
    		e.printStackTrace();
    		sqlSession.rollback();
    	}
    	finally{
    		sqlSession.close();
    	}
    	return str;
    }
    
    public String reviewOutYes(Admin admin,String applyId,Timestamp date){
    	String str="";
    	String error ="";
    	try{
    		InventoryApply iay = inventoryApplyMapper.selectByApplyId(applyId);
    		if(iay!=null){
    			if(iay.getState()==1){
    				List<InventoryDetail> ilist = inventoryDetailMapper.selectByApplyId(applyId);
    				for(int i=0;i<ilist.size();i++){
    					int num = ilist.get(i).getNum();
    					if(num>0){
    						Integer up = productMapper.updateSubNum(ilist.get(i).getProductId(),num);
    						if(up==null||up==0){
    							error+=error+ilist.get(i).getProductId()+"库存数量不足或更新失败;<br>";
    						}
    					}
    				}
    			if(error.equals("")){
    				InventoryApply iay1 = new InventoryApply();
    				iay1.setId(iay.getId());;
    				iay1.setState(2);
    				iay1.setReviewerId(admin.getAdminName());
    				iay1.setReviewTime(date);
    				if(inventoryApplyMapper.update(iay1)>0){
    					str="yes";
    					sqlSession.commit();
    				}else{
        				str="出库申请审核流程有误。";
        				sqlSession.rollback();
        			}
    			}else{
    				str=error;
    				sqlSession.rollback();
    			}
    			}else{
        			str="出库单当前状态不能进行审批操作。";
        			sqlSession.rollback();
        		}
        		
    		}else{
    			str="出库单信息获取失败。";
    			sqlSession.rollback();
    		}
    		
    	}catch(Exception e){
    		str="系统繁忙中，请稍后再试。";
    		e.printStackTrace();
    		sqlSession.rollback();
    	}
    	finally{
    		sqlSession.close();
    	}
    	return str;
    }
	
}
