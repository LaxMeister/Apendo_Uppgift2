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

    public void Modify(String inputWord) throws IOException {


        String st;
        String[] stTest;
        List<String> l = new ArrayList<String>();
        File file = new File("textfil_apendo.text");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((st = br.readLine()) != null) {
            stTest = st.split(Pattern.quote("."));
            for (String words : stTest) {

                Pattern p = Pattern.compile(inputWord);
                Matcher m = p.matcher(words);
                while (m.find()) {
//                    System.out.println(words + " " + i);
                    StringBuilder sb =  new StringBuilder(words);
                    sb.delete(0,1);
                    String cleanedString = sb.toString();
                    l.add(cleanedString);

                }
            }
        }

        for(int i = 0; i < l.size(); i++ ){
            System.out.println(l.get(i));
        }

//        String bosse = l.get(1);
//        StringBuilder sb =  new StringBuilder(bosse);
//        sb.delete(0,1);
//        bosse = sb.toString();
//        l.add(1,bosse);
//        System.out.println(l.get(0));



        Set<String> s = new LinkedHashSet<String>(l);
        System.out.println(s.size());


    }
}
