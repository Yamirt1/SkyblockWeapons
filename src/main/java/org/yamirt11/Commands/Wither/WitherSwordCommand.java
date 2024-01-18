package org.yamirt11.Commands.Wither;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WitherSwordCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Este comando solo puede ser ejecutado por un jugador.");
            return true;
        }

        Player player = (Player) sender;

        // Verifica si el jugador tiene permisos para ejecutar el comando
        if (!player.hasPermission("tuplugin.withersword")) {
            player.sendMessage(ChatColor.RED + "No tienes permisos para este comando.");
            return true;
        }

        // Crea la espada especial "Espada Withers"
        ItemStack witherSword = createWitherSword();

        // Añade la espada al inventario del jugador
        player.getInventory().addItem(witherSword);

        player.sendMessage(ChatColor.GREEN + "Has recibido la Espada Withers.");

        return true;
    }

    private ItemStack createWitherSword() {
        ItemStack witherSword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = witherSword.getItemMeta();

        // Establece el nombre de la espada
        meta.setDisplayName("Espada Withers");

        // Puedes personalizar más atributos si lo deseas

        witherSword.setItemMeta(meta);

        return witherSword;
    }
}