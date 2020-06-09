package xyz.pepepvp.core;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		this.getCommand("masssay").setExecutor(new MassSay(this));
		this.getCommand("goaway").setExecutor(new GoAway(this));
		this.getCommand("cnpc").setExecutor(new NPC(this));
		Bukkit.getServer().getPluginManager().registerEvents(new ListenerEvents(this), this);
	}
	
	@Override
	public void onDisable() {
	}
	
	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
		return false;
	}
	
}
