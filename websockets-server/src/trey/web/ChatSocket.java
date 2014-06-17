package trey.web;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chatsocket")
public class ChatSocket {

	private String user = "guest";

	private void log(String str) {
		System.out.println(String.format("[%s]\t%s", user, str));
	}

	@OnOpen
	public void handleOpen() {
		log("Opening connection...");
	}

	@OnClose
	public void handleClose() {
		log("Closing connection...");
	}

	@OnMessage
	public void handleMessage(String message, Session session) {
		log(String.format("Received message from %s: %s", user, message));
		if (message.startsWith("login@")) {
			// each client stores its username in this.user (stateful socket!)
			this.user = message.substring("login@".length());
		} else {
			for (Session s : session.getOpenSessions()) {
				if (s.isOpen()) {
					try {
						s.getBasicRemote().sendObject(user + '@' + message);
					} catch (Exception e) {
						log("Error sending message to session: " + e.getMessage());
						e.printStackTrace();
					}
				}
			}
		}
	}

	@OnError
	public void handleError(Throwable t) {
		log("Processing error: " + t.getMessage());
	}

}
