$(document).ready(function () {
    $('#revele').hide();
    // aqui um seletor por name, para pegar todos os radio buttons "devweb"
    $('input:radio[name="novanotificacao"]').change(function () {
      // aqui, this é o radio quem foi clicado, então basta comparar o valor com val()
      if ($(this).val() == "especifica1") {
        $('#revele').show();
      } else {
        $('#revele').hide();
      }
    });
  });

  $(document).ready(function () {
    $('#revele1').hide();
    // aqui um seletor por name, para pegar todos os radio buttons "devweb"
    $('input:radio[name="novanotificacao"]').change(function () {
      // aqui, this é o radio quem foi clicado, então basta comparar o valor com val()
      if ($(this).val() == "especifica1") {
        $('#revele1').show();
      } else {
        $('#revele1').hide();
      }
    });
  });

  $(document).ready(function () {
    $('#mostrar').hide();
    // aqui um seletor por name, para pegar todos os radio buttons "devweb"
    $('input:radio[name="Preferencia"]').change(function () {
      // aqui, this é o radio quem foi clicado, então basta comparar o valor com val()
      if ($(this).val() == "categoria") {
        $('#mostrar').show();
      } else {
        $('#mostrar').hide();
      }
    });
  }); 