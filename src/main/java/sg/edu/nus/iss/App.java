package sg.edu.nus.iss;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) throws IOException {
        Integer port = Integer.parseInt(args[0]);
        String fileName = args[1]; //filename needs to include path directory

        File cookieFile = new File(fileName);
        if (!cookieFile.exists()) {
            System.out.println("Cookie file not found.");
            System.exit(0);
        }
        
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        ServerSocket server = new ServerSocket(port);
                
        while(true){
            Socket socket = server.accept();

            Runnable r = new CookieClientHandler(server, socket, fileName);

            threadPool.submit(r);

            //another way to start thread
            new Thread(r).start();
        }

        // Day04 working code
        // try (InputStream is = socket.getInputStream()) {
        //     BufferedInputStream bis = new BufferedInputStream(is);
        //     DataInputStream dis = new DataInputStream(bis);

        //     try (OutputStream os = socket.getOutputStream()) {
        //         BufferedOutputStream bos = new BufferedOutputStream(os);
        //         DataOutputStream dos = new DataOutputStream(bos);

        //         while (!line.equals("close")) {
        //             line = dis.readUTF();

        //             if (line.equals("get-cookie")) {
        //                 System.out.println("success");
        //                 Cookie cookie = new Cookie();
        //                 cookie.readCookieFile(fileName);

        //                 //send out using DataOutputStream
        //                 dos.writeUTF(cookie.getRandomCookie());
        //                 dos.flush();
        //             }
        //         }

        //         dos.close();
        //         bos.close();
        //         os.close();

        //     } catch (EOFException e) {
        //         e.printStackTrace();
        //         System.out.println("error here");
        //     }

        //     dis.close();
        //     bis.close();
        //     is.close();

        // } catch (EOFException e) {
        //     e.printStackTrace();
        //     socket.close();
        //     server.close();
        // }

    }
}
