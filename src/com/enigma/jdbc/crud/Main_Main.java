package com.enigma.jdbc.crud;
//BELAJAR COLLECTION


import java.lang.reflect.Array;
import java.util.*;

public class Main_Main {
    public static void main(String[] args) {
        Iterable <String> words =new ArrayList<>(List.of("Ati","Tai","Nuha","Nop")) ;
        Iterator<String> iterator = words.iterator();
        Collection<String> names =new ArrayList<>(  List.of("Ati","Tai","Nuha","Nop"));
        for (String word : words) {
            System.out.println(word);
        }
        names.add("Jamilah");
        names.addAll(List.of("Mudryk", "Benzema","Struick"));
        System.out.println("\nBY ITERATOR II");
        for (String name : names) {
            System.out.println(name);
        }
        names.remove("Andi");
        names.removeAll(List.of("Ati","Tai","Nuah","Benzema","Struickk")); // kalo typo yang gak ke hapus even itu beda kapital
        System.out.println("\nBY ITERATOR II after remove");
        for (String name : names) {
            System.out.println(name);
        }
        System.out.println("\nCEK EKSITENSI");
        System.out.println(
                names.containsAll(List.of("Nuha","Nop"))
        );
        System.out.println(
                names.containsAll(List.of("Nuha"))
        );
        System.out.println(
                names.contains("Jamilah")
        );
        System.out.println(names.size());
        System.out.println(names);

//        AbstractCollection<String> abstractCollection = new AbstractCollection<String>() { // saya cuman ingin tahu to string milik collection
//            @Override
//            public Iterator<String> iterator() {
//                return null;
//            }
//
//            @Override
//            public int size() {
//                return 0;
//            }
//        };
    }
}
