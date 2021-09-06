package com.lax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WordSearch {

    public static int count;

    public WordSearch() {
    }

    public String FindWord(String inputWord, boolean caseSensitive) throws IOException {

        File file = new File("textfil_apendo.text");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        String stTest;
        String ifCasesensitive = inputWord;

        if (!caseSensitive) {
            ifCasesensitive = ifCasesensitive.substring(0, 1).toUpperCase() + ifCasesensitive.substring(1);
        }

        while ((st = br.readLine()) != null) {
            String st2 = st.replaceAll("\\p{Punct}", "");
            stTest = st2;
            search(stTest, inputWord);
            if(!caseSensitive){
                search(stTest,ifCasesensitive);
            }
        }
        fr.close();

        if (count != 0)  //Check for count not equal to zero
        {
            String sucessResult = "The given word( " + inputWord + " ) is present for " + count + " times in the file";
            return sucessResult;
        } else {
           String noResult ="The given word (" + inputWord + ") is not present in the file";
           return noResult;
        }

    }

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
