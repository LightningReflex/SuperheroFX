package me.lightningreflex.superherofx.listeners;

import me.lightningreflex.superherofx.renderer.Render;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;


public class EntityDamageByEntityListener implements Listener {

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		Render.FX(e.getEntity().getLocation());
	}
}
