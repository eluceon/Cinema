<#include "../main-template.ftl"/>

<#macro content>
    <div class="/admin/profile">
        <div class="avatar">
            <#if avatarHistory?has_content>
                <img src=${avatarHistory[avatarHistory?size - 1].path}/>
            <#else>
                <img src="/resources/static/images/noimage.jpg" />
            </#if>

            <form action="/admin/profile" method="post" enctype="multipart/form-data">
                <label>
                    Select
                    <input type="file" name="file" accept="image/*" required>
                </label>
                <input type="submit" value="Upload">
            </form>
        </div>
        <p>First Name: ${user.firstName}</p>
        <p>Last Name: ${user.lastName}</p>
        <p>Phone: ${user.phoneNumber}</p>
        <p>Email: ${user.email}</p>
    </div>
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
                            <td><a href="${row.path}" target="_blank">${row.name}</a></td>
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
