package Strikeboom.HTTPuppet.WebServer;

import Strikeboom.HTTPuppet.WebServer.json.IJSON;
import Strikeboom.HTTPuppet.WebServer.json.JSONFiles;
import Strikeboom.HTTPuppet.operations.IOperation;
import Strikeboom.HTTPuppet.operations.InvalidOperationException;
import Strikeboom.HTTPuppet.operations.Operations;
import com.sun.net.httpserver.HttpServer;
import org.json.JSONObject;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Timer;
import java.util.TimerTask;

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

                exchange.sendResponseHeaders(200, ijson.getArrayString().getBytes(StandardCharsets.UTF_8).length);
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write(ijson.getArrayString().getBytes());
                outputStream.close();
            });
        }
    }
    public void hostOperations() {
        for (IOperation operation : Operations.OPERATIONS) {
            server.createContext(operation.getUrl(), exchange -> {
                exchange.getResponseHeaders().add("Content-Type","text/html");

                byte[] file = Files.readAllBytes(Paths.get(operation.getFileLocation()));

                exchange.sendResponseHeaders(200, file.length);
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write(file);
                outputStream.close();
            });
        }
    }
    public void hostJS() {
        server.createContext("/js/JSONFilesFetching.js",exchange -> {
            exchange.getResponseHeaders().add("Content-Type","text/javascript");
            byte[] file = Files.readAllBytes(Paths.get("src/main/resources/html/js/JSONFilesFetching.js"));

            exchange.sendResponseHeaders(200,file.length);

            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(file);
            outputStream.close();
        });
        server.createContext("/js/SendOperationRequest.js",exchange -> {
            exchange.getResponseHeaders().add("Content-Type","text/javascript");
            byte[] file = Files.readAllBytes(Paths.get("src/main/resources/html/js/SendOperationRequest.js"));

            exchange.sendResponseHeaders(200,file.length);

            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(file);
            outputStream.close();
        });
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

        //create CSS directory
        server.createContext("/template.css",exchange -> {
            exchange.getResponseHeaders().add("Content-Type","text/css");

            byte[] file = Files.readAllBytes(Paths.get("src/main/resources/html/css/template.css"));

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
    //canSend for rate limiting every second, only 1 operation per second
    boolean canSend = true;
    public void hostOperationHandler() {
        server.createContext("/handleoperation", exchange -> {
            if (!canSend) {
                exchange.getResponseHeaders().add("Retry-After","1000");
                exchange.sendResponseHeaders(429,0);
                exchange.close();
            } else {
                canSend = false;
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        canSend = true;
                    }
                    //set rate limit to 1 second
                }, 1000);
            }

            InputStream inputStream = exchange.getRequestBody();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            for (int result = bis.read(); result != -1; result = bis.read()) {
                buf.write((byte) result);
            }
            inputStream.close();
            bis.close();
            String data = buf.toString("UTF-8");
            buf.close();

            JSONObject object = new JSONObject(data);
            if (!object.has("operation") || !object.has("parameters")) {
                try {
                    exchange.sendResponseHeaders(404,0);
                    exchange.close();
                    throw new InvalidOperationException("Operation has no operation or parameters");
                } catch (InvalidOperationException e) {
                    e.printStackTrace();
                }
            }
            String operation = object.getString("operation");
            for (IOperation iOperation : Operations.OPERATIONS) {
                if (iOperation.getUrl().equals(operation)) {
                    try {
                        iOperation.handleOperation(object.getJSONArray("parameters").toList().toArray());
                    } catch (InvalidOperationException e) {
                        exchange.sendResponseHeaders(404,0);
                        exchange.close();
                        e.printStackTrace();
                    }
                }
            }
            exchange.sendResponseHeaders(200,0);
            exchange.close();
        });
    }
    public void start() {
        server.start();
    }
}
