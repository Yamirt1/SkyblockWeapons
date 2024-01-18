package org.yamirt11;

import org.bukkit.plugin.java.JavaPlugin;
import org.yamirt11.Commands.Aspect.AspectCommand;
import org.yamirt11.Commands.Healing.HealingSwordCommand;
import org.yamirt11.Commands.Wither.WitherSwordCommand;
import org.yamirt11.Hablidades.Healing.HealingSword;
import org.yamirt11.Hablidades.Aspect.WeaponListener;
import org.yamirt11.Hablidades.Wither.WitherSwordListener;

public class SkyblockWeapons extends JavaPlugin {

    @Override
    public void onEnable() {
        // Inicialización del plugin
        getLogger().info("SkyblockWeapons ha sido habilitado.");

        // Registra eventos y comandos aquí
        getServer().getPluginManager().registerEvents(new WeaponListener(), this);
        getCommand("aspect").setExecutor(new AspectCommand());

        getServer().getPluginManager().registerEvents(new HealingSword(), this);
        getCommand("healingsword").setExecutor(new HealingSwordCommand());

        getServer().getPluginManager().registerEvents(new WitherSwordListener(), this);
        getCommand("withersword").setExecutor(new WitherSwordCommand());


    }

    @Override
    public void onDisable() {
        // Desactivación del plugin
        getLogger().info("SkyblockWeapons ha sido deshabilitado.");
    }
}
