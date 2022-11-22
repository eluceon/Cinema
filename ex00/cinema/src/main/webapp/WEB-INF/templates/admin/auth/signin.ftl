<#import "/spring.ftl" as spring />
<#include "../../main-template.ftl"/>

<#macro content>
    <form method="post">
        <div class="container">
            <div>
                <label>Email:</label><br />
                <@spring.formInput "loginForm.email"/> <@spring.showErrors "<br />"/>
            </div>
            <div>
                <label>Password:</label><br />
                <@spring.formInput "loginForm.password"/> <@spring.showErrors "<br />"/>
            </div>
            <input type="submit" class="btn" value="Sign In">
        </div>
    </form>
</#macro>

<@main title="👤 Sign In"/>