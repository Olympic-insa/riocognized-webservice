<%-- 
    Image   : image
    Created on : 18 févr. 2014, 15:39:20
    Author     : alex
--%>

<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
    <head>
        <meta charset="utf-8">
        <title>Rio'Cognized Application</title>

        <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Latest compiled and minified JavaScript -->
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css">
        <!-- Optional theme -->
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap-theme.min.css">
        <style type="text/css">
            /* The white background content wrapper */
            body {
                padding-top: 40px;
                padding-bottom: 40px;
                background-color: #eee;
            }

            .form-signin {
                max-width: 400px;
                padding: 15px;
                margin: 0 auto;
            }
            .form-signin .form-signin-heading,
            .form-signin .checkbox {
                margin-bottom: 10px;
            }
            .form-signin .checkbox {
                font-weight: normal;
            }
            .form-signin .form-control {
                position: relative;
                height: auto;
                -webkit-box-sizing: border-box;
                -moz-box-sizing: border-box;
                box-sizing: border-box;
                padding: 10px;
                font-size: 16px;
            }
            .form-signin .form-control:focus {
                z-index: 2;
            }
            .form-signin input[type="email"] {
                margin-bottom: 5px;
                border-bottom-right-radius: 0;
                border-bottom-left-radius: 0;
            }
            .form-signin input[type="password"] {
                margin-bottom: 10px;
                margin-top: 10px;
                border-top-left-radius: 0;
                border-top-right-radius: 0;
            }

        </style>
    </head>

    <body>
        <div class="container" id="content">
            <div class="login-form"><p style="text-align:center"><img src="${pageContext.request.contextPath}/img/logo_imane_transparent.png" height="30px"></p>
                <form class="form-signin" action="j_spring_security_check" method="post">
                    <h2 class="form-signin-heading">Authentification Required</h2><br>
                    <input type="username" class="form-control" name='username' placeholder="Username" required autofocus>
                    <input type="password" class="form-control" name='password' placeholder="Password" required>

                    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
                </form>
            </div>
        </div> <!-- /container -->

    </body>
</html>