package lk.uom.minigame.Utility;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionHandler {
    public static String getSessionID(HttpServletRequest request){
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        return sessionId;
    }
}
