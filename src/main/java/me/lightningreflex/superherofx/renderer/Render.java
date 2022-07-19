package me.lightningreflex.superherofx.renderer;

import me.lightningreflex.superherofx.SuperheroFX;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Render {
	public static void FX(Location location) {
		Random r = new Random();
		List<ArmorStand> activeFX = new ArrayList<>();
		Location modifiedLocation = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
		for (int i = 0; i < 3; ++i) {
			double randomX = 0;
			double randomY = 0;
			double randomZ = 0;
			boolean withinRange;

			for (int ii = 0; ii < 100; ++ii) {
				withinRange = false;
				modifiedLocation = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
				while (!(randomX < 0.4 && randomX > -0.4) || (randomX == 0)) {
					randomX = ((r.nextInt(120) + 1) - 60) / 100D;
				}

				while ((randomY < 0.2) || (randomY == 0)) {
					randomY = (r.nextInt(170) + 1) / 100D;
				}

				while (!(randomZ < 0.4 && randomZ > -0.4) || (randomZ == 0)) {
					randomZ = ((r.nextInt(120) + 1) - 60) / 100D;
				}
				if (activeFX.size() == 0) {
					modifiedLocation.add(randomX, randomY, randomZ);
					break;
				}

				for (ArmorStand fx : activeFX) {
					if (fx.getLocation().distance(modifiedLocation.add(randomX, randomY, randomZ)) < 0.45) {
						withinRange = true;
					}
				}
				if (!withinRange) {
					break;
				}
				randomX = 0;
				randomY = 0;
				randomZ = 0;
			}


			ArmorStand armorStand = modifiedLocation.getWorld().spawn(modifiedLocation, ArmorStand.class, entity -> {
				entity.setInvisible(true);
				entity.setInvulnerable(true);
				entity.setMarker(true);
				entity.setCanMove(false);
				entity.setCustomNameVisible(true);
				entity.customName(Component.text("Kapow!"));
			});

			activeFX.add(armorStand);

			Bukkit.getScheduler().runTaskLater(SuperheroFX.getPlugin(), () -> {
				armorStand.remove();
				activeFX.remove(armorStand);
			}, 20L);
		}
	}
}
