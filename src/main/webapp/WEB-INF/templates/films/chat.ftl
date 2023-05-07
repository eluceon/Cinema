<!DOCTYPE html>
<html>
  <head>
      <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
      <title>Movie Chat Room ${movie.title}</title>
      <script src="/resources/static/js/sockjs.min.js"></script>
      <script src="/resources/static/js/stomp.min.js"></script>
      <link rel="stylesheet" href="/resources/static/css/chat.css" />
  </head>
  <body>
    <noscript>
      <h2>Sorry! Your browser doesn't support Javascript</h2>
    </noscript>

    <div id="username-page">
        <div class="username-page-container">
            <h1 class="title">Type your username</h1>
            <form id="usernameForm" name="usernameForm">
                <div class="form-group">
                    <input type="text" id="name" placeholder="Username" autocomplete="off" class="form-control" />
                </div>
                <div class="form-group">
                    <button type="submit" class="accent username-submit">Start Chatting</button>
                </div>
            </form>
        </div>
    </div>

    <div id="chat-page" class="hidden">
        <div class="chat-container">
            <div class="chat-header">
                <h2>Movie Chat - ${movie.title}</h2>
            </div>
            <div class="connecting">
                Connecting...
            </div>
            <ul id="messageArea">
                <#list chatHistory as message>
                    <li class="chat-message">
                        <i style="background-color: #86869AFF;">
                            ${message.sender[0]}
                        </i>
                        <span>
                            ${message.sender}
                        </span>
                        <p>${message.content}</p>
                    </li>
                </#list>
            </ul>
            <form id="messageForm" name="messageForm" nameForm="messageForm">
                <div class="form-group">
                    <div class="input-group clearfix">
                        <input type="text" id="message" placeholder="Type a message..." autocomplete="off" class="form-control"/>
                        <button type="submit" class="primary">Send</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

  <script>
      'use strict';

      const usernamePage = document.querySelector('#username-page');
      const chatPage = document.querySelector('#chat-page');
      const usernameForm = document.querySelector('#usernameForm');
      const messageForm = document.querySelector('#messageForm');
      const messageInput = document.querySelector('#message');
      const messageArea = document.querySelector('#messageArea');
      const connectingElement = document.querySelector('.connecting');

      let stompClient = null;
      let username = null;

      const colors = [
          '#2196F3', '#32c787', '#00BCD4', '#ff5652',
          '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
      ];

      function connect(event) {
          username = document.querySelector('#name').value.trim();

          if(username) {
              setCookie('name', username, 1);

              usernamePage.classList.add('hidden');
              chatPage.classList.remove('hidden');

              const socket = new SockJS('/ws');
              stompClient = Stomp.over(socket);

              stompClient.connect({}, onConnected, onError);
          }
          event.preventDefault();
      }


      function onConnected() {
          // Subscribe to the Public Topic
          stompClient.subscribe('/topic/films/${movie.id}', onMessageReceived);

          // Tell your username to the server
          stompClient.send("/app/films/${movie.id}/chat/addUser",
              {},
              JSON.stringify({sender: username, type: 'JOIN'})
          )

          connectingElement.classList.add('hidden');
      }


      function onError(error) {
          connectingElement.style.color = 'red';
          connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
      }


      function sendMessage(event) {
          const messageContent = messageInput.value.trim();

          if(messageContent && stompClient) {
              const message = {
                  sender: username,
                  content: messageInput.value,
                  type: 'CHAT',
                  movieId: ${movie.id},
              };

              stompClient.send('/app/films/${movie.id}/chat/sendMessage', {}, JSON.stringify(message));
              messageInput.value = '';
          }
          event.preventDefault();
      }

      function onMessageReceived(payload) {
          const message = JSON.parse(payload.body);

          const messageElement = document.createElement('li');

          if(message.type === 'JOIN') {
              messageElement.classList.add('event-message');
              message.content = message.sender + ' joined!';
          } else if (message.type === 'LEAVE') {
              messageElement.classList.add('event-message');
              message.content = message.sender + ' left!';
          } else {
              messageElement.classList.add('chat-message');

              const avatarElement = document.createElement('i');
              const avatarText = document.createTextNode(message.sender[0]);
              avatarElement.appendChild(avatarText);
              avatarElement.style['background-color'] = getAvatarColor(message.sender);

              messageElement.appendChild(avatarElement);

              const usernameElement = document.createElement('span');
              const usernameText = document.createTextNode(message.sender);
              usernameElement.appendChild(usernameText);
              messageElement.appendChild(usernameElement);
          }

          const textElement = document.createElement('p');
          const messageText = document.createTextNode(message.content);
          textElement.appendChild(messageText);

          messageElement.appendChild(textElement);

          messageArea.appendChild(messageElement);
          messageArea.scrollTop = messageArea.scrollHeight;
      }

      function getAvatarColor(messageSender) {
          let hash = 0;
          for (let i = 0; i < messageSender.length; i++) {
              hash = 31 * hash + messageSender.charCodeAt(i);
          }

          const index = Math.abs(hash % colors.length);
          return colors[index];
      }

      function setCookie(name, value, days) {
          let expires = "";
          if (days) {
              const date = new Date();
              date.setTime(date.getTime() + (days*24*60*60*1000));
              expires = "; expires=" + date.toUTCString();
          }
          document.cookie = name + "=" + (value || "")  + expires + "; path=/";
      }

      function getCookie(name) {
          const nameEQ = name + "=";
          const ca = document.cookie.split(';');
          for(let i=0; i < ca.length; i++) {
              let c = ca[i];
              while (c.charAt(0)==' ') c = c.substring(1,c.length);
              if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
          }
          return null;
      }

      window.onload = function() {
          username = getCookie('name');
          if (username){
              usernamePage.classList.add('hidden');
              chatPage.classList.remove('hidden');

              const socket = new SockJS('/ws');
              stompClient = Stomp.over(socket);

              stompClient.connect({}, onConnected, onError);
          }
      };

      usernameForm.addEventListener('submit', connect, true)
      messageForm.addEventListener('submit', sendMessage, true)
  </script>
  </body>
</html>