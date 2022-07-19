package me.lightningreflex.superherofx;

import me.lightningreflex.superherofx.listeners.EntityDamageByEntityListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class SuperheroFX extends JavaPlugin {
	private static SuperheroFX instance;
	private static Logger logger;

	@Override
	public void onEnable() {
		// Plugin startup logic
		instance = this;
		logger = getServer().getLogger();
		getServer().getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}

	public static SuperheroFX getPlugin() {
		return instance;
	}
	public static Logger getPluginLogger() {
		return logger;
	}

}
