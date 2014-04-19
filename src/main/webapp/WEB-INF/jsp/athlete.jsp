
<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

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

        <div class="container" style="text-align: center">
            <div class="row">
                <div class="span8 offset2">
                    
                    <div class="add"  style="max-width: 500px; display: inline-block; text-align: left">
                    <h1>Athlètes</h1>
                    <form:form method="post" action="${contextPath}/add" commandName="athlete" enctype="multipart/form-data" class="form-horizontal">
                        <div class="control-group">
                            <form:label cssClass="control-label" path="surname">Prénom :</form:label>
                                <div class="controls">
                                <form:input path="surname" class="form-control" placeholder="Prénom"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <form:label cssClass="control-label" path="name">Nom :</form:label>
                                <div class="controls">
                                <form:input path="name" class="form-control" placeholder="Nom"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <form:label cssClass="control-label" path="age">Age :</form:label>
                            <div class="controls">
                                <form:input path="age" class="form-control" placeholder="Age"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <form:label cssClass="control-label" path="sport">Sport :</form:label>
                            <div class="controls">
                                <form:input path="sport" class="form-control" placeholder="Sport"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <form:label cssClass="control-label" path="country">Pays :</form:label>
                            <div class="controls">
                                <form:input path="country" class="form-control" placeholder="Pays"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <form:label cssClass="control-label" path="content">Palmarès:</form:label>
                            <div class="controls">
                                <form:textarea path="content" class="form-control" placeholder="Palmarès de l'athlete"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <form:label cssClass="control-label" path="content">Ne pas identifier:</form:label>
                            <div class="controls">
                               <form:checkbox path="privacy" placeholder="Pays" class="form-control"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <form:label cssClass="control-label" path="content">Image :</form:label>
                            <div class="controls">
                                <input type="file" name="file" id="file" class="form-control"></input>
                            </div>
                        </div>
                        <div class="control-group">
                            <div class="controls">
                                <br>
                                <input type="submit" value="Add Athlete" class="btn-primary btn btn-block"/>
                            </form:form>
                            </div>
                        </div>
                    </div>
                     <div class="show">
                        <c:if test="${!empty athletes}">
                        <h3>Athletes</h3>
                        <table class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>Nom</th>
                                    <th>Pays</th>
                                    <th>Age</th>
                                    <th>Sport</th>
                                    <th>Palmares</th>
                                    <th>Image</th>
                                    <th>Privé</th>
                                    <th>&nbsp;</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${athletes}" var="athlete">
                                    <tr>
                                        <td>${athlete.name}, ${athlete.surname}</td>
                                        <td>${athlete.country.name}</td>
                                        <td>${athlete.age}</td>
                                        <td style="text-transform: capitalize">${athlete.sport.id}</td>
                                        <td>${athlete.content}</td>
                                        <td>
                                            <c:if test="${!empty athlete.image}">
                                            <img
                                                src="${pageContext.request.contextPath}/image/download/${athlete.image.id}" border="0" width="200px"/></c:if></td>
                                        <td><b><tags:ouinon value="${athlete.privacy}"/></b></td>
                                        <td>
                                            <form action="${contextPath}/delete/${athlete.id}" method="post"><input type="submit" class="btn btn-danger btn-block" value="Delete"/></form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        </c:if>
                     </div>
                </div>
            </div>
        </div>

    </body>
</html>