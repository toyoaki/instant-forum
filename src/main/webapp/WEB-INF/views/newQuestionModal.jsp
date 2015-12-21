<!-- Modal para criar nova pergunta -->

<div class="modal fade" tabindex="-1" role="dialog" id="newQuestionModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Nova Pergunta</h4>
			</div>
			<div class="modal-body">

				<div class="alert alert-danger hidden" role="alert" id="newQuestionErrorMessages"></div>
				
				<form>
					<div class="form-group">
						<label for="newQuestionCategory">Categoria</label>
						<select class="form-control" id="newQuestionCategory">
							<option value="1">Pesca</option>
							<option value="2">Java</option>
							<option value="3">Reforma de Casa</option>
							<option value="4">Música Sertaneja</option>
							<option value="5">Futebol</option>
							<option value="6">Comida Japonesa</option>
						</select>
					</div>
					<div class="form-group">
						<label for="newQuestionTitle">Título da Pergunta</label>
						<input type="text" class="form-control" id="newQuestionTitle" placeholder="Algo resumido">
					</div>
					<div class="form-group">
						<label for="newQuestionQuestion">Pergunta</label>
						<textarea class="form-control" rows="5" id="newQuestionDescription" placeholder="Algo detalhado"></textarea>
					</div>
				</form>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
				<button type="button" class="btn btn-primary" onclick="askQuestion();">Perguntar!</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
