package ir.ciph3r.mercury.modules.speed;

import ir.ciph3r.mercury.Mercury;
import ir.ciph3r.mercury.modules.model.Model;
import ir.ciph3r.mercury.storage.Permissions.Perms;
import ir.ciph3r.mercury.storage.yaml.Config;
import ir.ciph3r.mercury.storage.yaml.Messages;
import ir.ciph3r.mercury.utility.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Speed extends Model {
    public Speed(Mercury mercury) {
        super("Speed", "Speed", Config.SPEED_ENABLED, mercury);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender.hasPermission(Perms.SPEED))) {
            Utils.sendColorizedMessage(sender, Messages.NO_PERMISSION);
            return true;
        }
        if (!(sender instanceof Player)) {
            Utils.sendColorizedMessage(sender, Messages.NO_CONSOLE);
            return true;
        }
        Player player = (Player) sender;

        if (args.length == 0) {
            Utils.sendColorizedMessage(player, Messages.SPEED_USAGE);
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reset")) {
                player.setFlySpeed(0.1f);
                player.setWalkSpeed(0.2f);
                Utils.sendColorizedMessage(player, Messages.SPEED_RESET);
            } else {
                Utils.sendColorizedMessage(player, Messages.SPEED_USAGE);
            }
        } else if (args.length == 2) {
            if (!(Utils.isNumeric(args[1]))) {
                Utils.sendColorizedMessage(player, Messages.ARGS_NOT_NUMBER);
                return true;
            }
            if (!(Double.parseDouble(args[1]) >= 0) || !(Double.parseDouble(args[1]) <= 10)) {
                Utils.sendColorizedMessage(player, Messages.SPEED_USAGE);
                return true;
            }
            float amount = Float.parseFloat(args[1]) / 10;

            if (args[0].equalsIgnoreCase("Walk")) {
                player.setWalkSpeed(amount);
                Utils.sendColorizedMessage(player, Messages.SPEED_WALK_SUCCESS.replace("{amount}", args[1]));
            } else if (args[0].equalsIgnoreCase("Fly")) {
                player.setFlySpeed(amount);
                Utils.sendColorizedMessage(player, Messages.SPEED_FLY_SUCCESS.replace("{amount}", args[1]));
            } else {
                Utils.sendColorizedMessage(player, Messages.SPEED_USAGE);
            }
        }
        return true;
    }
}