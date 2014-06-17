package trey.web;

import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class ChatClient {

	@OnMessage
	public void onMessage(String message) {
		System.out.println("Received message > " + message);
	}

	public static void main(String[] args) {
		String username = args[0];

		WebSocketContainer container = null;
		Session session = null;
		try {
			// Tyrus is plugged via ServiceLoader API: https://tyrus.java.net/dependencies.html
			container = ContainerProvider.getWebSocketContainer();

			// websocket lib for J2SE: http://mvnrepository.com/artifact/javax.websocket/javax.websocket-api
			session = container.connectToServer(ChatClient.class, URI.create("ws://localhost:8080/websockets-demo/chatsocket"));

			// login
			session.getBasicRemote().sendText(String.format("login@%s", username));
			session.getBasicRemote().sendText(String.format("hello, my name is %s. i will send 10 messages before disconnecting", username));
			

			// send some messages
			for (int i = 1; i <= 10; i++) {
				session.getBasicRemote().sendText(String.format("Ping %s", i));
				Thread.sleep(1000);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
