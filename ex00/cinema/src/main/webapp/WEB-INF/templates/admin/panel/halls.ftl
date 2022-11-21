<#import "/spring.ftl" as spring />
<#include "../../main-template.ftl"/>

<#macro content>
    <form method="post">
        <div>
            <div>
                <label>Serial number:</label>
                <@spring.formInput "movieHall.serialNumber" /> <@spring.showErrors "<br />"/>
            </div>
            <div>
                <label>Number of seats:</label>
                <@spring.formInput "movieHall.seats"/> <@spring.showErrors "<br />"/>
            </div>
        </div>
        <input type="submit" class="btn" value="Add hall">
    </form>

    <#if movieHalls?has_content>
        <div class="container">
            <table class="statistics">
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

