<!-- Modal para interagir com a pergunta -->

<div class="modal fade" tabindex="-1" role="dialog" id="viewQuestionModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Visualizar Pergunta</h4>
			</div>
			<div class="modal-body view-question-modal-body">

				<div class="alert alert-danger hidden" role="alert" id="viewQuestionErrorMessages"></div>

				<form>
					<input type="hidden" id="viewQuestionId">

					<div class="form-group">
						<label for="newQuestionCategory">Título</label>
						<input type="text" class="form-control" id="viewQuestionTitle" disabled>
					</div>
					<div class="form-group">
						<label for="newQuestionCategory">Categoria</label>
						<input type="text" class="form-control" id="viewQuestionCategory" disabled>
					</div>
					<div class="form-group">
						<label for="newQuestionCategory">Pergunta</label>
						<textarea class="form-control" rows="5" id="viewQuestionDescription" disabled></textarea>
					</div>
					<div class="form-group">
						<label for="newQuestionTitle">Respostas <a href="#" onclick="viewAnswersInEnglish()" class="translate-link">Traduzir para Inglês</a></label>
						<div id="viewQuestionAnswers"></div>
					</div>
					<div class="form-group">
						<label for="newQuestionQuestion">Postar Nova Reposta</label>
						<textarea class="form-control" rows="5" id="newAnswer" placeholder="Nova Resposta"></textarea>
					</div>
				</form>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
				<button type="button" class="btn btn-primary" onclick="sendAnswer()">Responder!</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
