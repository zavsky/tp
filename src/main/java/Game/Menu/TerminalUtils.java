package Game.Menu;

import java.io.IOException;

public class TerminalUtils {
    public static void resetTerminal() throws IOException, InterruptedException {
        // Execute 'stty sane' to restore terminal defaults
        new ProcessBuilder("sh", "-c", "stty sane </dev/tty").start().waitFor();
    }
}
