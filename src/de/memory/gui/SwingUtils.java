package de.memory.gui;

import javax.swing.SwingUtilities;

/**
 * Class copied from author
 * @author de.unibw.inf2.sweu.timer.gui author
 *
 */
class SwingUtils {
    static void invokeAndWait(Runnable task) {
        if (SwingUtilities.isEventDispatchThread()) {
            task.run();
        }
        else {
            try {
                SwingUtilities.invokeAndWait(task);
            }
            catch (Exception ex) {
                ex.printStackTrace();
                ex.getLocalizedMessage();
            }
        }
    }

    public static void invokeLater(Runnable task) {
        SwingUtilities.invokeLater(task);
    }
}
