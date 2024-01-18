package org.yamirt11.Commands.Aspect;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AspectCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Este comando solo puede ser ejecutado por jugadores.");
            return true;
        }

        Player player = (Player) sender;

        // Crea la "Aspect of the End" y dásela al jugador
        ItemStack aspectOfTheEnd = createAspectOfTheEnd();
        player.getInventory().addItem(aspectOfTheEnd);

        player.sendMessage("¡Has recibido la Aspect of the End!");

        return true;
    }

    private ItemStack createAspectOfTheEnd() {
        ItemStack aspectOfTheEnd = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = aspectOfTheEnd.getItemMeta();
        meta.setDisplayName("Aspect of the End");

        // Puedes personalizar más las propiedades del item aquí, como agregar encantamientos, atributos, etc.

        aspectOfTheEnd.setItemMeta(meta);
        return aspectOfTheEnd;
    }
}
