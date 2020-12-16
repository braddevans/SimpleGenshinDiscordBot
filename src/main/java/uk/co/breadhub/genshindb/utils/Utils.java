package uk.co.breadhub.genshindb.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import net.dv8tion.jda.api.entities.Message;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.entity.ContentType;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.base.Splitter;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.body.MultipartBody;

import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.exceptions.PermissionException;

public class Utils {

    public static List<String> listSplitMaxLength(List<String> list, int maxLength) {
        List<String> result = new ArrayList<>();
        for (String item : list) {
            if (item.length() > maxLength) {
                for (int i = 0; i < item.length(); i += maxLength) {
                    String part = item.substring(i, Math.min(item.length(), i + maxLength));
                    result.add(part);
                }
            }
            else result.add(item);
        }
        return result;
    }

    public static List<String> messageSplit(String message, int maxLength) {
        List<String> list = Splitter.on('\n').splitToList(message);
        list = listSplitMaxLength(list, maxLength-1);

        ListIterator<String> iterator = list.listIterator();

        List<String> result = new ArrayList<>();
        String current = "";

        while (iterator.hasNext()) {
            String line = iterator.next();
            if (current.length() + line.length() + 1 > maxLength) {
                iterator.previous();
                result.add(current);
                current = "";
            }
            else {
                if (!current.isEmpty())
                    current += "\n";
                current += line;
            }
        }
        result.add(current);
        return result;
    }



    public static String trimLength(String text, int maxLength, String denoter) {
        if (text.length() <= maxLength)
            return text;
        else
            return text.substring(0, maxLength - denoter.length()) + denoter;
    }

    public static String escapeMarkdown(String text) {
        return text.replace("*", "\\*").replace("`", "\u200B`\u200B").replace("&#39;", "'");
    }

    public static String escapeMarkdownBlock(String text) {
        return text.replace("`", "\u200B`\u200B").replace("&#39;", "'");
    }

    public static <T> void addAllUniques(List<T> master, List<T> donor) {
        for (T o : donor)
            if (!master.contains(o))
                master.add(o);
    }

    public static String toTimeString(long miliseconds) {
        int days = (int) (miliseconds / (1000*60*60*24));
        miliseconds = miliseconds % (1000*60*60*24);
        int hours = (int) (miliseconds / (1000*60*60));
        miliseconds = miliseconds % (1000*60*60);
        int minutes = (int) (miliseconds / (1000*60));
        miliseconds = miliseconds % (1000*60);
        int seconds = (int) (miliseconds / (1000));
        miliseconds = miliseconds % (1000);

        String sDays = days <= 0 ? null : (days + " day" + (days > 1 ? "s" : ""));
        String sHours = hours <= 0 ? null : (hours + " hour" + (hours > 1 ? "s" : ""));
        String sMinutes = minutes <= 0 ? null : (minutes + " minute" + (minutes > 1 ? "s" : ""));
        String sSeconds = seconds <= 0 ? null : (seconds + " second" + (seconds > 1 ? "s" : ""));

        List<String> strings = new ArrayList<>();
        if (sDays != null) strings.add(sDays);
        if (sHours != null) strings.add(sHours);
        if (sMinutes != null) strings.add(sMinutes);
        if (sSeconds != null) strings.add(sSeconds);

        return strings.stream().collect(Collectors.joining(" "));
    }

    public static String numberToDual(int number) {
        return (number > 9 ? "": "0") + number;
    }

    public static String compactTimeString(int seconds) {
        int mins = seconds / 60;
        int secs = seconds % 60;

        return numberToDual(mins) + ":" + numberToDual(secs);
    }
}