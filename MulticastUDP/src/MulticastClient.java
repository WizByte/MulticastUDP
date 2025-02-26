import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastClient {
    private String group;
    private int port;
    private MulticastSocket socket;

    public MulticastClient(String group, int port) throws IOException {
        this.group = group;
        this.port = port;
        this.socket = new MulticastSocket(port);
        InetAddress multicastAddress = InetAddress.getByName(group);
        socket.joinGroup(multicastAddress);
    }

    public void receiveAlerts() throws IOException {
        byte[] buffer = new byte[1024];
        while (true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength());
            System.out.println("[CLIENT] Ricevuto messaggio, contenuto: " + message);
        }
    }
}