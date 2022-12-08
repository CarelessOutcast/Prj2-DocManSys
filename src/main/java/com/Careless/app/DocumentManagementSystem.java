package com.Careless.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;

//Document Management System
public class DocumentManagementSystem {
    private final List<Document> documents = new ArrayList<>();
    private final List<Document> documentView = unmodifiableList(documents);
    private final Map<String,ImporterI> extensionToImporter = new HashMap<>();

//Constructor w/ supported extensions
    public DocumentManagementSystem(){

            extensionToImporter.put("letter",new ImporterLetter());
            extensionToImporter.put("report",new ImporterReport());
            extensionToImporter.put("image",new ImporterImage());
            extensionToImporter.put("invoice",new ImporterInvoice());

        }

//Import file function
    public void importFile(final String path)throws IOException{
        final File file = new File(path);
        if(!file.exists()){
            throw new FileNotFoundException();
        }

        final int seperatorIndex = path.lastIndexOf('.');

        if(seperatorIndex != -1){
            if(seperatorIndex==path.length()){
                throw new UnknownFileTypeException("No extension found for file: "+path);
            }

            final String extension = path.substring(seperatorIndex+1);

            final ImporterI importer = extensionToImporter.get(extension);

            if (importer == null){
                throw new UnknownFileTypeException("For file: "+path);
            }

            final Document document = importer.importItem(file);
            documents.add(document);

        }else {
            throw new UnknownFileTypeException("No extension found for file: "+path);
        }
        }

// get Documents of from the system
    public List<Document> contents(){
        return documentView;
    }
    
// //print the attributes in document
//     public void printAttributes(){
//         for(Document doc: documents){
//                 doc.printAttributes();
//                 }
//     }

// search through the files that are in the system.
    public List<Document>search (final String query){
        return documents.stream()
                        .filter(Query.parse(query))
                        .collect(Collectors.toList());
    }

}
