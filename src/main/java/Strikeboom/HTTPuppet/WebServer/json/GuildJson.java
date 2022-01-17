package Strikeboom.HTTPuppet.WebServer.json;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.channels.Channel;
import java.util.List;

public class GuildJson {
    final private JSONArray array;
    public GuildJson() {
        array = new JSONArray();
    }
    public void addGuilds(List<Guild> guilds) {
        //iterate over all the guilds
        for (Guild guild : guilds) {
            JSONObject object = new JSONObject();
            object.put("id",guild.getId());
            object.put("name",guild.getName());

            //iterate over all memebrs and add to separate member array
            JSONArray membersArray = new JSONArray();
            for (Member member : guild.getMembers()) {
                JSONObject memberObject = new JSONObject();
                memberObject.put("id",member.getId());
                memberObject.put("name",member.getNickname());
                membersArray.put(memberObject);
            }

            //iterate over all channels and add to separate channel array
            JSONArray channelArray = new JSONArray();
            for (TextChannel channel : guild.getTextChannels()) {
                JSONObject channelObject = new JSONObject();
                channelObject.put("id", channel.getId());
                channelObject.put("name", channel.getName());
                channelArray.put(channelObject);
            }
            //put the separate arrays in the main array
            object.put("channels",channelArray);
            object.put("members",membersArray);
            array.put(object);
        }
    }

    public String getArrayString() {
        return array.toString();
    }
}
