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
    <form id="subscriptionForm" style="height: 234px;" method="post" action="login.htm">
        <input class="email" type="text"
               placeholder="<s:property value="getText('label.user.email')"/>"
               maxlength="50" style="color:#bbb;" name="email">
        <br>
        <span class="errorFieldSub"></span>
        <input class="password"
               type="password"
               placeholder="<s:property value="getText('label.user.password')"/>"
               style="color:#bbb;"
               name="subEmail">
        <br>
        <span class="errorFieldSub"></span>

        <div id="captcha_div">
            <img id="captcha_image" class="form_captcha" width="80" height="21" title="" alt="captcha"
                 src="">
            <img id="captchaRefresh" title="Refresh" alt="Refresh"
                 src="<%=request.getContextPath()%>/icon/general/header/captcha_refresh.png">
            <input id="captcha_text" type="text" maxlength="7" name="captcha">
            <br>
        </div>

        <div style="margin-top: 2px;">
            <button id="doSubscribe"><s:text name="button.submit"/></button>

            <input id="resetSubscribe"
                   type="reset"
                   value="<s:text name="button.reset"/>">
            <br>

            <a id="refuseLink" href="preRegistration.htm"><s:text name="button.reg"/> </a>

        </div>
    </form>
</div>

