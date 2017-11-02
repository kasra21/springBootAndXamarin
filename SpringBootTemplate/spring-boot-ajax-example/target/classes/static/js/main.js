$(document).ready(function () {

	$("#getBtn")[0].onclick = onGetClick;
    $("#searchBtn")[0].onclick = onSearchClick;
    $("#deleteBtn")[0].onclick = onDeleteClick;
    $("#addBtn")[0].onclick = onAddClick;
    

});

this.onGetClick = function() {

    $("#getBtn").prop("disabled", true);

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/api/getUsers",
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);

            console.log("SUCCESS : ", data);
            $("#getBtn").prop("disabled", false);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#getBtn").prop("disabled", false);

        }
    });
}


this.onSearchClick = function() {

    var req = {}
    req["username"] = $("#username").val();

    $("#searchBtn").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/search",
        data: JSON.stringify(req),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);

            console.log("SUCCESS : ", data);
            $("#searchBtn").prop("disabled", false);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#searchBtn").prop("disabled", false);

        }
    });
}

this.onDeleteClick = function() {

    var req = {}
    req["username"] = $("#username").val();

    $("#deleteBtn").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/deleteUser",
        data: JSON.stringify(req),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);

            console.log("SUCCESS : ", data);
            $("#deleteBtn").prop("disabled", false);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#deleteBtn").prop("disabled", false);

        }
    });
}

this.onAddClick = function() {

    var req = {}
    req["username"] = $("#addUsername").val();
    req["email"] = $("#addEmail").val();
    req["first"] = $("#addFirst").val();
    req["last"] = $("#addLast").val();

    $("#addBtn").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/addUser",
        data: JSON.stringify(req),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);

            console.log("SUCCESS : ", data);
            $("#addBtn").prop("disabled", false);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#addBtn").prop("disabled", false);

        }
    });
}