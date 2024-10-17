package fc.util;
//javac --source-path fc fc/*.java fc/util/*.java -d classes

import fc.Cookie;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

public class CookieClientHandler implements Runnable {

    private Socket socket;
    private String fileName;

    public CookieClientHandler(Socket socket,String fileName) {
    
        this.socket = socket;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        String name  = Thread.currentThread().getName();
        Cookie cookie = new Cookie();
        List<String> generatedList = cookie.generateCookieList(fileName);
        while (true) {
            
            InputStream is;
            try {
                is = socket.getInputStream();
                DataInputStream dis = new DataInputStream(is);
                String clientInput = dis.readUTF();
                OutputStream os = socket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                
                if (clientInput.equals("get-cookie")){
                    String randomCookie = cookie.generateRandomCookie(generatedList);
                    String serverOutput = "cookie-text: " + randomCookie + " <<<" + name;
                    System.out.println(serverOutput);
                    dos.writeUTF(serverOutput);
                    dos.flush();
            
                } else if (clientInput.equals("close")) {
                    System.out.printf("Client has closed the connection. Thread[%s] closing...\n",name);
                    // socket.close();
                    // System.exit(-1);
                    return;
                } else {
                    System.out.println("Wrong command initiated by client\n");
                    dos.writeUTF("Thread["+name+"]>>>Invalid command...\n");
                    
                }
            } catch (IOException ex) {
            }
            

        }


        
       
    }
    
}
