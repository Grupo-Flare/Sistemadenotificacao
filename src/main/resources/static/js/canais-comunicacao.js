function inscrever(channel, isRegistred) {

  var myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");

  //Corpo da requisição
  var body = JSON.stringify({
    "name": channel,
  });

  var method;
  var text;
  var className;
  if (isRegistred) {
    method = "DELETE";
    text = "Inscrever-se";
    className = "inscrever";
  } else {
    method = "POST";
    text = "Inscrito";
    className = "desinscrever";
  }

  var requestOptions = {
    method: method,
    headers: myHeaders,
    body: body,
    redirect: 'follow'
  };


  fetch("/canais", requestOptions)
    .then(response => response.text())
    .then(result => {
      console.log(result);
      document.getElementById(channel).className = className;
      document.getElementById(channel).firstChild.data = text;
      window.location.href = "/canais";
    })
    .catch(error => console.log('error', error));
}

function desinscrever(channel) {

  var myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");

  //Corpo da requisição
  var body = JSON.stringify({
    "name": channel,
  });

  var requestOptions = {
    method: 'DELETE',
    headers: myHeaders,
    body: body,
    redirect: 'follow'
  };

  fetch("/canais", requestOptions)
    .then(response => response.text())
    .then(result => {
      console.log(result)
      document.getElementById(channel).className = className;
      document.getElementById(channel).firstChild.data = "Inscrever-se";
      document.getElementById(channel).attribute("onclick", "inscrever('channel')");
    })
    .catch(error => console.log('error', error));
}

function changeEvent(id) {

}