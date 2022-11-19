<#import "/spring.ftl" as spring />
<#include "../main-template.ftl"/>

<#macro content>
    <ul>
        <li>${admin.firstName} ${admin.lastName} ${admin.email}</li>
    </ul>
    <form action="/admin/panel/films" method="get">
        <div class="container">
            <button type="submit" class="btn">🎞 Films panel</button>
        </div>
    </form>
    <form action="/admin/panel/halls" method="get">
        <div class="container">
            <button type="submit" class="btn">🍿 Halls panel</button>
        </div>
    </form>
    <form action="/admin/panel/sessions" method="get">
        <div class="container">
            <button type="submit" class="btn">🌅 Sessions panel</button>
        </div>
    </form>
    <form action="/admin/logout" method="post">
        <div class="container">
            <button type="submit" class="btn">🚪 Logout</button>
        </div>
    </form>
</#macro>

<@main title="⚙️ Cinema Admin Panel"/>