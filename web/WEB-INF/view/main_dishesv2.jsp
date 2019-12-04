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

                <!-- add item button -->

                <button class="add-button"
                        onclick="window.location.href='${path}/showAddItemPage'; return false;">Add Item
                </button>

                <!-- search form -->
                <form:form action="${path}/search" method="GET">
                    Search items <input type="search" name="searchTerm"/>
                    <input type="submit" value="Search" class="add-button"/>
                </form:form>

                <!-- item list table -->

                <table>
                    <tr>
                        <th></th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Action</th>
                    </tr>
                    <c:forEach var="tempItem" items="${groceryItems}">

                        <!-- construct an "update" link with item id -->
                        <!-- c:url is same as JSP's response.encodeURL() -->
                        <c:url var="updateLink" value="/showUpdateGroceryItemForm">
                            <c:param name="groceryItemId" value="${tempItem.id}"/>
                        </c:url>

                        <!-- construct a "delete" link with item id -->
                        <c:url var="deleteLink" value="/delete">
                            <c:param name="groceryItemId" value="${tempItem.id}"/>
                        </c:url>

                        <tr>
                            <td>
                                <img src="${path}/resources/img/${tempItem.imagePath}"
                                     alt="${tempItem.itemName}"></td>
                            <td>${tempItem.itemName}</td>
                            <td>${tempItem.itemDescription}</td>
                            <td>${tempItem.itemPrice}</td>
                            <td>
                                <!-- display the update link -->
                                <a href="${updateLink}">Update</a>
                                |
                                <!-- display the delete link -->
                                <a href="${deleteLink}"
                                   onclick="if (!confirm('Are you sure?')) return false">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

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