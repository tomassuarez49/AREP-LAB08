package com.example.twitterclone;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.util.Map;

public class LambdaHandler implements RequestHandler<Map<String, Object>, Map<String, Object>> {
    @Override
    public Map<String, Object> handleRequest(Map<String, Object> event, Context context) {
        //  Verificar estructura del evento
        context.getLogger().log("Evento recibido: " + event);

        //  Extraer datos del body
        Map<String, String> body = parseBody(event.get("body"));

        String username = body.get("username");

        if (username == null || username.isEmpty()) {
            return createResponse(400, "Falta el campo 'username'");
        }

        //  Simulando creación de usuario
        String message = "Usuario '" + username + "' creado exitosamente";

        return createResponse(200, message);
    }

    private Map<String, String> parseBody(Object bodyObj) {
        if (bodyObj instanceof String) {
            String body = (String) bodyObj;
            return Map.of("username", body.replaceAll("\\{\"username\":\"(.*?)\"\\}", "$1"));
        }
        return Map.of();
    }

    private Map<String, Object> createResponse(int statusCode, String message) {
        return Map.of(
            "statusCode", statusCode,
            "headers", Map.of("Content-Type", "application/json"),
            "body", "{ \"message\": \"" + message + "\" }"
        );
    }
}

