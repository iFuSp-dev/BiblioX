package org.aifusp.utils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.aifusp.Main;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

public class BookFile {
    Gson gson = new Gson();
    JsonObject bookJson = new JsonObject();
    JsonArray pagesArray = new JsonArray();
    private String bookName;

    private File file;
    private ItemStack book;

    public BookFile(ItemStack boook) {
        book = boook;
        createFile();
    }


    public void createFile(){
        if (book.getType() == Material.WRITTEN_BOOK){
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            if (bookMeta != null) {
                List<String> pages = bookMeta.getPages();

                for (String page : pages){
                    pagesArray.add(page);
                }
                bookJson.add("pages",pagesArray);
                String bookJsonString = gson.toJson(bookJson);
                String base64Encoded = Base64.getEncoder().encodeToString(bookJsonString.getBytes());



                //Obtener datos

                String title = bookMeta.getTitle();
                String author = bookMeta.getAuthor();
                String genre = "Ejemplo de Género"; // Asigna el género según tus necesidades
                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

                // Crear el archivo YAML

                File bookFile = new File(Main.getInstance().getDataFolder(), bookName+"yml");
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

                yaml.set("Titulo", title);
                yaml.set("Autor", author);
                yaml.set("Genero", genre);
                yaml.set("Fecha", date);
                yaml.set("Base64", base64Encoded);


                //Save
                try {
                    yaml.save(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
