<!-- Modal para seleção de categoria -->

<div class="modal fade" tabindex="-1" role="dialog" id="categoriesModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Categorias</h4>
			</div>

			<div class="alert alert-danger hidden modal-message" role="alert" id="modalErrorMessages"></div>

			<div class="modal-body">
				<p><b>Sou um expert em:</b></p>
				<div class=row>
					<div class="col-sm-4">
						<div class="checkbox">
							<label>
								<input type="checkbox" id="fishingCb" value="1">
								Pesca
							</label>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="checkbox">
							<label>
								<input type="checkbox" id="javaCb" value="2">
								Java
							</label>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="checkbox">
							<label>
								<input type="checkbox" id="remodelCb" value="3">
								Reforma de Casa
							</label>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="checkbox">
							<label>
								<input type="checkbox" id="sertanejaCb" value="4">
								Música Sertaneja
							</label>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="checkbox">
							<label>
								<input type="checkbox" id="soccerCb" value="5">
								Futebol
							</label>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="checkbox">
							<label>
								<input type="checkbox" id="japaneaseFoodCb" value="6">
								Comida Japonesa
							</label>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
				<button type="button" class="btn btn-primary" onclick="registerUser();">Finalizar Cadastro</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
