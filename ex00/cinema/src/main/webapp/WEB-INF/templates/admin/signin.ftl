<#import "/spring.ftl" as spring />
<#include "../main-template.ftl"/>

<#macro content>
    <h1>Login Form</h1>
    <form method="post">
        <div class="row">
            <div class="col">
                <label>Email:</label><br />
                <@spring.formInput "loginForm.email"/> <@spring.showErrors "<br />"/>
            </div>
            <div class="col">
                <label>Password:</label><br />
                <@spring.formInput "loginForm.password"/> <@spring.showErrors "<br />"/>
            </div>

        </div>
        <input type="submit" class="btn" value="Sign me In">
    </form>
</#macro>

<@main title="👤 Sign In"/>