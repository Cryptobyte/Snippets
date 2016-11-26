package com.lawlsec.core.Util;

import com.lawlsec.core.Util.Enums.DebugLevel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

public class Debugger {
    private Plugin _Instance;

    /**
     * Automatically disables the plugin on critical error
     */
    private void CriticalShutdown() {
        Log(DebugLevel.Error, String.format(
            "Encountered Critical Error! Shutting Down..",
            _Instance.getName()
        ));

        Bukkit.getPluginManager().disablePlugin(_Instance);
    }

    /**
     * Dispatch a prebuilt message to Console
     * @param Message Message to Dispatch
     */
    private void Dispatch(String Message) {
        Bukkit.getServer().getConsoleSender().sendMessage(Message);
    }

    public void Log(String Message) {
        Log(DebugLevel.Info, Message);
    }

    public void Log(DebugLevel Level, String Message) {
        String S_Prefix = ChatColor.BLUE + "[" + ChatColor.WHITE + _Instance.getName() + ChatColor.BLUE + "] ";

        switch (Level) {
            case Info:
                Message = ChatColor.WHITE + Message;
                break;
            case Warning:
                Message = ChatColor.AQUA + Message;
                break;
            case Error:
                Message = ChatColor.RED + Message;
                break;
            case Critical:
                Message = ChatColor.DARK_RED + Message;
                break;
        }

        Dispatch(S_Prefix + S_Prefix + Message);

        if (Level.equals(DebugLevel.Critical))
            CriticalShutdown();

    }

    public Debugger(Plugin Instance) {
        _Instance = Instance;
    }
}
