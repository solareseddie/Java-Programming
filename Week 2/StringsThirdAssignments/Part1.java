
/**
 * Storage Resource Part 1-3
 * 
 * @author (Eddie Solares) 
 */

import edu.duke.*;

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
    //Stores all the genes into a Storage Resource 
    public StorageResource getAllGenes(String dna) {
        //Creates an empty StorageResource
        StorageResource geneList = new StorageResource();
        //Sets the startIndex to 0
        int startIndex = 0;
        //Repeat until no genes found
        while (true) {
            //Find the next gene after startIndex
            String currentGene = findGene(dna, startIndex);
            //If no gene is found, exits loop
            if (currentGene.isEmpty()) { break; }
            //Adds gene to geneList storage resource
            geneList.add(currentGene);
            //Shifts the startIndex to past the end of the gene found in iteration
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        //Returns the geneList
        return geneList;
        
    }
    //Returns a ratio of number of "C"'s and "G"'s to string length
    public float cgRatio(String dna) {
        //Sets counts and indices for both C and G
        int count = 0;
        int gindex = 0;
        int cindex = 0;
        //Counts and adds for every G in dna
        while (gindex != -1) {
            int currg = dna.indexOf("G", gindex);
            if (currg == -1) { break;}
            count = count + 1;
            gindex = currg + 1;
        }
        //Counts and adds for every C in dna
        while (cindex != -1) {
            int currc = dna.indexOf("C", cindex);
            if (currc == -1) { break;}
            count = count + 1;
            cindex = currc + 1;
        }
        //Converts to float the ratio and returns
        float ratio = (float) count / dna.length();
        return ratio;
    }
    //Counts the amount of CTG in dna
    public int countCTG(String dna) { 
        //Sets count and index
        int count = 0;
        int index = 0;
        //Loops and counts all the cases of CTG in the dna 
        while (index != -1) {
            int currg = dna.indexOf("CTG", index);
            if (currg == -1) { break;}
            count = count + 1;
            index = currg + 3;
        }
        //Returns the count
        return count;
    }
    //Processes the list of for various methods
    public void processGenes(StorageResource sr) { 
        int countn = 0; 
        int countcg = 0;
        String lrgstr = "";
        StorageResource sumlist = new StorageResource();
        System.out.println("Printing strings longer than 60");
        for (String s: sr.data()) {
            if (s.length() > 60) { 
                System.out.println(s);
                countn = countn + 1;
            }
        }
        System.out.println("The number of strings bigger than 60 is: " + countn);
        System.out.println("Printing strings with C-G ratio higher than 0.35");
        for (String s: sr.data()) {
            if (cgRatio(s) > 0.35) { 
                System.out.println(s);
                countcg = countcg + 1;
            }
        }
        System.out.println("The number of strings with C-G ratio higher than 0.35 is: " + countcg);
        for (String s: sr.data()) { 
            StorageResource geneList = getAllGenes(s);
            for (String k: geneList.data()) { 
                sumlist.add(k);
            }
        }
        for (String s: sumlist.data()) {
            if (s.length() > lrgstr.length()) { lrgstr = s;}
        }
        System.out.println("The largest gene is: " + lrgstr);
        System.out.println("Length: " + lrgstr.length());
    }
    //Method to test to see if the method processGenes is valid
    public void testProcessGenes() {
        //StorageResource sr = new StorageResource();
        //sr.add("ATGCCCCCCCCCCCCTAAG");
        //sr.add("ATGTAG");
        //sr.add("CGCGCGATGTAACGCGCG");
        //sr.add("CGATGCCCGGGTGAGTA");
        //sr.add("ATGTTTTTTTTTTTTTTTTAA");
        //processGenes(sr);
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase();
        StorageResource geneList = getAllGenes(dna);
        System.out.println(geneList.size());
        processGenes(geneList);
        System.out.println(countCTG(dna));
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
