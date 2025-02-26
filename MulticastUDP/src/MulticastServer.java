import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

// Server multicast migliorato
public class MulticastServer {
    private static final String MULTICAST_GROUP = "230.0.0.1";
    private static final int PORT = 5000;

    public static void main(String[] args) {
        try (MulticastSocket socket = new MulticastSocket()) {
            InetAddress group = InetAddress.getByName(MULTICAST_GROUP);
            Scanner scanner = new Scanner(System.in);

            System.out.println("[Server] Avviato. Inserisci un messaggio (digita 'exit' per terminare): ");

            while (true) {
                String alert = scanner.nextLine();
                if ("exit".equalsIgnoreCase(alert)) {
                    System.out.println("Chiusura del server in corso.");
                    break;
                }

                byte[] buffer = alert.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, PORT);
                socket.send(packet);
                System.out.println("Messaggio inviato, contenuto: " + alert);
            }
        } catch (IOException e) {
            System.err.println("Errore durante l'invio del messaggio: " + e.getMessage());
        }
    }
}
