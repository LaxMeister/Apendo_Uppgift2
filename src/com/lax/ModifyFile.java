package com.lax;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModifyFile {

    public ModifyFile() {
    }
    /**Funktion som söker efter ett ord och hämtar alla meningar ordet förekommer i,
     * och sedan lägger till ett ord i meningarna efter ett antal ord som användaren har bestämt.
     * Detta sparas sedan till en ny fil.**/

    public void Modify(String inputWord, int wordInt) throws IOException {
        String stringForBufferReader;
        String[] stringArrayForSplit;
        List<String> listOfLines = new ArrayList<String>();
        File file = new File("textfil_apendo.text");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((stringForBufferReader = br.readLine()) != null) {
            stringArrayForSplit = stringForBufferReader.split(Pattern.quote("."));
            for (String words : stringArrayForSplit) {
                Pattern p = Pattern.compile(inputWord);
                Matcher m = p.matcher(words);
                while (m.find()) {
                    StringBuilder sb =  new StringBuilder(words);
                    sb.delete(0,1);
                    String cleanedString = sb.toString();
                    listOfLines.add(addWordToLine(cleanedString,wordInt));
                }
            }
        }

        Set<String> noDuplicatesOfLinesSets = new LinkedHashSet<String>(listOfLines);
        FileWriter writer = new FileWriter("output.txt");
        for(String loopString : noDuplicatesOfLinesSets){
            writer.write(loopString + System.lineSeparator());
        }
        writer.close();
        fr.close();
        System.out.println("File Written..");
    }
    /**Funktion som lägger till ett ord efter x-antal ord.
     * funktionen fungerar som så att den letar efter alla mellanslag i mening och sedan
     * sparar alla positioner för mellanslagen i en lista.
     * Listan används sedan till att lägga till ett ord i meningen efter
     * x-antal ord som användaren har angett.**/
    public static String addWordToLine(String line, int numofword) {
        int index = 0;
        List<Integer> indexOfWhite = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            if (Character.isWhitespace(line.charAt(i))) {
                indexOfWhite.add(line.indexOf(" ", i));
                index++;
            }
        }
        StringBuilder sb = new StringBuilder(line);
        if (numofword > indexOfWhite.size()) {
            sb.append(" Sundsvall");
            return sb.toString();
        } else {
            sb.insert(indexOfWhite.get(numofword - 1), " Sundsvall");
            return sb.toString();

        }





    }
}
