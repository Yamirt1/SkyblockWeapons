package org.yamirt11.Hablidades.Healing;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public class HealingSword implements Listener {

    private final Map<Player, Long> cooldowns = new HashMap<>();
    private static final long COOLDOWN_TIME = 3 * 1000; // 10 segundos en milisegundos

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        // Verifica si el jugador hizo clic derecho
        if (event.getAction().toString().contains("RIGHT")) {
            ItemStack weapon = player.getItemInHand();

            // Verifica si el jugador está usando la espada especial y si ha pasado el cooldown
            if (weapon != null && weapon.getType() == Material.DIAMOND_SWORD && weapon.getItemMeta().getDisplayName().equals("Espada Curativa")) {
                if (checkCooldown(player)) {
                    // Aplica la curación al hacer clic derecho
                    double amountToHeal = 5.0; // Ajusta la cantidad de curación según tus necesidades
                    player.setHealth(Math.min(player.getHealth() + amountToHeal, player.getMaxHealth()));

                    // Reproduce un sonido al teletransportarse
                    player.playSound(player.getLocation(), Sound.EAT, 1.0f, 1.0f);

                    // Registra el tiempo de uso en el cooldown
                    cooldowns.put(player, System.currentTimeMillis() + COOLDOWN_TIME);
                } else {
                    player.sendMessage("La habilidad está en cooldown. Debes esperar.");
                }
            }
        }
    }

    private boolean checkCooldown(Player player) {
        if (cooldowns.containsKey(player)) {
            long cooldownTime = cooldowns.get(player);
            return System.currentTimeMillis() > cooldownTime;
        }
        return true; // Si no hay cooldown registrado, permite usar la habilidad
    }
}