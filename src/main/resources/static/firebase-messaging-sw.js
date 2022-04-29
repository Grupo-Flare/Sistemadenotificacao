// Importando scripts do firebase
importScripts('https://www.gstatic.com/firebasejs/9.2.0/firebase-app-compat.js');
importScripts('https://www.gstatic.com/firebasejs/9.2.0/firebase-messaging-compat.js');

// Configuração do firebase
firebase.initializeApp({
  apiKey: "AIzaSyDBXiG5NM8pbBZHHcXV5Zb63qFTJWjAYe8",
    authDomain: "flare-d3e52.firebaseapp.com",
    projectId: "flare-d3e52",
    storageBucket: "flare-d3e52.appspot.com",
    messagingSenderId: "341132455706",
    appId: "1:341132455706:web:fcc242cf12255e5ed646a3",
    measurementId: "G-31W3RHLBSY"
});

const messaging = firebase.messaging();

// Configurações da Notification Push
// Evento de mensagem, assim que receber a mensagem mostra pro usuario
messaging.onBackgroundMessage(function(payload) {
  console.log('[firebase-messaging-sw.js] Received background message ', payload);
  // Customizar a notificação
  const notificationTitle = payload.notification.title;
  const notificationOptions = {
    body: payload.notification.body,
    icon: payload.notification.image || './images/flare_logo.png',
    vibrate: [100, 50, 100],
  };

  self.registration.showNotification(notificationTitle,
    notificationOptions);
});

