import { initializeApp } from 'https://www.gstatic.com/firebasejs/9.2.0/firebase-app.js';
import { getMessaging, onMessage, getToken } from 'https://www.gstatic.com/firebasejs/9.2.0/firebase-messaging.js';

// Elementos do HTML
const tokenDiv = document.getElementById("token");
const botao = document.getElementById("inscrever");
const user = document.getElementById("usuario");

//Configurações do Firebase
const firebaseConfig = {
  apiKey: "AIzaSyDBXiG5NM8pbBZHHcXV5Zb63qFTJWjAYe8",
  authDomain: "flare-d3e52.firebaseapp.com",
  projectId: "flare-d3e52",
  storageBucket: "flare-d3e52.appspot.com",
  messagingSenderId: "341132455706",
  appId: "1:341132455706:web:fcc242cf12255e5ed646a3",
  measurementId: "G-31W3RHLBSY"
};

// Inicialização do Firebase
const app = initializeApp(firebaseConfig);
const messaging = getMessaging(app);

// Chave do firebase
const publicKey = 'BAEsApKlUTrFdJCRz6eXRogneeObnXnhJTbQW5BFZi_V5z9-_3iYXfEs4UmL1CtccivhGezXnGhcMnC2as2kO54';

// Ao clicar no botão de inscrever-se, cadastra o usuario
botao.onclick = function() {

  getToken(messaging, { vapidKey: publicKey })
    .then((currentToken) => {
      if (currentToken) {
        tokenDiv.innerHTML = 'Token: <br>' + JSON.stringify(currentToken); // Mostra o token na tela
        botao.innerHTML = 'Inscrito';
        botao.style.backgroundColor = 'blue';

        sendSubscriptionToServer(currentToken);

      } else {
        console.log('No registration token available. Request permission to generate one.');
      }
    }).catch((err) => {
      if (Notification.permission === 'denied') {
        console.warn('Permissão de notificações negada');
        tokenDiv.innerHTML = err;
      } else {
        console.error('Falha ao inscrever: ', err);
        tokenDiv.innerHTML = err;
      }
    });
}

//Envia o token para o servidor
function sendSubscriptionToServer(token) {

  // Headers
  var myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");

   // TODO: Pegar automaticamente o usuario que está logado
  //Corpo da requisição
  var body = JSON.stringify({
    "token": token,
    "user": user.value
  });

  var requestOptions = {
    method: 'POST',
    headers: myHeaders,
    body: body,
    redirect: 'follow'
  };

  fetch("/subscription", requestOptions)
    .then(response => response.text())
    .then(result => console.log(result))
    .catch(error => console.log('error', error));
}

// Evento de mensagem, assim que receber a mensagem mostra pro usuario
onMessage(messaging, (payload) => {
  console.log('Message received. ', payload);
  // Customizar a notificação
  const options = {
    body: payload.notification.body,
    // Se tiver uma imagem, no corpo da notificação mostra ela, se não mostra a logo do app
    icon: payload.notification.image || './images/flare_logo.png',
    vibrate: [100, 50, 100],
  }
  new Notification(payload.notification.title, options);
});

