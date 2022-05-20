function validaCadastro() {

    var formulario = document.getElementById("cadastro")
    var nome = document.getElementById("nome-register")
    var email = document.getElementById("email-register");
    // var permissao = document.getElementById("checkboxes");

    if (nome.value == "") {
        nome.focus();
        nome.placeholder = "Digite um nome antes de prosseguir!";
        return;
    }

    if (email.value == "") {
        email.focus();
        email.placeholder = "Digite um email antes de prosseguir!";
        return;
    }

    // if (permissao.options[permissao.selectedIndex].value == "") {
    //     permissao.focus();
    //     alert("Selecione em quais categorias o usuarios poder enviar notificações.")
    //     return;
    // }

    formulario.submit();
}

function validaDeleta() {

    var formulario = document.getElementById("deleta")
    var nome = document.getElementById("nome-delete")

    if (nome.value == "") {
        nome.focus();
        nome.placeholder = "Digite um nome antes de prosseguir!";
        return;
    }

    formulario.submit();
}

function validaAtualiza() {

    var formulario = document.getElementById("atualiza")
    var nome = document.getElementById("nome-update")
    var permissao = document.getElementById("permissao-update");

    if (nome.value == "") {
        nome.focus();
        nome.placeholder = "Digite um nome antes de prosseguir!";
        return;
    }

    if (permissao.options[permissao.selectedIndex].value == "") {
        permissao.focus();
        alert("Selecione em quais categorias o usuarios poder enviar notificações.")
        return;
    }

    formulario.submit();
}


var expanded = false;
function showCheckboxes() {

    let checkboxes = document.getElementById("checkboxes");
    if (!expanded) {
        checkboxes.style.display = "block";
        expanded = true;
    } else {
        checkboxes.style.display = "none";
        expanded = false;
    }
}

var expanded2 = false;
function showCheckboxes2() {

    let checkboxes2 = document.getElementById("checkboxes2");
    if (!expanded2) {
        checkboxes2.style.display = "block";
        expanded2 = true;
    } else {
        checkboxes2.style.display = "none";
        expanded2 = false;
    }
}