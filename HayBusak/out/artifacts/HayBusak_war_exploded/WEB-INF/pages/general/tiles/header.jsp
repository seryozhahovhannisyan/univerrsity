<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<script type="text/javascript">

    $(document).ready(function () {
        drop_down_menu();
    })

    function drop_down_menu() {
        var closetimer = 0;
        if ($(".menu_horizont")) {
            $(".menu_horizont b").mouseover(function () {
                clearTimeout(closetimer);
                if (this.className.indexOf("clicked") != -1) {
                    $(this).parent().next().slideUp(100);
                    $(this).removeClass("clicked");
                }
                else {
                    $(".menu_horizont b").removeClass();
                    $(this).addClass("clicked");
                    $(".menu_horizont ul:visible").slideUp(100);
                    $(this).parent().next().slideDown(500);
                }
                return false;
            });
            $(".menu_horizont").mouseover(function () {
                clearTimeout(closetimer);
            });
            $(".menu_horizont").mouseout(function () {
                closetimer = window.setTimeout(function () {
                    $(".menu_horizont ul:visible").slideUp(100);
                    $(".menu_horizont b").removeClass("clicked");
                }, 2000);
            });
        }

    }

</script>
<div class="header">

    <%--LOGO--%>
    <div class="logo">
        <a href="#">
            <img width="130" height="129" style=" border:0;" title="logo title"
                 alt="logo title" src="<%=request.getContextPath()%>/icon/general/logo256x256.png">
        </a>
    </div>
    <div class="menus">

        <s:url action="preRegistration" id="reg"/>

        <table width="100%" cellspacing="0" cellpadding="0" bordercolor="#FF0000" border="0" style="margin-bottom: -5">
            <tbody>
            <tr>
                <td width="0" valign="top">
                    <div class="perexod"></div>
                </td>
                <td height="33"
                    style="background:url(/icon/general/header/index_new_083.jpg);
                    background-repeat:repeat-x;
                    background-position:left top;">
                    <div class="menu_hor">
                        <table width="764" cellspacing="0" cellpadding="0" border="0" height="33">
                            <tbody>
                            <tr>
                                <td valign="top">
                                    <ul class="menu_horizont">
                                        <li class="sub">
                                            <a href="#">
                                                <b class=""><s:property value="getText('menu.home')"/> </b>
                                            </a>
                                            <ul class="w1" style="display: none;">
                                                <li>
                                                    <a href="#">Sub menu1</a>
                                                </li>
                                            </ul>
                                        </li>

                                        <li class="sub">
                                            <a href="#">
                                                <b class=""><s:property value="getText('menu.about')"/> </b>
                                            </a>
                                            <ul class="w1" style="display: none;">
                                                <li>
                                                    <a href="#">Sub menu1</a>
                                                </li>
                                            </ul>
                                        </li>

                                        <li class="sub">
                                            <a href="#">
                                                <b class=""><s:property value="getText('menu.universities')"/> </b>
                                            </a>
                                            <ul class="w1" style="display: none;">
                                                <li>
                                                    <a href="#">Sub menu1</a>
                                                </li>
                                            </ul>
                                        </li>

                                        <li class="sub">
                                            <a href="#">
                                                <b class=""><s:property value="getText('menu.lib')"/> </b>
                                            </a>
                                            <ul class="w1" style="display: none;">
                                                <li>
                                                    <a href="#">Sub menu1</a>
                                                </li>
                                            </ul>
                                        </li>

                                        <li class="sub">
                                            <a href="#">
                                                <b class=""><s:property value="getText('menu.contact')"/> </b>
                                            </a>
                                            <ul class="w1" style="display: none;">
                                                <li>
                                                    <a href="#">Sub menu1</a>
                                                </li>
                                            </ul>
                                        </li>

                                    </ul>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </td>
                <td class="menu_gray">
                    <form id="search" method="post" action="#">
                        <input id="search_text" type="text" placeholder="SEARCH" maxlength="70" name="search">
                        <button id="btn_search" class="search" value="" style="border: 0; cursor:pointer"></button>
                        <input id="msgSearch" type="hidden" value="Enter a words of 5 or more letters">
                        <input id="msgValid" type="hidden" value="Enter valid term">
                    </form>
                </td>
            </tr>
            </tbody>
        </table>


    </div>


    <%--
    ####################################################################################################################
    ####################################################################################################################
    --%>


    <div class="show">
        <div class="slide_show">
            <div class="border"></div>
            <img width="764" height="232" title="university"
                 alt="<s:text name="menu.university"/>" src="<%=request.getContextPath()%>/icon/general/university.jpg"
                 style="opacity: 1;">

        </div>

        <s:if test="%{#session.session_user==null}">
            <tiles:insertAttribute name="login"/>
        </s:if>
        <s:else>
            <tiles:insertAttribute name="user_info"/>
        </s:else>


    </div>

</div>