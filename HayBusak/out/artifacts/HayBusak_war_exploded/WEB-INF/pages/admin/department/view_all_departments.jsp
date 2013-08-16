<%--
  Created by IntelliJ IDEA.
  User: Serozh
  Date: 26.07.13
  Time: 2:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<style>
</style>
<script>

    $(document).ready(function () {
    });


</script>

<s:if test="#session.info != null">
    <s:property value="#session.info"/>
    <s:set var="#session.info" value=""/>
</s:if> view all


<div id="universities_block" class="sblock" style="float: left;
                  width: 479;
                  margin-left: 7%;
                  background-color: #ffe3ff;">
    <div class="corners">
        <s></s>
        <b></b>
        <u></u>
        <i></i>
    </div>
    <div class="title"
         style="width: 470; background: url('/icon/general/left_menu/sblock-sprite.png') no-repeat scroll -16px transparent ">
        <%--<s:property value="getText('menu.universities')"/> <s:property value="getText('button.add')"/>--%>
        <b style="font-size: 1.3em;">
            <s:property value="getText('model.departments')"/>
        </b>
    </div>

    <s:if test="#session.info != null">
        <s:property value="#session.info"/>
        <s:set var="#session.info" value=""/>
    </s:if>
    <s:if test="#session.err_message != null">
        <s:property value="#session.err_message"/>
        <s:set var="#session.err_message" value=""/>
    </s:if>

    <div class="universities_content">


        <table style="width: 100%" class="view_departments">

            <tr>
                <td><s:property value="getText('model.universities')"/></td>
                <td>
                    <s:select list="%{#session.universities}" name="universityId"
                              listKey="id" listValue="name"/>
                    <s:property value="#session.title"/>
                </td>
            </tr>

            <s:iterator var="department" value="departments">

                <tr>
                    <td>Name is ::</td>
                    <td>
                        <s:property value="#department.name"/>
                        <s:url action="remove_department" var="remove_department">
                            <s:param name="id"><s:property value="#department.id"/></s:param>
                        </s:url>

                        <p>
                            <a href="${remove_department}"><s:property value="getText('button.delete')"/></a>
                        </p>


                    </td>
                    <tr>
                        <td colspan="2">
                            <p>
                                <s:url action="pre_edit_department_info" var="pre_edit_department_info">
                                    <s:param name="id"><s:property value="#department.id"/></s:param>
                                </s:url>
                                <a href="${pre_edit_department_info}">
                                    <s:property value="getText('button.view')"/> /
                                    <s:property value="getText('button.edit')"/>
                                    <s:property value="getText('link.lang.other')"/>
                                </a>
                            </p>
                        </td>
                    </tr>
                </tr>

                <tr>
                    <td colspan="2">
                        <div class="content_line"></div>
                    </td>
                </tr>

            </s:iterator>

        </table>
    </div>
</div>
</div>
