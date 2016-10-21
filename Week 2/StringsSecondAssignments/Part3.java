/**
 * How Many Genes?
 * 
 * @author (Eddie Solares) 
 */

public class Part3 {
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
    //Method that counts all genes of a dna strand
    public int countGenes(String dna) {
        //Sets the startIndex to 0 and prints DNA string
        int startIndex = 0;
        int count = 0;
        //Repeat until no genes found
        while (true) {
            //Find the next gene after startIndex
            String currentGene = findGene(dna, startIndex);
            //If no gene is found, exits loop
            if (currentGene.isEmpty()) { return count; }
            //Updates the count if a new gene found
            count = count + 1;
            //Shifts the startIndex to past the end of the gene found in iteration
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }
    //Method that tests the method countGenes for several different genes
    public void testCountGenes() {
        //Test 1: Valid with two genes
        String dna = "ATGTAAGATGCCCTAGT";
        int count = countGenes(dna);
        System.out.println("Genes in " + dna + " : " + count);
        //Test 2: Not valid with no genes and start codon
        dna = "ATGTTTCCCGGG";
        count = countGenes(dna);
        System.out.println("Genes in " + dna + " : " + count);
        //Test 3: Not valid with no genes and no start or stop codon
        dna = "AAATTTCCCGGG";
        count = countGenes(dna);
        System.out.println("Genes in " + dna + " : " + count);
        //Test 4: Not valid with no mod 3 genes
        dna = "AAATTTCTAACTAGCGGG";
        count = countGenes(dna);
        System.out.println("Genes in " + dna + " : " + count);
        //Test 5: Not valid empty string
        dna = "";
        count = countGenes(dna);
        System.out.println("Genes in " + dna + " : " + count);
    }
}
