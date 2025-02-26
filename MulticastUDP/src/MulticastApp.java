import java.io.IOException;
import java.util.Scanner;

public class MulticastApp {
    public static void main(String[] args) {
        String role = (args.length > 0) ? args[0] : "client";
        String multicastGroup = "224.1.1.1";
        int multicastPort = 5007;

        try {
            if (role.equals("server")) {
                MulticastServer server = new MulticastServer(multicastGroup, multicastPort);
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    System.out.print("Inserisci il messaggio: ");
                    String message = scanner.nextLine();
                    server.sendAlert(message);
                }
            } else if (role.equals("client")) {
                MulticastClient client = new MulticastClient(multicastGroup, multicastPort);
                System.out.println("[CLIENT] In attesa di messaggi...");
                client.receiveAlerts();
            } else {
                System.out.println("Errore, usa 'server' o 'client'.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}