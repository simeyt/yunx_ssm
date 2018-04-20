<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>


<script>
    $(function () {
        <c:if test="${!empty msg}">
            $("span.errorMessage").html("${msg}");
            $("div.loginErrorMessageDiv").show();
        </c:if>
        
        $("form.loginForm").submit(function () {
            if($("#name").val().length==0 || $("#password").val().length==0 ){
                $("span.errorMessage").html("请输入账号密码");
                $("div.loginErrorMessageDiv").show();
                return false;
            }
            return true;
        });

        $("from.loginForm input").keyup(function () {
            $("div.loginErrorMessageDiv").hidden();
        });

        var left = window.innerWidth/2+162;
        $("div.loginSmallDiv").css("left",left);

    })
</script>

<div id="loginDiv" style="position: relative">
    <div class="simpleLogo">
        <a href="forehome"><img src="img/site/yunxlogo.png"></a>
    </div>
    <img id="loginBackgroundImg" class="loginBackgroundImg" src="img/site/Background.jpg" style="width: 1800px;">

    <form class="loginForm" action="forelogin" method="post">
        <div id="loginSmallDiv" class="loginSmallDiv">
            <div class="loginErrorMessageDiv">
                <div class="alert alert-danger">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
                    <span class="errorMessage"></span>
                </div>
            </div>

            <div class="login_acount_text">账户登陆</div>
            <div class="loginInput">
                <span class="loginInputIcon">
                    <span class="glyphicon glyphicon-user"></span>
                </span>
                <input id="name" name="name" placeholder="手机/会员名/邮箱" type="text">
            </div>

            <div class="loginInput">
                <span class="loginInputIcon">
                    <span class="glyphicon glyphicon-lock"></span>
                </span>
                <input id="password" name="password" placeholder="密码" type="password">
            </div>
            <div style="margin-top: 20px">
                <button class="btn btn-block redButton" type="submit">登陆</button>
            </div>
            <div style="padding:20px 30px 20px;">
                <a class="notImplementLink" href="#nowhere">忘记密码</a>
                <a class="pull-right" href="registerPage">注册账号</a>
            </div>
        </div>
    </form>
</div>