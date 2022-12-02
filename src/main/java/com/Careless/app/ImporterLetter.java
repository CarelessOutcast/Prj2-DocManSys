package com.Careless.app;

import java.io.File;
import java.util.Map;
import java.util.HashMap;

public class ImporterLetter implements ImporterI{

    public Document importItem(File file){
        final Map<String,String> attributes = new HashMap<>();
        return new Document(attributes);
    }
}
