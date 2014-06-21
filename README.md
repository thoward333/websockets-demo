websockets-demo
===============
websockets-server and websockets-client are separate eclipse projects.

---------------
websockets-server must be run on a JEE 7 webapp container, such as Glassfish 4.0

index.html demonstrate a 1-to-1 client/server relationship. *Requires modern web browser (IE10+).*

chat.html demonstrates a 1-to-many client/server relationship where each message is broadcast to other clients. Open chat.html in multiple tabs and see that messages are visible in all tabs. *Requires modern web browser (IE10+).*

---------------
websockets-client demonstrates a WebSockets client in J2SE by adding 2 libraries:
1. javax.websocket-api-1.0.jar (http://mvnrepository.com/artifact/javax.websocket/javax.websocket-api)
1. tyrus-standalone-client-1.7.jar (https://tyrus.java.net/dependencies.html)

ChatClient.java uses a command line interface. It takes a single argument, the username of the instance. Fully compatible with chat.html (above). Open 1+ chat.html while ChatClient is running and see that messages are visible in both places.
