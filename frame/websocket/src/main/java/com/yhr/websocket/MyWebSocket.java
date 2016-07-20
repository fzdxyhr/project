package com.yhr.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author yhr
 * @version latest
 * @date 2016/7/19
 * @description
 */

@Component
@ServerEndpoint("/websocket")
public class MyWebSocket {
    private static int onlineCount = 0;

    private static CopyOnWriteArraySet<WebSocketSession> webSocketSet = new CopyOnWriteArraySet<WebSocketSession>();

    private WebSocketSession session;

    @OnOpen
    public void onOpen (WebSocketSession session){
        this.session = session;
        webSocketSet.add(session);
        addOnlineCount();
        System.out.println("有新链接加入!当前在线人数为" + getOnlineCount()+"sessionId:");
    }

    @OnClose
    public void onClose (WebSocketSession session){
        webSocketSet.remove(session);
        subOnlineCount();
        System.out.println("有一链接关闭!当前在线人数为" + getOnlineCount());
    }

    @OnMessage
    public void onMessage (String message, Session session) throws IOException {
        System.out.println("来自客户端的消息:" + message);
        // 群发消息
        for (WebSocketSession item : webSocketSet ){
            TextMessage textMessage = new TextMessage(message);
            item.sendMessage(textMessage);
        }
    }

    public void sendMessage (String message) throws IOException {

    }


    public static synchronized  int getOnlineCount (){
        return MyWebSocket.onlineCount;
    }

    public static synchronized void addOnlineCount (){
        MyWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount (){
        MyWebSocket.onlineCount--;
    }


}
