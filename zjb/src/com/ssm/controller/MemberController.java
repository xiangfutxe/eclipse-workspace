package com.ssm.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.pojo.Admin;
import com.ssm.pojo.Member;
import com.ssm.pojo.MemberInfo;
import com.ssm.service.MemberService;
import com.utils.Constants;
import com.utils.MD5;
import com.utils.Pager;
import com.utils.StringUtil;

@Controller("memberController")
@RequestMapping("")
public class MemberController {
	
	@Autowired
	private MemberService memberService = null;
	
	@RequestMapping("/admin/member_list")
	public ModelAndView list(HttpServletRequest request){
		Admin sys_admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		ModelAndView mv = new ModelAndView();
		String error ="";
		int lt = 0;
		try{
		if(sys_admin!=null){
			 String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
			 String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
				Member member = new Member();
				member.setState(1);
				int pageNo = 1;
				int pageSize = 10;
				if(!pageNoStr.equals("")) pageNo = Integer.valueOf(pageNoStr);
				if(!pageSizeStr.equals("")) pageSize = Integer.valueOf(pageSizeStr);
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = memberService.findByPager(member, pager);
				mv.addObject("pager",pager);
			}else{
				error = "用户未登陆，请退出重新登陆！";
				lt++;
			}
		}catch(Exception e){
			e.printStackTrace();
			error="数据异常，请重新操作或者上报系统管理员！";
		}finally{
			if(error.equals("")){
				mv.setViewName("/admin/member_list");
			}else if(lt>0){
				mv.setViewName("/admin/error_login");
			}else{
				mv.setViewName("/admin/member_error");

			}
		}
		return mv;
	}
	
	
	
	@RequestMapping("/admin/member_add")
	public ModelAndView admin_add(HttpServletRequest request){
		Admin sys_admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		
		ModelAndView mv = new ModelAndView();
		int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
		if(sys_admin!=null){
			request.getSession().setAttribute("token", String.valueOf(token));
			mv.setViewName("/admin/member_add");
		}else{
			mv.setViewName("/admin/error_login");
		}
		return mv;
	}
	
	
	@RequestMapping(value="/admin/member_detail",method=RequestMethod.GET)
	public ModelAndView member_detail(@RequestParam("id") Long id){
		ModelAndView mv = new ModelAndView();
		Member member = memberService.findAllById(id);
		String message="";
		if(member==null){
			message  ="用户信息已过期或此链接不存在！";
			mv.addObject("message",message);
		}
		if(message.equals("")){
			mv.addObject("member",member);
			mv.setViewName("/admin/member_detail");
		}else{
			mv.setViewName("/admin/member_error");
		}
		return mv;
	}
	
	@RequestMapping(value="/wap/login")
	public ModelAndView wap_login(HttpServletRequest request,Member member){
		ModelAndView mv = new ModelAndView();
		String message="";
		try{
		if(member!=null){
			if(!StringUtil.notNull(member.getNickName()).equals("")&&!StringUtil.notNull(member.getPassword()).equals("")){
				Member login_m = memberService.findByNickName(member.getNickName());
				if(login_m!=null){
					if(login_m.getPassword().equals(MD5.GetMD5Code(member.getPassword()))){
						if(login_m.getState()==1){
							Timestamp date= new Timestamp(new Date().getTime());
							Member update_member = new Member();
							update_member.setId(login_m.getId());
							update_member.setVersion(login_m.getVersion());
							update_member.setViewNum(login_m.getViewNum()+1);
							update_member.setEndTime(date);
							if(memberService.update(update_member)>0){
								login_m.setVersion(login_m.getVersion()+1);
								login_m.setViewNum(login_m.getViewNum()+1);
								request.getSession().setAttribute(Constants.MEMBER_SESSION,login_m);
							}else{
								message  ="用户信息发生变化，请重新登陆！";
							}
						}else{
							message  ="用户状态异常，请与客服联系！";
						}
					}else{
						message  ="用户状态异常，请与客服联系！";
					}
				}else{
					message  ="用户信息已过期或此链接不存在！";
				}
			}else{
				message  ="用户信息已过期或此链接不存在！";
			}
		}else{
			message  ="用户信息已过期或此链接不存在！";
			
		}
		}catch(Exception e){
			e.printStackTrace();
			message="数据异常，请重试！";
		}finally{
			if(message.equals("")){
				mv.setViewName("/wap/userhome");
			}else{
				mv.addObject("message",message);
				mv.setViewName("/wap/login");
			}
		}
		return mv;
	}
	
