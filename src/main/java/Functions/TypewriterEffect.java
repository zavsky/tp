package Functions;

public class TypewriterEffect {
    private static volatile boolean skipAnimation = false;

    public static void main(String[] args) {
        String paragraph = "This is an example of a typewriter effect, which pauses slightly longer on punctuation. "
                         + "It creates a cinematic effect... but you can press any key to skip it.";

        Thread inputThread = new Thread(() -> {
            try {
                System.in.read(); // Wait for any key press
                skipAnimation = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        inputThread.setDaemon(true);
        inputThread.start();

        print(paragraph);
    }

    /**
     * (Do not use)
     * Prints to console with Typewriter effect, and allows for skipping with any button press
     * 
     * @param text text to print with effect
     */
    public static void printWithInterrupt(String text) {
        skipAnimation = false;
        Thread inputThread = new Thread(() -> {
            try {
                System.in.read();
                skipAnimation = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        inputThread.setDaemon(true);
        inputThread.start();

        print(text);
    }

    /**
     * Prints to console with Typewriter effect, pausing longer at commas and full stops
     * 
     * @param text text to print with effect
     */
    public static void print(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (skipAnimation) {
                System.out.print(text.substring(i)); // Print the rest instantly
                break;
            }

            char ch = text.charAt(i);
            System.out.print(ch);
            System.out.flush();

            try {
                if (ch == '.' || ch == ',') {
                    Thread.sleep(300);
                } else if (ch == ' ') {
                    Thread.sleep(50);
                } else {
                    Thread.sleep(30);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println(); // Move to next line after printing
    }

    public static void print(String text, int endDelay) throws InterruptedException {
        print(text);
        Thread.sleep(endDelay);
    }
}
