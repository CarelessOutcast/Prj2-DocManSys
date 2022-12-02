package com.Careless.app;

import java.io.File;
import java.io.IOException;

public interface ImporterI {
    public Document importItem(File file) throws IOException;
}
