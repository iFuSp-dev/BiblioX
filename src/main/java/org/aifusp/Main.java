package org.aifusp;
import org.aifusp.commands.MainCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin{
    public  static Main getInstance() {
        return getPlugin(Main.class);
    }

    @Override
    public void onEnable(){

        registerMainCommand();
        loadFolder();
    }

    public void registerMainCommand() {
        this.getCommand("bibliox").setExecutor(new MainCommand(this));
    }

    private void loadFolder(){
        File dataFolder = getDataFolder(); // Obtiene la carpeta de datos de tu plugin

        if (!dataFolder.exists()) {
            dataFolder.mkdirs(); // Crear la carpeta y cualquier directorio faltante
            getLogger().info("Carpeta del plugin creada correctamente.");
        } else {
            getLogger().info("Carpeta del plugin ya existe.");
        }

        // Puedes crear subdirectorios si los necesitas de la misma manera
        File booksFolder = new File(dataFolder, "Books");
        if (!booksFolder.exists()) {
            booksFolder.mkdirs();
            getLogger().info("BooksFolder creada correctamente.");
        }
    }
}
