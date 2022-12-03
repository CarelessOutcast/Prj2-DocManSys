package com.Careless.app;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static com.Careless.app.Attributes.*;

public class ImporterLetter implements ImporterI{
    private static final String NAME_PREFIX = "Dear ";

    public Document importItem(File file) throws IOException {
        final TextFile textFile = new TextFile(file);
        textFile.addLineSuffix(NAME_PREFIX, PATIENT);

        final int lineNum = textFile.addLines(2, String::isEmpty,ADDRESS);
        textFile.addLines(lineNum+1, (line)->line.startsWith("regards,"), BODY);

        final Map<String,String> attributes = textFile.getAttributes();
        attributes.put(TYPE,"LETTER");
        return new Document(attributes);

    }
}
