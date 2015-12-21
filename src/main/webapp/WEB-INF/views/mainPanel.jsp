<!DOCTYPE html>
<html lang="en">

<head>

<jsp:include page="fragments/head.jsp" />

<link href="resources/style/app/mainPanel.css" rel="stylesheet">

<title>Painel de Perguntas</title>
</head>

<body>

	<jsp:include page="fragments/header.jsp" />

	<div class="container">

		<div class="row">

			<div class="col-md-10 main-panel center-block">

				<button type="button" class="btn btn-primary new-question-btn" onclick="displayNewQuestionModal();">Fazer Nova Pergunta</button>

				<p class="panel-description1">Painel de Perguntas, Vamos Ajudar!</p>
				<p class="panel-description2">Aqui você visualiza suas perguntas e as perguntas em que você é um expert.</p>
				
				<div class="alert alert-danger hidden" role="alert" id="errorMessages"></div>

				<table id="questionsTable" class="table table-striped">
					<thead>
						<tr>
							<th>Título</th>
							<th>Categoria</th>
							<th>Quem Criou</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>

			</div>

		</div>

	</div>

	<jsp:include page="newQuestionModal.jsp" />
	<jsp:include page="viewQuestionModal.jsp" />

	<jsp:include page="fragments/footer.jsp" />

	<script src="resources/script/app/mainPanel.js" charset="utf-8"></script>

</body>

</html>
