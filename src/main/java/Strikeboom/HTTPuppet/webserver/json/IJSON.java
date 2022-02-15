package Strikeboom.HTTPuppet.webserver.json;

public interface IJSON {
    /**
     * Used to add everything to the json file
     */
    void add();

    /**
     * Used to refresh all data after client updates it
     */
    void refresh();

    /**
     * @return JSON contents as a string
     */
    String getArrayString();

    /**
     * @return the url the file is hosted on
     */
    String getHostedUrl();
}
