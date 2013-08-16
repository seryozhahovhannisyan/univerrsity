<%--
  Created by IntelliJ IDEA.
  User: Serozh
  Date: 6/6/13
  Time: 4:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div style="margin-left: 20%;margin-top: 5%">
    <s:form action="registration" method="post"  enctype="multipart/form-data" >
        <tr style="margin-bottom: 5%">
            <td><img src="" alt="" width="150" height="200"> </td>
            <td><input type="file" name="photo"></td>
        </tr>
        <s:textfield name="name" key="label.user.name"/>
        <s:textfield name="surname" key="label.user.surname"/>
        <s:textfield name="email" key="label.user.email"/>
        <s:textfield name="password" key="label.user.password"/>
        <s:textfield name="address" key="label.user.address"/>
        <s:textfield name="phone" key="label.user.phone"/>
        <%--<s:file name="photo" label="photo"/>--%>
            <td>
                <input type="submit" value="<s:property value="getText('button.submit')"/> ">
            </td>
            <td>
                <input type="reset" value="<s:property value="getText('button.reset')"/> ">
            </td>
        </tr>
    </s:form>
</div>
