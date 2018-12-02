package be.amedee.adventofcode;

public class App {

    public static final String GREETING = "Hello world.";

    public String getGreeting() {
        return GREETING;
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
    }
}
