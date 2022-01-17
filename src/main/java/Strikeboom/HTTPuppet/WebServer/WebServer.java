package Strikeboom.HTTPuppet.WebServer;

import Strikeboom.HTTPuppet.HTTPuppet;
import com.sun.net.httpserver.HttpServer;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WebServer {
    private final HttpServer server;
    public WebServer(int port) throws IOException {
        //create server
        server = HttpServer.create(new InetSocketAddress(port),0);
        server.setExecutor(null);
    }
    //host the guilds in a json file that contains the ids and names and lets jda do the rest of the work
    public void hostGuilds() {
        server.createContext("/guilds.json",exchange -> {
            exchange.getResponseHeaders().add("Content-Type","application/json");

            exchange.sendResponseHeaders(200, HTTPuppet.guildJson.getArrayString().length());
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(HTTPuppet.guildJson.getArrayString().getBytes());
            outputStream.close();
        });
    }
    public void hostHomepage() {
        //create homepage at / first
        server.createContext("/",exchange -> {
            exchange.getResponseHeaders().add("Content-Type","text/html");

            byte[] file = Files.readAllBytes(Paths.get("src/main/resources/html/bot.html"));

            exchange.sendResponseHeaders(200,file.length);

            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(file);
            outputStream.close();
        });
    }
    public void start() {
        server.start();
    }
}
