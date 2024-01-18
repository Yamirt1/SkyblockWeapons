package org.yamirt11.Hablidades.Wither;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import java.util.Objects;

public class WitherSwordListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        // Verifica si el jugador hizo clic derecho
        if (event.getAction().toString().contains("RIGHT")) {
            ItemStack weapon = player.getItemInHand();

            // Verifica si el jugador está usando la espada especial
            if (weapon != null && weapon.getType() == Material.DIAMOND_SWORD && Objects.equals(weapon.getItemMeta().getDisplayName(), "Espada Withers")) {
                shootWitherHead(player);
            }
        }
    }

    private void shootWitherHead(Player player) {
        // Lanza un proyectil de WitherSkull
        WitherSkull witherSkull = player.launchProjectile(WitherSkull.class);
        witherSkull.setCustomName("WitherHeadProjectile");

        // Ajusta la velocidad y dirección según tus necesidades
        witherSkull.setVelocity(player.getLocation().getDirection().multiply(2.0));

        // Reproduce el sonido de wither
        player.playSound(player.getLocation(), Sound.WITHER_SHOOT, 1.0f, 1.0f);

        // Puedes agregar más configuraciones según sea necesario
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();

        if (projectile.getCustomName() != null && projectile.getCustomName().equals("WitherHeadProjectile")) {
            // Aquí se ejecutará el código cuando la bola de nieve (representando la cabeza de wither) impacte en algo

            // Verificar si impactó en una entidad
            if (projectile.getNearbyEntities(1, 1, 1).stream().anyMatch(entity -> entity instanceof Player)) {
                // Detonar TNT en el lugar del impacto (o realizar otra acción según tus necesidades)
                Objects.requireNonNull(projectile.getWorld()).createExplosion(projectile.getLocation(), 3.0f);
            }

            // Verificar si impactó en un bloque
            BlockIterator blockIterator = new BlockIterator(projectile.getWorld(), projectile.getLocation().toVector(), projectile.getVelocity().normalize(), 0, 4);
            while (blockIterator.hasNext()) {
                if (blockIterator.next().getType() != Material.AIR) {
                    // Detonar TNT en el lugar del impacto (o realizar otra acción según tus necesidades)
                    Objects.requireNonNull(projectile.getWorld()).createExplosion(projectile.getLocation(), 3.0f);
                    break;
                }
            }

            // Puedes ajustar la fuerza de la explosión y otros parámetros según tus necesidades
            // También puedes implementar lógica adicional aquí, como aplicar efectos o daño a entidades cercanas
        }
    }

}