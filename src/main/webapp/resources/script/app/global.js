function displayModal(modalId) {
	$('#' + modalId).modal('show');
}

function hideModal(modalId) {
	$('#' + modalId).modal('hide');
}

function displayErrorMessage(msg, errorComponentId) {
	errorComponentId = errorComponentId ? errorComponentId : "errorMessages";
	var errorMessages = $("#" + errorComponentId);
	errorMessages.empty();
	errorMessages.append(msg);
	errorMessages.removeClass("hidden");
	errorMessages.addClass("show");
}

function hideErrorMessage(errorComponentId) {
	var errorMessages = $("#" + errorComponentId);
	errorMessages.removeClass("show");
	errorMessages.addClass("hidden");
}

function callAjax(method, url, data, success, errorPanel) {
	var error = function() {
		displayErrorMessage("Desculpe, algo deu errado...", errorPanel);
		unblockScreen();
	};

	var newSuccess = function(args) {
		if (success) {
			success.apply(this, arguments);
		}
		unblockScreen();
	}

	blockScreen();

	$.ajax({
		method : method,
		url : url,
		dataType : "json",
		contentType : 'application/json',
		data : data
	}).then(newSuccess, error);
}

function blockScreen() {
	$.blockUI({
		message : '<span>Carregando...</span>'
	});
}

function unblockScreen() {
	$.unblockUI();
}

function getContextRoot(){
	var path = window.location.pathname;
	return path.substring(0, path.indexOf("/",2));
}

function formatDate(date){
	return $.format.date(date, "dd/MM/yyyy HH:mm:ss")	
}
