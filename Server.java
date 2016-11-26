package <redacted>;

import org.bukkit.Bukkit;

import java.util.Objects;

/**
 * Compatibility layer for Bukkit server and NMS Classes
 */
public class Server {
    public enum CraftbukkitVersion {
        v1_8_R3, // 1.8.7-9
        v1_9_R1, // 1.9.0-2
        v1_9_R2, // 1.9.4
        v1_10_R1 // 1.10.0
    }

    /**
     * Gets a double value representing a specified CraftbukkitVersion
     * @param _Ver CraftbukkitVersion to calculate
     * @return Double value representing specified CraftbukkitVersion
     */
    public static double getVersionDouble(CraftbukkitVersion _Ver) {
        String Step1 = _Ver.name().replaceAll("_", ".");
        String Step2 = Step1.replaceAll("[^\\d.]", "");
        return Double.valueOf(Step2);
    }

    /**
     * Gets the correct MajorVersion Enum for the running server
     * @return Running MajorVersion
     */
    public static CraftbukkitVersion getCraftbukkitVersion() {
        String rawVersion = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];

        for (CraftbukkitVersion mVer : CraftbukkitVersion.values()) {
            if (Objects.equals(rawVersion, mVer.name()))
                return mVer;

        }

        return null;
    }

    /**
     * Gets the base NMS Class for the running Craftbukkit version
     * @param SubClass Sub Class to fetch from NMS
     * @return NMS Class for running Craftbukkit
     * @throws ClassNotFoundException Can't find NMS Class for running version
     *
     * Example: getNMSClass("PacketPlayOutWorldParticles");
     *
     */
    public static Class<?> getNMSClass(String SubClass) throws ClassNotFoundException {
        return Class.forName(String.format("net.minecraft.server.%s.%s", getCraftbukkitVersion().name(), SubClass));
    }
}
