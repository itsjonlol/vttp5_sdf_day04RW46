package fc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cookie {
    //read the cookie file
    //put the contents of the file into a list<string> for the cookies: a function
    //create a function to obtain a cookie from the list
    //-> ensure that the cookie obtained is random
    //-> create a random index generator

    private List<String> cookiesList;

    public List<String> generateCookieList(String fileName) {

        cookiesList = new ArrayList<>();
        try {
            Reader reader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                cookiesList.add(line);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return cookiesList;
    }
    public String generateRandomCookie(List<String> cookiesList) {
        System.out.println("Generating a cookie...");
        Random rnd = new Random();
        int randomIndex = rnd.nextInt(cookiesList.size());
        System.out.println("Random index is " + randomIndex);
        String randomCookie = cookiesList.get(randomIndex);
        return randomCookie;
    }


    
}
