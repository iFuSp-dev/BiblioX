package me.aifusp.bibliox;

public enum InventoryName {
    MAIN_MENU("Inmobiliaria Menu", 9),
    UPLOAD_MENU("Inmobiliaria Hotel", 54),

    FILTER_MENU("Inmobiliaria Menu Compra",27),
    LIBRARY_MENU("Inmobiliaria Menu Compra",27);


    private final String menuName;
    private final int size;
    private  InventoryName(String name,int size){
        this.menuName = name;
        this.size = size;
    }

    public String getName(){return this.menuName;}
    public  int getSize(){return this.size;}

}
