package com.ssm.controller;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.pojo.Admin;
import com.ssm.pojo.Unit;
import com.ssm.service.UnitService;
import com.utils.Constants;
import com.utils.Pager;
import com.utils.StringUtil;

@Controller("unitController")
@RequestMapping("")
public class UnitController {
	
	@Autowired
	private UnitService unitService = null;
	
	@RequestMapping("/admin/unit")
	public ModelAndView admin_unit_list(HttpServletRequest request,ModelAndView mv){
		Admin admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);

		 String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		 String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
			int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
			request.getSession().setAttribute("token", String.valueOf(token));
		String message="";
		int lt = 0;
		try{
			if(admin!=null){
				Unit unit = new Unit();
				unit.setState(1);
				int pageNo = 1;
				int pageSize = 10;
				if(!pageNoStr.equals("")) pageNo = Integer.valueOf(pageNoStr);
				if(!pageSizeStr.equals("")) pageSize = Integer.valueOf(pageSizeStr);
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = unitService.findByPager(unit, pager);
				mv.addObject("pager",pager);
			}else lt++;
		}catch(Exception e){
			e.printStackTrace();
			message ="数据异常，请刷新！";
		}finally{
			if(lt>0){
				mv.setViewName("/admin/error_login");
			}else{
				mv.addObject("message",message);
				mv.setViewName("/admin/unit_list");
			}
		}
		return mv;
	}
	
	@RequestMapping(value="/admin/unit_detail",method=RequestMethod.GET)
	public ModelAndView admin_unit_detail(@RequestParam("id") Long id){
		ModelAndView mv = new ModelAndView();
		Unit unit = unitService.findById(id);
		String message="";
		if(unit==null){
			message  ="计量单位已过期或此链接不存在！";
			mv.addObject("message",message);
		}
		mv.addObject("unit",unit);
		mv.setViewName("/admin/unit_detail");
		return mv;
	}
	
	@RequestMapping(value="/admin/unit_save",method=RequestMethod.POST)
	public ModelAndView admin_unit_save(HttpServletRequest request,@ModelAttribute Unit unit){
		Admin admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String message = "";
		String error="";
		ModelAndView mv = new ModelAndView();
		String token_old = (String)request.getSession().getAttribute("token");
		String token = StringUtil.notNull(request.getParameter("token"));
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		int lt=0;
		try{
			if(admin!=null){
				if(StringUtil.notNull(token_old).equals(token)){
				Timestamp date = new Timestamp(new Date().getTime());
				unit.setVersion((long) 0);
				unit.setType(1);
				unit.setParentId(0);
				unit.setEntryTime(date);
				unit.setEndTime(date);
				unit.setState(1);
				int save = unitService.insert(unit);
				if(save>0){
					message  ="恭喜你，计量单位信息保存成功！";					
				}else{
					error  ="非常遗憾，计量单位信息保存失败！";
				}
				
				}else{
					error  ="请勿重复提交数据！";
				}
			}else{
				error  ="用户未登录，请先登陆！";
				lt++;
			}
		}catch(Exception e){
			e.printStackTrace();
			error="数据操作异常，请重试！";
		}finally{
			
				if(lt>0){
					mv.setViewName("/admin/error_login");
			}else{
				if(message.equals("")){
					mv.addObject("error", error);
				}else{
					mv.addObject("message",message);
				
				}
				admin_unit_list(request,mv);
			}
			
		}
			return mv;
	}
	
	@RequestMapping("/admin/unit_edit")
	public ModelAndView admin_unit_edit(HttpServletRequest request){
		Admin sys_admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		ModelAndView mv = new ModelAndView();
		int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
		String error="";
		if(sys_admin!=null){
			String id = request.getParameter("id");
			if(!StringUtil.notNull(id).equals("")){
				Unit unit = unitService.findById(new Long(id));
				if(unit!=null){
					mv.addObject("unit",unit);
				}else{
					error = "很遗憾，未获得相应ID的计量单位！";
				}
			}else{
				error = "很遗憾，相应计量单位的ID获取失败！";
			}
			
			if(error.equals("")){
				request.getSession().setAttribute("token", String.valueOf(token));
				mv.setViewName("/admin/unit_edit");
			}else{
				mv.addObject("error",error);
				mv.setViewName("/admin/unit_error");
			}
		}else{
			mv.setViewName("/admin/error_login");
		}
		return mv;
	}
	
	@RequestMapping(value="/admin/unit_update",method=RequestMethod.POST)
	public ModelAndView admin_unit_update(HttpServletRequest request,@ModelAttribute Unit unit){
		Admin admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String message = "";
		String error="";
		ModelAndView mv = new ModelAndView();
		String token_old = (String)request.getSession().getAttribute("token");
		String token = StringUtil.notNull(request.getParameter("token"));
		int token_new = (int)(1+Math.random()*(1000000-1+1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		if(admin!=null){
			if(StringUtil.notNull(token_old).equals(token)){
				Timestamp date = new Timestamp(new Date().getTime());
				unit.setEndTime(date);
				int update = unitService.update(unit);
				if(update>0){
					message  ="恭喜你，计量单位信息修改成功！";
				}else{
					error  ="非常遗憾，计量单位修改失败，请重新核对！";
				}
			
			}else{
				error  ="请勿重复提交数据！";
			}
		}else{
			error  ="用户未登录，请先登陆！";
		}
		if(!message.equals("")){
			mv.addObject("message",message);
			mv.setViewName("/admin/unit_message");
		}else if(!error.equals("")){
			mv.addObject("error",error);
			mv.setViewName("/admin/unit_error");
		}
			return mv;
	}
	
	@RequestMapping(value="/admin/unit_del",method=RequestMethod.GET)
	public ModelAndView admin_unit_del(HttpServletRequest request,@ModelAttribute Unit unit){
		Admin admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String message = "";
		String error="";
		ModelAndView mv = new ModelAndView();
		try{
			if(admin!=null){
				String[] ids = request.getParameterValues("ids");
				if(ids!=null&&ids.length>0){
					String str = "";
					for(int i=0;i<ids.length;i++){
						if(str.equals("")) str +=ids[i];
						else str +=","+ids[i];
					}
					str = "("+str+")";
					int delnum = unitService.delete(str);
					if(delnum>0){
						message  ="恭喜你，计量单位信息批量删除成功！";
					}else{
						error = "非常抱歉，计量单位信息批量删除失败！";
					}
				}else{
					error  ="非常抱歉，未能获取需要删除的计量单位信息！";
				}
			}else{
				error  ="用户未登录，请先登陆！";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			error  ="非常遗憾，数据操作有误！";
		}finally{
			if(!message.equals("")){
				request.setAttribute("message",message);
			}else if(!error.equals("")){
				request.setAttribute("error",error);
			}
			admin_unit_list(request,mv);
		}
			return mv;
	}
	
	@RequestMapping(value="/admin/unit_remove",method=RequestMethod.POST)
	public ModelAndView admin_unit_remove(HttpServletRequest request,@ModelAttribute Unit unit){
		Admin admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String message = "";
		String error="";
		ModelAndView mv = new ModelAndView();
		int lt = 0;
		try{
			if(admin!=null){
				String[] ids = request.getParameterValues("ids");
				if(ids!=null&&ids.length>0){
					String str = "";
					for(int i=0;i<ids.length;i++){
						if(str.equals("")) str +=ids[i];
						else str +=","+ids[i];
					}
					str = "("+str+")";
					int delnum = unitService.remove(str);
					if(delnum>0){
						message  ="恭喜你，计量单位信息批量下架成功！";
					}else{
						error = "非常抱歉，计量单位信息批量下架失败！";
					}
				}else{
					error  ="非常抱歉，未能获取需要下架的计量单位信息！";
				}
			}else{
				error  ="用户未登录，请先登陆！";
				lt++;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			error  ="非常遗憾，数据操作有误！";
		}finally{
			if(!message.equals("")){
				mv.addObject("message",message);
				mv.setViewName("/admin/unit_message");
			}else if(!error.equals("")){
				
				mv.addObject("error",error);
				if(lt>0)
					mv.setViewName("/admin/error_login");
				else
				mv.setViewName("/admin/unit_error");
			}
		}
			return mv;
	}
}
