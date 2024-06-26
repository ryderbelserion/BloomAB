package host.bloom.ab.bukkit;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;

public class BukkitLoginListener implements Listener {

    private final BukkitPlugin plugin;

    public BukkitLoginListener(BukkitPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        plugin.getManager().incrementConnectionCount();

        Player player = e.getPlayer();

        if (player.hasPermission("bab.admin.actionbar.onjoin")) {
            this.plugin.getManager().addSeer(player.getUniqueId());
        }
    }

    @EventHandler
    public void onServerListPingEvent(ServerListPingEvent e) {
        plugin.getManager().incrementConnectionCount();
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        // Always remove it just in case as they might not have the permission.
        this.plugin.getManager().removeSeer(event.getPlayer().getUniqueId());
    }
}
