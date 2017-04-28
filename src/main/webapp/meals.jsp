<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meal list</title>
    <style>
        .exceed-true {background: #ffdbdb;}
    </style>
</head>

<body>

<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>

<table border="1">
    <tr>
        <th>Date/Time</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>

    <jsp:useBean id="meals" scope="request" type="java.util.List"/>

    <c:forEach items="${meals}" var="meal">
        <tr class="exceed-${meal.exceed}">
            <td>${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>