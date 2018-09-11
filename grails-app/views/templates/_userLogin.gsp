<div class="col-sm-10 col-sm-offset-1" style="margin-top: 30%;background-color: rgba(70,184,218,.5);font-weight: bold;">

    <g:form controller="login" action="authenticate">

        <div class="col-sm-4 col-sm-offset-4 form-group" style="margin-top: 2%;">
            <label class="label" style="font-weight: bold;font-size: x-large;">login</label>
        </div>

        <g:if test="${flash.message}">
            <div class="col-sm-12" style="color: red">
                ${flash.message}
            </div>
        </g:if>

        <div class="col-sm-12 form-group">
            <input type="text" name="username" id="username" placeholder=" User Name" class="form-control">
        </div>

        <div class="col-sm-12 form-group">
            <input type="password" name="password" id="password" placeholder=" Password" class="form-control">
        </div>

        <div class="col-sm-12 form-group">
            <input type="submit" name="logInBtn" id="logInBtn" value="login" class="btn btn-default form-control ">
        </div>

        <div class="col-sm-6 form-group">
            <input type="button" name="openSignUpBtn" id="openSignUpBtn" value="Sign Up" class="btn btn-default form-control" onclick="openSignUpForm()">
        </div>

        <div class="col-sm-4 col-sm-offset-2 form-control-static">
            <g:link controller="abc" action="abc" >Forgot Password</g:link>
        </div>

    </g:form>
</div>

<script>
    function openSignUpForm() {
        var div = document.getElementById("loginSingupFormDiv");
        var url = "${createLink(controller: 'signUp',action: 'openSignUpForm')}"
        $.ajax({
            url: url,
            success: function (data) {
                $(div).html("");
                $(div).html(data.template);
            },
            error: function (data){

            }
        })
    }
</script>
