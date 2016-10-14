/**
 * Finding Web Links
 * 
 * @author (Eddie Solares) 
 */

import edu.duke.*;

public class Part4 {
    public void youtubeurl() {
        System.out.println("Looking for youtube links...");
        URLResource doc = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String word: doc.words()) {
            String word_lc = word.toLowerCase();
            if (word_lc.contains("http://www.youtube.com")) { 
                int start = word.indexOf("\"");
                int end = word.lastIndexOf("\"");
                System.out.println(word.substring(start + 1, end)); 
            }
        }
    }
}
