package com.Careless.app;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static com.Careless.app.Attributes.PATH;
import static java.util.stream.Collectors.toList;

class TextFile{
    private final Map<String,String> attributes;
    private final List<String> lines;

    Map<String,String> getAttributes(){
        return attributes;
    }

    TextFile(final File file) throws IOException{
        attributes = new HashMap<>();
        attributes.put(PATH, file.getPath());
        lines = Files.lines(file.toPath()).collect(toList());
    }
    int addLines(final int start, final Predicate<String> isEnd,final String attributeName){
        final StringBuilder accumulator = new StringBuilder();
        int lineNum;
        for(lineNum=start; lineNum < lines.size();lineNum++){
            final String line = lines.get(lineNum);
            if(isEnd.test(line)){
                break;
            }
            accumulator.append(line);
            accumulator.append("\n");
        }
        attributes.put(attributeName,accumulator.toString().trim());
        return lineNum; 
    }
    void addLineSuffix(final String prefix, final String attributeName){
        for(final String line: lines){
            if(line.startsWith(prefix)){
                attributes.put(attributeName, line.substring(prefix.length()));
                break;
            }
        }

    }

}

