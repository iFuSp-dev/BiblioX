package org.aifusp.commands;

import org.aifusp.Main;
import org.aifusp.utils.Book;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import java.io.File;


public class MainCommand implements CommandExecutor {
    private Main plugin;
    private Book book = new Book();

    public MainCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("bibliox")) {



            if (sender instanceof Player){
                Player player = (Player) sender;
                ItemStack wbook = player.getInventory().getItemInMainHand();

                if (args.length > 1){
                    if (args[0].equalsIgnoreCase("getbook")){
                        String bookname = args[1];

                        //Encontrar archivo
                        File file = new File(Main.getInstance().getDataFolder()+"/Books",bookname+".yml");
                        if (!file.exists()){
                            Bukkit.getConsoleSender().sendMessage("Este libro no existe");
                        }

                        ItemStack nBook = book.createBook(file);
                        player.getInventory().addItem(nBook);

                    }
                }


                if (wbook.getType() == Material.WRITTEN_BOOK){
                    book.createFile(wbook);
                }

            }

        }
        return false;
    }
}
