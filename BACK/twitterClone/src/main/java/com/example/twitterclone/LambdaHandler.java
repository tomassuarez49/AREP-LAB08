package com.example.twitterclone;

import java.io.*;
import java.util.Map;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.example.twitterclone.Post.MicroPostService;
import com.example.twitterclone.Post.ModelPost;
import com.example.twitterclone.Stream.MicroStreamService;
import com.example.twitterclone.User.MicroUserService;
import com.example.twitterclone.User.ModelUser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LambdaHandler implements RequestStreamHandler {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final MicroUserService userService = new MicroUserService();
    private final MicroPostService postService = new MicroPostService(userService);
    private final MicroStreamService streamService = new MicroStreamService();

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        PrintWriter writer = new PrintWriter(outputStream);

        JsonNode event = objectMapper.readTree(reader);
        String path = event.has("path") ? event.get("path").asText() : "";
        String httpMethod = event.has("httpMethod") ? event.get("httpMethod").asText() : "";
        JsonNode bodyNode = event.has("body") ? event.get("body") : null;

        if (bodyNode != null && bodyNode.isTextual()) {
            bodyNode = objectMapper.readTree(bodyNode.asText());
        }

        String responseBody = "";
        int statusCode = 200;

        try {
            if ("/users".equals(path) && "POST".equals(httpMethod)) {
                if (bodyNode == null || !bodyNode.has("username")) {
                    throw new IllegalArgumentException("Falta el campo 'username' en el body");
                }
                String username = bodyNode.get("username").asText();
                ModelUser newUser = userService.createUser(username);
                responseBody = objectMapper.writeValueAsString(newUser);
            } 
            else if ("/posts".equals(path) && "POST".equals(httpMethod)) {
                if (bodyNode == null || !bodyNode.has("userId") || !bodyNode.has("content")) {
                    throw new IllegalArgumentException("Faltan los campos 'userId' o 'content' en el body");
                }
                Long userId = bodyNode.get("userId").asLong();
                String content = bodyNode.get("content").asText();
                ModelPost newPost = postService.createPost(userId, content);
                responseBody = objectMapper.writeValueAsString(newPost);
            } 
            else if ("/users".equals(path) && "GET".equals(httpMethod)) {
                responseBody = objectMapper.writeValueAsString(userService.getUsers());
            } 
            else {
                statusCode = 400;
                responseBody = "Ruta o método no soportado";
            }
        } catch (Exception e) {
            statusCode = 500;
            responseBody = "Error interno: " + e.getMessage();
        }

        writer.write(objectMapper.writeValueAsString(Map.of("statusCode", statusCode, "body", responseBody)));
        writer.flush();
    }
}
