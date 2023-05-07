<#import "/spring.ftl" as spring />
<#include "../main-template.ftl"/>
<#macro content>
    <#if sessionInfo?has_content>
        <div class="container">
            <table class="statistics">
                <thead>
                <th>Price</th>
                <th>Date</th>
                </thead>
                <tr>
                    <td>${sessionInfo.price}</td>
                    <td>${sessionInfo.dateTime}</td>
                </tr>
            </table>
        </div>
        <div class="container">
            <h2 class="h2">Movie info</h2>
            <table class="statistics">
                <thead>
                    <th>Title</th>
                    <th>Year of release</th>
                    <th>Age restriction</th>
                    <th>Description</th>
                    <th>Poster</th>
                </thead>
                <tr>
                    <td>${sessionInfo.movie.title}</td>
                    <td>${sessionInfo.movie.yearOfRelease}</td>
                    <td>${sessionInfo.movie.ageRestriction}+</td>
                    <td>${sessionInfo.movie.description}</td>
                    <td>
                        <#if sessionInfo.movie.poster??>
                            <img src=${sessionInfo.movie.poster.path} style="max-width:255px">
                        <#else>
                            No poster
                        </#if>
                    </td>
                </tr>
            </table>
        </div>
        <div class="container">
            <h2 class="h2">Hall info</h2>
            <table class="statistics">
                <thead>
                    <th>Serial number</th>
                    <th>Seats count</th>
                </thead>
                <tr>
                    <td>${sessionInfo.movieHall.serialNumber}</td>
                    <td>${sessionInfo.movieHall.seats}</td>
                </tr>
            </table>
        </div>
    <#else>
        <h1 class="h1">No such session</h1>
    </#if>
</#macro>

<@main title="Session info"/>