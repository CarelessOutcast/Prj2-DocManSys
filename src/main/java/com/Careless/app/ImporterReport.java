package com.Careless.app;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static com.Careless.app.Attributes.BODY;
import static com.Careless.app.Attributes.PATIENT;
import static com.Careless.app.Attributes.TYPE;

public class ImporterReport implements ImporterI{
    private final static String NAME_PREFIX = "Patient: "; 

    public Document importItem(File file) throws IOException{

        final TextFile textFile = new TextFile(file);

        textFile.addLineSuffix(NAME_PREFIX, PATIENT);
        textFile.addLines(2, line->false, BODY);

        final Map<String,String> attributes = textFile.getAttributes();

        attributes.put(TYPE, "REPORT");

        return new Document(attributes);
    }
}
