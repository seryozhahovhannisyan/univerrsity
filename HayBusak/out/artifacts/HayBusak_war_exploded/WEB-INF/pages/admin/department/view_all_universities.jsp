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
    .wwFormTable {
        display: none;
    }
</style>
<script>

    $(document).ready(function () {
        edit_toggle();
    });

    function edit_toggle() {
        $(".edit").live("click",function(){
            $id = $(this).find(".hidden").val();
            $photo = $(this).find(".hidden_img").val();
            $(".edit_university_id").val($id);
            $(".edit_university_img").val($photo);
            $(".wwFormTable").show();
            $(".view_universities").hide();
        })

        $(".back").live("click",function(){
            $(".view_universities").show();
            $(".wwFormTable").hide();
        })
    }


</script>
<s:text name="button.view"/>
<s:text name="button.all"/>

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
            <s:text name="model.universities"/>
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

        <table class="edit_university_form">
            <s:form action="edit_university" method="post" enctype="multipart/form-data" theme="xhtml" >
                
                <input type="hidden" name="id" class="edit_university_id"/>
                
                <tr>
                    <td>
                        <img  class="edit_university_img" src=''width="96" height="96">
                    </td>
                    <td>
                        <s:file name="logo" class="add_university_form_logo" style="margin-top: 10"/>
                    </td>
                </tr>

                <tr>
                    <td>
                        Phone
                    </td>
                    <td>
                        <s:textfield name="phone" style="width: 100%;margin-top: 10"/>
                    </td>
                </tr>

                <tr>
                    <td>
                        Email
                    </td>
                    <td>
                        <s:textfield name="email" style="width: 100%;margin-top: 10"/>
                    </td>
                </tr>

                <tr>
                    <td>
                        Link
                    </td>
                    <td>
                        <s:textfield name="link" style="width: 100%;margin-top: 10"/>
                    </td>
                </tr>

                <tr style="margin: 10" class="submit_actions">
                    <td>
                        <input type="submit" value="<s:text name="button.submit"/>">
                    </td>

                    <td>
                        <input type="reset" value="<s:text name='button.reset'/>">
                        <a href="#" class="back">Back</a>
                    </td>
                </tr>

            </s:form>
        </table>
        <table style="width: 100%" class="view_universities">
            <s:iterator var="university" value="#session.universities">

                <tr>
                    <td><img src='<%=request.getContextPath()%><s:property value="#university.logoPath"/>' width="96"
                             height="96"></td>
                    <td>
                        <p>Name is :: <s:property value="#university.name"/>
                            <a href="#" class="edit">
                                <s:text name="button.edit"/>
                                <input type="hidden" class="hidden" value="<s:property value="#university.id"/>">
                                <input type="hidden" class="hidden_img" value="<s:property value="#university.logoPath"/>">
                            </a>
                        </p>

                        <s:url action="remove_university" var="remove_university">
                            <s:param name="id"><s:property value="#university.id"/></s:param>
                        </s:url>

                        <p><a href="${remove_university}"><s:text name="button.delete"/></a></p>
                    </td>
                </tr>

                <tr>
                    <td>
                        Abbreviation
                    </td>
                    <td>
                        <p><s:property value="#university.abbreviation"/></p>
                    </td>
                </tr>

                <tr>
                    <td>
                        Phone
                    </td>
                    <td>
                        <p><s:property value="#university.phone"/></p>
                    </td>
                </tr>

                <tr>
                    <td>
                        email
                    </td>
                    <td>
                        <p><s:property value="#university.email"/></p>
                    </td>
                </tr>

                <tr>
                    <td>
                        link
                    </td>
                    <td>
                        <p><s:property value="#university.link"/></p>
                    </td>
                </tr>

                <tr>
                    <td>
                        info
                    </td>
                    <td>
                        <p><s:property value="#university.info"/></p>

                        <p>
                            <a href="pre_edit_university_info.htm?id=<s:property value="#university.id"/>">
                                <s:text name="button.view"/>/
                                <s:text name="button.edit"/>
                                <s:text name="link.lang.other"/>
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
