<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <title>Accident App</title>
</head>
<body>

<jsp:useBean id="accident" scope="request" type="ru.job4j.accident.model.Accident"/>
<div class="container pt-1">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Edit accident
            </div>
            <div class="card-body">
                <form action="<c:url value='/save'/>" method="POST">
                    <input type="hidden" name="id" value="${accident.id}">
                    <div class="form-group row">
                        <label class="col-form-label col-sm-3" for="name">Name</label>
                        <input type="text" class="form-control col-sm-3" name="name" id="name"
                               value="${accident.name}" placeholder="Input accident name">
                    </div>
                    <div class="form-group row">
                        <label class="col-form-label col-sm-3" for="text">Text</label>
                        <input type="text" class="form-control col-sm-3" name="text" id="text"
                               value="${accident.text}" placeholder="Input accident text">
                    </div>
                    <div class="form-group row">
                        <label class="col-form-label col-sm-3" for="address">Address</label>
                        <input type="text" class="form-control col-sm-3" name="description" id="address"
                               value="${accident.address}" placeholder="Input accident address">
                    </div>
                    <div class="form-group row">
                        <label class="col-form-label col-sm-3"></label>
                        <button type="submit" class="btn btn-dark">Save</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>