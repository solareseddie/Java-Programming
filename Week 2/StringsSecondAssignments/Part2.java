/**
 * How Many - Finding Multiple Occurrences
 * 
 * @author (Eddie Solares) 
 */

public class Part2 {
    //Returns the number of times stringa appears in strinb
    public int howMany(String stringa, String stringb) { 
        //Initializes count and startIndex
        int count = 0;
        int startIndex = 0;
        //Loop that breaks once it cannot find no more stringa in stringb
        while (true) {
            //Sets currIndex to the index of stringa found in stringb after startIndex
            int currIndex = stringb.indexOf(stringa, startIndex);
            //Returns integer and then breaks once it cannot find any more
            if (currIndex == -1) { 
                return count;
            }
            //Updates count if found another stringa in strinb
            count = count + 1;
            //Updates startIndex to occur such that no overlap occurs
            startIndex = stringb.indexOf(stringa, startIndex) + stringa.length();
        }
    }
    //Tests the method howMany to ensure it is working
    public void testHowMany() {
        //Test Case 1: Valid with possibility for overlap
        String stringa = "AA";
        String stringb = "ATAAAAA";
        testHowManyPrint(stringa, stringb);
        //Test Case 2: Valid with no possibility of overlap
        stringa = "ss";
        stringb = "mississippi";
        testHowManyPrint(stringa, stringb);
        //Test Case 3: No matches found
        stringa = "banana";
        stringb = "oatmeal";
        testHowManyPrint(stringa, stringb);
    }
    //Submethod of method testHowMany
    public void testHowManyPrint(String stringa, String stringb) {
        int count = howMany(stringa, stringb);
        System.out.println("Count for " + stringa + " in " + stringb + " : " + count);
    }
}
