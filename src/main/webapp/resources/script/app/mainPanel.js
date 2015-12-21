var userQuestions;

$(document).ready(function() {
	refreshUserQuestions();
});

function refreshUserQuestions() {
	var success = function(questions) {
		userQuestions = questions;
		fillTableWithQuestions(questions)
	};

	function fillTableWithQuestions(questions) {
		var tbody = $("#questionsTable tbody").empty();

		questions.forEach(function(question) {
			var row = // 
			"<tr>" + // 
			createTd(question.title) + // 
			createTd(question.category.name) + //
			createTd(question.user.username) + //
			createTd("<a href='#'><span class='glyphicon glyphicon-eye-open' onclick='viewQuestion(" + question.id + ")'></span></a>") + // 
			"</tr>"
			tbody.append(row);
		});

		function createTd(content) {
			return "<td>" + content + "</td>";
		}
	}

	callAjax("GET", "question", null, success, null);
}

function displayNewQuestionModal() {
	function clearValues() {
		$('#newQuestionCategory').val(1);
		$("#newQuestionTitle").val("");
		$("#newQuestionDescription").val("");
		hideErrorMessage("newQuestionErrorMessages");
	}

	clearValues();
	displayModal('newQuestionModal');
}

function askQuestion() {
	var success = function() {
		hideModal("newQuestionModal");
		refreshUserQuestions();
	};

	function getQuestion() {
		return {
			category : {
				id : $('#newQuestionCategory').find(":selected").val()
			},
			title : newQuestionTitle(),
			description : newQuestionDescription()
		};
	}

	function validateQuestion() {
		if (!newQuestionTitle() || !newQuestionDescription()) {
			displayErrorMessage("Favor preencher Título e Descrição da pergunta", "newQuestionErrorMessages");
			return false;
		}
		return true;
	}

	if (validateQuestion())
		callAjax("POST", "question", JSON.stringify(getQuestion()), success, "newQuestionErrorMessages");
}

function viewQuestion(questionId) {
	function clearViewQuestionValues() {
		$("#newAnswer").val("");
		hideErrorMessage("viewQuestionErrorMessages");
	}

	function fillViewQuestionValues() {
		var question = userQuestions.filter(function(q) {
			return q.id == questionId
		})[0];

		$("#viewQuestionId").val(questionId);
		$("#viewQuestionTitle").val(question.title);
		$("#viewQuestionCategory").val(question.category.name);
		$("#viewQuestionDescription").val(question.description);
	}

	clearViewQuestionValues();
	fillViewQuestionValues();
	refreshAnswers(questionId, "pt");
	displayModal("viewQuestionModal");
}

function refreshAnswers(questionId, language) {
	var success = function(answers) {
		var answersDiv = $("#viewQuestionAnswers");
		answersDiv.empty();

		if (answers.length == 0) {
			answersDiv.append("<div>Seja o primeiro a responder!</div>");
		} else {
			answers.forEach(function(answer) {
				var answerDiv = "<div><p><b>" + answer.user.username + "</b></p><p>" + answer.text + "</p></div>"
				answersDiv.append(answerDiv);
			});
		}
	};
	callAjax("GET", "answer/" + questionId + "/" + language, null, success, "viewQuestionErrorMessages");
}

function viewAnswersInEnglish() {
	refreshAnswers($("#viewQuestionId").val(), "en");
}

function saveAnswer() {
	var success = function() {
		hideModal("viewQuestionModal");
	}

	function isValidAnswer() {
		if (!newAnswer()) {
			displayErrorMessage("Favor fornecer uma resposta", "viewQuestionErrorMessages");
			return false;
		}
		return true;
	}

	function getAnswer() {
		return {
			text : newAnswer(),
			question : {
				id : $("#viewQuestionId").val()
			}
		};
	}

	if (isValidAnswer())
		callAjax("POST", "answer", JSON.stringify(getAnswer()), success, "viewQuestionErrorMessages");
}

function newQuestionTitle() {
	return $("#newQuestionTitle").val();
}

function newQuestionDescription() {
	return $("#newQuestionDescription").val();
}

function newAnswer() {
	return $("#newAnswer").val();
}