	@RequestMapping(value="/wap/logout",method=RequestMethod.GET)
	public ModelAndView wap_logout(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		request.getSession().removeAttribute(Constants.MEMBER_SESSION);
		mv.setViewName("/wap/login");
		return mv;
	}
	
	
	
	
	
	@RequestMapping(value="/admin/member_save",method=RequestMethod.POST)
	public ModelAndView member_save(HttpServletRequest request,@ModelAttribute Member member){
		Admin sys_admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String message = "";
		String error ="";
		int lt = 0;
		ModelAndView mv = new ModelAndView();
		if(sys_admin!=null){
			Timestamp date = new Timestamp(new Date().getTime());
			String psw = MD5.GetMD5Code(member.getPassword());
			member.setPassword(psw);
			member.setPassword2(psw);
			member.setVersion((long) 0);
			member.setViewNum((long) 0);
			member.setEntryTime(date);
			member.setEndTime(date);
			member.setState(1);
			MemberInfo info = new MemberInfo();
			info.setRealName(member.getRealName());
			info.setSex(member.getSex());
			info.setEducation(member.getEducation());
			info.setEntryTime(date);
			info.setEndTime(date);
			info.setMaritalStatus(member.getMaritalStatus());
			info.setMobile(member.getMobile());
			info.setVersion((long) 0);
			int save = memberService.insert(member,info);
			if(save>0){
				message  ="会员信息保存成功！";
			}else{
				error  ="会员信息保存失败，请重新编辑！";
			}
		}else{
			error  ="用户未登录，请先登陆！";
		}
		if(!message.equals("")){
			mv.addObject("message",message);
			mv.setViewName("/admin/member_message");
		}else if(!error.equals("")){
			if(lt>0){
			mv.setViewName("/admin/error_login");
			}else{
				mv.addObject("error",error);
				mv.setViewName("/admin/member_error");

			}
		}
		return mv;
	}
	
	@RequestMapping("/admin/member_edit")
	public ModelAndView admin_member_edit(HttpServletRequest request,@RequestParam("id") Long id){
		Admin sys_admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String error= "";
		ModelAndView mv = new ModelAndView();
		try{
			Member member = memberService.findAllByIdForInfo(id);
		if(sys_admin!=null){
			int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
			request.getSession().setAttribute("token", String.valueOf(token));
			mv.addObject("member", member);
			mv.setViewName("/admin/member_edit");
		}else{
			mv.setViewName("/admin/error_login");
		}
		}catch(Exception e){
			e.printStackTrace();
			error = "数据异常，请重新操作或系统管理员联系。";
		}finally{
			if(!error.equals("")){
				mv.addObject("error", error);
				mv.setViewName("/admin/member_error");;
			}
		}
		return mv;
	}
	
	@RequestMapping(value="/admin/member_update",method=RequestMethod.POST)
	public ModelAndView admin_member_update(HttpServletRequest request,@ModelAttribute Member member){
		Admin sys_admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String message = "";
		String error ="";
		int lt = 0;
		ModelAndView mv = new ModelAndView();
		try{
		if(sys_admin!=null){
			Timestamp date = new Timestamp(new Date().getTime());
			MemberInfo info = new MemberInfo();
			info.setId(member.getInfoId());
			info.setMemberId(member.getId());
			info.setRealName(member.getRealName());
			info.setSex(member.getSex());
			info.setEducation(member.getEducation());
			info.setEndTime(date);
			info.setVersion(member.getVersion());
			info.setMaritalStatus(member.getMaritalStatus());
			info.setMobile(member.getMobile());
			int save = memberService.update(info);
			
			if(save>0){
				message  ="会员信息更新成功！";
			}else{
				error  ="会员信息更新失败，原因可能是最近有最新信息更新，请重新编辑！";
			}
		}else{
			error  ="用户未登录，请先登陆！";
			lt++;
		}
		}catch(Exception e){
			e.printStackTrace();
			error = "数据异常，请重新操作或系统管理员联系。";
		}finally{
			if(!message.equals("")){
				mv.addObject("message",message);
				mv.setViewName("/admin/member_message");
			}else if(!error.equals("")){
				if(lt>0){
				mv.setViewName("/admin/error_login");
				}else{
					mv.addObject("error",error);
					mv.setViewName("/admin/member_error");
	
				}
			}
		}
		return mv;
	}
	
