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
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

public class Book {
    Gson gson = new Gson();
    JsonObject bookJson = new JsonObject();
    JsonArray pagesArray = new JsonArray();




    public Book (){}

    public ItemStack createBook(File file){
        if (!file.exists()) {
            System.out.println("El archivo no existe.");
            return null;
        }

        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

        // Leer los datos del archivo YAML
        String title = yaml.getString("Titulo");
        String author = yaml.getString("Autor");
        String base64Encoded = yaml.getString("Base64");

        if (title == null || author == null || base64Encoded == null) {
            System.out.println("Faltan datos en el archivo YAML.");
            return null;
        }

        // Decodificar el contenido Base64
        byte[] decodedBytes = Base64.getDecoder().decode(base64Encoded);
        String decodedJsonString = new String(decodedBytes, StandardCharsets.UTF_8);

        // Convertir la cadena JSON a las páginas del libro
        JsonObject decodedBookJson = gson.fromJson(decodedJsonString, JsonObject.class);
        JsonArray decodedPagesArray = decodedBookJson.getAsJsonArray("pages");

        // Crear un nuevo ItemStack del libro firmado
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) book.getItemMeta();

        if (bookMeta != null) {
            bookMeta.setTitle(title);
            bookMeta.setAuthor(author);

            for (int i = 0; i < decodedPagesArray.size(); i++) {
                bookMeta.addPage(decodedPagesArray.get(i).getAsString());
            }

            book.setItemMeta(bookMeta);
        }

        return book;

    }








    public void createFile(ItemStack book) {
        if (book.getType() == Material.WRITTEN_BOOK) {
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            if (bookMeta != null) {
                List<String> pages = bookMeta.getPages();

                for (String page : pages) {
                    pagesArray.add(page);
                }
                bookJson.add("pages", pagesArray);
                String bookJsonString = gson.toJson(bookJson);
                String base64Encoded = Base64.getEncoder().encodeToString(bookJsonString.getBytes());


                //Obtener datos

                String title = bookMeta.getTitle();
                String author = bookMeta.getAuthor();
                String genre = "Ejemplo de Género"; // Asigna el género según tus necesidades
                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                String sinopsis = "Vacia";

                // Crear el archivo YAML

                File file = new File(Main.getInstance().getDataFolder()+"/Books", title + ".yml");
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
                yaml.set("Sinopsis",sinopsis);
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
