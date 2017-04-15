package lab6;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author Jacob Ragains
 */
public class Assignment4 {

    public static Long wordF = 0L;
    public static Long wordNF = 0L;
    public static Long compsF = 0L;
    public static Long compsNF = 0L;

    public static void main(String[] args) {
        MyLinkedList[] list = new MyLinkedList[26];
        for (int i = 0; i < list.length; i++) {
            list[i] = new MyLinkedList<String>();
        }
        Assignment4 l = new Assignment4();
        File f = new File("./src/lab6/random_dictionary.txt");
        l.sort(f, list);
        File e = new File("./src/lab6/oliver.txt");
        l.read(e, list);
        System.out.println("Words found: "+ wordF);
        System.out.println("Words not found: "+ wordNF);
        Long total=wordF+wordNF;
        System.out.println("Total words: "+ total);
        System.out.println("Searches per words found: "+ compsF/wordF);
        System.out.println("Searches per words not found: "+ compsNF/wordNF);
        Long t2= compsF+compsNF;
        System.out.println("Total searches: "+ total);
        System.out.println("Total searches per word: "+ t2/total );
    }

    public void sort(File f, MyLinkedList[] l) {
        String w;
        try{ 
            Scanner input = new Scanner(f); 
            while (input.hasNext()) {
                w = input.next();
                w = w.toLowerCase();
                l[w.charAt(0) - 97].add(w);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
    }

    public void read(File f, MyLinkedList[] l) {
        try {
            Scanner input = new Scanner(f);
            String s;
            String delims = "[ .,!?\"*#():\\]\\[\\-;]+";
            while (input.hasNext()) {
                s = input.next();
                s = s.toLowerCase();
                String[] parts = s.split(delims);
                s = "";
                if (parts.length == 0) {
                    s = "*";
                }
                if (parts.length == 1) {
                    s = parts[0];
                } else {
                    for (int i = 0; i < parts.length; i++) {
                        s = s + parts[i];
                    }
                }

                if (s.charAt(0) - 97 >= 0 && s.charAt(0) - 97 <= 26) {
                    l[s.charAt(0) - 97].contains(s);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
    }
}
