package me.lightningreflex.superherofx;

import me.lightningreflex.superherofx.listeners.EntityDamageByEntityListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class SuperheroFX extends JavaPlugin {
	private static SuperheroFX instance;

	@Override
	public void onEnable() {
		// Plugin startup logic
		instance = this;
		getServer().getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}

	public static SuperheroFX getInstance() {
		return instance;
	}
}
