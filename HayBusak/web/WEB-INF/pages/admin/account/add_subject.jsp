<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="S" uri="/struts-tags" %>
<style>
    .edit_subject {
        display: none;
    }
</style>
<script>

    $(document).ready(function () {
    });

</script>

<div id="main">

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
        <div class="title" style="width: 470;
        background: url('/icon/general/left_menu/sblock-sprite.png') no-repeat scroll -16px transparent ">
            <b style="font-size: 1.3em;">
                <s:text name="button.add"/>
                <s:text name="model.subject"/>
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
            <table style="width: 100%">

                <s:form action="add_subject" method="post">

                    <tr>
                        <td>
                            name
                        </td>
                        <td>
                            <s:textfield name="name" style="width: 100%;margin-top: 10"/>
                        </td>
                    </tr>


                    <tr style="margin: 10" class="submit_actions">
                        <td>
                            <input type="submit" value="<s:text name="button.submit"/>">
                        </td>

                        <td>
                            <input type="reset" value="<s:text name="button.reset"/>">
                        </td>
                    </tr>


                </s:form>

                <table>
                    <s:iterator var="subject" value="#session.subjects">
                        <tr>
                            <td>
                                <s:property value="#subject.name"/>
                            </td>

                            <td>
                                <a href="#" class="edit_subject_url">
                                    <s:text name="button.change"/>
                                </a>
                            </td>
                            <td>
                                <s:url action="delete_subject" id="delete">
                                    <s:param name="id"><s:property value="#subject.id"/></s:param>
                                </s:url>
                                <a href="${delete}">
                                    <s:text name="button.delete"/>
                                </a>
                            </td>

                        </tr>
                        <tr>
                            <td colspan="3">
                                <table class="edit_subject">
                                    <form action="edit_subject" method="post">
                                        <tr>
                                            <td>
                                                <s:textfield name="name"/>
                                                <input type="hidden" name="id" class="subject_id"
                                                       value="<s:property value="#subject.id"/>">
                                            </td>
                                            <td>
                                                <input type="submit" value="<s:text name="button.submit"/>">
                                            </td>

                                            <td>
                                                <input type="reset" class="edit_subject_back"
                                                       value="<s:text name="button.reset"/>">
                                            </td>
                                        </tr>
                                    </form>
                                </table>
                            </td>
                        </tr>

                    </s:iterator>
                    <s:set var="#session.subjects" value=""/>
                </table>


            </table>
        </div>
    </div>
</div>