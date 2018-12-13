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
import com.ssm.service.AdminService;
import com.utils.Constants;
import com.utils.MD5;
import com.utils.Pager;
import com.utils.StringUtil;

@Controller("adminController")
@RequestMapping("")
public class AdminController {
	
	@Autowired
	private AdminService adminService = null;
	
	@RequestMapping("/admin/admin_list")
	public ModelAndView admin_list(HttpServletRequest request){
		 String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		 String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		ModelAndView mv = new ModelAndView();
		Admin admin = new Admin();
		admin.setState(2);
		int pageNo = 1;
		int pageSize = 10;
		if(!pageNoStr.equals("")) pageNo = Integer.valueOf(pageNoStr);
		if(!pageSizeStr.equals("")) pageSize = Integer.valueOf(pageSizeStr);
		Pager pager= new Pager();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		pager = adminService.findByPager(admin, pager);
		mv.addObject("pager",pager);
		mv.setViewName("/admin/admin_list");
		return mv;
	}
	
	@RequestMapping("/admin/logout")
	public ModelAndView logout(HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView();
		request.getSession().removeAttribute(Constants.ADMIN_SESSION);
		mv.setViewName("/admin/login_out");
		return mv;
	}
	
	@RequestMapping("/admin/login")
	public ModelAndView login(HttpServletRequest request,Admin admin) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		String message="";
		Timestamp date = new Timestamp(new Date().getTime());
		admin.setEndTime(date);
		try{
			Admin login_admin = adminService.login(admin);
			if(login_admin!=null){
				request.getSession().setAttribute(Constants.ADMIN_SESSION, login_admin);
			}else{
				message="用户名不存在或者密码有误！";
			}
		}catch(RuntimeException e){
			message=e.getMessage();
		}
		if(message.equals("")){
			mv.setViewName("/admin/login_success");
		}else{
			mv.setViewName("/admin/login");
		}
		return mv;
	}
	
	@RequestMapping("/admin/admin_add")
	public ModelAndView admin_add(HttpServletRequest request){
		Admin sys_admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		
		ModelAndView mv = new ModelAndView();
		int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
		if(sys_admin!=null){
			request.getSession().setAttribute("token", String.valueOf(token));
			mv.setViewName("/admin/admin_add");
		}else{
			mv.setViewName("/admin/error_login");
		}
		return mv;
	}
	
	
	@RequestMapping(value="/admin/admin_detail",method=RequestMethod.GET)
	public ModelAndView admin_detail(@RequestParam("id") Integer id){
		ModelAndView mv = new ModelAndView();
		Admin admin = adminService.findById(id);
		String message="";
		if(admin==null){
			message  ="用户信息已过期或此链接不存在！";
			mv.addObject("message",message);
		}
		mv.addObject("admin",admin);
		mv.setViewName("/admin/admin_detail");
		return mv;
	}
	
	@RequestMapping(value="/admin/admin_save",method=RequestMethod.POST)
	public ModelAndView admin_save(HttpServletRequest request,@ModelAttribute Admin admin){
		Admin sys_admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String message = "";
		ModelAndView mv = new ModelAndView();
		if(sys_admin!=null){
			if(sys_admin.getType()-1>0&&admin.getType()-1<0) admin.setType(1); 
			Timestamp date = new Timestamp(new Date().getTime());
			String psw = MD5.GetMD5Code(admin.getPassword());
			admin.setAdminName(admin.getAdminName());
			admin.setPassword(psw);
			admin.setPassword2(psw);
			admin.setRank("");
			admin.setType(admin.getType());
			admin.setDeptId(0);
			admin.setDeptName("");
			admin.setJobId(0);
			admin.setJobName("");
			admin.setEntryTime(date);
			admin.setEndTime(date);
			admin.setState(1);
			int save = adminService.insert(admin);
			if(save>0){
				message  ="用户信息保存成功！";
			}else{
				message  ="用户信息保存失败，请重新编辑！";
			}
		}else{
			message  ="用户未登录，请先登陆！";
		}
		mv.addObject("message",message);
		mv.setViewName("/admin/admin_message");
		return mv;
	}
	
	@RequestMapping("/admin/admin_init_save")
	public ModelAndView admin_init_save(){
		String message = "";
		ModelAndView mv = new ModelAndView();
			Timestamp date = new Timestamp(new Date().getTime());
			Admin admin = adminService.findByAdminName("zhkc");
			if(admin==null){
				admin = new Admin();
			String psw = MD5.GetMD5Code("zhkc2018");
			String rank="";
			admin.setAdminName("zhkc");
			admin.setRealName("深圳市智华科创科技有限责任公司");
			admin.setPassword(psw);
			admin.setPassword2(psw);
			admin.setDeptId(0);
			admin.setDeptName("");
			admin.setJobId(0);
			admin.setJobName("");
			admin.setRank(rank);
			admin.setEntryTime(date);
			admin.setEndTime(date);
			admin.setType(2);
			admin.setState(1);
			int save = adminService.insert(admin);
			if(save>0){
				message  ="超级用户信息保存成功,超级用户名为：zhkc，密码：zhkc2018。";
			}else{
				message  ="超级用户信息保存失败，请重新编辑！";
			}
			}else{
				message  ="初始化超级用户已经存在。如果忘记密码请按超级用户密码重置方法进行重置密码！";
			}
		mv.addObject("message",message);
		mv.setViewName("/admin/admin_init_message");
		return mv;
	}
}
