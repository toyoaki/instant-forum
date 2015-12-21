<!DOCTYPE html>
<html lang="en">

<head>

<jsp:include page="fragments/head.jsp" />

<link href="resources/style/app/login.css" rel="stylesheet">

<title>Login / Cadastro</title>
</head>

<body>

	<jsp:include page="fragments/header.jsp" />

	<div class="container">

		<div class="row">

			<div class="col-md-6 main-panel center-block">

				<h1 class="main-msg">Compartilhe seu conhecimento e ajude as pessoas!</h1>

				<div class="alert alert-danger hidden" role="alert" id="errorMessages"></div>

				<form id="logonForm" action="j_spring_security_check" method="POST">
					<div class="form-group">
						<label for="username">Usuário</label>
						<input type="text" name="username" class="form-control" id="username" placeholder="Seu Usuário">
					</div>
					<div class="form-group">
						<label for="password">Senha</label>
						<input type="password" name="password" class="form-control" id="password" placeholder="Sua Senha">
					</div>

					<a class="btn btn-primary" href="#" role="button" onclick="openRegisterModal()">Criar Nova Conta</a>
					<a class="btn btn-default" href="#" role="button" onclick="logon()">Entrar, Já Tenho Cadastro</a>
				</form>

			</div>

		</div>

	</div>

	<jsp:include page="categorySelectionModal.jsp" />

	<jsp:include page="fragments/footer.jsp" />

	<script src="resources/script/app/login.js" charset="utf-8"></script>

</body>

</html>
