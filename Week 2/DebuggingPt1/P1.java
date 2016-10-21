/**
 * Part 1 of Debugging
 * 
 * @author (Eddie Solares) 
 */

public class P1 {
    //For every abc_ it prints out bc_ where _ is any letter after the sequence abc
    public void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true) {
        if (index > input.length() - 4) { break; }
        if (index == -1) {
            break;
        }
        String found = input.substring(index+1, index+4);
        System.out.println(found);
        index = input.indexOf("abc", index+4);
        }
   }
   //Tests cases
   public void test() {
       //findAbc("abcd");
       findAbc("abcdabc");
   }
}
