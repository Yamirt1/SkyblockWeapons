package org.yamirt11.Hablidades.Aspect;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class WeaponListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        // Verifica si el jugador hizo clic derecho y si está usando la "Aspect of the End"
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (player.getItemInHand().getType() == Material.DIAMOND_SWORD &&
                    player.getItemInHand().getItemMeta().getDisplayName().equals("Aspect of the End")) {

                // Ejecuta la habilidad "Blink"
                blink(player);

                // Reproduce un sonido al teletransportarse
                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 1.0f);

                // Aplica el efecto de velocidad al jugador
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 4 * 20, 2)); // 3 segundos de duración, velocidad 2
            }
        }
    }

    private void blink(Player player) {
        // Obtén la dirección en la que el jugador está mirando
        Vector direction = player.getLocation().getDirection();

        // Teleporta al jugador en la dirección mirada
        player.teleport(player.getLocation().add(direction.multiply(5))); // Puedes ajustar la distancia del teletransporte según tus necesidades
    }
}