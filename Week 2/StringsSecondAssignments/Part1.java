/**
 * Finding Many Genes
 * 
 * @author (Eddie Solares) 
 */

public class Part1 {
    //Method to find the index of the stop codon, else length of dna
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        //Finds the index of the stop codon after the start index codon
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        //Loops while there exists a stop codon
        while (currIndex != -1) {
            int diff = currIndex - startIndex;
            //Checks to see if the index is a multiple of 3 for a gene else keeps checking
            if (diff % 3 == 0) {
                return currIndex;
            }
            else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        //If no stop codon exists returns the length of the string
        return dna.length();
    }
    //Method to find the first gene that occurs between the three stop codons, else empty string
    public String findGene(String dna, int where) {
        //Finds the index of start codon ATG after where
        int startIndex = dna.indexOf("ATG", where);
        //If no start codon ATG returns empty string
        if (startIndex == -1) {
            return "";
        }       
        //Finds the index of the first occurrence of the stop codons after start codon ATG
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        //Finds the first gene formed between the three stop codons
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        //If no gene found returns empty string
        if (minIndex == dna.length()) {
            return "";
        }
        //Returns the gene
        return dna.substring(startIndex, minIndex + 3);
    }
    //Method that prints all genes of a dna strand
    public void printAllGenes(String dna) {
        //Sets the startIndex to 0 and prints DNA string
        int startIndex = 0;
        System.out.println("DNA: " + dna);
        //Repeat until no genes found
        while (true) {
            //Find the next gene after startIndex
            String currentGene = findGene(dna, startIndex);
            //If no gene is found, exits loop
            if (currentGene.isEmpty()) { break; }
            //Prints the current gene
            System.out.println(currentGene);
            //Shifts the startIndex to past the end of the gene found in iteration
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }
    //Method to check to see if method findStopCodon working properly, else returns errors
    public void testFindStopCodon() {
        //Test cases to check validity of findStopCodon method
        //            012345678901234567890123456
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAyyy";
        int dex = findStopCodon(dna, 0, "TAA");
        if (dex != 9) System.out.println("Error: on 9");
        dex = findStopCodon(dna, 9, "TAA");
        if (dex != 21) System.out.println("Error: on 21");
        dex = findStopCodon(dna, 1, "TAA");
        if (dex != 27) System.out.println("Error: on 27");
        dex = findStopCodon(dna, 0, "TAG");
        if (dex != 27) System.out.println("Error: on 27 TAG");
        System.out.println("Tests Finished: Method findStopCodon working properly");
    }
    //Method to check to see if method findGene working properly
    public void testFindGene() {
        //Test Case 1: Valid Example, all stop codons
        String dna = "AAAATGGGGCCCTTTTAAATGTAGTGA";
        String gene = findGene(dna, 0);
        System.out.println("DNA: " + dna);
        System.out.println("Gene: " + gene);
        //Test Case 2: No Stop Codon
        dna = "AAATTTATGGGGCCCAAAGGG";
        gene = findGene(dna, 0);
        System.out.println("DNA: " + dna);
        System.out.println("Gene: " + gene);
        //Test Case 3: No Stop Codon multiple of 3
        dna = "AAATTTATGGGGCCCCTAAGGGTAGCTGA";
        gene = findGene(dna, 0);
        System.out.println("DNA: " + dna);
        System.out.println("Gene: " + gene);
        //Test Case 4: No Start Codon
        dna = "AAATTTGGGCCCAAATAAATGGGG";
        gene = findGene(dna, 0);
        System.out.println("DNA: " + dna);
        System.out.println("Gene: " + gene);
        //Test Case 5: Valid Example, one stop codon
        dna = "ATCATGGCTTAAAAA";
        gene = findGene(dna, 0);
        System.out.println("DNA: " + dna);
        System.out.println("Gene: " + gene);
    }
}
