<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="left">

    <div id="universities_block" class="sblock">
        <div class="corners">
            <s></s>
            <b></b>
            <u></u>
            <i></i>
        </div>
        <div class="title">
            <b><s:property value="getText('menu.universities')"/></b>
        </div>
        <div class="universities_content">
            <table>
                <tbody>
                <tr>
                    <td>
                        <div>
                            <a href="#">Moderate</a>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div>
                            Yerevan State University:
                        </div>
                        <a href="#"><s:property value="getText('button.more')"/></a>
                        <span>YSU</span>

                    </td>
                </tr>
                <tr class="even"><td></td>  </tr>
                <tr>
                    <td>
                        <div>
                            Armenian Economic University
                        </div>
                        <a href="#"><s:property value="getText('button.more')"/></a>
                        <span>AEU</span>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div id="contributors" class="sblock">
        <div class="corners">
            <s></s>
            <b></b>
            <u></u>
            <i></i>
        </div>
        <div class="title">
            <b>Subject</b>
        </div>
        <div id="contributors-content" >
            <select id="contributors-report">
                <option value="day">2012/I kisamyak</option>
                <option value="day">2012/II kisamyak</option>
                <option value="day">2013/I kisamyak</option>
                <option value="day">2013/II kisamyak</option>
            </select>
            <img id="contributors-loading-select" align="absmiddle" alt="loading" src="/icon/general/left_menu/loading.gif"
                 style="display: none;">
            <div id="contributors-table">
                <table class="table">
                    <tbody>
                    <tr class="second">
                        <td class="place">1.</td>
                        <td class="login">
                            <a rel="nofollow" href="#">Geographi</a>
                        </td>
                        <td class="points">18.0 Q</td>
                    </tr>
                    <tr class="first">
                        <td class="place">2.</td>
                        <td class="login">
                            <a rel="nofollow" href="#">English</a>
                        </td>
                        <td class="points">14.5 Q</td>
                    </tr>
                    </tbody>
                </table>
                <a style="float:left; margin: 3px 0 0 5px;" href="#">Получение Q</a>
            </div>
            <div id="contribution-paging" class="paging">
                <img id="contributors-loading" align="absmiddle" alt="loading"
                     src="/icon/general/left_menu/loading.gif?0" style="display: none;">
                <span class="arrow" style="color: gray; cursor: default;">←</span>
                <span class="arrow" style="color: rgb(65, 159, 221); cursor: pointer;">→</span>
            </div>
        </div>
    </div>
</div>