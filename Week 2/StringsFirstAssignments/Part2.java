/**
 * Finding a Gene - Using the Simplified Algorithm Reorganized
 * 
 * @author (Eddie Solares) 
 */

public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        //Makes the dna string strictly uppercase
        String dna_up = dna.toUpperCase();
        //Finds the index position of the start codon "ATG"
        int startIndex = dna_up.indexOf(startCodon);
        //Finds the index position of the end codon "TAA"
        int endIndex = dna_up.indexOf(stopCodon);
        //Checks to see if it has the start codon and end codon
        if (startIndex == -1 && endIndex == -1) {
            return "no start and end codon";
        }
        else if (startIndex == -1) {
            return "no start codon";
        }
        else if (endIndex == -1) {
            return "no end codon";
        }
        //Checks to see if organized by codons and returns, else returns not valid
        if ((endIndex - startIndex) % 3 == 0) {
            return "Gene: " + dna.substring(startIndex, endIndex + 3);
        }
        else {
            return "not a valid gene";
        }
    }
    public void testSimpleGene() {
        String startCodon = "ATG";
        String stopCodon = "TAA";
        //Test Case 1: Valid
        String dna = "ATCATGTCGCCGGGATAACG";
        System.out.println("DNA strand is: " + dna);
        String gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println(gene);
        //Test Case 2: No Start Codon
        dna = "AATCGCCTGACTTAAACG";
        System.out.println("DNA strand is: " + dna);
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println(gene);
        //Test Case 3: No End Codon
        dna = "ATGCGCGGCTACGTA";
        System.out.println("DNA strand is: " + dna);
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println(gene);
        //Test Case 4: No Start and End Codon
        dna = "ACTGCGTACCGGTACGA";
        System.out.println("DNA strand is: " + dna);
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println(gene);
        //Test Case 5: Not A Gene
        dna = "AATGCGCCTGACTCGTAAACG";
        System.out.println("DNA strand is: " + dna);
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println(gene);
        //Test Case 6: Lowercase Valid
        dna = "atcatgtcgccgggataacg";
        System.out.println("DNA strand is: " + dna);
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println(gene);
    }
}
