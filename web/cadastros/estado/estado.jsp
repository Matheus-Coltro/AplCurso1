<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>
<div class="container-fluid">

    <h1 class="h3 mb-2 text-gray-800">Estados</h1>
    <p class="mb-4">Listagem de Estados Cadastrados</p>

    <a class="btn btn-primary mb-4" href="${pageContext.request.contextPath}/EstadoNovo">
        <i class="fas fa-plus"></i>
        <strong>Novo Estado</strong>
    </a>

    <div class="card shadow mb-4">
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered table-striped" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nome do Estado</th>
                            <th>Sigla</th>
                            <th>Alterar</th>
                            <th>Excluir</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${listaEstados}">
                            <tr>
                                <td>${item.id}</td>
                                <td>${item.nomeEstado}</td>
                                <td>${item.siglaEstado}</td>
                                <td>
                                    <a class="btn btn-warning btn-sm"
                                       href="${pageContext.request.contextPath}/EstadoCarregar?id=${item.id}">
                                        <i class="fas fa-edit"></i> Alterar
                                    </a>
                                </td>
                                <td>
                                    <button class="btn btn-danger btn-sm"
                                            onclick="excluir(${item.id}, '${item.nomeEstado}')">
                                        <i class="fas fa-trash"></i> Excluir
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script>
    function excluir(id, nome) {
        Swal.fire({
            title: 'Confirma a exclusão?',
            text: 'Estado: ' + nome,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            cancelButtonColor: '#6c757d',
            confirmButtonText: 'Sim, excluir!',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    type: 'get',
                    url: 'EstadoExcluir',
                    data: { id: id },
                    success: function (response) {
                        if (response == '1') {
                            Swal.fire({
                                icon: 'success',
                                title: 'Excluído com sucesso!',
                                showConfirmButton: false,
                                timer: 1500
                            }).then(function () {
                                window.location.href = 'EstadoListar';
                            });
                        } else {
                            Swal.fire({
                                icon: 'error',
                                title: 'Erro ao excluir!',
                                text: 'Não foi possível excluir o estado.'
                            });
                        }
                    },
                    error: function () {
                        Swal.fire({
                            icon: 'error',
                            title: 'Erro de comunicação com o servidor.'
                        });
                    }
                });
            }
        });
    }
</script>

<jsp:include page="/footer.jsp"/>