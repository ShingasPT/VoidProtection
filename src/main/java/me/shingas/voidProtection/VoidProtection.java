package me.shingas.voidProtection;

import me.shingas.voidProtection.listeners.VoidProtectionListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class VoidProtection extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(
                new VoidProtectionListener(this),
                this
        );

        getLogger().info("VoidProtection has started.");
    }

    @Override
    public void onDisable() {
        getLogger().info("VoidProtection is offline.");
    }
}
