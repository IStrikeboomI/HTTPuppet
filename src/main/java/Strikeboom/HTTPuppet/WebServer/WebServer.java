package Strikeboom.HTTPuppet.WebServer;

import Strikeboom.HTTPuppet.HTTPuppet;
import Strikeboom.HTTPuppet.WebServer.json.IJSON;
import Strikeboom.HTTPuppet.WebServer.json.JSONFiles;
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
    public void hostJSONS() {
        for (IJSON ijson : JSONFiles.JSON_FILES) {
            server.createContext(ijson.getHostedUrl(), exchange -> {
                exchange.getResponseHeaders().add("Content-Type","application/json");

                exchange.sendResponseHeaders(200, ijson.getArrayString().length());
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write(ijson.getArrayString().getBytes());
                outputStream.close();
            });
        }
    }
    public void hostHomepage() {
        //create favicon directory
        server.createContext("/favicon.ico",exchange -> {
            exchange.getResponseHeaders().add("Content-Type","image/x-icon");

            byte[] file = Files.readAllBytes(Paths.get("src/main/resources/image/icon/icon.ico"));

            exchange.sendResponseHeaders(200,file.length);

            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(file);
            outputStream.close();
        });

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
