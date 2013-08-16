<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<style>
</style>
<script>

    $(document).ready(function () {

    });

    function edit_toggle() {
        $(".title").live("click", function () {
            $(this).find("div").show();
        });
    }

</script>
<div id="left">

    <div id="universities_block" class="sblock">

        <div class="corners">
            <s></s>
            <b></b>
            <u></u>
            <i></i>
        </div>
        <div class="title">
            <b>
                <a href="#" class="model_title">
                    <s:property value="getText('model.groups')"/>
                </a>
            </b>


        </div>
        <div class="universities_content">
            <table>
                <tbody>

                <tr>
                    <td>
                        <div class="add_title">
                            <s:text name="button.add"/>
                            <s:text name="button.new"/>
                            <s:text name="model.group"/>
                        </div>
                        <a href="pre_add_course_group.htm" class="add_href"><s:text name="button.add"/></a>
                    </td>
                </tr>
                <tr class="even">
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <div>
                            <s:text name="button.view"/> /

                        </div>
                        <a href="view_course_groups.htm" class="view_href">
                            <s:text name="button.view"/>
                            <s:text name="model.groups"/>
                        </a>
                    </td>

                </tr>
                <tr class="even">
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <div><s:text name="button.add"/>
                        </div>
                        <a href="view_course_groups.htm?act=1" class="view_href">
                            <s:text name="button.add"/> <s:text name="model.subjects"/>
                        </a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="title">
            <b>
                <a href="#" class="model_title">
                    <s:text name="model.subject"/>
                </a>
            </b>
        </div>
        <div class="universities_content">
            <table>
                <tbody>

                <tr>
                    <td>
                        <div class="add_title">
                            <s:text name="button.add"/> /
                            <s:text name="button.view"/>
                            <s:text name="button.new"/>
                            <s:text name="model.subject"/>
                        </div>
                        <a href="pre_add_subject.htm">
                            <s:text name="button.add"/> /
                            <s:text name="button.view"/>
                        </a>
                    </td>
                </tr>

                <tr class="even">
                    <td></td>
                </tr>

                <tr>
                    <td>
                        <div class="add_title">
                            view user topic
                        </div>
                        <a href="pre_add_subject.htm">
                            <s:text name="button.add"/> /
                            <s:text name="button.view"/>
                        </a>
                    </td>
                </tr>

                <tr class="even">
                    <td></td>
                </tr>

                <tr>
                    <td>
                        <div class="add_title">
                            add question
                        </div>
                        <a href="pre_add_question.htm">
                            <s:text name="button.add"/> /
                            <s:text name="button.view"/>
                        </a>
                    </td>
                </tr>

                </tbody>
            </table>
        </div>

        <div class="universities_content">
            <table>
                <tbody>

                <tr>
                    <td>
                        <div class="add_title">
                            <s:text name="button.add"/> /
                            <s:text name="button.view"/>
                            <s:text name="button.new"/>
                            topic
                        </div>
                        <a href="pre_add_topic.htm">
                            <s:text name="button.add"/> /
                            <s:text name="button.view"/>
                        </a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

    </div>


</div>