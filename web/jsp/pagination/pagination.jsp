<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Pagination</title>
    </head>
<body>

<c:if test="${page!=null}">

<form action="${href}" method="Get">
<c:if test="${param.sort!=null}">
<input hidden="true" name="sort" value="${param.sort}">
</c:if>
<c:if test="${param.theme!=null}">
<input hidden="true" name="theme" value="${param.theme}">
</c:if>
<c:if test="${param.name!=null}">
<input hidden="true" name="name" value="${param.name}">
</c:if>
<div>
<c:choose>


	<c:when test="${page==1}">
		${page}<button type="submit" name="page" value="${page+1}">${page+1}</button>
	</c:when>
	
	<c:when test="${page==lastPage}">
		<button type="submit" name="page" value="${lastPage-1}">${page-1}</button>${page}
	</c:when>
	
	<c:otherwise>
		<button type="submit" name="page" value="${page-1}">${page-1}</button>${page}<button type="submit" name="page" value="${page+1}">${page+1}</button>
	</c:otherwise>

</c:choose>
</div>
<form>
</c:if>

</body>
</html>


























