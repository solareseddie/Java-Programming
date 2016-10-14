/**
 * Problem Solving with Strings
 * 
 * @author (Eddie Solares) 
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb) {
        //Checks the first and last occurence of stringa in stringb
        int firstin = stringb.indexOf(stringa);
        int lastin = stringb.lastIndexOf(stringa);
        //If unequal, there is at least two occurences thus returning true
        if (firstin != lastin) {
            return true;
        } else { return false; }
    }
    public String lastPart(String stringa, String stringb) {
        //Checks the first occurence of stringa in stringb and length of stringa
        int firstin = stringb.indexOf(stringa);
        int len = stringa.length();
        //If occurence returns the last part of stringb after first occurence, else returns strinb
        if (firstin >= 0) {
            return stringb.substring(firstin + len);
        } else { return stringb; }
    }
    public void testing() {
        System.out.println("Now Testing...");
        //Test Case 1: True, twice
        String stringa = "an";
        String stringb = "Ready and Randy";
        boolean torf = twoOccurrences(stringa, stringb);
        String lastp = lastPart(stringa, stringb);
        System.out.println("The word " + "\"" + stringa + "\"" + " appears in " + "\"" + stringb + "\"" + " at least twice: " + torf);
        System.out.println("Last part of " + stringb + " after " + stringa + " is: " + lastp);
        //Test Case 2: True, many times
        stringa = "s";
        stringb = "mississippi";
        torf = twoOccurrences(stringa, stringb);
        lastp = lastPart(stringa, stringb);
        System.out.println("The word " + "\"" + stringa + "\"" + " appears in " + "\"" + stringb + "\"" + " at least twice: " + torf);
        System.out.println("Last part of " + stringb + " after " + stringa + " is: " + lastp);
        //Test Case 3: False, none
        stringa = "ok";
        stringb = "hello world";
        torf = twoOccurrences(stringa, stringb);
        lastp = lastPart(stringa, stringb);
        System.out.println("The word " + "\"" + stringa + "\"" + " appears in " + "\"" + stringb + "\"" + " at least twice: " + torf);
        System.out.println("Last part of " + stringb + " after " + stringa + " is: " + lastp);
        //Test Case 4: False, only once
        stringa = "gel";
        stringb = "gelatinous";
        torf = twoOccurrences(stringa, stringb);
        lastp = lastPart(stringa, stringb);
        System.out.println("The word " + "\"" + stringa + "\"" + " appears in " + "\"" + stringb + "\"" + " at least twice: " + torf);
        System.out.println("Last part of " + stringb + " after " + stringa + " is: " + lastp);
    }
}
