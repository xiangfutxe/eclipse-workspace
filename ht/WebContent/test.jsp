<html> 
<style> 
.unchecked{ 
border: 1px solid gray; 
padding: 6px 6px 6px 6px; 
} 
.checked{ 
border: 2px solid #c00; 
padding: 6px 6px 6px 6px; 
} 
</style> 
<script type="text/javascript"> 
var obj={ 
　　 colorSpan:"", 
　　 sizeSpan:"" 
}; 
function change(span) 
{ 
　 $('span[name="'+$(span).attr('name')+'"]').each(function(){ 
　　　　　　 if(this.checked&&this!=span) 
　　　　 { 
　　　　　　　 this.className="unchecked"; 
　　　　　　　 this.checked=false; 
　　　　 }　　　　　　　　 
　 }); 
　 obj[$(span).attr('name')]=span.innerHTML; 
　 span.className="checked"; 
　 span.checked=true; 
　 select(); 
} 
function select() 
{ 
　　 var html=''; 
　 for(var i in obj) 
　 { 
　　　　 if(obj[i]!='') 
　　　　 { 
　　　　　　 html+='<font color=orange$amp;>quot;$'+ obj[i]+'"</font> 、'; 
　　　　　 } 
　 } 
　 html='<b>已选择:</b> '+html.slice(0,html.length-1); 
　 $('#resultSpan').html(html); 
　　 
} 
</script> 
<body> 
<br /> 
<div> 
颜色: <span class='unchecked' name='colorSpan' checked='false' onclick='change(this);' >卡其格</span> <span class='unchecked' name='colorSpan' checked='false' onclick='change(this);' >黑白格</span> 
</div> 
<br /> 
<br /> 
<div> 
尺码: <span class='unchecked' name='sizeSpan' checked='false' onclick='change(this);' >S</span> <span class='unchecked' name='sizeSpan' checked='false' onclick='change(this);' >M</span> <span class='unchecked' name='sizeSpan' checked='false' onclick='change(this);' >L</span> 
</div> 
<br /> 
<br /> 
<div> 
<b>提示:</b> <span id='resultSpan'$amp;>amp;$lt;/span> 
</div> 
</body> 
</html>