import { initializeApp } from 'https://www.gstatic.com/firebasejs/9.2.0/firebase-app.js';
import { getMessaging, onMessage, getToken } from 'https://www.gstatic.com/firebasejs/9.2.0/firebase-messaging.js';

// Elementos do HTML
const tokenDiv = document.getElementById("token");
const botao = document.getElementById("inscrever");

//Configurações do Firebase
const firebaseConfig = {
  apiKey: "",
  authDomain: "",
  projectId: "",
  storageBucket: "",
  messagingSenderId: "",
  appId: "",
  measurementId: ""
};

// Inicialização do Firebase
const app = initializeApp(firebaseConfig);
const messaging = getMessaging(app);

// Chave do firebase
const fKey = '';

// Ao clicar no botão de inscrever-se, cadastra o usuario
botao.onclick = function() {

  getToken(messaging, { vapidKey: '' })
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

