package mcmmorpggroup.flaggames.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mcmmorpggroup.flaggames.FlagGame;
import net.akaishi_teacher.util.lang.Language;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Heal extends FlagGamesCommand {

	public Heal(FlagGame flaggame, String pattern, String permission,
			String description) {
		super(flaggame, pattern, permission, description);
	}

	@Override
	public boolean execute(CommandSender sender, ArrayList<String> args) {
		Player player = null;

		//Get player.
		if (hasOption(args, 2)) {
			if ((player = Bukkit.getPlayer(args.get(2))) != null) {} else {
				sender.sendMessage(plugin.getLang().get("Err_PlayerNotFound"));
				return true;
			}
		} else {
			if ((player = castPlayer(sender)) != null);
			else //By console or cmdBlock.
				return true;
		}

		//Heal.
		player.setHealth(player.getMaxHealth());
		player.setFoodLevel(30);

		Map<String, String> replaceMap = new HashMap<>();
		replaceMap.put("Player", player.getName());
		sender.sendMessage(Language.replaceArgs(plugin.getLang().get("Cmd_Out_Heal_Heal"), replaceMap));

		return true;
	}

	@Override
	public String getUsage(CommandSender sender) {
		return plugin.getLang().get("Cmd_Usage_Heal");
	}

}