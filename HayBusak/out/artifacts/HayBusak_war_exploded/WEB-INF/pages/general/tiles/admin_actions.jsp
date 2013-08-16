<%--
  Created by IntelliJ IDEA.
  User: Serozh
  Date: 09.08.13
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<style type="text/css">
    @import url(/css/admin_actions.css);
</style>
<script type="text/javascript">

    $(document).ready(function () {
        actions_toggle();
    });
    function actions_toggle() {

        $(".add").live("click",function(){
            $("#crud-add-wrap").attr("display","block");
            $("#crud-add-wrap").toggle();
        });

        $(".edit").live("click",function(){
            $("#crud-update-wrap").attr("display","block");
            $("#crud-update-wrap").toggle();
        });

        $(".view").live("click",function(){
            $("#crud-view-wrap").attr("display","block");
            $("#crud-view-wrap").toggle();
        });

    }
</script>

<div id="crud-widget" class="widget-reset" style="display: none;">

    <div id="crud-add-wrap" style="display: none;">
        <div id="crud-widget-switcher"  >
            <span class="crud-widget-title"><s:text name="button.add"/></span>
            <ul class="switcher-list-left">
                <li>
                    <a rel="nofollow" href="#">
                        <span class="widget-icon icon-envato-notes"> </span>
                        <s:text name="model.universities"/>
                    </a>
                </li>
            </ul>
            <%--<ul class="switcher-list-right">
                <li>
                    <a rel="nofollow" href="#">
                        <span class="widget-icon icon-envato-notes"> </span>
                        <s:text name="model.departments"/>
                    </a>
                </li>
                <li>
                    <a rel="nofollow" href="#">
                        <span class="widget-icon icon-freelanceswitch-jobs"> </span>
                        Freelance Jobs
                    </a>
                </li>
            </ul>--%>
        </div>
        <span class="switcher-arrow"></span>
    </div>
    <div id="crud-update-wrap" style="display: none;">
        <div id="crud-update">
            <span class="crud-widget-title"><s:text name="button.change"/></span>
            <ul class="support-list">
                <li>
                    <a rel="nofollow" href="#">
                        <span class="widget-icon icon-envato-notes"> </span>
                        <s:text name="model.universities"/>
                    </a>
                </li>
                <li>
                    <a rel="nofollow" href="#">
                        <span class="widget-icon icon-envato-notes"> </span>
                        <s:text name="model.departments"/>
                    </a>
                </li>
                <li>
                    <a rel="nofollow" href="#">
                        <span class="widget-icon icon-envato-notes"> </span>
                        <s:text name="model.faculties"/>
                    </a>
                </li>
                <li>
                    <a rel="nofollow" href="#">
                        <span class="widget-icon icon-envato-notes"> </span>
                        <s:text name="model.degrees"/>
                    </a>
                </li>
                <li>
                    <a rel="nofollow" href="#">
                        <span class="widget-icon icon-envato-notes"> </span>
                        <s:text name="model.degrees"/>
                    </a>
                </li>
                <li>
                    <a rel="nofollow" href="#">
                        <span class="widget-icon icon-envato-notes"> </span>
                        <s:text name="model.groups"/>
                    </a>
                </li>
                <li>
                    <a rel="nofollow" href="#">
                        <span class="widget-icon icon-envato-notes"> </span>
                        <s:text name="model.subjects"/>
                    </a>
                </li>
            </ul>
        </div>
        <span class="switcher-arrow"></span>
    </div>
    <div id="crud-view-wrap" style="display: none;">
        <div id="crud-view">
            <span class="crud-widget-title"><s:text name="button.view"/></span>
            <ul class="community-list">
                <li>
                    <a rel="nofollow" href="#">
                        <span class="widget-icon icon-envato-notes"> </span>
                        <s:text name="model.universities"/>
                    </a>
                </li>
                <li>
                    <a rel="nofollow" href="#">
                        <span class="widget-icon icon-envato-notes"> </span>
                        <s:text name="model.departments"/>
                    </a>
                </li>
                <li>
                    <a rel="nofollow" href="#">
                        <span class="widget-icon icon-envato-notes"> </span>
                        <s:text name="model.faculties"/>
                    </a>
                </li>
                <li>
                    <a rel="nofollow" href="#">
                        <span class="widget-icon icon-envato-notes"> </span>
                        <s:text name="model.degrees"/>
                    </a>
                </li>
                <li>
                    <a rel="nofollow" href="#">
                        <span class="widget-icon icon-envato-notes"> </span>
                        <s:text name="model.degrees"/>
                    </a>
                </li>
                <li>
                    <a rel="nofollow" href="#">
                        <span class="widget-icon icon-envato-notes"> </span>
                        <s:text name="model.groups"/>
                    </a>
                </li>
                <li>
                    <a rel="nofollow" href="#">
                        <span class="widget-icon icon-envato-notes"> </span>
                        <s:text name="model.subjects"/>
                    </a>
                </li>


            </ul>
        </div>
        <span class="switcher-arrow"></span>
    </div>

    <a class="widget-button add" rel="nofollow" href="#"><s:text name="button.add"/></a>
    <a class="widget-button edit" rel="nofollow" href="#"><s:text name="button.edit"/></a>
    <a class="widget-button view" rel="nofollow" href="#"><s:text name="button.view"/></a>
</div>