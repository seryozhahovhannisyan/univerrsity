<%--
  Created by IntelliJ IDEA.
  User: Serozh
  Date: 26.07.13
  Time: 3:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="left">

    <div class="sblock">
        <div class="corners">
            <s></s>
            <b></b>
            <u></u>
            <i></i>
        </div>
        <div class="title">
            <b>
                <s:property value="getText('menu.universities')"/>
            </b>
        </div>
        <div class="universities_content">
            <table>
                <tbody>

                <tr>
                    <td>
                        <div>
                            <s:property value="getText('button.add')"/>
                            <s:property value="getText('button.new')"/>
                            <s:property value="getText('menu.university')"/>
                        </div>
                        <s:url action="pre_add_university" var="pre_add_university"></s:url>
                        <s:a href="%{pre_add_university}"><s:property value="getText('button.add')"/></s:a>
                        <%--<a href="pre_add_university.htm"></a>--%>

                    </td>
                </tr>
                <tr class="even">
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <div>
                            <s:property value="getText('button.view')"/>
                            <s:property value="getText('button.all')"/>
                            <s:property value="getText('menu.universities')"/>
                        </div>
                        <a href="view_universities.htm"><s:property value="getText('button.view')"/> </a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div class="sblock">
        <div class="corners">
            <s></s>
            <b></b>
            <u></u>
            <i></i>
        </div>
        <div class="title">
            <b><s:property value="getText('model.departments')"/></b>
        </div>
        <div class="universities_content">
            <table>
                <tbody>

                <tr>
                    <td>
                        <div>
                            <s:property value="getText('button.add')"/>
                            <s:property value="getText('button.new')"/>
                            <s:property value="getText('model.department')"/>
                        </div>
                        <a href="pre_add_department.htm"><s:property value="getText('button.add')"/></a>
                    </td>
                </tr>
                <tr class="even">
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <div>
                            <s:property value="getText('button.view')"/>
                            <s:property value="getText('button.all')"/>
                            <s:property value="getText('model.departments')"/>
                        </div>
                        <a href="view_departments.htm">
                            <s:property value="getText('button.view')"/>
                        </a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div id="universities_block" class="sblock">
        <div class="corners">
            <s></s>
            <b></b>
            <u></u>
            <i></i>
        </div>
        <div class="title">
            <b><s:property value="getText('model.faculties')"/></b>
        </div>
        <div class="universities_content">
            <table>
                <tbody>

                <tr>
                    <td>
                        <div>
                            <s:property value="getText('button.add')"/>
                            <s:property value="getText('button.new')"/>
                            <s:property value="getText('model.faculty')"/>
                        </div>
                        <a href="pre_add_faculty.htm"><s:property value="getText('button.add')"/></a>
                    </td>
                </tr>
                <tr class="even">
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <div>
                            <s:property value="getText('button.view')"/>
                            <s:property value="getText('button.all')"/>
                            <s:property value="getText('model.faculties')"/>
                        </div>
                        <a href="view_faculties.htm">
                            <s:property value="getText('button.view')"/>
                        </a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div class="sblock">
        <div class="corners">
            <s></s>
            <b></b>
            <u></u>
            <i></i>
        </div>
        <div class="title">
            <b><s:property value="getText('model.degrees')"/></b>
        </div>
        <div class="universities_content">
            <table>
                <tbody>

                <tr>
                    <td>
                        <div>
                            <s:property value="getText('button.add')"/>
                            <s:property value="getText('button.new')"/>
                            <s:property value="getText('model.degree')"/>
                        </div>
                        <a href="pre_add_faculty_degree.htm"><s:property value="getText('button.add')"/></a>
                    </td>
                </tr>
                <tr class="even">
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <div>
                            <s:property value="getText('button.view')"/>
                            <s:property value="getText('button.all')"/>
                            <s:property value="getText('model.degrees')"/>
                        </div>
                        <a href="view_and_download_docs.htm">
                            <s:property value="getText('button.view')"/>
                        </a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>


</div>
