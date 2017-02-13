<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 
<html> 
<head> 
　　<title>演示${title}</title> 
   <link href="/js/test.css" rel="stylesheet" type="text/css" />
</head> 
<body onclick="show()"> 
   
   <div class="main">
   <div id="header" style="display:none">
   		<h2>这是header</h2>
   		<a href="">下载合同</a>
   </div>
　　<#list users as user> 
　　测试..............username : ${user.nickname} 
　　测试..............password : ${user.password} 
　　</#list>
	</div>
	<h1>HTML to PDF</h1>

 <p>

     <span class="itext">itext</span> 5.4.2 <span class="description"> converting HTML to PDF</span>

 </p>

 <table>

   <tr>

         <th class="label">Title</th>

         <td>iText - Java HTML to PDF</td>

     </tr>

     <tr>

         <th>URL</th>

         <td>http://hmkcode.com/itext-html-to-pdf-using-java</td>

     </tr>

 </table>
<script>
  
  function show(){
  	document.getElementById("header").style.display="block";
  }
  
</script> 
</body>

<script>
  
  function show(){
  	document.getElementById("header").style.display="block";
  }
</script> 
</html>