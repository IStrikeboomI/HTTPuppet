package Strikeboom.HTTPuppet.webserver.json;

import Strikeboom.HTTPuppet.HTTPuppet;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import org.json.JSONArray;
import org.json.JSONObject;

public class GuildJson implements IJSON {
    private JSONArray array;
    public GuildJson() {
        array = new JSONArray();
        add();
    }
    @Override
    public void add() {
        //iterate over all the guilds
        for (Guild guild : HTTPuppet.jda.getGuilds()) {
            JSONObject object = new JSONObject();
            object.put("id",guild.getId());
            object.put("name",guild.getName());

            //iterate over all members and add to separate member array
            JSONArray membersArray = new JSONArray();
            //use load members because get members only gets cached members
            for (Member member : guild.loadMembers().get()) {
                JSONObject memberObject = new JSONObject();
                memberObject.put("id",member.getId());
                memberObject.put("name",member.getEffectiveName());
                membersArray.put(memberObject);
            }

            //iterate over all categories and add to separate category array
            JSONArray categoryArray = new JSONArray();
            for (Category category : guild.getCategories()) {
                JSONObject channelObject = new JSONObject();
                channelObject.put("id", category.getId());
                channelObject.put("name", category.getName());
                categoryArray.put(channelObject);
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
            object.put("categories",categoryArray);
            array.put(object);
        }
    }

    @Override
    public void refresh() {
        array = new JSONArray();
        add();
    }

    @Override
    public String getArrayString() {
        return array.toString();
    }

    @Override
    public String getHostedUrl() {
        return "/guilds.json";
    }
}
