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
import com.ssm.pojo.News;
import com.ssm.service.NewsService;
import com.utils.Constants;
import com.utils.Pager;
import com.utils.StringUtil;

@Controller("newsController")
@RequestMapping("")
public class NewsController {
	
	@Autowired
	private NewsService newsService = null;
	
	@RequestMapping("/news")
	public ModelAndView news_list(HttpServletRequest request){
		 String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		 String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		ModelAndView mv = new ModelAndView();
		News news = new News();
		news.setState(1);
		int pageNo = 1;
		int pageSize = 10;
		if(!pageNoStr.equals("")) pageNo = Integer.valueOf(pageNoStr);
		if(!pageSizeStr.equals("")) pageSize = Integer.valueOf(pageSizeStr);
		Pager pager= new Pager();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		pager = newsService.findByPager(news, pager);
		mv.addObject("pager",pager);
		mv.setViewName("news");
		return mv;
	}
	
	@RequestMapping(value="/news_detail",method=RequestMethod.GET)
	public ModelAndView news_detail(@RequestParam("id") Long id){
		ModelAndView mv = new ModelAndView();
		News news = newsService.findById(id);
		String message="";
		if(news==null){
			message  ="新闻信息已过期或此链接不存在！";
			mv.addObject("message",message);
		}
		mv.addObject("news",news);
		mv.setViewName("news_detail");
		return mv;
	}
	
	@RequestMapping("/admin/news")
	public ModelAndView admin_news_list(HttpServletRequest request){
		 String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		 String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		ModelAndView mv = new ModelAndView();
		News news = new News();
		news.setState(1);
		int pageNo = 1;
		int pageSize = 10;
		if(!pageNoStr.equals("")) pageNo = Integer.valueOf(pageNoStr);
		if(!pageSizeStr.equals("")) pageSize = Integer.valueOf(pageSizeStr);
		Pager pager= new Pager();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		pager = newsService.findByPager(news, pager);
		mv.addObject("pager",pager);
		mv.setViewName("/admin/news_list");
		return mv;
	}
	
	@RequestMapping(value="/admin/news_detail",method=RequestMethod.GET)
	public ModelAndView admin_news_detail(@RequestParam("id") Long id){
		ModelAndView mv = new ModelAndView();
		News news = newsService.findById(id);
		String message="";
		if(news==null){
			message  ="新闻信息已过期或此链接不存在！";
			mv.addObject("message",message);
		}
		mv.addObject("news",news);
		mv.setViewName("/admin/news_detail");
		return mv;
	}
	
	@RequestMapping("/admin/news_add")
	public ModelAndView admin_news_add(HttpServletRequest request){
		Admin sys_admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		ModelAndView mv = new ModelAndView();
		int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
		if(sys_admin!=null){
			request.getSession().setAttribute("token", String.valueOf(token));
			mv.setViewName("/admin/news_add");
		}else{
			mv.setViewName("/admin/error_login");
		}
		return mv;
	}
	
	@RequestMapping(value="/admin/news_save",method=RequestMethod.POST)
	public ModelAndView admin_news_save(HttpServletRequest request,@ModelAttribute News news){
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
			news.setPublisher(admin.getAdminName());
			news.setEntryTime(date);
			news.setEndTime(date);
			news.setState(1);
			int save = newsService.insertNews(news);
			if(save>0){
				message  ="恭喜你，新闻信息保存成功！";
			}else{
				error  ="非常遗憾，新闻信息保存失败！";
			}
			
			}else{
				error  ="请勿重复提交数据！";
			}
		}else{
			error  ="用户未登录，请先登陆！";
		}
		if(!message.equals("")){
			mv.addObject("message",message);
			mv.setViewName("/admin/news_message");
		}else if(!error.equals("")){
			mv.addObject("error",error);
			mv.setViewName("/admin/news_error");
		}
			return mv;
	}
	
	@RequestMapping("/admin/news_edit")
	public ModelAndView admin_news_edit(HttpServletRequest request){
		Admin sys_admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		ModelAndView mv = new ModelAndView();
		int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
		String error="";
		if(sys_admin!=null){
			String id = request.getParameter("id");
			if(!StringUtil.notNull(id).equals("")){
				News news = newsService.findById(new Long(id));
				if(news!=null){
					mv.addObject("news",news);
				}else{
					error = "很遗憾，未获得相应ID的新闻内容！";
				}
			}else{
				error = "很遗憾，相应新闻的ID获取失败！";
			}
			
			if(error.equals("")){
				request.getSession().setAttribute("token", String.valueOf(token));
				mv.setViewName("/admin/news_edit");
			}else{
				mv.addObject("error",error);
				mv.setViewName("/admin/news_error");
			}
		}else{
			mv.setViewName("/admin/error_login");
		}
		return mv;
	}
	
	@RequestMapping(value="/admin/news_update",method=RequestMethod.POST)
	public ModelAndView admin_news_update(HttpServletRequest request,@ModelAttribute News news){
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
				news.setEndTime(date);
				int update = newsService.updateNews(news);
				if(update>0){
					message  ="恭喜你，新闻信息修改成功！";
				}else{
					error  ="非常遗憾，新闻修改失败，请重新核对！";
				}
			
			}else{
				error  ="请勿重复提交数据！";
			}
		}else{
			error  ="用户未登录，请先登陆！";
		}
		if(!message.equals("")){
			mv.addObject("message",message);
			mv.setViewName("/admin/news_message");
		}else if(!error.equals("")){
			mv.addObject("error",error);
			mv.setViewName("/admin/news_error");
		}
			return mv;
	}
	
	@RequestMapping(value="/admin/news_del",method=RequestMethod.POST)
	public ModelAndView admin_news_del(HttpServletRequest request,@ModelAttribute News news){
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
					int delnum = newsService.deleteAll(str);
					if(delnum>0){
						message  ="恭喜你，新闻信息批量删除成功！";
					}else{
						error = "非常抱歉，新闻信息批量删除失败！";
					}
				}else{
					error  ="非常抱歉，未能获取需要删除的新闻信息！";
				}
			}else{
				error  ="用户未登录，请先登陆！";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			error  ="非常遗憾，数据操作有误！";
		}finally{
			if(!message.equals("")){
				mv.addObject("message",message);
				mv.setViewName("/admin/news_message");
			}else if(!error.equals("")){
				mv.addObject("error",error);
				mv.setViewName("/admin/news_error");
			}
		}
			return mv;
	}
	
	@RequestMapping(value="/admin/news_remove",method=RequestMethod.POST)
	public ModelAndView admin_news_remove(HttpServletRequest request,@ModelAttribute News news){
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
					int delnum = newsService.removeAll(str);
					if(delnum>0){
						message  ="恭喜你，新闻信息批量下架成功！";
					}else{
						error = "非常抱歉，新闻信息批量下架失败！";
					}
				}else{
					error  ="非常抱歉，未能获取需要下架的新闻信息！";
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
				mv.setViewName("/admin/news_message");
			}else if(!error.equals("")){
				
				mv.addObject("error",error);
				if(lt>0)
					mv.setViewName("/admin/error_login");
				else
				mv.setViewName("/admin/news_error");
			}
		}
			return mv;
	}
}
