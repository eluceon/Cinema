<#import "/spring.ftl" as spring />
<#include "../../main-template.ftl"/>

<!DOCTYPE html>
<html lang="en">

<#macro content>
    <form method="post">
        <div class="container">
            <div>
                <label>First Name:</label><br />
                <@spring.formInput "admin.firstName"/> <@spring.showErrors "<br />"/>
            </div>
            <div>
                <label>Last Name:</label><br />
                <@spring.formInput "admin.lastName"/> <@spring.showErrors "<br />"/>
            </div>
            <div>
                <label>Email:</label><br />
                <@spring.formInput "admin.email"/> <@spring.showErrors "<br />"/>
            </div>
            <div>
                <label>Phone:</label><br />
                <@spring.formInput "admin.phoneNumber"/> <@spring.showErrors "<br />"/>
            </div>
            <div>
                <label>Password:</label><br />
                <@spring.formPasswordInput "admin.password"/> <@spring.showErrors "<br />"/>
            </div>
            <input type="submit" class="btn" value="Submit">
        </div>
    </form>
</#macro>

<@main title="👤 Register Form"/>