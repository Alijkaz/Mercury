package ir.ciph3r.mercury.modules.shuffle;

import ir.ciph3r.mercury.Mercury;
import ir.ciph3r.mercury.modules.model.Model;
import ir.ciph3r.mercury.storage.Permissions.Perms;
import ir.ciph3r.mercury.utility.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Shuffle extends Model {

    public Shuffle(Mercury mercury) {
        super(mercury, "Shuffle", "Shuffle", mercury.getConfigFile().SHUFFLE_ENABLED);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender.hasPermission(Perms.SHUFFLE))) {
            Utils.sendColorizedMessage(sender, getMessages().NO_PERMISSION);
            return true;
        }

        if (args.length == 0) {
            Utils.sendColorizedMessage(sender, getMessages().SHUFFLE_USAGE);
        } else if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                Utils.sendColorizedMessage(sender, getMessages().PLAYER_NOT_FOUND.replace("{player}", args[0]));
            } else {
                target.getInventory().setHeldItemSlot(Utils.getRandomNumber(0, 9, target.getInventory().getHeldItemSlot()));
                Utils.sendColorizedMessage(sender, getMessages().SHUFFLE_SUCCESS_ADMIN.replace("{player}", target.getName()));
            }
        }
        return true;
    }
}
