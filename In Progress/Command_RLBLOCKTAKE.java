package com.draconequus.recycleleather;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class Command_RLBLOCKTAKE implements CommandExecutor {
	
	private final RecycleLeather plugin;

	public Command_RLBLOCKTAKE(RecycleLeather plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		String action = "take";
		String item = "block";
		
		Commands.checkCommand(sender, args, action, item, plugin);

		return true;
	}

}
