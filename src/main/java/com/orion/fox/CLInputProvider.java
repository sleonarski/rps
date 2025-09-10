package com.orion.fox;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;

@Service
class CLInputProvider implements InputProvider {

    private final BufferedReader reader;

    public CLInputProvider(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public InputData getInput() {
        try {
            return new InputData(reader.readLine());

        } catch (IOException e) {
            //TODO use Logger instead
            System.out.println("Error during input collecting");
            return null;
        }
    }
}
