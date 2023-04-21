package sg.edu.nus.iss;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class CookieClientHandler implements Runnable{

    public CookieClientHandler (ServerSocket server, Socket socket, String fileName) throws IOException {
        
        String line = "";

        try (InputStream is = socket.getInputStream()) {
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);

            try (OutputStream os = socket.getOutputStream()) {
                BufferedOutputStream bos = new BufferedOutputStream(os);
                DataOutputStream dos = new DataOutputStream(bos);

                while (!line.equals("close")) {
                    line = dis.readUTF();

                    if (line.equals("get-cookie")) {
                        System.out.println("success");
                        Cookie cookie = new Cookie();
                        cookie.readCookieFile(fileName);

                        //send out using DataOutputStream
                        dos.writeUTF(cookie.getRandomCookie());
                        dos.flush();
                    }
                }

                dos.close();
                bos.close();
                os.close();

            } catch (EOFException e) {
                e.printStackTrace();
                System.out.println("error here");
            }

            dis.close();
            bis.close();
            is.close();

        } catch (EOFException e) {
            e.printStackTrace();
            socket.close();
            server.close();
        }
        
    }
        
    public void run(){
    }
    
}