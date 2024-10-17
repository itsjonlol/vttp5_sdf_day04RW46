package fc;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    public static void main(String[] args)  {
        if (args.length<2){
            System.err.println("Ensure argument format is <port> <filename.txt>");
        }
        int port = Integer.parseInt(args[0]);
        String fileName = args[1];

        Cookie cookie = new Cookie();

        List<String> generatedList = cookie.generateCookieList(fileName);
        

        //create the server
        ServerSocket server;
        try {
            server = new ServerSocket(port);
            System.out.printf("Server listening on port %d\n",port);


            while (true) { 
                Socket conn = server.accept();
                System.out.println("Got a connection");

                while (true) {
                    InputStream is = conn.getInputStream();
                    DataInputStream dis = new DataInputStream(is);
                    String clientInput = dis.readUTF();
                    OutputStream os = conn.getOutputStream();
                    DataOutputStream dos = new DataOutputStream(os);
                    
                    if (clientInput.equals("get-cookie")){
                        String randomCookie = cookie.generateRandomCookie(generatedList);
                        String serverOutput = "cookie-text: " + randomCookie;
                        System.out.println(serverOutput);
                        dos.writeUTF(serverOutput);
                        dos.flush();
                
                    } else if (clientInput.equals("close")) {
                        System.out.println("Client has closed the connection. Server closing...");
                        conn.close();
                        System.exit(-1);
                    } else {
                        System.out.println("Wrong command initiated by client");
                        dos.writeUTF("Invalid command...");
                        
                    }

                }
                
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        


    }
}