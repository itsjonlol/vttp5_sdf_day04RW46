package fc;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) throws UnknownHostException, IOException {
        if (args.length != 1) {
            System.err.println("Ensure it is 'localhost:12345'");
        }

        String[] argTokens = args[0].split(":");
        String host = argTokens[0];
        int port = Integer.parseInt(argTokens[1]);

        Socket sock = new Socket(host,port);
        System.out.printf("Connected to server at %s, %d\n",host,port);

        Console console = System.console();

        String keyboardInput = "";


        OutputStream os = sock.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);

        InputStream is = sock.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        

        while (!keyboardInput.equals("close")) {
            keyboardInput = console.readLine("Type 'get-cookie' or 'close'\n");
            String serverOutput;
            if (keyboardInput.toLowerCase().equals("get-cookie")) {
                dos.writeUTF(keyboardInput);
                serverOutput = dis.readUTF();
                String filterWords = "cookie-text: ";
                System.out.println(serverOutput.substring(filterWords.length()));
            
            } else if (keyboardInput.toLowerCase().equals("close")) {
                dos.writeUTF(keyboardInput);
            } else {
                dos.writeUTF(keyboardInput);
                serverOutput = dis.readUTF(); // will read the "invalid operation" response from server
                System.out.println(serverOutput);
            }
            
        }
        dos.flush();
        dos.close();
        os.close();
        sock.close();


    }
    
    
}
