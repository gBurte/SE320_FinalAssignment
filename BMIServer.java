import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BMIServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8081);

            System.out.println("🌟 Welcome to the BMI Wellness Hub! 🌈 The BMI Server is now online, eagerly awaiting health-conscious clients to join the virtual fitness zone! 💪✨");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                DataInputStream input = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

                double weight = input.readDouble();
                double height = input.readDouble();

                double bmi = calculateBMI(weight, height);

                output.writeUTF("Your BMI: " + bmi);

                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private double calculateBMI(double weight, double height) {
            return weight / (height * height);
        }
    }
}
