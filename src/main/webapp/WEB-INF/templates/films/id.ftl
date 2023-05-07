<#include "../main-template.ftl"/>

<#macro content>
    <#if movie?has_content>
        <div class="container">
            <table class="statistics">
                <thead>
                    <th>Title</th>
                    <th>Year of release</th>
                    <th>Age restriction</th>
                    <th>Description</th>
                    <th>Poster</th>
                    <th>Chat Room</th>
                </thead>
                <tr>
                    <td><a href="/films/${movie.id}/chat">${movie.title}</a></td>
                    <td>${movie.yearOfRelease}</td>
                    <td>${movie.ageRestriction}+</td>
                    <td>${movie.description}</td>
                    <td>
                        <#if movie.poster??>
                            <img src=${movie.poster.path} style="max-width:255px">
                        <#else>
                            No poster
                        </#if>
                    </td>
                    <td>
                        <form action="/films/${movie.id}/chat" method="get">
                            <div class="container">
                                <button style="width: 250px" type="submit" class="btn">👥 Go To Chat!</button>
                            </div>
                        </form>
                    </td>
                </tr>
            </table>
        </div>
    </#if>
</#macro>

<@main title="${movie.title}"/>
