<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Guido's Quick Pantry</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${path}/resources/css/styles.css" rel="stylesheet" type="text/css"/>
    <link href="https://fonts.googleapis.com/css?family=Indie+Flower" rel="stylesheet">
</head>
<body>
<div id='wrapper'>
    <header>
        <h1>
            <a href='${path}'>Guido's Quick Pantry</a>
        </h1>
    </header>
    <nav>
        <ul>
            <li><b><a href='${path}'>Home</a></b></li>
            <li><b><a href='${path}/mainDishes'>Main Dishes</a></b></li>
        </ul>
    </nav>
    <main>



        <div id="container">

            <div id="content">

                <form:form action="${path}/save" enctype="multipart/form-data" modelAttribute="groceryItem"
                           method="post">
                    <form:hidden path="id"/>
                    <form:hidden path="imagePath"/>

                    <table>
                        <tr>
                            <td><label>Name</label></td>
                            <td><form:input path="itemName"/>
                                <form:errors path="itemName" cssClass="error"/></td>
                        </tr>
                        <tr>
                            <td><label>Description</label></td>
                            <td><form:input path="itemDescription"/>
                                <form:errors path="itemDescription" cssClass="error"/></td>
                        </tr>
                        <tr>
                            <td><label>Price</label></td>
                            <td><form:input path="itemPrice"/>
                                <form:errors path="itemPrice" cssClass="error"/></td>
                        </tr>
                        <tr>
                            <td><label>Image</label></td>
                            <td>
                                <input type="file" name="image">
                                <c:if test="${groceryItem.imagePath != null}">
                                    <br/><br/>
                                    <img src="${path}/resources/img/${groceryItem.imagePath}" alt="${groceryItem.imagePath}">
                                    <br/>${groceryItem.imagePath}
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" value="Save" class="save"></td>
                        </tr>
                    </table>
                </form:form>

                <div style="clear: both;">
                    <p>
                        <a href="${path}/mainDishes">Back to List</a>
                    </p>
                </div>

            </div>
        </div>




    </main>
    <footer>
        <small>
            <i>
                Copyright &copy 2018 Guido's Quick Pantry<br>
                <a href="contact_us.html">Guido's Quick Pantry</a>
            </i>
        </small>
    </footer>
</div>
</body>
</html>