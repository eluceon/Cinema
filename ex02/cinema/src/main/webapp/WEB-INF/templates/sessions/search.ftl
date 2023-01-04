<#include "../main-template.ftl"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<#macro content>
    <form action="">
        <div class="container">
            <input type="text" id="search" name="filmName" placeholder="Enter a film name"/>
        </div>
    </form>
    <div id="sessions-search-result"></div>

    <script>
        $(document).ready(function () {
            $("#search").keyup(function () {
                $("#sessions-search-result").html("");
                var searchField = $("#search").val();
                if (searchField !== "") {
                    $.ajax({
                        url: "sessions/search",
                        method: "GET",
                        data: {filmName: searchField},
                        dataType: "JSON",
                        success: function (data) {
                            display(data);
                        }
                    })
                }
            });
        });

        function display(data) {
            data.sessions.forEach(function (session) {
                $("#sessions-search-result").append(
                    '<div class="searchItem" style="display: inline-block; margin: 5px; text-align: center;">'
                    + '<img src='
                        + session.movie.poster.path
                        + ' width="150" height="225"/>'
                        + '<div>'
                            + session.dateTime
                        + '</div>'
                        + '<div>'
                            + '<a href="/sessions/'
                                + session.id
                                + '" target="_blank">'
                                + session.movie.title
                            + '</a>'
                        + '</div>'
                    + '</div>'
                );
            });
        }
    </script>
</#macro>

<@main title="🫣 Sessions Search"/>