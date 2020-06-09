package xyz.pepepvp.core;
import com.google.gson.JsonObject;
import net.labymod.serverapi.LabyModAPI;
import net.labymod.serverapi.bukkit.LabyModPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GoAway implements CommandExecutor {

	private final Main plugin;

	public GoAway(Main plugin) {
		this.plugin = plugin;
	}

	public void sendClientToServer( Player player, String address ) {
		JsonObject object = new JsonObject();
		object.addProperty( "title", "Changing Server" );
		object.addProperty( "address", address );
		object.addProperty( "preview", true );
		LabyModPlugin.getInstance().sendServerMessage( player, "server_switch", object );
	}

	public boolean onCommand(CommandSender pSender, Command pCmd, String s, String[] args) {
		if (pSender.hasPermission("pepepvp.goaway")) {
			sendClientToServer(Bukkit.getPlayer(args[0]), args[1]);
		}
		return true;
	}
}
