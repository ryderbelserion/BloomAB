package host.bloom.ab.common.commands.sub;

import host.bloom.ab.common.AbstractPlugin;
import host.bloom.ab.common.commands.Sender;
import host.bloom.ab.common.commands.SubCommand;
import host.bloom.ab.common.config.enums.Messages;
import java.util.Collections;
import java.util.List;

public class ForceStop implements SubCommand {

    private final AbstractPlugin plugin;

    public ForceStop(AbstractPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getPermission() {
        return "bab.admin.manage";
    }

    @Override
    public void run(Sender sender, String[] args) {
        if (!plugin.getManager().isForceTrigger()) {
            sender.sendMessage(Messages.trigger_stopped.getMessage());
            return;
        }

        plugin.getManager().setForceTrigger(false, 0);
        sender.sendMessage(Messages.trigger_manually_stopped.getMessage());
    }

    @Override
    public List<String> getTabCompletion(String[] args) {
        if (args.length == 2) return Collections.singletonList("[seconds]");
        return Collections.emptyList();
    }
}