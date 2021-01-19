package com.draconequus.recycleleather;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class CreateScrap {
	public static ItemStack leatherScrap;
	static RecycleLeather plugin;
	
	public static void init(Plugin plugin) {
		boolean LeatherScrap_to_Leather = plugin.getConfig().getBoolean("LeatherScrap_to_Leather");
		boolean Leather_to_LeatherScrap = plugin.getConfig().getBoolean("Leather_to_LeatherScrap");
		boolean Smelt_RottenFlesh = plugin.getConfig().getBoolean("Smelt_RottenFlesh");
		boolean Smelt_LeatherArmor = plugin.getConfig().getBoolean("Smelt_LeatherArmor");
		boolean Smelt_Book = plugin.getConfig().getBoolean("Smelt_Book");
		boolean Smelt_ItemFrame = plugin.getConfig().getBoolean("Smelt_ItemFrame");
		boolean Smelt_LeatherHorseArmor = plugin.getConfig().getBoolean("Smelt_LeatherHorseArmor");

		createLeatherScrap();
		
		if (LeatherScrap_to_Leather) {
			createShapedRecipe(leatherScrap);
		}
		if (Leather_to_LeatherScrap) {
			createShapelessRecipe(leatherScrap);
		}
		if (Smelt_RottenFlesh) {
			createFurnaceRecipe0(leatherScrap);
		}
		if (Smelt_LeatherArmor) {
			createFurnaceRecipe1(leatherScrap);
		}
		if (Smelt_Book) {
			createFurnaceRecipe2(leatherScrap);
		}
		if (Smelt_ItemFrame) {
			createFurnaceRecipe3(leatherScrap);
		}
		if (Smelt_LeatherHorseArmor) {
			createFurnaceRecipe4(leatherScrap);
		}
	}
	
	private static void createLeatherScrap() {
		ItemStack item = new ItemStack(Material.LEATHER);
		ItemMeta meta = item.getItemMeta();
		String itemName = "§fLeather Scrap";
		meta.setDisplayName(itemName);
		meta.setCustomModelData(900003);
		item.setItemMeta(meta);
		leatherScrap = item;
	}
	
	private static void createShapedRecipe(ItemStack item) {
		@SuppressWarnings("deprecation")
		RecipeChoice rc = new RecipeChoice.ExactChoice(item);
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("leatherscrap_to_leather"), new ItemStack(Material.LEATHER));
		sr.shape("LLL","LLL", "LLL");
		sr.setIngredient('L', rc);
		Bukkit.getServer().addRecipe(sr);
	}
	
	private static void createShapelessRecipe(ItemStack item) {
		item.setAmount(9);
		ShapelessRecipe sr = new ShapelessRecipe(NamespacedKey.minecraft("leather_to_leatherscrap"), item);
		sr.addIngredient(1, Material.LEATHER);
		Bukkit.getServer().addRecipe(sr);
	}
	
	private static void createFurnaceRecipe0(ItemStack item) {
		item.setAmount(1);
		FurnaceRecipe smelt = new FurnaceRecipe(NamespacedKey.minecraft("rottenflesh_smelt"), item, Material.ROTTEN_FLESH, 1.0f, 7 * 20);
		Bukkit.getServer().addRecipe(smelt);
	}
	private static void createFurnaceRecipe1(ItemStack item) {
		item.setAmount(1);
		FurnaceRecipe smelt0 = new FurnaceRecipe(NamespacedKey.minecraft("leatherhelmet_smelt"), item, Material.LEATHER_HELMET, 1.0f, 7 * 20);
		Bukkit.getServer().addRecipe(smelt0);
		FurnaceRecipe smelt1 = new FurnaceRecipe(NamespacedKey.minecraft("leatherchestplate_smelt"), item, Material.LEATHER_CHESTPLATE, 1.0f, 7 * 20);
		Bukkit.getServer().addRecipe(smelt1);
		FurnaceRecipe smelt2 = new FurnaceRecipe(NamespacedKey.minecraft("leatherleggings_smelt"), item, Material.LEATHER_LEGGINGS, 1.0f, 7 * 20);
		Bukkit.getServer().addRecipe(smelt2);
		FurnaceRecipe smelt3 = new FurnaceRecipe(NamespacedKey.minecraft("leatherboots_smelt"), item, Material.LEATHER_BOOTS, 1.0f, 7 * 20);
		Bukkit.getServer().addRecipe(smelt3);
	}
	private static void createFurnaceRecipe2(ItemStack item) {
		item.setAmount(1);
		FurnaceRecipe smelt = new FurnaceRecipe(NamespacedKey.minecraft("book_smelt"), item, Material.BOOK, 1.0f, 7 * 20);
		Bukkit.getServer().addRecipe(smelt);
	}
	private static void createFurnaceRecipe3(ItemStack item) {
		item.setAmount(1);
		FurnaceRecipe smelt = new FurnaceRecipe(NamespacedKey.minecraft("itemframe_smelt"), item, Material.ITEM_FRAME, 1.0f, 7 * 20);
		Bukkit.getServer().addRecipe(smelt);
	}
	private static void createFurnaceRecipe4(ItemStack item) {
		item.setAmount(1);
		FurnaceRecipe smelt = new FurnaceRecipe(NamespacedKey.minecraft("leatherhorsearmor_smelt"), item, Material.LEATHER_HORSE_ARMOR, 1.0f, 7 * 20);
		Bukkit.getServer().addRecipe(smelt);
	}
	
}
