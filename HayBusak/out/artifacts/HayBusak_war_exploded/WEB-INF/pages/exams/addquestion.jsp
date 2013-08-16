<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<script>
    $(function () {
        $("#datepicker").datepicker();
    });
</script>


<form>
    <table>
        <tr>
            <td>Question</td>
            <td>
                <textarea rows="10" cols="45" name="text"></textarea>
            </td>
        </tr>
        <tr>
            <td>Description</td>
            <td>
                <textarea rows="10" cols="45" name="text"></textarea>
            </td>
        </tr>
        <tr>
            <td>Answer 1</td>
            <td>
                <textarea rows="10" cols="45" name="text"></textarea>
            </td>
        </tr>
        <tr>
            <td>Answer 2</td>
            <td>
                <textarea rows="10" cols="45" name="text"></textarea>
            </td>
        </tr>
        <tr>
            <td>Answer 3</td>
            <td>
                <textarea rows="10" cols="45" name="text"></textarea>
            </td>
        </tr>
        <tr>
            <td>Answer 4</td>
            <td>
                <textarea rows="10" cols="45" name="text"></textarea>
            </td>
        </tr>
        <tr>
            <td>Creation date 4</td>
            <td>
                <input type="text" id="datepicker"/>
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
    </table>
</form>