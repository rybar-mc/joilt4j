package org.rybar.joilt4j;

public class Bootstrap {
    public static void main(String[] args) {
        Logger.standard()
                .info("Hello, world!");

        lol();

        try {
            throw new RuntimeException("Goodbye Exception!");
        } catch (RuntimeException e) {
            Logger.standard()
                    .error("Goodbye, world!", e);
        }
    }

    public static void lol() {
        Logger.standard()
                .info("Hello, world!");
    }
}
