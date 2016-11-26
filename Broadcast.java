package com.lawlsec.<redacted>.Util;

import com.lawlsec.<redacted>.Configs.SettingsConfig;
import <redacted>;
import <redacted>;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Broadcast {

    /**
     * Default message prefix
     */
    private static String Prefix = "[Boosters]";

    /**
     * Default prefix color
     */
    private static ChatColor cPrefix  = ChatColor.DARK_PURPLE;

    /**
     * Default message color
     */
    private static ChatColor cMessage = ChatColor.WHITE;

    /**
     * Maximum length for messages, longer messages
     * will be split into parts.
     */
    private static int MaxMessageLength = 95;

    /**
     * Get formatted Prefix
     * @return Formatted Prefix
     */
    private static String getPrefix() {
        return cPrefix + Prefix + " ";
    }

    /**
     * Color a string with & codes
     * @param string String to Color
     * @return Colorized String
     */
    private static String Color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    /**
     * Dispatch a message globally
     * @param _Message Message to dispatch
     */
    public static void Dispatch(String _Message) {
        if (SettingsConfig.getDebug()) {
            Debug.Log(Debug.DebugType.Other, String.format(
                "Broadcast blocked because Debug mode is Enabled"
            ));

            Debug.Log(Debug.DebugType.Other, String.format(
                "Blocked: %s", _Message
            ));

            return;
        }

        Bukkit.getServer().dispatchCommand(
                Bukkit.getConsoleSender(),
                "bc " + Color(getPrefix() + cMessage + _Message)
        );
    }

    /**
     * Dispatch a message to a certain player
     * @param _Message Message to dispatch
     * @param _Player Player to dispatch message to
     */
    public static void Dispatch(String _Message, Player _Player) {
        if (!Players.isOnline(DevAPI.lookupPlayer(_Player)))
            return;

        _Player.sendMessage(Color(getPrefix() + cMessage + _Message));
    }

    /**
     * Dispatch a message to a certain player
     * @param _Message Message to dispatch
     * @param _Player Player to dispatch message to
     */
    public static void Dispatch(String _Message, GlobalPlayer _Player) {
        if (!Players.isOnline(_Player))
            return;

        Bukkit.getPlayer(_Player.getUUID()).sendMessage(Color(getPrefix() + cMessage + _Message));
    }

    /**
     * Split a message into multiple messages if the
     * message is too long for Bukkit to display
     * @param Content Strings to parse
     * @return List of parsed strings
     */
    public static List<String> SplitMessage(List<String> Content) {
        List<String> Ret = new ArrayList<>();

        String CompactContent = "";
        for (String Item : Content)
            CompactContent += Item;

        if (CompactContent.length() <= MaxMessageLength) {
            Ret.add(CompactContent);
            return Ret;
        }

        Pattern p = Pattern.compile("\\G\\s*(.{1," + MaxMessageLength + "})(?=\\s|$)", Pattern.DOTALL);
        Matcher m = p.matcher(CompactContent);

        while (m.find())
            Ret.add(m.group(1));

        return Ret;
    }

    /**
     * Automatically pluralizes a string input (single word)
     * @param Input String to pluralize
     * @param Amount Amount of whatever object
     * @return Properly plural string
     */
    public static String PluralizeMe(String Input, int Amount) {
        return Amount > 1 ? Input + "s" : Input;
    }
}
