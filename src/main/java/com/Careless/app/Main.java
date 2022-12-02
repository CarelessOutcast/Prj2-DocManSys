package com.Careless.app;

import java.util.HashMap;
import java.util.Map;

//Document Management System
public class Main {
    private static final Map<String,ImporterI> extensionToImporter = new HashMap<>();
public static void main(String[] args){
        extensionToImporter.put("letter",new ImporterLetter());
        extensionToImporter.put("report",new ImporterReport());
        extensionToImporter.put("image",new ImporterImage());
    }
}
