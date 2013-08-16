<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <link rel="icon"
          type="image/png"
          href="<%=request.getContextPath()%>/icon/general/logo_16x16.png"/>

    <title><s:property value="getText('pages.home.title')"></s:property></title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.timers.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.dateFormat-1.0.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/modalPopLite.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/general.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-ui.js"></script>

    <link href="<%=request.getContextPath()%>/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/css/left_menu.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/css/general.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/css/header.css" rel="stylesheet" type="text/css"/>
    <%--<link href="<%=request.getContextPath()%>/css/quiz.css" rel="stylesheet" type="text/css"/>--%>
</head>
<body>
<div class="header_" style="background-color: #eae7ff;">
    <tiles:insertAttribute name="head"/>
</div>
<tiles:insertAttribute name="admin_actions"/>
<div class="menu">
    <tiles:insertAttribute name="menu"/>
</div>
<div class="content">
    <tiles:insertAttribute name="main"/>
</div>
<div id="banner">
    <tiles:insertAttribute name="banner"/>
</div>
<div class="page_footer">
    <tiles:insertAttribute name="foot"/>
</div>
</body>
</html>