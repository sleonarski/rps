package com.orion.fox;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.*;

class CLInputProviderTest {

    @Test
    void shouldCorrectlyCollectInputData() {
        //given
        String expectedInput = "1";
        ByteArrayInputStream in = new ByteArrayInputStream(expectedInput.getBytes());
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        CLInputProvider clInputProvider = new CLInputProvider(reader);

        //when
        InputData userInput = clInputProvider.getInput();

        //then
        assertEquals(expectedInput, userInput.getData());
    }
}