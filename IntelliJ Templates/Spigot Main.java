#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import org.bukkit.plugin.java.JavaPlugin;

public class ${NAME} extends JavaPlugin {
    private static ${NAME} Instance;

    @Override
    public void onEnable() {
        Instance = this;
    }

    @Override
    public void onDisable() {

    }

    public static ${NAME} Get() {
        return Instance;
    }
}
