<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<script>

    $(document).ready(function () {
        /* add_university();*/
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
                <s:property value="getText('button.add')"/>
                <s:property value="getText('model.department')"/> </b>
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

                <s:form action="add_department" method="post">

                    <tr>
                        <td><s:property value="getText('model.universities')"/> </td>
                        <td>
                            <s:select list="%{#session.universities}" name="universityId"
                                      listKey="id" listValue="name"/>
                            <s:property value="#session.title"/>
                        </td>
                    </tr>

                    <s:iterator var="lang" value="languages">

                        <tr>
                            <td></td>
                            <td><s:property value="#lang.title"/></td>
                        </tr>

                        <tr>
                            <td>
                                Name
                            </td>
                            <td>
                                <s:textfield name="names" style="width: 100%;margin-top: 10"/>
                            </td>
                        </tr>

                    </s:iterator>

                    <tr style="margin: 10" class="submit_actions">
                        <td>
                            <input type="submit" value="<s:property value="getText('button.submit')"/>">
                        </td>

                        <td>
                            <input type="reset" value="<s:property value="getText('button.reset')"/>">
                        </td>
                    </tr>


                </s:form>

            </table>
        </div>
    </div>
</div>
<%--
style="float: left;
             width: 479;
             margin-left: 10%;
             background-color: #ffe3ff;
             "
--%>