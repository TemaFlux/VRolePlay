package me.temaflux.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Config {
	private String name = null;
	private JavaPlugin plugin = null;
	private File DataFolder = null;
	private File file = null;
	private FileConfiguration configuration = null;
	public boolean isInitialized = false;
	public boolean isCreated = false;
	public boolean isLoaded = false;
	
	public Config(String name, JavaPlugin plugin) {
		this.name = name;
		this.plugin = plugin;
		this.DataFolder = plugin.getDataFolder();
		this.file = new File(getDataFolder(), name);
		this.isInitialized = true;
	}
	
	public void create() {
		if (!getDataFolder().exists())
            getDataFolder().mkdir();
		
        File file = new File(getDataFolder(), getName());
        
        if (!file.exists()) {
            try (InputStream in = getResourceAsStream(getName())) {
                Files.copy(in, file.toPath());
                this.isCreated = true;
            }
            catch (Exception e) {
            	try {
					file.createNewFile();
					this.isCreated = true;
				} catch (IOException e1) {
					this.isCreated = false;
					e1.printStackTrace();
				}
            }
        }
        else {
        	this.isCreated = true;
        }
	}
	
	public void load() {
		if (!this.isCreated) this.create();
		if (!this.isLoaded) {
			try {
				configuration = YamlConfiguration.loadConfiguration(getFile());
				this.isLoaded = true;
			} catch (Exception e) {
				this.isLoaded = false;
				e.printStackTrace();
			}
		}
		if (this.isLoaded)
			this.plugin.getLogger().info("File \""+this.name+"\" has been loaded.");
		else
			this.plugin.getLogger().info("Config ("+this.name+") don't loaded.");
	}
	
	public void reload() {
		this.load();
	}
	
	private InputStream getResourceAsStream(String string) {
		return plugin.getResource(string);
	}

	public String getName() {
		return this.name;
	}
	
	public File getDataFolder() {
		return this.DataFolder;
	}

	public File getFile() {
		return this.file;
	}
	
	public FileConfiguration getConfig() {
		return this.configuration;
	}
}