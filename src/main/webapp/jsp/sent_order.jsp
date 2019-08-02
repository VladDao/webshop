<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="/WEB_INF/tags/language.tld" %>
<%@ include file="/WEB-INF/jspf/directives/taglib.jspf"%>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<html>
  <head>
    <title><fmt:message key="order.title"/></title>
    <meta charset="utf-8">
      <link rel="stylesheet" type="text/css" href="resources/css/style.css">
      <script src='https://code.jquery.com/jquery-2.2.4.min.js'></script>
  </head>
  <body>
    <div class="cart-content sent-order">
      <h1 class="cart-content-header"><fmt:message key="sent.order.title"/></h1>
      <hr/>
      <table class="cart-list">
      <caption><fmt:message key="sent.order.details"/></caption>
          <tr>
            <td><fmt:message key="sent.order.client"/>:</td>
            <td>${order.user.name} ${order.user.surname}</td>
          </tr>
          <tr>
            <td><fmt:message key="order.id"/>:</td>
            <td>${order.id}</td>
          </tr>
          <tr>
            <td><fmt:message key="sent.order.status"/>:</td>
            <td>${order.orderStatus}</td>
          </tr>
          <tr>
            <td><fmt:message key="sent.order.date"/>:</td>
            <td>${order.date}</td>
          </tr>
          <tr>
            <td><fmt:message key="cart.table.total"/>:</td>
            <td>${totalCost}</td>
          </tr>
      </table>
      <hr/>
      <div class="buttons">
        <input id="go-to-main" type="button" value='<fmt:message key="error.404.back-to-main"/>' class="green">
      </div>
    </div>
    <a href="products" id="go-to-main-href"></a>
    <script type="text/javascript" src="resources/js/cart-order-ref.js"></script>
  </body>
</html>
