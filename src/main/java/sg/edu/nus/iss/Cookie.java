package sg.edu.nus.iss;

import java.io.*;
import java.util.*;

public class Cookie {
    List<String> cookieList = null;
    
    public void readCookieFile(String fileName) throws IOException{
        cookieList = new ArrayList<>();

        File file = new File(fileName);

        if(file.exists()){
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";

            while((line = br.readLine()) != null){
                cookieList.add(line);
            }

            br.close();
            fr.close();
        } else throw new FileNotFoundException();

    }

    public String getRandomCookie() throws IOException{
        if(cookieList != null){
            int min = 0;
            int max = cookieList.size() - 1;
            int range =  max + min - 1;
            int rand = (int)(Math.random() * range) + min;
            return cookieList.get(rand);
        } else throw new FileNotFoundException();
    }
}

