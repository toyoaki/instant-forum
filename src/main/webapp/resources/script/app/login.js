$(document).ready(function() {
	if (window.location.href.endsWith("authenticationError")) {
		displayErrorMessage("Favor corrigir Usuário / Senha", "errorMessages");
	}
});

function logon() {
	if (validateLogon()) {
		$("#logonForm").submit();
	}
}

function validateLogon() {
	if (!username() || !password()) {
		displayErrorMessage("Preencher Usuário e Senha", "errorMessages");
		return false;
	}
	return true;
}

function openRegisterModal() {
	function verifyExistingUsername() {
		var user = $.ajax({
			type : "GET",
			url : "user/" + username(),
			async : false
		}).responseText;

		var userExists = user ? true : false;

		if (userExists) {
			displayErrorMessage("Favor escolher outro nome de usuário", "errorMessages");
		}

		return userExists;
	}

	if (validateLogon() && !verifyExistingUsername()) {
		displayModal("categoriesModal");
	}
}

function registerUser() {
	function validateNewUser() {
		if (getSelectedCategories().length == 0) {
			displayErrorMessage("Selecione ao menos uma Categoria", "modalErrorMessages");
			return false;
		}
		return true;
	}

	function getUser() {
		return {
			categories : getSelectedCategories(),
			username : username(),
			password : password()
		};
	}

	function getSelectedCategories() {
		var categoriesIds = [ "#fishingCb", "#javaCb", "#remodelCb", "#sertanejaCb", "#soccerCb", "#japaneaseFoodCb" ];
		var selectedCategories = categoriesIds.filter(function(category) {
			return $(category).prop('checked');
		});
		return selectedCategories.map(function(category) {
			return {
				id : $(category).attr('value')
			}
		});
	}

	var success = function() {
		logon();
	};

	if (validateNewUser())
		callAjax("POST", "user", JSON.stringify(getUser()), success, null);
}

function username() {
	return $("#username").val();
}

function password() {
	return $("#password").val();
}
