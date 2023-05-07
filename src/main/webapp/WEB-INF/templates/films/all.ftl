<#include "../main-template.ftl"/>

<#macro content>
    <#if movies?has_content>
        <div class="container">
            <table class="statistics">
                <thead>
                    <th>Title</th>
                    <th>Year of release</th>
                    <th>Age restriction</th>
                    <th>Description</th>
                    <th>Poster</th>
                </thead>
                <#list movies as movie>
                    <tr>
                        <td><a href="/films/${movie.id}">${movie.title}</a></td>
                        <td>${movie.yearOfRelease}</td>
                        <td>${movie.ageRestriction}+</td>
                        <td>${movie.description}</td>
                        <td>
                            <#if movie.poster??>
                                <a href="${movie.poster.path}" target="_blank">${movie.poster.name}</a>
                            <#else>
                                No poster
                            </#if>
                        </td>
                    </tr>
                </#list>
            </table>
        </div>
    </#if>
</#macro>

<@main title="🎞 Films"/>
