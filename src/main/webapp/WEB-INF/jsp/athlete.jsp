
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
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.css"></script>
        
    </head>

    <body>
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Rio'Cognized Application Management Tool</a>
    </div>
    <div>
      <ul class="nav navbar-nav">
         <li class="active"><a href="#">Home</a></li>
         <li><a href="/image">Images Management</a></li>
         <li><a href="http://lynxlabs.insa-lyon.fr">Database Management</a></li>
         <li><a href="http://lynxlabs.insa-lyon.fr">Lynxlabs Website</a></li>
      </ul>
    </div>
    </div>
    </nav>
        <div class="container" style="text-align: center">
            <div class="row" style="padding-top: 50px">
                <div class="span8 offset2">
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
<script>
    $('.datepicker').datepicker()
</script>