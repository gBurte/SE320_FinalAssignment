import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class BMIClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8080);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            Scanner scanner = new Scanner(System.in);

            System.out.print("Help me understand your amazing body better! Tell me your weight in kilograms: ");
            double weight = scanner.nextDouble();

            System.out.print("Curious to discover your unique stature? Share your height in meters: ");
            double height = scanner.nextDouble();

            dos.writeDouble(weight);
            dos.writeDouble(height);

            String response = dis.readUTF();
            System.out.println(response);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
