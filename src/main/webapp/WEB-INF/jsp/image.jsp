<%-- 
    Image   : image
    Created on : 18 févr. 2014, 15:39:20
    Author     : alex
--%>

<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                    <h1>Images Manager</h1>
                        <form:form method="post" action="image/save.html" commandName="image" enctype="multipart/form-data" class="form-horizontal">
                            <form:errors path="*" cssClass="error"/>
                        <div class="control-group">
                            <form:label cssClass="control-label" path="name">Nom :</form:label>
                            <div class="controls">
                                <form:input path="name" class="form-control" placeholder="Nom"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <form:label cssClass="control-label" path="description">Description :</form:label>
                            <div class="controls">
                                <form:textarea path="description" class="form-control" placeholder="Description"/>
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
                                <input type="submit" value="Add Image" class="btn-primary btn btn-block"/>
                            </form:form>
                            </div>
                        </div>
                    </div>
                   
                    <div class="show" style="margin-top: 30px;text-align: center; vertical-align: middle">
                        <c:if  test="${!empty imageList}">
                        <h3>Images</h3>
                        <table class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Description</th>
                                    <th>Display</th>
                                    <th>&nbsp;</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${imageList}" var="image">
                                <tr>
                                        <td>${image.name}</td>
                                        <td>${image.description}</td>
                                        <td><img
                                                src="${pageContext.request.contextPath}/image/download/${image.id}" border="0" width="200px"/></td>
                                        <td width="20px">
                                            <a href="${pageContext.request.contextPath}/image/download/${image.id}"><img
                                                src="${pageContext.request.contextPath}/img/save_icon.gif" border="0"
                                                title="Download this image"/></a> 
                                         
                                            <a href="${pageContext.request.contextPath}/image/remove/${image.id}"
                                                onclick="return confirm('Are you sure you want to delete this image?')"><img
                                                src="img/delete_icon.gif" border="0"
                                                title="Delete this image"/></a> 
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