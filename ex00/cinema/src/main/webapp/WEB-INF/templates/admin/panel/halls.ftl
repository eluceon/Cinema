<#import "/spring.ftl" as spring />
<#include "../../main-template.ftl"/>

<#macro content>
    <h1>Movie Halls</h1>
    <form method="post">
        <div class="row">
            <div class="col">
                <label>Enter hall's serial number:</label>
                <@spring.formInput "movieHall.serialNumber" /> <@spring.showErrors "<br />"/>
            </div>
            <div class="col">
                <label>Enter count of seats:</label>
                <@spring.formInput "movieHall.seats"/> <@spring.showErrors "<br />"/>
            </div>
        </div>
        <input type="submit" class="btn" value="Add hall">
    </form>

    <#if movieHalls?has_content>
        <div class="container">
            <table class="minimalistBlack">
                <thead>
                <th>Serial number</th>
                <th>Seats count</th>
                </thead>
                <#list movieHalls as row>
                    <tr>
                        <td>${row.serialNumber}</td>
                        <td>${row.seats}</td>
                    </tr>
                </#list>
            </table>
        </div>
    </#if>
</#macro>

<@main title="🍿 Movie Halls"/>

