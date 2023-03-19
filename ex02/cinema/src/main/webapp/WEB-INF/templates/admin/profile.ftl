<#include "../main-template.ftl"/>

<#macro content>
    <#if authHistory?has_content>
        <div class="container">
            <h2 class="h2">Authentications</h2>
            <table class="statistics">
                <thead>
                    <th>Date</th>
                    <th>Time</th>
                    <th>IP</th>
                </thead>
                 <#list authHistory as row>
                     <tr>
                         <td>${row.toDateTimeString("MMMM dd, yyyy")}</td>
                         <td>${row.toDateTimeString("HH:mm")}</td>
                         <td>${row.ip}</td>
                     </tr>
                </#list>
            </table>
        </div>
        <#if avatarHistory?has_content>
            <div class="container">
                <h2 class="h2">Avatars</h2>
                <table class="statistics">
                    <thead>
                        <th>File</th>
                        <th>Size</th>
                        <th>Mine</th>
                    </thead>
                    <#list avatarHistory as row>
                        <tr>
                            <td> <a href="../../images/avatar/${row.id}"  target="_blank">${row.id}</a></td>
                            <td>${row.size}</td>
                            <td>${row.mime}</td>
                        </tr>
                    </#list>
                </table>
            </div>
        </#if>
    </#if>
</#macro>

<@main title="${user.firstName}" + " " + "${user.lastName}"/>
