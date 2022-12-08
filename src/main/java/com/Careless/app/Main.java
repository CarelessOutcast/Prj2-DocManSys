package com.Careless.app;

import java.io.IOException;

public class Main{
    public static final String RESOURCES = "src/main/resources/";
    public static void main(String[] args) throws IOException{
        
            DocumentManagementSystem system = new DocumentManagementSystem();

            system.importFile(RESOURCES + "Carlos.letter");
            system.importFile(RESOURCES + "Carlos.invoice");
            system.importFile(RESOURCES + "Carlos.report");
    
            System.out.println(system.contents());
            System.out.println(system.search("patient:Carlos Tapia,body:mules"));
            // system.printAttributes();
        

        }
}
