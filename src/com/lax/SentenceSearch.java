package com.lax;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceSearch {
    public SentenceSearch() {
    }
    public static int totalParagrahs;

    /**funktion som hittar alla meningar där sökordet förkommer.
     Funktionen fungerar som så att den läser in filen och sedan delar upp den vid varje skiljetecken.
     Jag har i det här fallet valt att bara ha punkt som argument vid uppdelningen då det är bara punkter i denna text.
     Uppdelningen ligger i en loop som har en variabel som räknar varje var varv i loopen.
     Den variabeln har vi för att ta reda på total antalet meningar det finns i hela texten,
     som används sen vid uträkning av hur många meningar sökordet inte förekommer i.
     Sedan kollar vi om sökordet som vi har som argument för funktionen finns i någon
     av meningarna och om den gör det så lägger vi den meningen i en arraylista.
     sedan för att ta bort eventuella dubbletter så läggs arraylistan i en LinkedHashSet.
     Sedan körs ett funktionsanrop till funktionen som räknar ut hur många stycken sökordet
     finns inuti.
     Efter det så presenteras resultatet.
     **/
    public String FindSentences(String inputWord) throws IOException {
        String stringForBufferReader;
        String[] stringArrayForSplit;
        int count = 0;
        List<String> sentenceArrayList = new ArrayList<String>();
        File file = new File("textfil_apendo.text");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((stringForBufferReader = br.readLine()) != null) {
            stringArrayForSplit = stringForBufferReader.split(Pattern.quote("."));
            for (String words : stringArrayForSplit) {
                count++;
                Pattern p = Pattern.compile(inputWord);
                Matcher m = p.matcher(words);
                while (m.find()) {
                    sentenceArrayList.add(words);
                }
            }
        }
        Set<String> noDuplicatesSet = new LinkedHashSet<String>(sentenceArrayList);
        int wordOccursInParagraph = getParaCount(inputWord);
        int totalLines = count - totalParagrahs;
        String result = "The word : (" + inputWord +  ")  Occurs in " + noDuplicatesSet.size() + " lines, " + wordOccursInParagraph + " paragraphs and doesn't occur in " + (totalLines - noDuplicatesSet.size() + " lines" );
        fr.close();
        return result;
    }

    /** Funktion som räknar ut hur många stycken ordet förekommer i
     *  Funktionen fungerar liknade funktionen som raäknar meningar ovanför**/
    public static int getParaCount(String input) throws IOException {
        List<String> paragraphList = new ArrayList<String>();
        File file = new File("textfil_apendo.text");
        FileInputStream fileStream = new FileInputStream(file);
        byte[] byteArray = new byte[(int) file.length()];
        fileStream.read(byteArray);
        String data = new String(byteArray);
        String[] paragraphs = data.toString().split("\r\n\r\n");
        totalParagrahs = paragraphs.length;
        for(String paraString: paragraphs){
            Pattern p = Pattern.compile(input);
            Matcher m = p.matcher(paraString);
            while (m.find()) {
                paragraphList.add(paraString);
            }
        }
        Set<String> noDuplicatesOfParaSets = new LinkedHashSet<String>(paragraphList);
        return noDuplicatesOfParaSets.size();
    }
}
