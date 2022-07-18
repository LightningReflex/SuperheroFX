package me.lightningreflex.superherofx.listeners;

import me.lightningreflex.superherofx.SuperheroFX;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Random;


public class EntityDamageByEntityListener implements Listener {

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		double upper;
		double lower;
		double randomOffset;
		Random r = new Random();
		//System.out.println("Attacker - Victim");
		//System.out.println(e.getDamager().name());
		//System.out.println(e.getEntity().name());

		Entity attacker = e.getDamager();
		Entity victim = e.getEntity();
		Location victimLoc = victim.getLocation();

		Location armorStandLoc = victimLoc.add(((r.nextInt(35)+1)-10)/100D, 0, 0);

		ArmorStand armorStand = (ArmorStand) victim.getWorld().spawn(armorStandLoc, ArmorStand.class, entity -> {
			entity.setInvisible(true);
			entity.setInvulnerable(true);
			entity.setMarker(true);
			entity.setCanMove(false);
			entity.setCustomNameVisible(true);
			entity.customName(Component.text("Hi Wrld!"));
		});

		Bukkit.getScheduler().runTaskLater(SuperheroFX.getInstance(), () -> {
			armorStand.remove();
		}, 20L);


//        ArmorStand armorStand = (ArmorStand) victim.getWorld().spawnEntity(victim.getLocation(), EntityType.ARMOR_STAND);
//        armorStand.setInvisible(true);
//        armorStand.setInvulnerable(true);
//        armorStand.setMarker(true);
//        armorStand.setCanMove(false);
//        armorStand.setCustomNameVisible(true);
//        armorStand.customName(Component.text("big world cock"));
//        Bukkit.getScheduler().runTaskLater(SuperheroFX.getInstance(), () -> {
//            armorStand.remove();
//        }, 20L);
	}
}
