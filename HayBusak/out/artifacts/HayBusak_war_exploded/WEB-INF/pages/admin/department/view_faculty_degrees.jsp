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
    select_degrees();
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

function select_degrees() {

    $(".degrees").live("change", function () {

        $degreeKey = $(this).val();
        $facultyId = $(".faculties").val();

        if ($degreeKey < 1) {
            alert("Please select degree");
            return;
        }

        if ($facultyId < 1) {
            alert("Please select faculty");
            return;
        }

        $.ajax({
            url: "select_faculty_degrees.htm",
            type: "post",
            dataType: "json",
            async: false,
            data: {
                degreeKey: $degreeKey,
                facultyId: $facultyId
            },

            success: function (data) {

                if (data != null && data.responseDto.status == "SUCCESS") {

                    var facultyDegrees = data.facultyDegrees;
                    $(".faculty_degree_data").remove();
                    $(".content_line_tr").remove();

                    $.each(facultyDegrees, function (i, item) {
                        $faculty_degree_data = $(
                                '<tr class="faculty_degree_data">' +
                                        '<td>' +
                                            '<img class="faculty_img" height="96" src="<%=request.getContextPath()%>/' + item.logoPath + '" width="96"/>' +
                                        '</td>' +
                                        '<td>' +
                                        '<p class="faculty_name">' +
                                        'Name is ::' + item.name +
                                        '</p>' +
                                        '<table>' +
                                        '<tr>' +
                                        '<td>' +
                                        '<span>' +
                                        '<a href="#" class="edit">' +
                                        '<input type="hidden" class="edit_faculty_id" value="' + item.id + '">' +
                                        '<s:property value="getText('button.edit')"/>' +
                                        '</a>' +
                                        '</span>' +
                                        '</td>' +
                                        '<td>' +
                                        '<p class="faculty_remove">' +
                                        '<a href="remove_faculty.htm?id=' + item.id + '">' +
                                        '<s:property value="getText('button.delete')"/>' +
                                        '</a>' +
                                        '</p>' +
                                        '</td>' +
                                        '</tr>' +
                                        '</table>' +

                                        '</td>' +
                                        '</tr>' +

                                        '<tr  class="faculty_data">' +
                                        '<td>' +
                                        'Phone' +
                                        '</td>' +
                                        '<td>' +
                                        '<p class="faculty_phone">' + item.phone + '</p>' +
                                        '</td>' +
                                        '</tr>' +

                                        '<tr  class="faculty_data">' +
                                        '<td>' +
                                        'email' +
                                        '</td>' +
                                        '<td>' +
                                        '<p class="faculty_email">' + item.email + '</p>' +
                                        '</td>' +
                                        '</tr>' +

                                        '<tr  class="faculty_data">' +
                                        '<td>' +
                                        'info' +
                                        '</td>' +
                                        '<td>' +
                                        '<p class="faculty_info">' + item.info + '</p>' +

                                        '<p>' +
                                        '<a href="pre_edit_faculty_info.htm?id=' + item.id + '">' +
                                        '<s:property value="getText('button.view')"/> /' +
                                        '<s:property value="getText('button.edit')"/>' +
                                        '<s:property value="getText('link.lang.other')"/>' +
                                        '</a>' +
                                        '</p>' +
                                        '</td>' +
                                        '</tr>' +
                                        '<tr class="content_line_tr">' +
                                        '<td colspan="2">' +
                                        '<div class="content_line"></div>' +
                                        '</td>' +
                                        '</tr>'
                        );
                        $(".faculty_degrees_content").append($faculty_degree_data);
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
            <table style="width: 100%" class="faculty_degrees_content">


                <tr>
                    <td>
                        Please select a University
                    </td>
                    <td>
                        <select class="universities">
                            <option value="-1">Please select university</option>
                            <s:iterator var="university" value="#session.universities">
                                <option value="<s:property value="#university.id"/>"><s:property
                                        value="#university.name"/></option>
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

                <s:form action="add_faculty_degree" method="post" enctype="multipart/form-data">

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
                            <s:select list="degreeInfos" listKey="key" listValue="info" name="degreeKey"
                                      class="degrees"/>
                        </td>
                    </tr>

                </s:form>

                <table class="edit_faculty_form"> <%--todo--%>
                    <s:form action="edit_faculty" method="post" enctype="multipart/form-data">

                        <input type="hidden" name="id" class="edit_faculty_form_id"/>

                        <tr>
                            <td>
                                <img src='<%=request.getContextPath()%>/<s:property value="#university.logoPath"/>'
                                     width="96"
                                     height="96">
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

                        <tr style="margin: 10" class="submit_actions">
                            <td>
                                <input type="submit" value="<s:property value="getText('button.submit')"/>">
                            </td>

                            <td>
                                <input type="reset" value="<s:property value="getText('button.reset')"/>">
                                <a href="#" class="back">Back</a>
                            </td>
                        </tr>

                    </s:form>
                </table>


            </table>
        </div>
    </div>
</div>