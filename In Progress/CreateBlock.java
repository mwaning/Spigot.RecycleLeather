package com.draconequus.recycleleather;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class CreateBlock {
	public static ItemStack leatherBlock;
	static RecycleLeather plugin;
	
	public static void init(Plugin plugin) {
		boolean Leather_to_LeatherBlock = plugin.getConfig().getBoolean("Leather_to_LeatherBlock");
		boolean LeatherBlock_to_Leather = plugin.getConfig().getBoolean("LeatherBlock_to_Leather");
		
		createLeatherScrap();
		
		if (Leather_to_LeatherBlock) {
			createShapedRecipe(leatherBlock);
		}
		if (LeatherBlock_to_Leather) {
			createShapelessRecipe(leatherBlock);
		}
	}
	
	private static void createLeatherScrap() {
		ItemStack item = new ItemStack(Material.BRICK);
		ItemMeta meta = item.getItemMeta();
		String itemName = "§fLeather Block";
		meta.setDisplayName(itemName);
		meta.setCustomModelData(900004);
		item.setItemMeta(meta);
		leatherBlock = item;
	}
	
	private static void createShapedRecipe(ItemStack item) {
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("leather_to_leatherblock"), item);
		sr.shape("LLL","LLL", "LLL");
		sr.setIngredient('L', Material.LEATHER);
		Bukkit.getServer().addRecipe(sr);
	}
	
	private static void createShapelessRecipe(ItemStack item) {
		@SuppressWarnings("deprecation")
		RecipeChoice rc = new RecipeChoice.ExactChoice(item);
		ShapelessRecipe sr = new ShapelessRecipe(NamespacedKey.minecraft("leatherblock_to_leather"), new ItemStack(Material.LEATHER, 9));
		sr.addIngredient(rc);
		Bukkit.getServer().addRecipe(sr);
	}
}
