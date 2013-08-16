<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<script>

    $(document).ready(function () {
        select_department();
    });
    function select_department() {

        $(".universities").live("change", function () {
            $universityId = $(this).val();

            if ($universityId < 1) {
                alert("Please select university");
                return;
            }

            $.ajax({
                url: "select_university.htm",
                type: "post",
                dataType: "json",
                async: false,
                data: {
                    universityId: $universityId
                },

                success: function (data) {

                    if (data != null && data.responseDto.status == "SUCCESS") {

                        var departments = data.departments;
                        $(".departments").empty();

                        $.each(departments, function (i, item) {
                            $options = $(
                                    /*'<option value="' + (i+1) + '">' + item.name + '</option>'*/
                                    '<option value="' + item.id + '">' + item.name + '</option>'
                            );
                            $(".departments").append($options);
                        });



                    } else {
                        alert(data.responseDto.status + " " + data.responseDto.messages);
                    }

                }
            })
        })


    }

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
                <s:property value="getText('model.faculty')"/> </b>
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

                <s:form action="add_faculty" method="post" enctype="multipart/form-data">

                    <tr>
                        <td>
                            Please select a University
                        </td>
                        <td>
                            <select class="universities" name="universityId">
                                <option value="-1">Please select university</option>
                                <s:iterator var="university" value="#session.universities">
                                    <option value="<s:property value="#university.id"/>">
                                        <s:property value="#university.name"/></option>
                                </s:iterator>
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            Please select a Department
                        </td>
                        <td>
                            <select class="departments" name="departmentId">
                                <option value="departmentId"></option>
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <img src="" width="64" height="64" style="margin-top: 10">
                        </td>
                        <td>
                            <s:file name="logo" style="margin-top: 10"/>
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

                        <tr>
                            <td>
                                Info
                            </td>
                            <td>
                                <s:textarea name="infos" style="width: 100%;height: 150;margin-top: 10"/>
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