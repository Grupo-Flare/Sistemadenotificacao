import { initializeApp } from 'https://www.gstatic.com/firebasejs/9.2.0/firebase-app.js';
import { getMessaging, onMessage, getToken } from 'https://www.gstatic.com/firebasejs/9.2.0/firebase-messaging.js';

const confirmar = document.getElementById("confirmar");

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


// Ações do botão confirmar
confirmar.onclick = function () {

    var push = document.getElementById("radio-push")
    var teams = document.getElementById("radio-teams")
    var email = document.getElementById("radio-email");

    if (push.checked) {
        geraSubscription()
    }

    if (teams.checked) {
        alert("teams")
    }

    if (email.checked) {
        alert("email")
    }


}

// Ao clicar no botão de inscrever-se, cadastra o usuario
function geraSubscription() {

    getToken(messaging, { vapidKey: publicKey })
        .then((currentToken) => {
            if (currentToken) {
                console.log(JSON.stringify(currentToken));

                sendSubscriptionToServer(currentToken);

            } else {
                console.log('No registration token available. Request permission to generate one.');
            }
        }).catch((err) => {
            if (Notification.permission === 'denied') {
                console.warn('Permissão de notificações negada');
                console.log(err);
            } else {
                console.error('Falha ao inscrever: ', err);
                console.log(err);
            }
        });
}

//Envia o token para o servidor
function sendSubscriptionToServer(token) {

  // Headers
  var myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");

  //Corpo da requisição
  var body = JSON.stringify({
    "token": token,
    "user": "user" 
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