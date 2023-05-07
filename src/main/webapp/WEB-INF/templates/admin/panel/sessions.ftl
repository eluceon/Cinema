<#import "/spring.ftl" as spring />
<#include "../../main-template.ftl"/>

<#macro content>
    <form method="post" action="sessions">
        <div class="container">
            <div>
                <label for="movie">Select a film:</label>
                <select name="movie">
                    <#list movies as movie>
                        <option value=${movie.id}>${movie.title}</option>
                    </#list>
                </select>
            </div>
            <div>
                <label for="hall">Select a hall:</label>
                <select name="hall">
                    <#list movieHalls as hall>
                        <option value=${hall.id}>${hall.serialNumber}</option>
                    </#list>
                </select>
            </div>
            <div>
                <label for="dateTime">Enter session date and time</label>
                <input name="dateTime" type="datetime-local" min="${.now?string.iso_m_nz}" value="${.now?string.iso_m_nz}" required/>
            </div>
            <div>
                <label for="price">Enter ticket price:</label>
                <input name="price" type="number" value="100" min="0" max="1000" required/>
            </div>
            <div>
                <button type="Submit" class="btn">Add session</button>
            </div>
        </div>
    </form>
    <#if sessions?has_content>
        <div class="container">
            <table class="statistics">
                <thead>
                <th>Date and time</th>
                <th>Film title</th>
                <th>Movie hall serial number</th>
                <th>Ticker price</th>
                </thead>
                <#list sessions as row>
                    <tr>
                        <td>${row.dateTime}</td>
                        <td>${row.movie.title}</td>
                        <td>${row.movieHall.serialNumber}</td>
                        <td>${row.price}</td>
                    </tr>
                </#list>
            </table>
        </div>
    </#if>
</#macro>

<@main title="🌅 Sessions"/>
