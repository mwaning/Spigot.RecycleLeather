package com.draconequus.recycleleather;

import java.io.File;
import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class RecycleLeather extends JavaPlugin implements Listener {
	Plugin plugin = this;
	private File file;
	private FileConfiguration config;
	
	@Override
	public void onEnable() {		
	    getServer().getPluginManager().registerEvents(this, this);
	    
		this.getCommand("rlscrapgive").setExecutor(new Command_RLSCRAPGIVE(this));
		this.getCommand("rlscraptake").setExecutor(new Command_RLSCRAPTAKE(this));
		this.getCommand("rlblockgive").setExecutor(new Command_RLBLOCKGIVE(this));
		this.getCommand("rlblocktake").setExecutor(new Command_RLBLOCKTAKE(this));
		
		CreateScrap.init(plugin);
		CreateBlock.init(plugin);
				
		createConfig();
	}
	
	@Override
	public void onDisable() {
	}
	
	public Plugin getPluginVar() { 
		return plugin; 
	}
	
	private void createConfig() {
		file = new File(getDataFolder(), "config.yml");
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			saveResource("config.yml", false);
		}
		
		config = new YamlConfiguration();
		try {
			config.load(file);
		} catch (IOException | InvalidConfigurationException exception) {
			exception.printStackTrace();
		}
	}
	
   @EventHandler
    public void onJoin(PlayerJoinEvent event) {
	   boolean AutoLoad = plugin.getConfig().getBoolean("AutoLoad");
	   String URL = plugin.getConfig().getString("URL");

	   if (AutoLoad) {
		   event.getPlayer().setResourcePack(URL);
	   }
    }

   @EventHandler 
   public void onCraft(PrepareItemCraftEvent event) {
	   
	   Recipe inv = event.getInventory().getRecipe();
	   ItemStack leather = new ItemStack(Material.LEATHER);
	   ItemStack[] slots = event.getInventory().getMatrix();

	   try { // has to be debugged more properly
		   for (ItemStack is : slots) {
		   
			   if (is != null && is.hasItemMeta() && is.getItemMeta().getDisplayName().contains("Leather Scrap")) {
				   if (inv.getResult().getItemMeta().getDisplayName().contains("Leather Scrap") || inv.getResult().getType().equals(Material.BOOK) || inv.getResult().getType().equals(Material.ITEM_FRAME) || inv.getResult().getType().equals(Material.LEATHER_BOOTS) || inv.getResult().getType().equals(Material.LEATHER_HELMET) || inv.getResult().getType().equals(Material.LEATHER_HORSE_ARMOR) || inv.getResult().getType().equals(Material.LEATHER_LEGGINGS) || inv.getResult().getType().equals(Material.LEATHER_CHESTPLATE)) {
					   event.getInventory().setResult(null);
				   }
				   else if (inv.getResult().getItemMeta().getDisplayName().contains("Leather Block")) {
					   event.getInventory().setResult(leather);
				   }
				   else {
					   //
				   }
			   }
			   else if (is != null && is.getType() != Material.AIR && is.getItemMeta().getDisplayName().contains("Leather Block")) {
				   if (inv.getResult().getType().equals(Material.BRICKS) || inv.getResult().getType().equals(Material.FLOWER_POT)) {
					   event.getInventory().setResult(null);
				   }
		   }
   }

	  } catch (Exception exception) {}
   }
   
   /* @EventHandler
   public void onConsume(PlayerItemConsumeEvent event) {
	   Player player = event.getPlayer();
	   ItemStack item = player.getInventory().getItemInMainHand();
	   ItemMeta meta = item.getItemMeta();
	   
	   if (meta.getDisplayName().contains("Leather Scrap") || meta.getDisplayName().contains("Leather Block")) {
		   event.setCancelled(true);
	   }
   }*/
}
