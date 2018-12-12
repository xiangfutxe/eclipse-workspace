  function getByteLen(val) { 
var len = 0; 
for (var i = 0; i < val.length; i++) { 
if (val[i].match(/[^\x00-\xff]/ig) != null) //È«½Ç 
len += 2; 
else 
len += 1; 
} 
return len; 
} 