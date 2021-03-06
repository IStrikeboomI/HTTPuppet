package Strikeboom.HTTPuppet;

import Strikeboom.HTTPuppet.logger.Logger;
import Strikeboom.HTTPuppet.logger.TextColors;
import Strikeboom.HTTPuppet.operation.Operations;
import Strikeboom.HTTPuppet.webserver.WebServer;
import Strikeboom.HTTPuppet.webserver.json.JSONFiles;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class HTTPuppet {
    public static JDA jda;
    public static void main(String[] args) {
        //use a file to hold the token
        String token = null;
        File tokenFile = new File("token.txt");
        //create a token file if none exists if not then read the token in there
        if (!tokenFile.exists()) {
            try {
                System.out.println("Fill out token.txt with bot token");
                tokenFile.createNewFile();
                System.exit(0);
            } catch (IOException ignored) {}
        }
        try {
            Scanner reader = new Scanner(tokenFile);
            token = reader.nextLine();
            reader.close();
        } catch (FileNotFoundException ignored) {}
        try {
            //build jda
            jda = JDABuilder.createDefault(token)
                    .setChunkingFilter(ChunkingFilter.ALL) // enable member chunking for all guilds
                    .setMemberCachePolicy(MemberCachePolicy.ALL) // ignored if chunking enabled
                    .enableIntents(GatewayIntent.GUILD_MEMBERS) // use this to get all the members
                    .build();
            jda.awaitReady();
            Logger.getInstance().log(TextColors.CYAN,"Bot Started");

            Operations.init();

            JSONFiles.init();

            WebServer server = new WebServer(80);
            server.hostJSONS();
            server.hostJS();
            server.hostOperations();
            server.hostHomepage();
            server.hostOperationHandler();
            server.start();
            Logger.getInstance().log(TextColors.CYAN,"Web Server Started");
        } catch (LoginException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
