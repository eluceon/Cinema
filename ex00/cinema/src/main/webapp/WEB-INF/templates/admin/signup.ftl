<#import "/spring.ftl" as spring />
<#include "../main-template.ftl"/>

<!DOCTYPE html>
<html lang="en">

<#macro content>
    <h1>Register Form</h1>
    <form method="post">
        <div class="row">
            <div class="col">
                <label>First Name:</label><br />
                <@spring.formInput "admin.firstName"/> <@spring.showErrors "<br />"/>
            </div>
            <div class="col">
                <label>Last Name:</label><br />
                <@spring.formInput "admin.lastName"/> <@spring.showErrors "<br />"/>
            </div>
            <div class="col">
                <label>Email:</label><br />
                <@spring.formInput "admin.email"/> <@spring.showErrors "<br />"/>
            </div>
            <div class="col">
                <label>Phone:</label><br />
                <@spring.formInput "admin.phoneNumber"/> <@spring.showErrors "<br />"/>
            </div>
            <div class="col">
                <label>Password:</label><br />
                <@spring.formInput "admin.password"/> <@spring.showErrors "<br />"/>
            </div>
        </div>
        <input type="submit" class="btn" value="Submit">
    </form>
</#macro>

<@main title="👤 Sign Up"/>