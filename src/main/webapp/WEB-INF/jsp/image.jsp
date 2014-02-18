<%-- 
    Image   : image
    Created on : 18 févr. 2014, 15:39:20
    Author     : alex
--%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Image Manager - Olympic INSA</title>
</head>
<body>
 
<h2>Image Manager</h2>
 
<h3>Add new image</h3>
<form:form method="post" action="save.html" commandName="image" enctype="multipart/form-data">
    <form:errors path="*" cssClass="error"/>
    <table>
    <tr>
        <td><form:label path="name">Name</form:label></td>
        <td><form:input path="name" /></td> 
    </tr>
    <tr>
        <td><form:label path="description">Description</form:label></td>
        <td><form:textarea path="description" /></td>
    </tr>
    <tr>
        <td><form:label path="content">Image</form:label></td>
        <td><input type="file" name="file" id="file"></input></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Add Image"/>
        </td>
    </tr>
</table>  
</form:form>
 
<br/>
<h3>Image List</h3>
<c:if  test="${!empty imageList}">
<table class="data">
<tr>
    <th>Name</th>
    <th>Description</th>
    <th>&nbsp;</th>
</tr>
<c:forEach items="${imageList}" var="image">
    <tr>
        <td width="100px">${image.name}</td>
        <td width="250px">${image.description}</td>
        <td width="20px">
            <a href="${pageContext.request.contextPath}/download/${image.id}.html"><img
                src="${pageContext.request.contextPath}/img/save_icon.gif" border="0"
                title="Download this image"/></a> 
         
            <a href="${pageContext.request.contextPath}/remove/${image.id}.html"
                onclick="return confirm('Are you sure you want to delete this image?')"><img
                src="${pageContext.request.contextPath}/img/delete_icon.gif" border="0"
                title="Delete this image"/></a> 
        </td>
    </tr>
</c:forEach>
</table>
</c:if>
</body>
</html>