	@RequestMapping(value="/admin/member_psw_update",method=RequestMethod.POST)
	public ModelAndView admin_member_psw_update(HttpServletRequest request,@ModelAttribute Member member){
		Admin sys_admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String message = "";
		String error ="";
		int lt = 0;
		ModelAndView mv = new ModelAndView();
		try{
		if(sys_admin!=null){
				Timestamp date = new Timestamp(new Date().getTime());
				String[] str = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g"};
				String psw = "";
				Random ran2 = new Random(3);
				int codeLength = ran2.nextInt(3)+6;
				for(int i=0;i<codeLength;i++){
					int charNum = (int) Math.floor(Math.random() * 17);
					psw +=str[charNum];
				}
				Member update_member = new Member();
				update_member.setId(member.getId());
				update_member.setPassword(MD5.GetMD5Code(psw));
				update_member.setEndTime(date);
				update_member.setVersion(member.getVersion());
				int save = memberService.update(update_member);
			if(save>0){
				message  ="会员登陆密码重置成功，重置密码为随机"+codeLength+"位数："+psw+"，请妥善保管！";
			}else{
				error  ="会员登陆密码重置失败，原因可能是最近有最新信息更新，请重新操作！";
			}
			
		}else{
			error  ="用户未登录，请先登陆！";
			lt++;
		}
		}catch(Exception e){
			e.printStackTrace();
			error = "数据异常，请重新操作或系统管理员联系。";
		}finally{
			if(!message.equals("")){
				mv.addObject("message",message);
				mv.setViewName("/admin/member_message");
			}else if(!error.equals("")){
				if(lt>0){
				mv.setViewName("/admin/error_login");
				}else{
					mv.addObject("error",error);
					mv.setViewName("/admin/member_error");
	
				}
			}
		}
		return mv;
	}
	
	@RequestMapping(value="/admin/member_psw2_update",method=RequestMethod.POST)
	public ModelAndView admin_member_psw2_update(HttpServletRequest request,@ModelAttribute Long id){
		Admin sys_admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String message = "";
		String error ="";
		int lt = 0;
		ModelAndView mv = new ModelAndView();
		try{
		if(sys_admin!=null){
			Member member = memberService.findById(id);
			if(member!=null){
				Timestamp date = new Timestamp(new Date().getTime());
				String[] str = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g"};
				String psw = "";
				Random ran2 = new Random(3);
				int codeLength = ran2.nextInt(3)+6;
				for(int i=0;i<codeLength;i++){
					int charNum = (int) Math.floor(Math.random() * 17);
					psw +=str[charNum];
				}
				Member update_member = new Member();
				update_member.setId(member.getId());
				update_member.setPassword2(MD5.GetMD5Code(psw));
				update_member.setEndTime(date);
				update_member.setVersion(member.getVersion());
				int save = memberService.update(update_member);
			if(save>0){
				message  ="会员支付密码重置成功，重置密码为随机"+codeLength+"位数："+psw+"，请妥善保管！";
			}else{
				error  ="会员支付密码重置失败，原因可能是最近有最新信息更新，请重新操作！";
			}
			}else{
				error  ="会员信息获取失败，请核对！";
			}
		}else{
			error  ="用户未登录，请先登陆！";
			lt++;
		}
		}catch(Exception e){
			e.printStackTrace();
			error = "数据异常，请重新操作或系统管理员联系。";
		}finally{
			if(!message.equals("")){
				mv.addObject("message",message);
				mv.setViewName("/admin/member_message");
			}else if(!error.equals("")){
				if(lt>0){
				mv.setViewName("/admin/error_login");
				}else{
					mv.addObject("error",error);
					mv.setViewName("/admin/member_error");
	
				}
			}
		}
		return mv;
	}

	
	@RequestMapping(value="/admin/member_other",method=RequestMethod.GET)
	public ModelAndView admin_member_other(HttpServletRequest request,@RequestParam("id") Long id){
		Admin sys_admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String error= "";
		ModelAndView mv = new ModelAndView();
		try{
			Member member = memberService.findAllById(id);
		if(sys_admin!=null){
			int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
			request.getSession().setAttribute("token", String.valueOf(token));
			mv.addObject("member", member);
			mv.setViewName("/admin/member_other");
		}else{
			mv.setViewName("/admin/error_login");
		}
		}catch(Exception e){
			e.printStackTrace();
			error = "数据异常，请重新操作或系统管理员联系。";
		}finally{
			if(!error.equals("")){
				mv.addObject("error", error);
				mv.setViewName("/admin/member_error");;
			}
		}
		return mv;
	}

}
