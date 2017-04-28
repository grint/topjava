<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meal list</title>
    <style>
        .exceed-true {
            background: #ffdbdb;
        }
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
        <th>Actions</th>
    </tr>

    <jsp:useBean id="meals" scope="request" type="java.util.List"/>

    <c:forEach items="${meals}" var="meal">
        <tr class="exceed-${meal.exceed}">
            <td>${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td>
                <a href="<c:url value='meals?action=edit&id=${meal.id}' />">Edit</a>
                <a href="<c:url value='meals?action=delete&id=${meal.id}' />">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>


<h1>
    <c:choose>
        <c:when test="${edit}">
            Edit meal
        </c:when>
        <c:otherwise>
            Add meal
        </c:otherwise>
    </c:choose>
</h1>

<form method="POST">
    <input type="hidden" name="id" value="${id}"/>
    <input type="hidden" name="action" value="update"/>

    <label for="dateTime">Date & Time</label>
    <br>

    <input required id="dateTime" placeholder="yyyy-MM-ddTHH:mm" name="dateTime" value="${meal.dateTime}"
           type="datetime-local"/>
    <br>

    <label for="description">Description</label>
    <br>

    <input required id="description" name="description" value="${meal.description}"/>
    <br>

    <label for="calories">Calories</label>
    <br>

    <input required id="calories" name="calories" value="${meal.calories}" type="number"/>
    <br>

    <c:choose>
        <c:when test="${edit}">
            <input type="submit" value="Save"/>
        </c:when>
        <c:otherwise>
            <input type="submit" value="Add"/>
        </c:otherwise>
    </c:choose>
</form>

</body>
</html>