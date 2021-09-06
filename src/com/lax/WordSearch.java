package com.lax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WordSearch {

    public static int count;

    public WordSearch() {
    }

    /**Funktion som hittar förekomsten av ett sökord i en text.
     * Funktionen fungerar som så att den tar in ett sökord och en bool
     * som bestämmer om du vill ha det skiftlägeskänsligt.
     * Först läser den in textfilen som du vill söka i sedan kollar den vad du
     * har svarat i bool true eller false.
     * Har du svarat true så går den vidare till en loop och tar bort alla skiljetecken i texten.
     * Efter att skiljetecken är borta så skickas texten och sökordet
     * till en funktion som söker igenom texten baserat på mönster, där sökordet är mönstret.
     * Tillbaka från den funktionen får du antal gånger ordet förekommer i texten och det
     * resultatet presenteras sedan.
     * **/
    public String FindWord(String inputWord, boolean caseSensitive) throws IOException {
        File file = new File("textfil_apendo.text");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String stringForBufferReader;
        String stringForRemovedPeriods;
        String ifCasesensitive = inputWord;

        if (!caseSensitive) {
            ifCasesensitive = ifCasesensitive.substring(0, 1).toUpperCase() + ifCasesensitive.substring(1);
        }

        while ((stringForBufferReader = br.readLine()) != null) {
            stringForRemovedPeriods = stringForBufferReader.replaceAll("\\p{Punct}", "");
            search(stringForRemovedPeriods, inputWord);
            if(!caseSensitive){
                search(stringForRemovedPeriods,ifCasesensitive);
            }
        }
        fr.close();

        if (count != 0)
        {
            String sucessResult = "The given word ( " + inputWord + " ) is present for " + count + " times in the file";
            return sucessResult;
        } else {
           String noResult ="The given word (" + inputWord + ") is not present in the file";
           return noResult;
        }

    }

    /** Funktion som söker på mönster.
     * Den tar in en text och ett ord och sedan kollar den efter ordet i texten.
     * Den använder ordet som mönster att söka i texten.
     **/
    public static void search(String txt, String pat) {
        int M = pat.length();
        int N = txt.length();

        for (int i = 0; i <= N - M; i++) {
            int j;

            for (j = 0; j < M; j++)
                if (txt.charAt(i + j) != pat.charAt(j))
                    break;
            if (j == M)
                count++;
        }
    }

}
