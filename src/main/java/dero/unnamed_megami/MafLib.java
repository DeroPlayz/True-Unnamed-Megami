package dero.unnamed_megami;

// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.awt.event.KeyEvent;
// import java.awt.event.KeyListener;
// import java.io.FileInputStream;
//import java.util.Properties;
import java.util.Scanner;
// import java.util.concurrent.CountDownLatch;

public class MafLib{
    public static String response;
    public static boolean asking = false;
    public static final String RESET = "\033[0m";
    public static final String CLEARC = "\033[39m";
    public static final String CLEARF = "\033[22m" + "\033[23m" + "\033[24m" + "\033[27m" + "\033[28" + "\033[29m";
    public static final String WHITE = "\033[37m"; public static final String WHITEH = "\033[47m";
    // public static final String BLACK = "\033[30m"; public static final String BLACKH = "\033[40m";
    public static final String BLACK = "\033[37m" + "\033[1m" /*Bold*/; public static final String BLACKH = "\033[47m";
    public static final String RED = "\033[31m"; public static final String REDH = "\033[41m";
    public static final String GREEN = "\033[32m"; public static final String GREENH = "\033[42m"; //Mint Green
    public static final String YELLOW = "\033[33m"; public static final String YELLOWH = "\033[43m";
    public static final String BLUE = "\033[34m"; public static final String BLUEH = "\033[44m"; //Dark Blue
    public static final String MAGENTA = "\033[35m"; public static final String MAGENTAH = "\033[45m";
    public static final String CYAN = "\033[36m"; public static final String CYANH = "\033[46m";
    public static final String BLINK = "\033[5m";
    public static final String BOLD = "\033[1m";
    public static final String UNDERLINE = "\033[4m";
    public static final String STRIKE = "\033[9m";
    public static final String INVERT = "\033[7m";
    public static final String HIDE = "\033[8m";
    
    static Scanner Scan = new Scanner(System.in);
    
    public static String askString(String Prompt){
        System.out.print(Prompt + RESET);
        String response = Scan.next();
        return response;
    }

    public static int askInt(String Prompt){
        System.out.print(Prompt + RESET);
        response = Scan.next();
        response = response.replaceAll("[^0-9.]", "");
        int dot = response.indexOf(".");
        if(dot == -1){
            response = response.replaceAll("[^0-9]", "");
        }
        else{
            response = response.substring(0, dot);
        }
        if (MafLib.isNumeric(response)){
            return Integer.valueOf(response);
        }
        return 0;
    }

    public static int askInt(){
        System.out.print(RESET);
        response = Scan.next();
        response = response.replaceAll("[^0-9.]", "");
        int dot = response.indexOf(".");
        if(dot == -1){
            response = response.replaceAll("[^0-9]", "");
        }
        else{
            response = response.substring(0, dot);
        }
        if (MafLib.isNumeric(response)){
            return Integer.valueOf(response);
        }
        return 0;
    }

    public static double askDouble(String Prompt){
        System.out.print(Prompt + RESET);
        response = Scan.next();
        response = response.replaceAll("[^0-9]", "");
        if(!response.contains(".")){
            return Double.valueOf(response + ".0");
        }
        else{
            return Integer.valueOf(response);
        }
        
    }

    public static void TimedPrint(String message, long text_speed){
        for(int i = 0; i < message.length(); i++){
            /* 0 = Instant
             * 50 = Fast
             * 100 = Regular
             * 150 = Slow
             * 200 = Are you kidding me? */
            long current_speed = text_speed;
            // if (String.valueOf(message.charAt(i)).equals("|")){current_speed = text_speed * 2;}
            // System.out.println(i);
            if (i < message.length()-2){
                // System.out.println(message.substring(i, i+2).contains("+("));
                if (message.substring(i, i+2).contains("+(")){
                    String formatting = message.substring(i+1, i + message.substring(i).indexOf(")") + 1);
                    // System.out.println(formatting);
                    if (formatting.contains("RESET")){System.out.print(MafLib.RESET);}
                    if (formatting.contains("CLEARC")){System.out.print(MafLib.CLEARC);}
                    if (formatting.contains("CLEARF")){System.out.print(MafLib.CLEARF);}
                    if (formatting.contains("WHITE")){System.out.print(MafLib.WHITE);}
                    if (formatting.contains("WHITEH")){System.out.print(MafLib.WHITEH);}
                    if (formatting.contains("BLACK")){System.out.print(MafLib.BLACK);}
                    if (formatting.contains("BLACKH")){System.out.print(MafLib.BLACKH);}
                    if (formatting.contains("RED")){System.out.print(MafLib.RED);}
                    if (formatting.contains("REDH")){System.out.print(MafLib.REDH);}
                    if (formatting.contains("GREEN")){System.out.print(MafLib.GREEN);}
                    if (formatting.contains("GREENH")){System.out.print(MafLib.GREENH);}
                    if (formatting.contains("YELLOW")){System.out.print(MafLib.YELLOW);}
                    if (formatting.contains("YELLOWH")){System.out.print(MafLib.YELLOWH);}
                    if (formatting.contains("BLUE")){System.out.print(MafLib.BLUE);}
                    if (formatting.contains("BLUEH")){System.out.print(MafLib.BLUEH);}
                    if (formatting.contains("MAGENTA")){System.out.print(MafLib.MAGENTA);}
                    if (formatting.contains("MAGENTAH")){System.out.print(MafLib.MAGENTAH);}
                    if (formatting.contains("CYAN")){System.out.print(MafLib.CYAN);}
                    if (formatting.contains("CYANH")){System.out.print(MafLib.CYANH);}
                    if (formatting.contains("BLINK")){System.out.print(MafLib.BLINK);}
                    if (formatting.contains("BOLD")){System.out.print(MafLib.BOLD);}
                    if (formatting.contains("UNDERLINE")){System.out.print(MafLib.UNDERLINE);}
                    if (formatting.contains("STRIKE")){System.out.print(MafLib.STRIKE);}
                    if (formatting.contains("INVERT")){System.out.print(MafLib.INVERT);}
                    if (formatting.contains("HIDE")){System.out.print(MafLib.HIDE);}
                    if (formatting.contains("WAIT:")){
                        int delay = Integer.valueOf(formatting.substring(formatting.indexOf(":")+1, formatting.indexOf(")")));
                        for(int j = 0; j < delay; j++){
                            try {
                                Thread.sleep(current_speed);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    i += formatting.length() + 1;
                    // System.out.println(message.substring(i));
                }
            }
            try {
                Thread.sleep(current_speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!String.valueOf(message.charAt(i)).equals("|")){
                System.out.print(message.charAt(i));
            }
        }
    }

    public static void ClearScreen(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    @SuppressWarnings("resource")
    public static void WaitForEnter(){
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
    }

    public static boolean isAlpha(String s){
        s = s.toLowerCase();
        for(int i = 0; i < s.length(); i++){
            int j = i + 1;
            String t;
            if(i + 1 >= s.length()){
                t = s.substring(i);
            }
            else{
                t = s.substring(i, j);
            }
            if("abcdefghijklmnopqrstuvwxyz".contains(t) == false){
                return false;
            }
        }
        return true;
    }
    
    public static boolean isNumeric(String s){
        s = s.toLowerCase();
        try {
            Integer.valueOf(s);
        }
        catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    public static String reduce(String s){
        return s.strip().toLowerCase();
    }
}