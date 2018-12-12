package com.ssm.dao.provider;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import com.ssm.pojo.WSettle;
import com.ssm.utils.Constants;
import com.ssm.utils.Pager;

public class WSettleDynaSqlProvider {
	
	public String selectListWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM("wsettle");
			if(params.get("wst")!=null){
				WSettle wst = (WSettle) params.get("wst");
				if(wst.getUserId()!=null && !wst.getUserId().equals("")){
					WHERE("  userId LIKE CONCAT ('%',#{wst.userId},'%')");
				}
				if(wst.getState()!=null && wst.getState()!=0){
					WHERE("  state LIKE CONCAT ('%',#{wst.state},'%')");
				}
				
				if(wst.getStartTime()!=null){
					WHERE("  entryTime >= #{wst.startTime}");
				}
				if(wst.getEndTime()!=null){
					WHERE("  entryTime <= #{wst.endTime}");
				}
				WHERE("  state>0 ");
			}
			}
		}.toString();
		return sql;
	}
	
	public String selectWhitParam(final Map<String,Object> params){
		String str = "";
		String tableName ="";
		if(params.get("tableName")!=null) tableName = (String)  params.get("tableName");
		if(params.get("wst")!=null){
			WSettle wst = (WSettle) params.get("wst");
			if(wst.getUserId()!=null && !wst.getUserId().equals("")){
				if(str.equals("")) str=" where userId like '%"+wst.getUserId()+"%' ";
				else str +=" and userId like '%"+wst.getUserId()+"%' ";
			}
			if(wst.getWeekTag()!=null){
				if(str.equals("")) str=" where weekTag ='"+wst.getWeekTag()+"' ";
				else str +=" and  weekTag ='"+wst.getWeekTag()+"' ";
			}
		}
		String sql = "select * from "+tableName+" "+str+" order by id asc ";
		if(params.get("pageModel")!=null){
			Pager pager = (Pager) params.get("pageModel");
			sql +="  limit "+pager.getStartIndex()+","+pager.getPageSize();
		}
		return sql;
	}
	
	public String selectByList(final Map<String,Object> params){
		String str = "";
		String tableName ="";
		if(params.get("tableName")!=null) tableName = (String)  params.get("tableName");
		if(params.get("wst")!=null){
			WSettle wst = (WSettle) params.get("wst");
			if(wst.getUserId()!=null && !wst.getUserId().equals("")){
				if(str.equals("")) str=" where userId like '%"+wst.getUserId()+"%' ";
				else str +=" and userId like '%"+wst.getUserId()+"%' ";
			}
			if(wst.getWeekTag()!=null){
				if(str.equals("")) str=" where weekTag ='"+wst.getWeekTag()+"' ";
				else str +=" and  weekTag ='"+wst.getWeekTag()+"' ";
			}
		}
		String sql = "select * from "+tableName+" "+str+" order by id asc ";
	
		return sql;
	}
	
	public String selectAll(final Map<String,Object> params){
		String str = "";
		String tableName ="";
		if(params.get("tableName")!=null) tableName = (String)  params.get("tableName");
		if(params.get("wst")!=null){
			WSettle wst = (WSettle) params.get("wst");
			if(wst.getUserId()!=null && !wst.getUserId().equals("")){
				if(str.equals("")) str=" where userId like '%"+wst.getUserId()+"%' ";
				else str+=" and userId like '%"+wst.getUserId()+"%' ";
			}
			if(wst.getWeekTag()!=null){
				if(str.equals("")) str=" where weekTag ='"+wst.getWeekTag()+"' ";
				else str+=" and  weekTag ='"+wst.getWeekTag()+"' ";
			}
		}
		String sql = "select * from "+tableName+" "+str+" order by id asc ";
		
		return sql;
	}
	
	public String count(final Map<String,Object> params){
		String str = "";
		String tableName ="";
		if(params.get("tableName")!=null) tableName = (String)  params.get("tableName");
		if(params.get("wst")!=null){
			WSettle wst = (WSettle) params.get("wst");
			if(wst.getUserId()!=null && !wst.getUserId().equals("")){
				if(str.equals("")) str=" where userId like '%"+wst.getUserId()+"%' ";
				else str+=" and userId like '%"+wst.getUserId()+"%' ";
			}
			if(wst.getWeekTag()!=null){
				if(str.equals("")) str=" where weekTag ='"+wst.getWeekTag()+"' ";
				else str+=" and  weekTag ='"+wst.getWeekTag()+"' ";
			}
			
			if(wst.getState()!=null){
				if(str.equals("")) str=" where state like '%"+wst.getState()+"%' ";
				else str+=" and  state like '%"+wst.getState()+"%' ";
			}
		}
		String sql = "select count(*) from "+tableName+" "+str;
		
		return sql;
	}
	
	public String findByUserId(String userId,String tableName){
		
		String sql = "select * from "+tableName+" where userId='"+userId+"'";
		return sql;
	}
	
	public String insertWSettle(final WSettle ws){
		return new SQL(){
			{
				INSERT_INTO(Constants.WSETTLETABLE);
				if(ws.getuId()!=null&& !ws.getuId().equals("")){
					VALUES("uId","#{uId}");
				}
				if(ws.getUserId()!=null&& !ws.getUserId().equals("")){
					VALUES("userId","#{userId}");
				}
				if(ws.getUserName()!=null&& !ws.getUserId().equals("")){
					VALUES("userName","#{userName}");
				}
				if(ws.getState()!=null){
					VALUES("state","#{state}");
				}
				if(ws.getTotalPerformance()!=null){
					VALUES("totalPerformance","#{totalPerformance}");
				}
				if(ws.getTotalPrice()!=null){
					VALUES("totalPrice","#{totalPrice}");
				}
				if(ws.getAtpv()!=null){
					VALUES("atpv","#{atpv}");
				}
				if(ws.getAtprice()!=null){
					VALUES("atprice","#{atprice}");
				}
				if(ws.getBtpv()!=null){
					VALUES("btpv","#{btpv}");
				}
				if(ws.getBtprice()!=null){
					VALUES("btprice","#{btprice}");
				}
				if(ws.getAcpv()!=null){
					VALUES("acpv","#{acpv}");
				}
				if(ws.getBcpv()!=null){
					VALUES("bcpv","#{bcpv}");
				}
				if(ws.getRtpv()!=null){
					VALUES("rtpv","#{rtpv}");
				}
				if(ws.getRtprice()!=null){
					VALUES("rtprice","#{rtprice}");
				}
				if(ws.getRtpv1()!=null){
					VALUES("rtpv1","#{rtpv1}");
				}
				if(ws.getRtprice()!=null){
					VALUES("rtprice1","#{rtprice1}");
				}
				if(ws.getTotalNum()!=null){
					VALUES("totalNum","#{totalNum}");
				}
				if(ws.getTotalNumReal()!=null){
					VALUES("totalNumReal","#{totalNumReal}");
				}
				if(ws.getEntryTime()!=null){
					VALUES("entryTime","#{entryTime}");
				}
				
			}
		}.toString();
	}
	
	public String updateWSettle(final WSettle ws){
		return new SQL(){
			{
				UPDATE(Constants.WSETTLETABLE);
				if(ws.getUserName()!=null&& !ws.getUserName().equals("")){
					SET("userName=#{userName}");
				}
				if(ws.getUserId()!=null&& !ws.getUserId().equals("")){
					SET("userId=#{userId}");
				}
				if(ws.getTotalPerformance()!=null){
					SET("totalPerformance=#{totalPerformance}");
				}
				if(ws.getTotalPrice()!=null){
					SET("totalPrice=#{totalPrice}");
				}
				if(ws.getAtpv()!=null){
					SET("atpv=#{atpv}");
				}
				if(ws.getAtprice()!=null){
					SET("atprice=#{atprice}");
				}
				if(ws.getBtpv()!=null){
					SET("btpv=#{btpv}");
				}
				if(ws.getBtprice()!=null){
					SET("btprice=#{btprice}");
				}
				if(ws.getAcpv()!=null){
					SET("acpv=#{acpv}");
				}
				if(ws.getBcpv()!=null){
					SET("bcpv=#{bcpv}");
				}
				if(ws.getRtpv()!=null){
					SET("rtpv=#{rtpv}");
				}
				if(ws.getRtprice()!=null){
					SET("rtprice=#{rtprice}");
				}
				if(ws.getRtpv1()!=null){
					SET("rtpv1=#{rtpv1}");
				}
				if(ws.getRtprice()!=null){
					SET("rtprice1=#{rtprice1}");
				}
				if(ws.getJoinPriceTal()!=null){
					SET("joinPriceTal=#{joinPriceTal}");
				}
				if(ws.getJoinPvTal()!=null){
					SET("joinPvTal=#{joinPvTal}");
				}
				if(ws.getTotalNum()!=null){
					SET("totalNum=#{totalNum}");
				}
				if(ws.getTotalNumReal()!=null){
					SET("totalNumReal=#{totalNumReal}");
				}
				if(ws.getState()!=null){
					SET("state=#{state}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	
	public String updateAll(final Map<String,Object> params){
		final String tableName =(String) params.get("tableName");
		String str = "";
			
				if(params.get("wst")!=null){
					WSettle wst = (WSettle) params.get("wst");
			
				if(wst.getAcpv()!=null){
					if(str.equals("")) str ="acpv='"+wst.getAcpv()+"'";
					else str+=",acpv='"+wst.getAcpv()+"' ";
				}
				if(wst.getBcpv()!=null){
					if(str.equals("")) str ="bcpv='"+wst.getBcpv()+"'";
					else str+=",bcpv='"+wst.getBcpv()+"' ";
				}
				if(wst.getUserName()!=null&& !wst.getUserName().equals("")){
					if(str.equals("")) str ="userName='"+wst.getUserName()+"'";
					else str+=",userName='"+wst.getUserName()+"' ";
				}
				if(wst.getUserId()!=null&& !wst.getUserId().equals("")){
					if(str.equals("")) str ="userId='"+wst.getUserId()+"'";
					else str+=",userId='"+wst.getUserId()+"' ";
				}
				if(wst.getTotalPerformance()!=null){
					if(str.equals("")) str ="totalPerformance='"+wst.getTotalPerformance()+"'";
					else str+=",totalPerformance='"+wst.getTotalPerformance()+"' ";
				}
				if(wst.getTotalPrice()!=null){
					if(str.equals("")) str ="totalPrice='"+wst.getTotalPrice()+"'";
					else str+=",totalPrice='"+wst.getTotalPrice()+"' ";
				}
				if(wst.getAtpv()!=null){
					if(str.equals("")) str ="atpv='"+wst.getAtpv()+"'";
					else str+=",atpv='"+wst.getAtpv()+"' ";
				}
				if(wst.getAtprice()!=null){
					if(str.equals("")) str ="atprice='"+wst.getAtprice()+"'";
					else str+=",atprice='"+wst.getAtprice()+"' ";
				}
				if(wst.getBtpv()!=null){
					if(str.equals("")) str ="btpv='"+wst.getBtpv()+"'";
					else str+=",btpv='"+wst.getBtpv()+"' ";
				}
				if(wst.getBtprice()!=null){
					if(str.equals("")) str ="btprice='"+wst.getBtprice()+"'";
					else str+=",btprice='"+wst.getBtprice()+"' ";
				}
				
				if(wst.getRtpv()!=null){
					if(str.equals("")) str ="rtpv='"+wst.getRtpv()+"'";
					else str+=",rtpv='"+wst.getRtpv()+"' ";
				}
				if(wst.getRtprice()!=null){
					if(str.equals("")) str ="rtprice='"+wst.getRtprice()+"'";
					else str+=",rtprice='"+wst.getRtprice()+"' ";
				}
				if(wst.getRtpv1()!=null){
					if(str.equals("")) str ="rtpv1='"+wst.getRtpv1()+"'";
					else str+=",rtpv1='"+wst.getRtpv1()+"' ";
				}
				if(wst.getRtprice1()!=null){
					if(str.equals("")) str ="rtprice1='"+wst.getRtprice1()+"'";
					else str+=",rtprice1='"+wst.getRtprice1()+"' ";
				}
				if(wst.getJoinPriceTal()!=null){
					if(str.equals("")) str ="joinPriceTal='"+wst.getJoinPriceTal()+"'";
					else str+=",joinPriceTal='"+wst.getJoinPriceTal()+"' ";
				}
				if(wst.getJoinPvTal()!=null){
					if(str.equals("")) str ="joinPvTal='"+wst.getJoinPvTal()+"'";
					else str+=",joinPvTal='"+wst.getJoinPvTal()+"' ";
				}
				if(wst.getTotalNum()!=null){
					if(str.equals("")) str ="totalNum='"+wst.getTotalNum()+"'";
					else str+=",totalNum='"+wst.getTotalNum()+"' ";
				}
				if(wst.getTotalNumReal()!=null){
					if(str.equals("")) str ="totalNumReal='"+wst.getTotalNumReal()+"'";
					else str+=",totalNumReal='"+wst.getTotalNumReal()+"' ";
				}
				if(wst.getState()!=null){
					if(str.equals("")) str ="state='"+wst.getState()+"'";
					else str+=",state='"+wst.getState()+"' ";
				}
				if(wst.getId()!=null){
					str+=" where id='"+wst.getId()+"' ";
				}
				}
		
		String sql= "update "+tableName+" set "+str;
		return sql;
		
	}
	
	public String updateUserId(final WSettle ws){
		return new SQL(){
			{
				UPDATE(Constants.WSETTLETABLE);
				if(ws.getUserName()!=null&& !ws.getUserName().equals("")){
					SET("userName=#{userName}");
				}
				if(ws.getUserId()!=null&& !ws.getUserId().equals("")){
					SET("userId=#{userId}");
				}
				if(ws.getState()!=null){
					SET("state=#{state}");
				}
				WHERE(" userId=#{userId}");
			}
		}.toString();
	}
	
	public String update(final WSettle ws){
		return new SQL(){
			{
				UPDATE(Constants.USERSTABLE);
				if(ws.getUserName()!=null&& !ws.getUserName().equals("")){
					SET("userName=#{userName}");
				}
				if(ws.getUserId()!=null&& !ws.getUserId().equals("")){
					SET("userId=#{userId}");
				}
				if(ws.getState()!=null){
					SET("state=#{state}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	
	public String createWSettle(String tableName){
		String sql ="create table if not exists "+tableName+" (id int(11) NOT NULL AUTO_INCREMENT, uId int(11) NOT NULL,userId varchar(10) not null,userName varchar(50) not null,"
	 			+ "totalPerformance decimal(16,3) DEFAULT '0',newPerformance decimal(16,3) DEFAULT '0',totalPrice decimal(16,3) DEFAULT '0', newPrice decimal(16,3) DEFAULT '0',"
				+ "totalNum int(11) DEFAULT '0',newNum int(11) DEFAULT '0',totalNumReal int(11) DEFAULT '0',newNumReal int(11) DEFAULT '0',apv decimal(16,3) DEFAULT '0',acpv decimal(16,3) DEFAULT '0',"
				+ "atpv decimal(16,3) DEFAULT '0',aprice decimal(16,3) DEFAULT '0',atprice decimal(16,3) DEFAULT '0',bpv decimal(16,3) DEFAULT '0',bcpv decimal(16,3) DEFAULT '0',btpv decimal(16,3) DEFAULT '0',bprice decimal(16,3) DEFAULT '0',btprice decimal(16,3) DEFAULT '0',"
				+ "rpv decimal(16,3) DEFAULT '0',rtpv decimal(16,3) DEFAULT '0',rprice decimal(16,3) DEFAULT '0',rtprice decimal(16,3) DEFAULT '0',"
				+ "rpv1 decimal(16,3) DEFAULT '0',rtpv1 decimal(16,3) DEFAULT '0',rprice1 decimal(16,3) DEFAULT '0',rtprice1 decimal(16,3) DEFAULT '0',maxRank TINYINT(2) DEFAULT '0',"
				+ "joinPvTal decimal(16,3) DEFAULT '0',joinPriceTal decimal(16,3) DEFAULT '0',maxRankNew TINYINT(2) DEFAULT '0',rank TINYINT(2) DEFAULT '0',"
				+ "weekTag int(11) DEFAULT '0', state tinyint(1) DEFAULT '0',payTag tinyint(1) DEFAULT '1',is_touch tinyint(1) DEFAULT '0',is_empty tinyint(1) DEFAULT '0',rankJoin tinyint(2) DEFAULT '0',rankJoinTag tinyint(2) DEFAULT '0',startTime datetime DEFAULT NULL,"
				+ "endTime datetime DEFAULT NULL,entryTime datetime DEFAULT NULL,PRIMARY KEY (userId),UNIQUE KEY userId(userId), UNIQUE KEY id(id),index(rank)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		return sql;
	}
	
	public String dropWSettle(String tableName){
		String sql ="drop table if exists "+tableName+" ";
		return sql;
	}
	
	public String insertAll(Map<String,Object> params) {  
        StringBuilder sb = new StringBuilder(); 
        String tableName=(String) params.get("tableName");
        sb.append("INSERT INTO "+tableName);  
        sb.append("(id,uId,userId,userName,totalPerformance,totalPrice,newPerformance,newPrice"
        		+ ",apv,atpv,acpv,aprice,atprice,bpv,btpv,bcpv,bprice,btprice"
        		+ ",rpv,rtpv,rprice,rtprice,rpv1,rtpv1,rprice1,rtprice1"
        		+ ",totalNum,newNum,totalNumReal,newNumReal,joinPvTal,joinPriceTal,rankJoin,rank,payTag,weekTag,startTime,endTime,entryTime)");  
        sb.append("VALUES "); 
        if(params.get("list")!=null){
        List<WSettle> list = (List<WSettle>) params.get("list");  
        MessageFormat mf = new MessageFormat("(null,#'{'list[{0}].uId},#'{'list[{0}].userId},#'{'list[{0}].userName},#'{'list[{0}].totalPerformance},#'{'list[{0}].totalPrice}"
        		+ ",#'{'list[{0}].newPerformance},#'{'list[{0}].newPrice}"
        		+ ",#'{'list[{0}].apv},#'{'list[{0}].atpv},#'{'list[{0}].acpv},#'{'list[{0}].aprice},#'{'list[{0}].atprice}"
        		+ ",#'{'list[{0}].bpv},#'{'list[{0}].btpv},#'{'list[{0}].bcpv},#'{'list[{0}].bprice},#'{'list[{0}].btprice}"
        		+ ",#'{'list[{0}].rpv},#'{'list[{0}].rtpv},#'{'list[{0}].rprice},#'{'list[{0}].rtprice}"
        		+ ",#'{'list[{0}].rpv1},#'{'list[{0}].rtpv1},#'{'list[{0}].rprice1},#'{'list[{0}].rtprice1}"
        		+ ",#'{'list[{0}].totalNum},#'{'list[{0}].newNum},#'{'list[{0}].totalNumReal},#'{'list[{0}].newNumReal}"
        		+ ",#'{'list[{0}].joinPvTal},#'{'list[{0}].joinPriceTal},#'{'list[{0}].rankJoin},#'{'list[{0}].rank},#'{'list[{0}].payTag}"
        		+ ",#'{'list[{0}].weekTag},#'{'list[{0}].startTime},#'{'list[{0}].endTime},#'{'list[{0}].entryTime})");  
        for (int i = 0; i < list.size(); i++) {  
            sb.append(mf.format(new Object[]{i})); 
           if(i<list.size()-1){
            	 sb.append(",");  
            }
        }  
		}
        return sb.toString();  
    }  

}
