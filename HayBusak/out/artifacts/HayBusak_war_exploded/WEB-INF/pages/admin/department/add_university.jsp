<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<script>

    $(document).ready(function () {

    });
    function add_university() {

        $(".add_university_form_logo").live("change", function () {
            alert("attached");
        })

        $(".select_lang").live("change", function () {

            $multi_lang_names = $(".multi_lang").find(".names").find("input");
            $multi_lang_abbreviations = $(".multi_lang").find(".abbreviations").find("input");
            $multi_lang_addresses = $(".multi_lang").find(".addresses").find("textarea");
            $multi_lang_infos = $(".multi_lang").find(".infos").find("textarea");

            $multi_lang_names_clone = $multi_lang_names.clone();
            $multi_lang_abbreviations_clone = $multi_lang_abbreviations.clone();
            $multi_lang_addresses_clone = $multi_lang_addresses.clone();
            $multi_lang_infos_clone = $multi_lang_infos.clone();

            $multi_lang_names.hide();
            $multi_lang_abbreviations.hide();
            $multi_lang_addresses.hide();
            $multi_lang_infos.hide();

            $multi_lang_names_clone.val("");
            $multi_lang_abbreviations_clone.val("");
            $multi_lang_addresses_clone.val("");
            $multi_lang_infos_clone.val("");

            $(".multi_lang").find(".names").append($multi_lang_names_clone);
            $(".multi_lang").find(".abbreviations").append($multi_lang_abbreviations_clone);
            $(".multi_lang").find(".addresses").append($multi_lang_addresses_clone);
            $(".multi_lang").find(".infos").append($multi_lang_infos_clone);

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
                <s:property value="getText('model.university')"/>
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

                <s:form action="add_university" method="post" enctype="multipart/form-data" class="add_university_form">
                    <tr>
                        <td>
                            <img src="" width="64" height="64" style="margin-top: 10">
                        </td>
                        <td>
                            <%--<s:file name="logo" class="add_university_form_logo" style="margin-top: 10"/>--%>
                            <input type="file" name="logo"  style="margin-top: 10">
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

                    <tr>
                        <td>
                            Link
                        </td>
                        <td>
                            <s:textfield  name="link" style="width: 100%;margin-top: 10"/>
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
                                Abbreviation
                            </td>
                            <td>
                                <s:textfield type="text" name="abbreviations" style="width: 100%;margin-top: 10"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                Address
                            </td>
                            <td>
                                <s:textarea name="addresses" class="addresses" style="width: 100%;height: 50;margin-top: 10"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                Info
                            </td>
                            <td>
                                    <s:textarea  name="infos" style="width: 100%;height: 150;margin-top: 10"/>
                            </td>
                        </tr>

                    </s:iterator>

                    <tr style="margin: 10" class="submit_actions">
                        <td>
                            <input type="submit" value="<s:text name="button.submit"/>">
                        </td>

                        <td>
                            <input type="reset" value="<s:text name="button.reset"/>">
                        </td>
                    </tr>


                </s:form>

            </table>
        </div>
    </div>
</div>
