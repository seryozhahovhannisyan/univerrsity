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
    .edit_faculty_info {
        display: none;
    }
</style>
<script>

    $(document).ready(function () {
        edit_toggle();
    });

    function edit_toggle() {
        $(".edit_faculty_info_hidden").live("click", function () {
            $(".edit_faculty_info").show();
            $id = $(this).find(".id").val();
            $lang_id = $(this).find(".lang_id").val();
            $(".faculty_lang_id").val($lang_id);
            $(".faculty_id").val($id);
            $(".view_faculty_info").hide();
        })

        $(".back_info").live("click", function () {
            $(".view_faculty_info").show();
            $(".edit_faculty_info").hide();
        })
    }


</script>

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
            <s:property value="getText('model.faculties')"/>
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


        <table style="width: 100%" class="edit_faculty_info">

            <s:form action="edit_faculty_info" method="POST">

                <input type="hidden" class="faculty_id" name="id" />
                <input type="hidden" class="faculty_lang_id" name="language"/>


                <tr>
                    <td>
                        Name
                    </td>
                    <td>
                        <s:textfield  name="name" style="width: 100%;margin-top: 10"/>
                    </td>
                </tr>

                <tr>
                    <td>
                        Info
                    </td>
                    <td>
                        <s:textarea name="info" style="width: 100%;margin-top: 10;height: 150px;"/>
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

        <table style="width: 100%" class="view_faculty_info">

            <s:iterator var="info" value="faculty.facultyInfos">

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
                    </td>
                </tr>

                <tr>
                    <td>
                        Info
                    </td>
                    <td>
                        <p><s:property value="#info.info"/></p>

                        <p>
                            <a href="#" class="edit_faculty_info_hidden">
                                <input type="hidden" class="id" value="<s:property value="#info.facultyId"/>">
                                <input type="hidden" class="lang_id" value="<s:property value="#info.language.value"/>">
                                <s:property value="getText('button.edit')"/>
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
