/**
 * 
 */


function confirmar(idprod) {
    var resposta = confirm("Você tem certeza que deseja excluir este produto?");
    if (resposta) {
        var url = "delete?idprod=" + idprod;
        window.location.href = url;
    }
}