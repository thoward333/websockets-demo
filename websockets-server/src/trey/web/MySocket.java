package trey.web;

import java.util.Date;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/mysocket")
public class MySocket {
	
	private long created = new Date().getTime();

	private void log(String str) {
		System.out.println(String.format("[%s]\t%s", created, str));
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
	public String handleMessage(String message) {
		log("Received message: " + message);
		if (message.equals("error")) {
			throw new RuntimeException("Testing WebSocket exception handling");
		} else {
			return "Echo: " + message;
		}
	}

	@OnError
	public void handleError(Throwable t) {
		log("Processing error: " + t.getMessage());
		t.printStackTrace();
	}
	
}
