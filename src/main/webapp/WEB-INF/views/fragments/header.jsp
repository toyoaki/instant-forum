<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authentication var="user" property="principal" />

<nav class="navbar navbar-inverse navbar-fixed-top header">
	<div class="container">
		<div class="navbar-header">
			<sec:authorize access="isAuthenticated()">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
			</sec:authorize>
			<span class="navbar-brand app-name">Instant Forum</span>
		</div>
		<sec:authorize access="isAuthenticated()">
			<div id="navbar" class="collapse navbar-collapse pull-right">
				<ul class="nav navbar-nav">
					<li>
						<div class="username">Usuário: ${user.username}</div>
					</li>
					<li>
						<a href="j_spring_security_logout">
							<span class="glyphicon glyphicon-log-out"></span>
							Sair
						</a>
					</li>
				</ul>
			</div>

		</sec:authorize>
		<!--/.nav-collapse -->
	</div>
</nav>
