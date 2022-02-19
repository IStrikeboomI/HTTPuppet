package Strikeboom.HTTPuppet.logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final Logger INSTANCE = new Logger();

    private final File LOG_FILE;
    private Logger() {
        final File LOG_DIRECTORY = new File("logs/");
        if (!LOG_DIRECTORY.exists()) {
            LOG_DIRECTORY.mkdirs();
        }
         LOG_FILE = new File("logs/" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh-mm-ss a")) + ".txt");
        if (!LOG_FILE.exists()) {
            try {
                LOG_FILE.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static Logger getInstance() {
        return INSTANCE;
    }
    public void log(String[] tags, String text) {
        log(TextColors.WHITE,tags,text);
    }
    public void log(String color, String[] tags, String text) {
        StringBuilder builder = new StringBuilder();
        builder.append("[").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss a"))).append("] ");
        for (String s : tags) {
            builder.append("[");
            builder.append(s);
            builder.append("] ");
        }
        builder.append(text);
        log(color,builder.toString());
    }
    public void log(String color, String string) {
        System.out.println(color + string + TextColors.RESET);
        try {
            final FileWriter writer = new FileWriter(LOG_FILE);
            writer.write(string);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
