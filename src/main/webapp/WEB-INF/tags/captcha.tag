<%@tag body-content="empty" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
<c:when test="${not empty captchaKey}">
   <input type="hidden" value="${captchaKey}" name ="captchaId">
   <img alt="captcha" src="data:image/png;base64,${captchaValue}"><br>
</c:when>
<c:otherwise>
 <img alt="captcha" src="data:image/png;base64,${captchaValue}"><br>
</c:otherwise>
</c:choose>