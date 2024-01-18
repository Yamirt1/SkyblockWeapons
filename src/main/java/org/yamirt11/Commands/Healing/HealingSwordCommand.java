package org.yamirt11.Commands.Healing;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HealingSwordCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            // Crea la espada curativa
            ItemStack healingSword = createHealingSword();

            // Dale la espada al jugador
            player.getInventory().addItem(healingSword);

            player.sendMessage("¡Has recibido la Espada Curativa!");

            return true;
        } else {
            sender.sendMessage("Este comando solo puede ser ejecutado por jugadores.");
            return false;
        }
    }

    private ItemStack createHealingSword() {
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = sword.getItemMeta();

        // Establece el nombre de la espada
        meta.setDisplayName("Espada Curativa");

        // Puedes personalizar más atributos si lo deseas

        sword.setItemMeta(meta);

        return sword;
    }
}