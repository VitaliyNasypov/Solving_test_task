package ru.solution;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class RunTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void changeOutputStream() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void shouldSuccessfullyProcessFirstSetData() {
        String inputFirst = "5 3\n12 96 6 34 73";
        InputStream is = new ByteArrayInputStream(inputFirst.getBytes());
        System.setIn(is);
        Run.main(new String[0]);
        Assertions.assertEquals("90\r\n1 4 5", outputStreamCaptor.toString().trim());
    }

    @Test
    public void shouldSuccessfullyProcessSecondSetData() {
        String inputSecond = "4 1\n40 80 50 60";
        InputStream is = new ByteArrayInputStream(inputSecond.getBytes());
        System.setIn(is);
        Run.main(new String[0]);
        Assertions.assertEquals("20\r\n3", outputStreamCaptor.toString().trim());
    }

    @Test
    public void shouldSuccessfullyProcessThirdSetData() {
        String inputThird = "3 1\n2 2 2";
        InputStream is = new ByteArrayInputStream(inputThird.getBytes());
        System.setIn(is);
        Run.main(new String[0]);
        Assertions.assertEquals("0\r\n3", outputStreamCaptor.toString().trim());
    }

    @Test
    public void shouldSuccessfullyProcessFourthSetData() {
        String inputThird = "3 1\n1 2 3";
        InputStream is = new ByteArrayInputStream(inputThird.getBytes());
        System.setIn(is);
        Run.main(new String[0]);
        Assertions.assertEquals("2\r\n2", outputStreamCaptor.toString().trim());
    }

    @Test
    public void shouldSuccessfullyProcessFifthSetData() {
        String inputThird = "3 1\n1 2 2";
        InputStream is = new ByteArrayInputStream(inputThird.getBytes());
        System.setIn(is);
        Run.main(new String[0]);
        Assertions.assertEquals("1\r\n3", outputStreamCaptor.toString().trim());
    }

    @Test
    public void shouldSuccessfullyProcessSixthSetData() {
        String inputThird = "5 1\n1 4 7 7 10";
        InputStream is = new ByteArrayInputStream(inputThird.getBytes());
        System.setIn(is);
        Run.main(new String[0]);
        Assertions.assertEquals("3\r\n4", outputStreamCaptor.toString().trim());
    }

    @Test
    public void shouldCheckIncorrectInputData() {
        String inputThird = " \n ";
        InputStream is = new ByteArrayInputStream(inputThird.getBytes());
        System.setIn(is);
        Run.main(new String[0]);
        Assertions.assertEquals("-1\r\n-1", outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void recoveryOutputStream() {
        System.setOut(standardOut);
    }
}
