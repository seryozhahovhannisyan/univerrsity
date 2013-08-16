<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<style type="text/css">
    .departments_tr, .faculties_tr, .label, .degrees, .courses, .name {
        display: none;
    }
</style>

<script>

    $(document).ready(function () {
        select_department();
        select_faculties();
        degrees_view();
        courses_view();
        name_view();
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
                            $(".departments_tr").show();
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
                            $(".faculties_tr").show();
                        });


                    } else {
                        alert(data.responseDto.status + " " + data.responseDto.messages);
                    }

                }
            })
        })
    }

    function degrees_view() {
        $(".faculties").live("change", function () {
            $(".degrees").show();
            $(".label").show();
        })
    }

    function courses_view() {
        $(".degrees").live("change", function () {
            $(".courses").show();
        })
    }
    function name_view() {
        $(".courses").live("change", function () {
            $(".name").show();
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
                <s:text name="button.add"/>
                <s:text name="model.group"/>
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

                <s:form action="add_course_group" method="post" enctype="multipart/form-data">

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

                    <tr class="departments_tr">
                        <td>
                            Please select a Department
                        </td>
                        <td>
                            <select class="departments" name="departmentId">
                                <option value="departmentId"></option>
                            </select>
                        </td>
                    </tr>


                    <tr class="faculties_tr">
                        <td>
                            Please select a Faculties
                        </td>
                        <td>
                            <select class="faculties" name="facultyId">
                                <option value="facultyId"></option>
                            </select>
                        </td>
                    </tr>


                    <s:select list="degreeInfos"
                              listKey="key"
                              listValue="info"
                              cssClass="degrees"
                              label="Please select a degree"/>


                    <s:select list="courses"
                              name="course"
                              listKey="id"
                              listValue="name"
                              cssClass="courses"
                              label="Please select a course"/>
                    <s:textfield name="name"
                                 label="Name"
                                 cssClass="name"
                                 style="width: 100%;margin-top: 10"/>


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