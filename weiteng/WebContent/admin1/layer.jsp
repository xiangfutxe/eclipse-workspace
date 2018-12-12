<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
<head>
<title>开始使用layer</title>
</head>
<body>
<button id="test1">小小提示层</button>

你必须先引入jQuery1.8或以上版本
<script src="../js/jquery.js"></script>
<script src="../js/layer.js"></script>
<script>
$('#test1').on('click', function(){
 layer.open({
  type: 2, 
  content:  ['http://sentsin.com', 'no'] //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
});
});

</script>
当然，你也可以写在外部的js文件
</body>
</html>   