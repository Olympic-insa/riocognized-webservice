
<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta charset="utf-8">
        <title>Rio'Cognized Application</title>

        <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css">
        <!-- Optional theme -->
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap-theme.min.css">
        <!-- Latest compiled and minified JavaScript -->
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
        
    </head>

    <body>

        <div class="container">
            <div class="row">
                <div class="span8 offset2">
                    <h1>Athletes</h1>
                    <form:form method="post" action="/add" commandName="athlete" class="form-horizontal">
                        <div class="control-group">
                            <form:label cssClass="control-label" path="surname">First Name:</form:label>
                                <div class="controls">
                                <form:input path="surname" class="form-control" placeholder="First Name"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <form:label cssClass="control-label" path="name">Last Name:</form:label>
                                <div class="controls">
                                <form:input path="name" class="form-control" placeholder="Name"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <form:label cssClass="control-label" path="content">Palmarès:</form:label>
                            <div class="controls">
                                <form:input path="content" class="form-control" placeholder="Palmarès de l'athlete"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <div class="controls">
                                <br>
                                <input type="submit" value="Add Athlete" class="btn-primary"/>
                            </form:form>
                        </div>
                    </div>

                    <c:if test="${!empty athletes}">
                        <h3>Athletes</h3>
                        <table class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Palmares</th>
                                    <th>&nbsp;</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${athletes}" var="athlete">
                                    <tr>
                                        <td>${athlete.name}, ${athlete.surname}</td>
                                        <td>${athlete.content}</td>
                                        <td>
                                            <form action="/delete/${athlete.id}" method="post"><input type="submit" class="btn btn-danger btn-mini" value="Delete"/></form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </div>
            </div>
        </div>

    </body>
</html>