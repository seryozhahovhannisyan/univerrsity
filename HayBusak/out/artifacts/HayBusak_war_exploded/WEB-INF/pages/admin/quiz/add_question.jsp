<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<style type="text/css">
    .departments_tr, .faculties_tr, .label, .degrees, .courses, .name {
        /*display: none;*/
    }

    .append_data {
        height: 10;
        overflow: auto;
    }

</style>

<script>

$(document).ready(function () {

    $(function () {
        $("#datepicker").datepicker();
    });

    select_department();
    select_faculties();
    degrees_view();
    courses_view();
    group_view();
    group_subject();
    group_subject_toggle();
    add_topic_form();
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

function group_view() {

    $(".courses").live("change", function () {
        $courseId = $(this).val();
        $facultyId = $(".faculties").val();
        if ($facultyId < 1) {
            alert("Please select faculty");
            return;
        }

        if ($courseId < 1) {
            alert("Please select course");
            return;
        }

        $.ajax({
            url: "select_course_group.htm",
            type: "post",
            dataType: "json",
            async: false,
            data: {
                courseId: $courseId,
                facultyId: $facultyId
            },

            success: function (data) {

                if (data != null && data.responseDto.status == "SUCCESS") {

                    var courseGroups = data.courseGroups;

                    $(".groups").empty();

                    $hidde = $(
                            '<tr>' +
                                    '<td colspan="3">' +
                                    '</td>' +
                                    '<td>' +
                                    '<a href="#" class="checkbox_group_href">' +
                                    '<img src="<%=request.getContextPath()%>/icon/general/header/arrow_down.png" />' +
                                    'select subjects' +
                                    '</a>' +
                                    '</td>' +
                                    '</tr>'
                    );
                    $(".groups").append($hidde);

                    $.each(courseGroups, function (i, item) {

                        $rows = $(
                                '<tr class="selected_group_' + item.id + ' selected_group__" >' +
                                        '<td><input type="checkbox" class="checkbox_group" name="groupId" value="' + item.id + '"></td>' +
                                        '<td>' + item.name + '</td>' +
                                        '<td class="append_data append_data_subject"></td><td class="append_data append_data_user"></td>' +
                                        '</tr>'
                        );

                        $(".groups").append($rows);
                    });

                } else {
                    alert(data.responseDto.status + " " + data.responseDto.messages);
                }

            }
        })

    })

}

function group_subject() {

    $(".checkbox_group").live('change', function () {

        $parent_row_id = $(this).val();
        $parent_row = $('.selected_group_' + $parent_row_id);

        if (!$(this).is(':checked')) {
            $parent_row.find(".append_data").empty();
        } else {
            if ($parent_row_id < 1) {
                alert("Please group ");
                return;
            }

            $.ajax({
                url: "select_group_subjects.htm",
                type: "post",
                dataType: "json",
                async: false,
                data: {
                    groupId: $parent_row_id
                },

                success: function (data) {

                    if (data != null && data.responseDto.status == "SUCCESS") {

                        var groupSubjects = data.groupSubjects;

                        $new_td = $parent_row.find(".append_data_subject");

                        $table = $('<table></table>');

                        $.each(groupSubjects, function (i, item) {
                            $new_td_data = $(
                                    '<tr>' +
                                            '<td><input type="checkbox" name="groupSubjectIdes" value="' + item.id + '"></td>' +
                                            '<td>' + item.subject.name + '</td>' +
                                            '</tr>'

                            );
                            $table.append($new_td_data);
                        });

                        $new_td.append($table);
                        $new_td_users.append($table_users);

                    } else {
                        alert(data.responseDto.status + " " + data.responseDto.messages);
                    }

                }
            })

            $parent_row.append($new_td);
        }
    })
}

function group_subject_toggle() {
    $(".checkbox_group_href").live('click', function () {
        $('.append_data_subject').toggle();
    });
    $(".user_group_href").live('click', function () {
        $('.append_data_user').toggle();
    })
}

function add_topic_form() {
    $(".add_topic_form_button").click(function () {
        var groupSubjectIdes = $("input[name='groupSubjectIdes']");
        $form = $(".add_topic_form");
        $form.append(groupSubjectIdes);
        $form.submit();
    });
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


                <tr>
                    <td>
                        Please select a Universities
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

                <tr class="group_tr">
                    <td>
                        Please select a Group
                    </td>
                    <td>
                        <table class="groups">
                        </table>
                    </td>
                </tr>


                <s:form action="add_question" method="post" cssClass="add_topic_form">
                    <s:textarea name="value" rows="10" cols="35" label="Question"/>
                    <s:textarea name="description" rows="10" cols="35" label="Description"/>

                    <s:textfield name="creationDate"
                                 id="datepicker"
                                 label="creationDate"
                                 cssClass="name"
                                 style="width: 100%;
                                        margin-top: 10"/>

                    <s:select list="types"
                              listKey="id"
                              listValue="name"
                              name="type"
                              label="Please select a type"/>
                    <tr>

                        <td>
                            <input type="checkbox" name="answersIsCorrect" > Is correct
                        </td>

                        <td>
                            <textarea rows="10" cols="35" name="answersValue"></textarea>
                        </td>

                    </tr>

                    <tr style="margin: 10" class="submit_actions">
                        <td>
                            <button class="add_topic_form_button"><s:text name="button.submit"/></button>
                            <%--<input type="submit" class="add_topic_form_button" value="<s:text name="button.submit"/>">--%>
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

