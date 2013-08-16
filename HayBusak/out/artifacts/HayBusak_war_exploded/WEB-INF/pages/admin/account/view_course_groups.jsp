<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<style>
    .add_subjects_to_groups {
        float: right;
        margin-right: 55;
        margin-top: -80px;
    }

    .groupSubjects {
        display: none;
    }
</style>

<script>

    $(document).ready(function () {
        select_department();
        select_faculties();
        show_groupSubjects();
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

                        $(".faculties").append('<option value="-1">Please select a faculty</option>');

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

    function accept_form() {
        $(".add_subjects_to_groups_submit").live("click", function () {

            var groupsId = $(".groupsId").val();
            var subjectsId = $(".subjectsId").val();

            $.ajax({
                url: "add_subjects_to_groups.htm",
                type: "post",
                /*dataType: "text/html",*/
                dataType: "json",
                async: false,
                data: {
                    groupsId: groupsId,
                    subjectsId: subjectsId
                },

                success: function () {
                    alert("done")
                }
            })

        });
    }

    function show_groupSubjects() {
        $(".view_subjects").live("click", function () {
            $(".groupSubjects").toggle();
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
                <s:text name="button.view"/>
                <s:text name="model.groups"/>
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
                            <option value="-1">Please select a Department</option>
                        </select>
                    </td>
                </tr>

                <s:form action="view_course_groups" method="post" class="view_course_groups_form" theme="simple">
                    <tr>
                        <td>
                            Please select a Faculties
                        </td>
                        <td>
                            <select class="faculties" name="facultyId">
                                <option value="-1">Please select a Faculties</option>
                            </select>
                        </td>
                    </tr>

                    <tr class="hidden_degrees">
                        <td>
                            Please select a Degree
                        </td>
                        <td>
                            <s:select list="degreeInfos" listKey="key" listValue="info"
                                      headerKey="-1" headerValue="Please select a degree"
                                      class="degrees"/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            Please select a Course
                        </td>
                        <td>

                            <s:select list="courses"
                                      name="course"
                                      listKey="id" headerKey="-1"
                                      listValue="name" headerValue="Please select a Course"
                                      class="courses"/>
                        </td>
                    </tr>

                    <tr>
                        <s:if test="%{#request.act==1}">
                            <td>
                                <input type="submit" value="<s:text name="button.view"/>">
                            </td>
                            <td>
                                <input type="submit" value="<s:text name="button.view"/>">
                            </td>
                        </s:if>
                        <s:else>
                            <td colspan="2">
                                <input type="submit" value="<s:text name="button.view"/>">
                            </td>
                        </s:else>
                    </tr>

                </s:form>

                <table style="margin-top: 10;margin-left: 10">
                    <tr>
                        <s:if test="%{#request.act==1}">
                            <td>
                                <input type="checkbox" name="groupsId" class="all_groups">
                            </td>
                        </s:if>
                        <td>faculty</td>
                        <td>course</td>
                        <td>Group</td>
                    </tr>

                    <form action="add_subjects_to_groups.htm" method="POST">

                        <s:iterator var="group" value="courseGroups">
                            <s:if test="%{#request.act!=1}">
                                <tr>
                                    <td><s:property value="#group.name"/></td>
                                    <td><s:property value="#group.course.name"/></td>
                                    <td><s:property value="#group.faculty.name"/></td>

                                </tr>
                            </s:if>
                            <s:else>
                                <tr>
                                    <td>
                                        <input type="checkbox" name="groupsId" class="groupsId"
                                               value="<s:property value="#group.id"/>"/>
                                    </td>
                                    <td>
                                        <s:property value="#group.name"/>
                                    </td>
                                    <td>
                                        <s:property value="#group.course.name"/>
                                    </td>
                                    <td>
                                        <s:property value="#group.faculty.name"/>
                                    </td>
                                    <td>
                                        <a class="view_subjects" href="#"> <s:text name="model.subjects"/> </a>
                                    </td>
                                </tr>
                                <s:iterator var="groupSubject" value="groupSubjects">
                                    <tr class="groupSubjects">
                                        <s:if test="%{#group.id == #groupSubject.groupId}">

                                            <td colspan="2"><s:property value="#groupSubject.subject.name"/></td>
                                            <td colspan="2">
                                                <a href="delete_group_subject.htm?id=<s:property value="#groupSubject.groupId"/>"><s:text name="button.delete"/></a>
                                            </td>
                                        </s:if>

                                    </tr>
                                </s:iterator>

                            </s:else>
                        </s:iterator>
                        <s:if test="%{#request.act==1}">
                            <table class="add_subjects_to_groups">
                                    <%--<s:form action="add_subjects_to_groups" method="POST" cssClass="add_subjects_to_groups">--%>
                                <s:iterator var="subject" value="subjects">
                                    <tr>
                                        <td>
                                            <input type="checkbox" name="subjectsId"
                                                   value="<s:property value="#subject.id"/>"/>
                                        </td>
                                        <td>
                                            <s:property value="#subject.name"/>
                                        </td>
                                    </tr>
                                </s:iterator>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" class="add_subjects_to_groups_submit"
                                               value="<s:text name="button.add"/>">
                                    </td>
                                </tr>
                            </table>
                        </s:if>
                    </form>


                </table>

            </table>
        </div>
    </div>
</div>