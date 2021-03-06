
<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
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
        <!-- Latest compiled and minified JavaScript -->
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css">
        <!-- Optional theme -->
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap-theme.min.css">
        <script type="text/javascript">
            var dbManager = "http://lynxlabs.fr.nf/phppgadmin/";
            $(document).ready(function()
            {
                $('#openBtn').click(function() {
                    $('#myModal').modal('show');
                });
            });
        </script>
    </head>

    <body>
        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <p class="navbar-text navbar-left"><img src="${pageContext.request.contextPath}/img/logo_imane_transparent.png" height="30px"></p>
                    <a class="navbar-brand" href="#"> Application Management Tool</a>
                </div>
                <div>
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#">Home</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Images Management <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="/image">Athletes</a></li>
                                <li class="divider"></li>
                                <li><a href="/recognition">Face Recognition</a></li>
                                <li class="divider"></li>
                                <li><a href="/ad/manage">Adverts</a></li>
                            </ul>
                        </li>
                        <li><a href="#" class="btn" id="openBtn">Database Management</a></li>
                        <li><a href="http://lynxlabs.insa-lyon.fr">Lynxlabs Website</a></li>
                        <li><sec:authorize access="isAuthenticated()"><a href="/j_spring_security_logout">Sign Out</a></p></sec:authorize></li>
                        </ul>
                    </div>
                </div>
            </nav>
            <div class="container" style="text-align: center">
                <div class="row" style="padding-top: 50px">
                    <div class="span8 offset2">
                        <div class="add"  style="max-width: 500px; display: inline-block; text-align: left">
                            <h1>Athl�tes</h1>
                        <form:form method="post" action="${contextPath}/add" modelAttribute="athlete" enctype="multipart/form-data" class="form-horizontal">
                            <div class="control-group">
                                <form:label cssClass="control-label" path="surname">Pr�nom :</form:label>
                                    <div class="controls">
                                    <form:input path="surname" required="true" class="form-control" placeholder="First Name"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <form:label cssClass="control-label" path="name">Nom :</form:label>
                                    <div class="controls">
                                    <form:input path="name" required="true" class="form-control" placeholder="Name"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <form:label cssClass="control-label" path="stringDoB">Birthday :</form:label>
                                    <div class="controls">
                                    <form:input path="stringDoB" type="date" required="true" class="form-control" placeholder="dd/mm/yyyy"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <form:label cssClass="control-label" path="sport">Sport :</form:label>
                                    <div class="controls">
                                    <form:select path="sport" required="true" class="form-control" id="sport" items="${sportList}" itemValue="id" itemLabel="id"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <form:label cssClass="control-label" path="country">Country :</form:label>
                                    <div class="controls">
                                    <form:select path="country" required="true" class="form-control" id="country" items="${countryList}" itemValue="id" itemLabel="name"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <form:label cssClass="control-label" path="content">Palmar�s:</form:label>
                                    <div class="controls">
                                    <form:textarea path="content" required="true" class="form-control" placeholder="Description"/>
                                </div>
                            </div>
                 
                            <div class="control-group">
                                <form:label cssClass="control-label" path="image">Image :</form:label>
                                    <div class="controls">
                                        <input type="file" accept="image/*" required="true" name="file" id="file" class="form-control"></input>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <div class="controls">
                                        <br>
                                        <input type="submit" value="Add Athlete" class="btn-primary btn btn-block"/>

                                    </div>
                                </div>
                        </form:form>
                    </div>
                    <div class="show">
                        <c:if test="${!empty athletes}">
                            <h3>Athletes</h3>
                            <table class="table table-bordered table-striped">
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Country</th>
                                        <th>Age</th>
                                        <th>Sport</th>
                                        <th>Palmares</th>
                                        <th>Image</th>
                                        <th>Privacy</th>
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
        <div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-content" style='margin:30px auto;width:80%'>
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h3 class="modal-title" id="myModalLabel">Rio'cognized Database Management</h3>
                </div>
                <div class="modal-body">
                    <iframe src="http://lynxlabs.fr.nf/phppgadmin/" style="zoom:0.60" width="99.6%" frameborder="0" height="1000"></iframe>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Fermer</button>
                </div>
            </div>
        </div>
    </body>
</html>
