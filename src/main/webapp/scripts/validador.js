/**
 * 
 */

function validar(){
	let nome = frmProduto.nome.value;
    let quant = frmProduto.quantidade.value;
    let valor = frmProduto.valor.value;

    if (nome === ""){
        alert('Preencha o campo Nome')
        frmProduto.nome.focus();
        return false
    } else if (quant === ""){
        alert('Informe a quantidade')
        frmProduto.quantidade.focus();
        return false
    } else if (valor === ""){
        alert('Informe o valor')
        frmProduto.valor.focus();
        return false
    } else{
        document.forms["frmProduto"].submit();
    }
}

