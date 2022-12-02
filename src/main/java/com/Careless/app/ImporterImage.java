package com.Careless.app;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ImporterImage implements ImporterI{
    public static final String PATH="path";
    public static final String WIDTH="width";
    public static final String HEIGHT="height";
    public static final String TYPE="type";

    @Override
    public Document importItem(final File file) throws IOException{
        final Map<String,String> attributes = new HashMap<>();
        attributes.put(PATH, file.getPath());

        final BufferedImage image = ImageIO.read(file);
        attributes.put(WIDTH, String.valueOf(image.getWidth()));
        attributes.put(HEIGHT, String.valueOf(image.getHeight()));
        attributes.put(TYPE, "IMAGE");

        return new Document(attributes);
    }
}
    
