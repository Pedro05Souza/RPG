package cores;
/*
 * Classe para definição de cores no texto do RPG
 */
public class cores {
    public static String black = "\u001B[30m";
    public static String red = "\u001B[31m";
    public static String green = "\u001B[32m";
    public static String yellow = "\u001B[33m";
    public static String blue = "\u001B[34m";
    public static String purple = "\u001B[35m";
    public static String cyan = "\u001B[36m";
    public static String white = "\u001B[37m";
    public static String reset = "\u001B[0m";

    public static void setBlack(String text){
        System.out.println(black + text + reset);
    }
    public static void setRed(String text){
        System.out.println(red + text + reset);
    }
    public static void setGreen(String text){
        System.out.println(green + text + reset);
    }
    public static void setYellow(String text){
        System.out.println(yellow + text + reset);
    }
    public static void setBlue(String text){
        System.out.println(blue + text + reset);
    }
    public static void setPurple(String text){
        System.out.println(purple + text + reset);
    }
    public static void setCyan(String text){
        System.out.println(cyan + text + reset);
    }
    public static void setWhite(String text){
        System.out.println(white + text + reset);
    }


    
}
