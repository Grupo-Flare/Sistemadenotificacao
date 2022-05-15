$(document).ready(function () {
    $('.revele').hide();
    // aqui um seletor por name, para pegar todos os radio buttons "devweb"
    $('input:radio[name="novanotificacao"]').change(function () {
        // aqui, this é o radio quem foi clicado, então basta comparar o valor com val()
        if ($(this).val() == "especifica1") {
            $('.revele').show();
        } else {
            $('.revele').hide();
        }
    });
});


$(document).ready(function () {
    $('#mostrar').hide();
    // aqui um seletor por name, para pegar todos os radio buttons "devweb"
    $('input:radio[name="category"]').change(function () {
        // aqui, this é o radio quem foi clicado, então basta comparar o valor com val()
        if ($(this).val() == "categoria") {
            $('#mostrar').show();
        } else {
            $('#mostrar').hide();
        }
    });
});


function validaFormulario() {

    var formulario = document.getElementById("formNotificacao")
    var titulo = document.getElementById("titulo");
    var mensagem = document.getElementById("msg");
    var categorias = document.getElementById("mostrar");
    var selecionado = document.getElementById("categoria");
    var radioAllUser = document.getElementById("radio-allUser");
    var enviarAgora = document.getElementById("dataTempo");
    var agendamento = document.getElementById("agenda");
    var data = document.getElementById("date");
    var time = document.getElementById("time");

    if (!radioAllUser.checked && (!selecionado.checked || categorias.options[categorias.selectedIndex].value == "")) {
        radioAllUser.focus();
        categorias.focus();
        alert("Selecione a audiência antes de prosseguir!");
        return;
    }

    if (titulo.value == "") {
        titulo.focus();
        titulo.placeholder = "Digite um titulo antes de prosseguir!";
        return;
    }

    if (mensagem.value == "") {
        mensagem.focus();
        mensagem.placeholder = "Digite uma mensagem antes de prosseguir!";
        return;
    }

    if (!enviarAgora.checked && (!agendamento.checked || (data.value == '' || time.value == ''))) {
        enviarAgora.focus();
        alert("Defina o horario de entrega antes de prosseguir!");
        return;
    } else {
        var todayDate = moment();
        var futureDate = moment(data.value, 'YYYY-MM-DD');
        if (todayDate.diff(futureDate) <= 0) {
            alert("A data tem que ser no futuro");
            return;
        }

    }

    formulario.submit();

}