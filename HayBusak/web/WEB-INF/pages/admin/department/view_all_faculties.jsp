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
        select_department();
        select_faculties();
    });

    function edit_toggle() {
        $(".edit").live("click", function () {

            var edit_faculty_id = $(this).find('.edit_faculty_id').val();
            $('.edit_faculty_form_id').val(edit_faculty_id);

            $(".wwFormTable").show();
            $(".view_faculties").hide();


        })

        $(".back").live("click", function () {
            $(".view_faculties").show();
            $(".wwFormTable").hide();
        })
    }

    function select_department() {

        $(".faculty_universities").live("change", function () {

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
                        $(".faculty_departments").empty();

                        $.each(departments, function (i, item) {
                            $options = $(
                                    '<option value="' + (i + 1) + '">' + item.name + '</option>'
                            );
                            $(".faculty_departments").append($options);
                        });


                    } else {
                        alert(data.responseDto.status + " " + data.responseDto.messages);
                    }

                }
            })
        })


    }

    function select_faculties() {

        $(".faculty_departments").live("change", function () {

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
                        /*$(".view_faculties").remove(".faculty_data"); */
                        $(".faculty_data").remove();
                        $(".content_line_tr").remove();

                        $.each(faculties, function (i, item) {
                            $faculty_data = $(
                                    '<tr class="faculty_data">'+
                                        '<td>'+
                                            '<img class="faculty_img" height="96" src="<%=request.getContextPath()%>'+item.logoPath+'" width="96"/>'+
                                        '</td>'+
                                        '<td>'+
                                            '<p class="faculty_name">'+
                                                'Name is ::'+item.name +
                                            '</p>'+
                                            '<table>' +
                                                '<tr>' +
                                                    '<td>' +
                                                        '<span>'+
                                                            '<a href="#" class="edit">'+
                                                                '<input type="hidden" class="edit_faculty_id" value="'+item.id+'">'+
                                                               '<s:property value="getText('button.edit')"/>'+
                                                            '</a>'+
                                                        '</span>'+
                                                    '</td>'+
                                                    '<td>' +
                                                        '<p class="faculty_remove">'+
                                                            '<a href="remove_faculty.htm?id='+item.id+'">'+
                                                                '<s:property value="getText('button.delete')"/>'+
                                                            '</a>'+
                                                        '</p>'+
                                                    '</td>'+
                                                '</tr>'+
                                            '</table>'+

                                        '</td>'+
                                    '</tr>'+

                                    '<tr  class="faculty_data">'+
                                        '<td>'+
                                            'Phone'+
                                        '</td>'+
                                        '<td>'+
                                            '<p class="faculty_phone">'+item.phone+'</p>'+
                                        '</td>'+
                                    '</tr>'+

                                    '<tr  class="faculty_data">'+
                                        '<td>'+
                                            'email'+
                                        '</td>'+
                                        '<td>'+
                                            '<p class="faculty_email">'+item.email+'</p>'+
                                        '</td>'+
                                    '</tr>'+

                                    '<tr  class="faculty_data">'+
                                        '<td>'+
                                            'info'+
                                        '</td>'+
                                        '<td>'+
                                            '<p class="faculty_info">'+item.info+'</p>'+

                                            '<p>'+
                                                '<a href="pre_edit_faculty_info.htm?id='+item.id+'">'+
                                                    '<s:property value="getText('button.view')"/> /'+
                                                    '<s:property value="getText('button.edit')"/>'+
                                                    '<s:property value="getText('link.lang.other')"/>'+
                                                '</a>'+
                                            '</p>'+
                                        '</td>'+
                                    '</tr>'+
                                    '<tr class="content_line_tr">'+
                                        '<td colspan="2">'+
                                            '<div class="content_line"></div>'+
                                        '</td>'+
                                    '</tr>'
                            );
                            $(".view_faculties").append($faculty_data);
                        });


                    } else {
                        alert(data.responseDto.status + " " + data.responseDto.messages);
                    }

                }
            })
        })


    }

</script>
<s:property value="getText('button.view')"/>
<s:property value="getText('button.all')"/>

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
        <%--<s:property value="getText('menu.universities')"/> <s:property value="getText('button.add')"/>--%>
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

        <table class="edit_faculty_form"> <%--todo--%>
            <s:form action="edit_faculty" method="post" enctype="multipart/form-data">

                <input type="hidden" name="id" class="edit_faculty_form_id" />

                <tr>
                    <td>
                        <img src='<%=request.getContextPath()%>/<s:property value="#university.logoPath"/>' width="96"
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
        <table style="width: 100%" class="view_faculties">

            <tr>
                <td>
                    Please select a University
                </td>
                <td>
                    <select class="faculty_universities">
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
                    <select class="faculty_departments" name="departmentId">
                        <option value="-1">Please select a Department</option>
                    </select>
                </td>
            </tr>

            <%--<tr class="faculty_data">
                <td>
                    <img class="faculty_img" height="96" src='<%=request.getContextPath()%>/<s:property value="#university.logoPath"/>' width="96"/>
                </td>
                <td>
                    <p class="faculty_name">
                        Name is ::
                        <s:property value="#university.name"/>
                    </p>
                    <span>
                        <a href="#" class="edit">
                            <s:property value="getText('button.edit')"/>
                        </a>
                    </span>

                    <s:url action="remove_university" var="remove_university">
                        <s:param name="id"><s:property value="#university.id"/></s:param>
                    </s:url>

                    <p class="faculty_remove">
                        <a href="${remove_university}">
                            <s:property value="getText('button.delete')"/>
                        </a>
                    </p>
                </td>
            </tr>

            <tr  class="faculty_data">
                <td>
                    Phone
                </td>
                <td>
                    <p class="faculty_phone"><s:property value="#university.phone"/></p>
                </td>
            </tr>

            <tr  class="faculty_data">
                <td>
                    email
                </td>
                <td>
                    <p class="faculty_email"><s:property value="#university.email"/></p>
                </td>
            </tr>

            <tr  class="faculty_data">
                <td>
                    info
                </td>
                <td>
                    <p class="faculty_info"><s:property value="#university.info"/></p>

                    <p>
                        <a href="pre_edit_university_info.htm?id=<s:property value="#university.id"/>">
                            <s:property value="getText('button.view')"/> /
                            <s:property value="getText('button.edit')"/>
                            <s:property value="getText('link.lang.other')"/>
                        </a>
                    </p>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <div class="content_line"></div>
                </td>
            </tr>--%>

        </table>
    </div>
</div>
</div>
