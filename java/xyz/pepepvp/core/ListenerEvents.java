package xyz.pepepvp.core;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.labymod.serverapi.bukkit.LabyModPlugin;
import net.labymod.serverapi.bukkit.event.LabyModPlayerJoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ListenerEvents implements Listener {

	private final Main plugin;

	public ListenerEvents(Main plugin) {
		this.plugin = plugin;
	}

	enum EnumActionType {
		NONE,
		CLIPBOARD,
		RUN_COMMAND,
		SUGGEST_COMMAND,
		OPEN_BROWSER
	}

	@EventHandler
	public void LabyModJoin(LabyModPlayerJoinEvent e) {
		JsonArray array = new JsonArray();
		JsonObject entry = new JsonObject();
		entry.addProperty("displayName", "Report Player");
		entry.addProperty("type", EnumActionType.SUGGEST_COMMAND.name());
		entry.addProperty("value", "report {name}");
		array.add(entry);
		LabyModPlugin.getInstance().sendServerMessage(e.getPlayer(), "user_menu_actions", array);
	}
}
