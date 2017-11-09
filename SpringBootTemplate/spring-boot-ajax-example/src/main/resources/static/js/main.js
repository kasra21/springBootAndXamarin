$(document).ready(function() {

	$('#updateDiv')[0].hidden = true;
	$('#updateUsername')[0].readOnly = true;
	$('#updateBtn')[0].style.display = "none";
	$('#userTable')[0].hidden = true;
	$("#getBtn")[0].onclick = onGetClick;
	$("#searchBtn")[0].onclick = onSearchClick;
	$("#deleteBtn")[0].onclick = onDeleteClick;
	$("#addBtn")[0].onclick = onAddClick;
	$("#updateBtn")[0].onclick = onUpdateClick;

	$('#userTable').on('click', '.clickable-row', function(event) {
		$(this).addClass('active').siblings().removeClass('active');
		$('#updateDiv')[0].hidden = false;
		$('#updateBtn')[0].style.display="";
		var req = {};
		req["username"] = $(this)[0].cells[1].textContent;

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/api/search",
			data : JSON.stringify(req),
			dataType : 'json',
			cache : false,
			timeout : 600000,
			success : function(data) {
				$("#updateUsername")[0].value = data.result[0].username;
				$("#updateEmail")[0].value = data.result[0].email;
				$("#updateFirst")[0].value = data.result[0].first;
				$("#updateLast")[0].value = data.result[0].last;
			},
			error : function(e) {
				console.log("ERROR : ", e);
			}
		});

	});

});

this.onGetClick = function() {

	$("#getBtn").prop("disabled", true);

	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/api/getUsers",
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {

			var json = "<h4>Ajax Response</h4><pre>"
					+ JSON.stringify(data, null, 4) + "</pre>";
			$('#feedback').html(json);

			var content = "";
			for (var i = 0; i < data.result.length; i++) {
				content += "<tr class='clickable-row'><td>"
						+ data.result[i].first + " " + data.result[i].last
						+ "</td><td>" + data.result[i].username + "</td><td>"
						+ data.result[i].email + "</td></tr>";
			}
			$('#userTableBody').html(content);

			$('#userTable')[0].hidden = false;

			console.log("SUCCESS : ", data);
			$("#getBtn").prop("disabled", false);

		},
		error : function(e) {

			$('#userTable')[0].hidden = true;

			var json = "<h4>Ajax Response</h4><pre>" + e.responseText
					+ "</pre>";
			$('#feedback').html(json);

			console.log("ERROR : ", e);
			$("#getBtn").prop("disabled", false);

		}
	});
}

this.onSearchClick = function() {

	var req = {};
	req["username"] = $("#username").val();

	$("#searchBtn").prop("disabled", true);

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/api/search",
		data : JSON.stringify(req),
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {

			var json = "<h4>Ajax Response</h4><pre>"
					+ JSON.stringify(data, null, 4) + "</pre>";
			$('#feedback').html(json);

			console.log("SUCCESS : ", data);
			$("#searchBtn").prop("disabled", false);

		},
		error : function(e) {

			var json = "<h4>Ajax Response</h4><pre>" + e.responseText
					+ "</pre>";
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
		type : "POST",
		contentType : "application/json",
		url : "/api/deleteUser",
		data : JSON.stringify(req),
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {

			var json = "<h4>Ajax Response</h4><pre>"
					+ JSON.stringify(data, null, 4) + "</pre>";
			$('#feedback').html(json);

			console.log("SUCCESS : ", data);
			$("#deleteBtn").prop("disabled", false);

		},
		error : function(e) {

			var json = "<h4>Ajax Response</h4><pre>" + e.responseText
					+ "</pre>";
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
		type : "POST",
		contentType : "application/json",
		url : "/api/addUser",
		data : JSON.stringify(req),
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {

			var json = "<h4>Ajax Response</h4><pre>"
					+ JSON.stringify(data, null, 4) + "</pre>";
			$('#feedback').html(json);

			console.log("SUCCESS : ", data);
			$("#addBtn").prop("disabled", false);

		},
		error : function(e) {

			var json = "<h4>Ajax Response</h4><pre>" + e.responseText
					+ "</pre>";
			$('#feedback').html(json);

			console.log("ERROR : ", e);
			$("#addBtn").prop("disabled", false);

		}
	});
}

this.onUpdateClick = function() {

	var req = {};
	req["username"] = $("#updateUsername").val();
	req["email"] = $("#updateEmail").val();
	req["first"] = $("#updateFirst").val();
	req["last"] = $("#updateLast").val();

	$("#updateBtn").prop("disabled", true);

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/api/upadteUser",
		data : JSON.stringify(req),
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {

			var json = "<h4>Ajax Response</h4><pre>"
					+ JSON.stringify(data, null, 4) + "</pre>";
			$('#feedback').html(json);

			console.log("SUCCESS : ", data);
			$("#updateBtn").prop("disabled", false);

		},
		error : function(e) {

			var json = "<h4>Ajax Response</h4><pre>" + e.responseText
					+ "</pre>";
			$('#feedback').html(json);

			console.log("ERROR : ", e);
			$("#updateBtn").prop("disabled", false);

		}
	});
}
