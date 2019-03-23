package poly.app.core.socket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

//@ServerEndpoint(value = "/huhu")
public class TicketBookingServerEndpoint {

    static Map<String, Set<Session>> realtime = Collections.synchronizedMap(new HashMap<String, Set<Session>>());

    @OnOpen
    public void handleOpen(Integer suatChieuId, Session session) {
        session.getUserProperties().put("suatChieuId", suatChieuId);
        realtime.get(suatChieuId).add(session);
    }

    @OnMessage
    public void handleMessage(String message, Session userSession) throws IOException {
        String suatChieuId;
        String username = (String) userSession.getUserProperties().get("username");
        if (username == null) {
            userSession.getUserProperties().put("username", message);
            userSession.getBasicRemote().sendText("System: you are connected as " + message);
        } else {
//            for (Session session : clients) {
//                session.getBasicRemote().sendText(username + ": " + message);
//            }
        }
    }

    @OnClose
    public void handleClose(Session session) {
//        clients.remove(session);
    }

    @OnError
    public void handleError(Throwable t) {
        t.printStackTrace();
    }
}
