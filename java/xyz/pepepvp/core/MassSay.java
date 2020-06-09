package xyz.pepepvp.core;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class MassSay implements CommandExecutor {

	private final Main plugin;

	public MassSay(Main plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender pSender, org.bukkit.command.Command pCmd, String s, String[] args) {
		if (pSender.hasPermission("pepepvp.masssay")) {
			String string = "";
			for (int i = 0; i < args.length; i++) {
				string += args[i] + " ";
			}
			for (Player player : Bukkit.getServer().getOnlinePlayers()) {
				player.chat(string);
			}
		}
		return true;
	}
}
