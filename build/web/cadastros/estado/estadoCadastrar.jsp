<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>
<div class="container-fluid">

    <h1 class="h3 mb-2 text-gray-800">Estados</h1>
    <p class="mb-4">Formulário de Cadastro</p>

    <a class="btn btn-secondary mb-4" href="${pageContext.request.contextPath}/EstadoListar">
        <i class="fas fa-undo-alt"></i>
        <strong>Voltar</strong>
    </a>

    <div class="row">
        <div class="col-lg-6">
            <div class="card shadow mb-4">
                <div class="card-body">

                    <div class="form-group">
                        <label>Id</label>
                        <input class="form-control" type="text" name="id" id="id"
                               value="${estado.id}" readonly="readonly"/>
                    </div>

                    <div class="form-group">
                        <label>Nome do Estado</label>
                        <input class="form-control" type="text" name="nomeestado" id="nomeestado"
                               value="${estado.nomeEstado}" maxlength="100"/>
                    </div>

                    <div class="form-group">
                        <label>Sigla do Estado</label>
                        <input class="form-control" type="text" name="siglaestado" id="siglaestado"
                               value="${estado.siglaEstado}" maxlength="2" style="width:80px;"/>
                    </div>

                    <div class="form-group">
                        <button class="btn btn-success" type="button" onclick="validarCampos()">
                            Salvar
                        </button>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {

        // Ao sair do campo sigla: converte pra maiúsculo, valida tamanho e verifica duplicidade
        $('#siglaestado').blur(function () {
            var sigla = $('#siglaestado').val().toUpperCase().trim();
            $('#siglaestado').val(sigla);

            if (sigla === '') return;

            if (sigla.length !== 2) {
                Swal.fire({
                    icon: 'error',
                    title: 'Sigla inválida!',
                    text: 'A sigla deve ter exatamente 2 letras.',
                    showConfirmButton: true
                });
                $('#siglaestado').focus();
                return;
            }

            // Só verifica duplicidade na inclusão (id == 0)
            if ($('#id').val() == 0) {
                $.ajax({
                    type: 'get',
                    url: 'EstadoVerificarSigla',
                    data: { sigla: sigla },
                    success: function (response) {
                        if (response == '1') {
                            Swal.fire({
                                icon: 'warning',
                                title: 'Sigla já cadastrada!',
                                text: 'Por favor, informe outra sigla.',
                                showConfirmButton: true,
                                timer: 4000
                            }).then(function () {
                                $('#siglaestado').val('').focus();
                            });
                        }
                    },
                    error: function () {
                        console.log('Erro ao verificar sigla no servidor.');
                    }
                });
            }
        });

        $('#nomeestado').focus();
    });

    function validarCampos() {

        if ($('#nomeestado').val().trim() === '') {
            Swal.fire({
                icon: 'error',
                title: 'Informe o nome do estado!',
                showConfirmButton: false,
                timer: 1500
            });
            $('#nomeestado').focus();
            return;
        }

        var sigla = $('#siglaestado').val().trim();
        if (sigla === '') {
            Swal.fire({
                icon: 'error',
                title: 'Informe a sigla do estado!',
                showConfirmButton: false,
                timer: 1500
            });
            $('#siglaestado').focus();
            return;
        }

        if (sigla.length !== 2) {
            Swal.fire({
                icon: 'error',
                title: 'Sigla inválida!',
                text: 'A sigla deve ter exatamente 2 letras.',
                showConfirmButton: false,
                timer: 1500
            });
            $('#siglaestado').focus();
            return;
        }

        // Passou nas validações do frontend — envia pro servlet
        $.ajax({
            type: 'post',
            url: 'EstadoCadastrar',
            data: {
                id:           $('#id').val(),
                nomeestado:   $('#nomeestado').val().trim(),
                siglaestado:  $('#siglaestado').val().toUpperCase().trim()
            },
            success: function (response) {
                if (response == '1') {
                    Swal.fire({
                        icon: 'success',
                        title: 'Estado salvo com sucesso!',
                        showConfirmButton: false,
                        timer: 1500
                    }).then(function () {
                        window.location.href = 'EstadoListar';
                    });
                } else if (response == '3') {
                    Swal.fire({ icon: 'error', title: 'Sigla inválida! Use exatamente 2 letras.' });
                } else if (response == '4') {
                    Swal.fire({ icon: 'warning', title: 'Sigla já cadastrada!', text: 'Informe outra sigla.' });
                } else if (response == '5') {
                    Swal.fire({ icon: 'error', title: 'Nome do estado não pode ser vazio!' });
                } else {
                    Swal.fire({ icon: 'error', title: 'Erro ao salvar! Tente novamente.' });
                }
            },
            error: function () {
                Swal.fire({ icon: 'error', title: 'Erro de comunicação com o servidor.' });
            }
        });
    }
</script>

<jsp:include page="/footer.jsp"/>