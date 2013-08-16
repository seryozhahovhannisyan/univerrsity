<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:if test="#session.info != null">
    <s:property value="#session.info"/>
    <s:set var="#session.info" value=""/>
</s:if>
<s:if test="#session.err_message != null">
    <s:property value="#session.err_message"/>
    <s:set var="#session.err_message" value=""/>
</s:if>

<div class="login">
    <div id="marquee" style="overflow: hidden; position: relative;">
        <div style="position: absolute; white-space: pre; left: -108px;">
            <p class="wop"></p>
        </div>
        <div style="position: absolute; white-space: pre; left: 239px;">
            <p class="wop"></p>
        </div>
    </div>


    <div id="captcha_div">
    </div>

    <div style="margin-top: 2px;">

        <a id="refuseLink" href="logout.htm"><s:text name="button.logout"/> </a>

    </div>
</div>

