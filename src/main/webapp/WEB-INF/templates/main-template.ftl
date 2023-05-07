<#macro main title>
    <!DOCTYPE html>
    <html lang="en">

    <html>
        <head>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" type="text/css" href="/resources/static/css/main.css">

            <title>${title}</title>
        </head>

        <body>
        <div class="column_main_wrapper">
            <div class="column_main">
                <H1 style="text-align: center">${title}</H1>
                <hr>
                <@content/>
            </div>
        </div>
        </body>
    </html>
</#macro>