<#macro main title>
    <!DOCTYPE html>
    <html lang="en">

    <html>
        <head>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" type="text/css" href="/static/css/main.css">

            <title>${title}</title>
        </head>

        <body>
            <@content/>
        </body>
    </html>
</#macro>