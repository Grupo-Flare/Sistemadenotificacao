function inscreve(channel) {

    var myHeaders = new Headers();
      myHeaders.append("Content-Type", "application/json");

     console.log(channel);

      //Corpo da requisição
      var body = JSON.stringify({
        "channel": channel,
      });

      var requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: body,
        redirect: 'follow'
      };

      fetch("/canais", requestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));

}