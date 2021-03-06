<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.ecside.org" prefix="ec" %>
<%@ page import="demo.TestData"  %>
<%@ page import="demo.common.CommonDictionary"  %>
<%

String webapp=request.getContextPath();
// begin: 下面这几步通常在action/servlet里完成,这个例子偷懒了,直接取了 :) 
request.setAttribute("records", TestData.getTestData(request));
request.setAttribute("GENDER_MAP", CommonDictionary.GENDER);
request.setAttribute("USERROLE_MAP", CommonDictionary.USERROLE);
// end.


%>
<html>

<head>
<jsp:include page="common_head.jsp" flush="true" />
</head>


<body style="margin:10px;" >


<ec:table items="records" var="record" retrieveRowsCallback="process" 
action="${pageContext.request.contextPath}/demo_ch.jsp"
title="用户列表" 


xlsFileName="用户列表.xls" 
csvFileName="用户列表.csv"
showPrint="true" 
useAjax="true"
doPreload="false"
width="100%" 

classic="true"

rowsDisplayed="10"
showHeader="false" 
>

<ec:extendrow location="top">
	<tr class="tableHeaderH">
		<td rowspan="2" >序号</td>
		<td colspan="3" >基本信息</td>
		<td rowspan="2" >角色</td>
		<td colspan="1" >其他信息</td>
	</tr>
	<tr class="tableHeaderH">
		<td >用户名</td>
		<td >密码</td>
		<td >电子信箱</td>
		<td >性别</td>	</tr>
</ec:extendrow>

<ec:row>
	<ec:column width="50" property="_0" title="序号" value="${GLOBALROWCOUNT}" />
	<ec:column width="80" property="USERROLE" title="角色"  mappingItem="USERROLE_MAP" />
	<ec:column width="120" property="USERNAME" title="用户名" />
	<ec:column width="100" property="PASSWD" title="密码"  />
	<ec:column width="120" property="EMAIL" title="电子信箱" />
	<ec:column width="70" property="GENDER" title="性别"  mappingItem="GENDER_MAP" />
</ec:row>

<ec:extendrow>
<tr style="background-color:#ffe3ee" title="这一行里可以是任意的 html代码">
<td colspan="2">扩展行</td>
<td><%="任何信息0"%></td>
<td><%="任何信息1"%></td>
<td><%="任何信息2"%></td>
<td><%="任何信息3"%></td>
</tr>
</ec:extendrow>

</ec:table>
	

</body>


</html>