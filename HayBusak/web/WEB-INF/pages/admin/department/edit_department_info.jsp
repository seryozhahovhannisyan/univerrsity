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
    .edit_department_info {
        display: none;
    }
</style>
<script>

    $(document).ready(function () {
        edit_toggle();
    });

    function edit_toggle() {
        $(".edit_info").live("click", function () {
            $(".edit_department_info").show();
            $id = $(this).find(".id").val();
            $lang_id = $(this).find(".lang_id").val();
            $(".department_lang_id").val($lang_id);
            $(".department_id").val($id);
            $(".view_department_info").hide();
        })

        $(".back_info").live("click", function () {
            $(".view_department_info").show();
            $(".edit_department_info").hide();
        })
    }


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


        <table style="width: 100%" class="edit_department_info">

            <s:form action="edit_department_info" method="POST">

                <input type="hidden" class="department_id" name="id" />
                <input type="hidden" class="department_lang_id" name="language"/>

                <tr>
                    <td>
                        Name
                    </td>
                    <td>
                        <s:textfield  name="name" style="width: 100%;margin-top: 10"/>
                    </td>
                </tr>

                <tr style="margin: 10" class="submit_actions">
                    <td>
                        <input type="submit" value="<s:property value="getText('button.submit')"/>">
                    </td>

                    <td>
                        <input type="reset" value="<s:property value="getText('button.reset')"/>">
                        <a href="#" class="back_info">back</a>
                    </td>
                </tr>

            </s:form>
        </table>

        <table style="width: 100%" class="view_department_info">

            <s:iterator var="info" value="department.departmentInfos">

                <tr>
                    <td>

                    </td>
                    <td>
                        <s:property value="#info.language.title"/>
                    </td>
                </tr>

                <tr>
                    <td>
                        Name
                    </td>
                    <td>
                        <s:property value="#info.name"/>
                        <p>
                            <a href="#" class="edit_info">
                                <input type="hidden" class="id" value="<s:property value="#info.departmentId"/>">
                                <input type="hidden" class="lang_id" value="<s:property value="#info.language.value"/>">
                                <s:property value="getText('button.view')"/> /
                                <s:property value="getText('button.edit')"/>
                                <s:property value="getText('link.lang.other')"/>
                            </a>
                        </p>
                    </td>
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
