#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME}.Events;#end

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ${NAME} extends Event {
    private static final HandlerList Handlers = new HandlerList();

    public HandlerList getHandlers() {
        return Handlers;
    }

    public static HandlerList getHandlerList() {
        return Handlers;
    }

    public ${NAME}() {

    }
}
