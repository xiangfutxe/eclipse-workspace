//引入pdf.js之后
var url = 'pdf/demo1.pdf';

//文件选择
function selectedFile(event) {
  //将本地pdf变成数据流
  event = event || window.event;
  var oFile = event.target.files[0];
  if (window.FileReader) {
      var reader = new FileReader();
      reader.readAsDataURL(oFile);
      //监听文件读取结束后事件
      reader.onloadend = function (e) {
          // $(".img").attr("src",e.target.result);    //e.target.result就是最后的路径地址 
          url = e.target.result;
      };
  }

}



function createPdfContainer(id,className) {
  var pdfContainer = document.getElementById('pdf-container');
  var canvasNew =document.createElement('canvas');
  canvasNew.id = id;
  canvasNew.className = className;
  var br =document.createElement('br');  //添加上br元素，防止页面变大时候pdf并排显示
  pdfContainer.appendChild(br);
  pdfContainer.appendChild(canvasNew);
};

//渲染pdf
//建议给定pdf宽度
function renderPDF(pdf,i,id) {
  pdf.getPage(i).then(function(page) {

      var scale = 0.5;   //pdf大的放大倍数
      var viewport = page.getViewport(scale);

      //
      //  准备用于渲染的 canvas 元素
      //

      var canvas = document.getElementById(id);
      var context = canvas.getContext('2d');
      canvas.height = viewport.height;
      canvas.width = viewport.width;

      //
      // 将 PDF 页面渲染到 canvas 上下文中
      //
      var renderContext = {
          canvasContext: context,
          viewport: viewport
      };
      page.render(renderContext);
  });
};
//创建和pdf页数等同的canvas数
function createSeriesCanvas(num,template) {
  var id = '';
  for(var j = 1; j <= num; j++){
      id = template + j;
      createPdfContainer(id,'pdfClass');
  }
}
//读取pdf文件，并加载到页面中
function loadPDF(fileURL) {
  //清除所有元素 
  var pdfContainer = document.getElementById('pdf-container');
  var childs = pdfContainer.childNodes;
  for(var i = childs.length - 1; i >= 0; i--) {
      pdfContainer.removeChild(childs[i]);
  }
  // pdfContainer.clear;
  PDFJS.getDocument(fileURL).then(function(pdf) {
      //用 promise 获取页面
      var id = '';
      var idTemplate = 'cw-pdf-';
      var pageNum = pdf.numPages;
      console.log("页码："+pageNum)
      //根据页码创建画布
      createSeriesCanvas(pageNum,idTemplate);
      //将pdf渲染到画布上去
      for (var i = 1; i <= pageNum; i++) {
          id = idTemplate + i;
          renderPDF(pdf,i,id);
      }

  });
}

function savePdf(){ 
  loadPDF(url);
}

function fsubmit() {
  var form=document.getElementById("form1");
  var fd =new FormData(form); 
  $.ajax({
      url: "server.php",
      type: "POST",
      data: fd,
      processData: false,  // 告诉jQuery不要去处理发送的数据
      contentType: false,   // 告诉jQuery不要去设置Content-Type请求头
      success: function(response,status,xhr){
          console.log(xhr);
          var json=$.parseJSON(response);
          var result = '';
          result +="个人信息：<br/>name:"+json['name']+"<br/>gender:"+json['gender']+"<br/>number:"+json['number'];
          result += '<br/>头像：<img src="' + json['photo'] + '" height="100" style="border-radius: 50%;" />';
          $('#result').html(result);
      }
  });
  return false;
}