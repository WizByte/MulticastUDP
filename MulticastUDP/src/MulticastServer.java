import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class MulticastServer {
    private String group;
    private int port;
    private DatagramSocket socket;
    private InetAddress multicastAddress;

    public MulticastServer(String group, int port) throws IOException {
        this.group = group;
        this.port = port;
        this.socket = new DatagramSocket();
        this.multicastAddress = InetAddress.getByName(group);
    }

    public void sendAlert(String message) throws IOException {
        byte[] buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, multicastAddress, port);
        socket.send(packet);
        System.out.println("[SERVER] Messaggio mandato.");
    }
}