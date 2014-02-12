
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
 
    <link href="http://twitter.github.io/bootstrap/assets/css/bootstrap.css" rel="stylesheet">
    <link href="http://twitter.github.io/bootstrap/assets/css/bootstrap-responsive.css" rel="stylesheet">
</head>
 
<body>
 
<div class="container">
    <div class="row">
        <div class="span8 offset2">
            <h1>Athletes</h1>
            <form:form method="post" action="add" commandName="athlete" class="form-horizontal">
            <div class="control-group">
                <form:label cssClass="control-label" path="surname">First Name:</form:label>
                <div class="controls">
                    <form:input path="surname"/>
                </div>
            </div>
            <div class="control-group">
                <form:label cssClass="control-label" path="name">Last Name:</form:label>
                <div class="controls">
                    <form:input path="name"/>
                </div>
            </div>
            <div class="control-group">
                <form:label cssClass="control-label" path="content">Palmares:</form:label>
                <div class="controls">
                    <form:input path="content"/>
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <input type="submit" value="Add Athlete" class="btn"/>
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
                                <form action="delete/${athlete.id}" method="post"><input type="submit" class="btn btn-danger btn-mini" value="Delete"/></form>
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