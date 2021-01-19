package com.draconequus.recycleleather;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class Commands {

	public static boolean checkCommand(CommandSender sender, String args[], String action, String item, Plugin plugin) {
		List<String> Errors = new ArrayList<String>();
		Errors.addAll(plugin.getConfig().getStringList("Errors"));
		List<String> Alerts = new ArrayList<String>();
		Alerts.addAll(plugin.getConfig().getStringList("Alerts"));		
		List<String> formatError0 = new ArrayList<String>();
		List<String> formatError1 = new ArrayList<String>();
		List<String> formatError2 = new ArrayList<String>();
		List<String> formatAlert0 = new ArrayList<String>();
		List<String> formatAlert1 = new ArrayList<String>();
		List<String> formatAlert2 = new ArrayList<String>();

		for (int i = 0; i < 4; i++) {
			formatError0.add(Errors.get(i).replace("&&", "¤"));
			formatError1.add(formatError0.get(i).replace("&", "§"));
			formatError2.add(formatError1.get(i).replace("¤", "&"));
			formatAlert0.add(Alerts.get(i).replace("&&", "¤"));
			formatAlert1.add(formatAlert0.get(i).replace("&", "§"));
			formatAlert2.add(formatAlert1.get(i).replace("¤", "&"));
		}
		
		int amount = 1;
		Player player = null;
		if (sender instanceof Player) { player = (Player) sender; }
		ItemStack leatherScrap = new ItemStack(CreateScrap.leatherScrap);
		ItemStack leatherBlock = new ItemStack(CreateBlock.leatherBlock);
			
		if (args.length < 1) {
			if (sender instanceof Player) {
				player.sendMessage((formatError2.get(0)));
			}
			else {
				Bukkit.getConsoleSender().sendMessage((formatError2.get(0)));
			}
			return false;
		}
		
		else if (args.length > 2) {
			if (sender instanceof Player) {
				player.sendMessage((formatError2.get(1)));
			}
			else {
				Bukkit.getConsoleSender().sendMessage((formatError2.get(1)));
			}
			return false;
		}
		
		if (args.length > 1) {
			if (args[1] != null) {
				try {
					Integer.parseInt(args[1]);
				} 
				catch (Exception exception) {
					if (sender instanceof Player) {
						player.sendMessage((formatError2.get(2)));
					}
					else {
						Bukkit.getConsoleSender().sendMessage((formatError2.get(2)));
					}
					return false;
				}
				amount = Integer.parseInt(args[1]);
			}
		}
		
		if (Bukkit.getPlayer(args[0]) == null) {
			String formatSplit0[] = formatError2.get(3).split("%player%");
			String formatSplit1 = formatSplit0[0] + args[0] + formatSplit0[1];
			
			if (sender instanceof Player) {
				player.sendMessage((formatSplit1));
			}
			else {
				Bukkit.getConsoleSender().sendMessage((formatSplit1));
			}
			return false;
		}

		Player target = Bukkit.getPlayer(args[0]);
		leatherScrap.setAmount(amount);
		leatherBlock.setAmount(amount);
		
		if (action.equals("give")) {
			if (item.equals("scrap")) {
				String formatSplit0[] = formatAlert2.get(0).split("%player%");
				String formatSplit1 = formatSplit0[0] + args[0] + formatSplit0[1];
				String formatSplit2[] = formatSplit1.split("%amount%");
				String formatSplit3 = formatSplit2[0] + amount + formatSplit2[1];
				
				target.getInventory().addItem(leatherScrap);
				
				if (sender instanceof Player) {
					player.sendMessage((formatSplit3));
				}
				else {
					Bukkit.getConsoleSender().sendMessage((formatSplit3));
				}
			}
			else if (item.equals("block")) {
				String formatSplit0[] = formatAlert2.get(1).split("%player%");
				String formatSplit1 = formatSplit0[0] + args[0] + formatSplit0[1];
				String formatSplit2[] = formatSplit1.split("%amount%");
				String formatSplit3 = formatSplit2[0] + amount + formatSplit2[1];
				
				target.getInventory().addItem(leatherBlock);
				
				if (sender instanceof Player) {
					player.sendMessage((formatSplit3));
				}
				else {
					Bukkit.getConsoleSender().sendMessage((formatSplit3));
				}
			}
		}
		else if (action.equals("take")) {
			if (item.equals("scrap")) {
				String formatSplit0[] = formatAlert2.get(2).split("%player%");
				String formatSplit1 = formatSplit0[0] + args[0] + formatSplit0[1];
				String formatSplit2[] = formatSplit1.split("%amount%");
				String formatSplit3 = formatSplit2[0] + amount + formatSplit2[1];
				
				target.getInventory().removeItem(leatherScrap);
				
				if (sender instanceof Player) {
					player.sendMessage((formatSplit3));
				}
				else {
					Bukkit.getConsoleSender().sendMessage((formatSplit3));
				}
			}
			else if (item.equals("block")) {
				String formatSplit0[] = formatAlert2.get(2).split("%player%");
				String formatSplit1 = formatSplit0[0] + args[0] + formatSplit0[1];
				String formatSplit2[] = formatSplit1.split("%amount%");
				String formatSplit3 = formatSplit2[0] + amount + formatSplit2[1];
				
				target.getInventory().removeItem(leatherBlock);
				
				if (sender instanceof Player) {
					player.sendMessage((formatSplit3));
				}
				else {
					Bukkit.getConsoleSender().sendMessage((formatSplit3));
				}
			}
		}
		
		return true;
	}

}
