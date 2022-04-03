// Importando scripts do firebase
importScripts('https://www.gstatic.com/firebasejs/9.2.0/firebase-app-compat.js');
importScripts('https://www.gstatic.com/firebasejs/9.2.0/firebase-messaging-compat.js');

// Configuração do firebase
firebase.initializeApp({
  apiKey: "",
  authDomain: "",
  projectId: "",
  storageBucket: "",
  messagingSenderId: "",
  appId: "",
  measurementId: ""
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

