<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PDF TEST</title>
<script src="js/pdf.js"></script>
<script src="js/pdf.worker.js"></script>
<script src="js/jquery.js"></script>

<style>
        .pdfClass{
            border:1px solid black;
        }
    </style>
</head>
<body>
<h1>全部显示</h1> 
<div>
    <input id="upload" type="file" accept="application/pdf" onchange="selectedFile(event)"  /> <br>
    <button id="save" onclick="savePdf()">Save</button>
</div>
<div class="wrapper" id="pdf-container"></div>

</body>

<script src="js/pdfjs.js"></script>
</html>