package com.draconequus.recycleleather;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class Command_RLSCRAPTAKE implements CommandExecutor {
	
	private final RecycleLeather plugin;

	public Command_RLSCRAPTAKE(RecycleLeather plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		String action = "take";
		String item = "scrap";
		
		Commands.checkCommand(sender, args, action, item, plugin);

		return true;
	}

}
