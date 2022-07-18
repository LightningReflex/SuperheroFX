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
		for (int i = 0; i < 3; ++i) {
			double randomX = 0;
			double randomY = 0;
			double randomZ = 0;
			boolean withinRange = false;

			Location modifiedLocation = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());

			for (int ii = 0; ii < 100; ++ii) {
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

				System.out.println(activeFX.size());
				for (int iii = 0; iii < activeFX.size(); ++iii) {
					Double temp = activeFX.get(iii).getLocation().distance(modifiedLocation.add(randomX, randomY, randomZ));
					System.out.println(temp);
					if (temp > 0.6) {
						System.out.println("breaking, distance good");
					} else {
						System.out.println("not breaking, too close");
						withinRange = true;
					}
				}
				if (withinRange) {
					modifiedLocation = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
				} else {
					break;
				}
			}


			ArmorStand armorStand = modifiedLocation.getWorld().spawn(modifiedLocation, ArmorStand.class, entity -> {
				entity.setInvisible(true);
				entity.setInvulnerable(true);
				entity.setMarker(true);
				entity.setCanMove(false);
				entity.setCustomNameVisible(true);
				entity.customName(Component.text("Hi Wrld!"));
			});

			activeFX.add(armorStand);

			Bukkit.getScheduler().runTaskLater(SuperheroFX.getInstance(), () -> {
				armorStand.remove();
				activeFX.remove(armorStand);
			}, 20L);
		}
	}
}
