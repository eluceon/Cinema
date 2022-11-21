<#import "/spring.ftl" as spring />
<#include "../../main-template.ftl"/>

<#macro content>
    <form method="post" action="films" enctype="multipart/form-data"/>
        <div>
            <div>
                <label>Title:</label>
                <@spring.formInput "movie.title" /> <@spring.showErrors "<br />"/>
            </div>
            <div>
                <label>Year of release:</label>
                <@spring.formInput "movie.yearOfRelease"/> <@spring.showErrors "<br />"/>
            </div>
            <div>
                <label>Age restriction:</label>
                <@spring.formInput "movie.ageRestriction"/> <@spring.showErrors "<br />"/>
            </div>
            <div>
                <label>Description:</label>
                <@spring.formInput "movie.description"/> <@spring.showErrors "<br />"/>
            </div>
            <div>
                <input name="file" placeholder="poster for a movie" type="file" accept="image/*" required/>
            </div>
        </div>
        <input type="submit" class="btn" value="Add movie">
    </form>

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
                <#list movies as row>
                    <tr>
                        <td>${row.title}</td>
                        <td>${row.yearOfRelease}</td>
                        <td>${row.ageRestriction}</td>
                        <td>${row.description}</td>
                        <td>
                             <#if row.poster??>
                                <a href="/admin/panel/films/poster?id=${row.id}" target="_blank">${row.poster.name}</a>
                             </#if>
                        </td>
                    </tr>
                </#list>
            </table>
        </div>
    </#if>
</#macro>

<@main title="🎞 Films"/>
