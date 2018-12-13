<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.pojo.News"%>
<%@ page import="java.util.List"%>
<%@ page import="com.utils.StringUtil"%>

<!--second -->
<div class="scd_ml">
            	<ul class="sidenav">
            	 <a href="#"><img alt="" src="images/left_1.png"></a>
            	<a href="#"><img alt="" src="images/left_1_1.jpg" width="300px"></a>
                <li>旗袍是民国的旗人之袍，盛行于三四十年代。</li>
                <li>二十世纪二十年代看作旗袍流行的起点，三十年代</li>
                <li>它到了顶峰状态，很快从发源地上海风靡至中国各</li>
                <li>地。旗袍追随着时代，承载着文明，以其流动的旋</li>
                <li>律、潇洒的画意与浓郁的诗情，表现出中华女性贤</li>
                <li>淑、典雅、温柔、清丽的性情与气质。旗袍连接起</li>
                <li>过去和未来，连接起生活与艺术，将美的风韵洒满</li>
                <li>人间。</li>
                </ul>
                  <ul>
            	 <li class="letf">&nbsp;</li>
            	 </ul>
                <ul  class="sidenav">
                <a href="#"><img alt="" src="images/left_2.png"></a>
                <%
List<News> left_news = (List<News>)request.getAttribute("left_news");
if(left_news!=null){
if(left_news.size()>0){
for(int i=0;i<left_news.size();i++){
String title = StringUtil.notNull(left_news.get(i).getTitle());
%>
<li><a href="NewsServlet.do?method=list&id=<%=left_news.get(i).getId()%>"><%if(title.length()>19){%><%=title.substring(0, 19)%>...<%}else{ %>
<%=title%><%} %></a></li>
<%}
}else{ %><li>&nbsp;</li><%} 
}else{ %>
 <li>&nbsp;</li>
                <%} %></ul>
                  <ul>
            	 <li class="letf">&nbsp;</li>
            	 </ul>
                 <ul  class="sidenav">
               <a href="#"><img alt="" src="images/left_3.png"></a>
                <li>&nbsp;</li></ul>
              
            </div>
