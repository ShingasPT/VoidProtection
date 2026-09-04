package me.shingas.voidProtection.listeners;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class VoidProtectionListener implements Listener {

    private final JavaPlugin plugin;

    public VoidProtectionListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (player.isDead()) {
            return;
        }

        int minHeight = plugin.getConfig().getInt("min-height", -100);

        if (player.getY() > minHeight) {
            return;
        }

        World world = player.getWorld();
        Location spawn = world.getSpawnLocation();

        player.teleportAsync(spawn).thenAccept(success -> {
            if (!success) {
                return;
            }

            String prefix = plugin.getConfig().getString("Prefix", "");
            String message = plugin.getConfig().getString("message", "<prefix> <yellow>You have been teleported to safety.");

            MiniMessage miniMessage = MiniMessage.miniMessage();

            player.sendMessage(miniMessage.deserialize(
                    message,
                    Placeholder.parsed("prefix", prefix)
            ));
        });
    }
}
