<%@tag body-content="empty" language="java" pageEncoding="UTF-8" %>
<a href="../../jsp/registration.jsp"></a>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
<c:when test="${not empty userAva}"> 
<img alt="avatar" src="${userAva}" style="width:30px;height:30px;"><br>
</c:when>
<c:otherwise>
 <img alt="avatar" src="images/customavatar/custom.png" style="width:30px;height:30px;"><br>
</c:otherwise>
</c:choose>