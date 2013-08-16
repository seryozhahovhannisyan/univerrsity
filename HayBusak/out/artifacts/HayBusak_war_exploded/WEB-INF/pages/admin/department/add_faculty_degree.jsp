<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<style>
    .hidden_degrees {
        display: none;
    }
</style>

<script>

    $(document).ready(function () {
        select_department();
        select_faculties();
        datepicker();
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

    function select_faculties() {

        $(".departments").live("change", function () {

            $departmentId = $(this).val();

            if ($departmentId < 1) {
                alert("Please select university");
                return;
            }

            $.ajax({
                url: "select_department.htm",
                type: "post",
                dataType: "json",
                async: false,
                data: {
                    departmentId: $departmentId
                },

                success: function (data) {

                    if (data != null && data.responseDto.status == "SUCCESS") {

                        var faculties = data.faculties;

                        $(".faculties").empty();

                        $.each(faculties, function (i, item) {

                            $options = $(
                                    '<option value="' + item.id + '">' + item.name + '</option>'
                            );

                            $(".faculties").append($options);
                            $(".hidden_degrees").show();
                        });


                    } else {
                        alert(data.responseDto.status + " " + data.responseDto.messages);
                    }

                }
            })
        })
    }

    function datepicker() {
        $(".datepicker").datepicker();
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
                <s:property value="getText('model.degree')"/> </b>
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

                <s:form action="add_faculty_degree" method="post" enctype="multipart/form-data">

                    <tr>
                        <td>
                            Please select a University
                        </td>
                        <td>
                            <select class="universities" name="universityId">
                                <option value="-1">Please select university</option>
                                <s:iterator var="university" value="#session.universities">
                                    <option value="<s:property value="#university.id"/>">
                                        <s:property value="#university.name"/>
                                    </option>
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
                            Please select a Faculties
                        </td>
                        <td>
                            <select class="faculties" name="facultyId">
                                <option value="facultyId"></option>
                            </select>
                        </td>
                    </tr>

                    <tr class="hidden_degrees">
                        <td>
                            Please select a Degree
                        </td>
                        <td>
                            <s:select list="degreeInfos" listKey="key" listValue="info" name="degreeKey"/>
                        </td>
                    </tr>

                    <s:iterator var="documentTypeInfo" value="DocumentTypeInfos">
                        <tr>
                            <td>

                            </td>
                            <td>
                                <s:property value="#documentTypeInfo.info"/>
                                <input type="hidden" name="docTypeKeys" value="<s:property value="#documentTypeInfo.key"/>"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                Document data
                            </td>
                            <td>
                                <s:file name="dataList"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                Document serial numbers
                            </td>
                            <td>
                                <s:textfield name="serialNumbers"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                Document confirm Date
                            </td>
                            <td>
                                <input type="text" name="confirmDates" class="datepicker"/>
                            </td>
                        </tr>

                        <s:iterator var="lang" value="languages">

                            <tr>
                                <td></td>
                                <td><s:property value="#lang.title"/></td>
                            </tr>

                            <tr>
                                <td>About</td>
                                <td><s:textarea name="abouts" style="width: 100%;margin-top: 10"/></td>
                            </tr>

                            <tr class="content_line_tr">
                                <td></td>
                                <td>
                                    <div class="content_line"></div>
                                </td>
                            </tr>

                        </s:iterator>

                        <tr class="content_line_tr">
                            <td colspan="2">
                                <div class="content_line"></div>
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