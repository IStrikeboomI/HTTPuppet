package Strikeboom.HTTPuppet.WebServer.json;

public interface IJSON {
    /**
     * Used to add everything to the json file
     */
    void add();

    /**
     * @return JSON contents as a string
     */
    String getArrayString();

    /**
     * @return the url the file is hosted on
     */
    String getHostedUrl();
}
