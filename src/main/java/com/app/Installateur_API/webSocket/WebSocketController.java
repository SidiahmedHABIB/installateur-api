package com.app.Installateur_API.webSocket;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


@Controller
public class WebSocketController {

    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    @MessageMapping("/random")
    @SendTo("/topic/random")
    public int generateRandomNumber() throws Exception {
        int randomNum = ThreadLocalRandom.current().nextInt(1, 1000);
        return randomNum;
    }

    @MessageExceptionHandler
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }

    @EventListener
    public void handleSessionConnected(SessionConnectedEvent event) {
        executorService.scheduleAtFixedRate(() -> {
            try {
                generateRandomNumber();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 2, TimeUnit.SECONDS);
    }

}
