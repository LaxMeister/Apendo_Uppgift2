package com.lax;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceSearch {
    public SentenceSearch() {
    }
    public static int totalParagrahs;

    public String FindStenences(String inputWord) throws IOException {
        String st;
        String[] stTest;
        int count = 0;
        int i = 0;
        List<String> l = new ArrayList<String>();
        File file = new File("textfil_apendo.text");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((st = br.readLine()) != null) {
            stTest = st.split(Pattern.quote("."));
            for (String words : stTest) {
                count++;
                Pattern p = Pattern.compile(inputWord);
                Matcher m = p.matcher(words);
                while (m.find()) {
                    l.add(words);
                    i++;
                }
            }
        }
        Set<String> s = new LinkedHashSet<String>(l);
        int wordOccursInParagraph = getParaCount(inputWord);
        int totalLines = count - totalParagrahs;
        String result = "The word : (" + inputWord +  ")  Occurs in " + s.size() + " lines, " + wordOccursInParagraph + " paragraphs and doesn't occur in " + (totalLines - s.size() + " lines" );
        fr.close();
        return result;
    }

    public static int getParaCount(String input) throws IOException {
        List<String> l = new ArrayList<String>();
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
                l.add(paraString);
            }
        }
        Set<String> s = new LinkedHashSet<String>(l);
        return s.size();
    }
}
