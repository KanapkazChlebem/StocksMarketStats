package stocksstatistics;


import java.io.IOException;

public class App {


    public static void main(String[] args) {
        CommandPropt commandPropt = new CommandPropt();
        try {
            commandPropt.commandLoop();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

