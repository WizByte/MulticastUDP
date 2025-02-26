import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

public class MulticastClient {
    private static final String MULTICAST_GROUP = "230.0.0.1";
    private static final int PORT = 5000;

    public static void main(String[] args) {
        try (MulticastSocket socket = new MulticastSocket(PORT)) {
            InetAddress group = InetAddress.getByName(MULTICAST_GROUP);
            socket.setReuseAddress(true);
            socket.joinGroup(group);

            System.out.println("Client in ascolto per messaggi");

            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String message = new String(packet.getData(), 0, packet.getLength());
                if ("exit".equalsIgnoreCase(message.trim())) {
                    System.out.println("[Server] Ha terminato la comunicazione.");
                    break;
                }

                System.out.println("Messaggio ricevuto, contenuto: " + message);
            }

            socket.leaveGroup(group);
        } catch (SocketException e) {
            System.err.println("Errore di connessione: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Errore di I/O: " + e.getMessage());
        }
    }
}
