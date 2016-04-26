var userQuestions;
var answerWebSocket;

$(document).ready(function() {
	refreshUserQuestions();
	$('#viewQuestionModal').on('hidden.bs.modal', closeAnswerWebsocket);
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
			createTd("<a href='javascript:;'><span class='glyphicon glyphicon-eye-open' onclick='viewQuestion(" + question.id + ")'></span></a>") + // 
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

	function initAnswerWebSocket() {
		function onNewAnswer(answer) {
			var responseObj = JSON.parse(answer.body);
			if (responseObj.error) {
				displayErrorMessage("Ocorreu um erro ao receber nova mensagem...", "viewQuestionErrorMessages");
			} else {
				var answerDiv = getHtmlAnswerForAnswerPanel(responseObj);
				var answerPanel = getAnswerPanel();
				if (answerPanel.find(".no-answer-div").length != 0) {
					answerPanel.empty();
				}
				answerPanel.append(answerDiv);
			}
		}

		if (answerWebSocket != null)
			answerWebSocket.disconnect();
		answerWebSocket = new WebSocketStompClient(questionId, onNewAnswer);
		answerWebSocket.connect();
	}

	clearViewQuestionValues();
	fillViewQuestionValues();
	refreshAnswers(questionId, "pt");
	initAnswerWebSocket();
	displayModal("viewQuestionModal");
}

function refreshAnswers(questionId, language) {
	var success = function(answers) {
		var answersDiv = getAnswerPanel();
		answersDiv.empty();

		if (answers.length == 0) {
			answersDiv.append("<div class='no-answer-div'>Seja o primeiro a responder!</div>");
		} else {
			answers.forEach(function(answer) {
				var answerDiv = getHtmlAnswerForAnswerPanel(answer);
				answersDiv.append(answerDiv);
			});
		}
	};
	callAjax("GET", "answer/" + questionId + "/" + language, null, success, "viewQuestionErrorMessages");
}

function getHtmlAnswerForAnswerPanel(answer) {
	return "<div class='answer-container-parent'>" + //
	"	<div class='answer-container'>" + //
	"		<div class='answer-header'>" + //
	"			<span class='answer-header-username'>" + answer.user.username + "</span>" + //
	"			<span class='answer-header-time'>" + formatDate(answer.dateInMillis) + "</span>" + //
	"		</div>" + //
	"		<div class='answer-body'>" + answer.text + "</div>" + //
	"	</div>" + "</div>";
}

function getAnswerPanel() {
	return $("#viewQuestionAnswers");
}

function viewAnswersInEnglish() {
	refreshAnswers($("#viewQuestionId").val(), "en");
}

function sendAnswer() {
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

	if (isValidAnswer()) {
		answerWebSocket.sendMessage(JSON.stringify(getAnswer()));
		$("#newAnswer").val("");
	}
}

function closeAnswerWebsocket() {
	answerWebSocket.disconnect();
	answerWebSocket = null;
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
