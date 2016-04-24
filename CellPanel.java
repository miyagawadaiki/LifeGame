import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CellPanel extends JPanel {
    public CellPanel() {
        fill(false);
    }

    void fill(boolean isAlive) {
        if(isAlive)
            setBackground(Color.GREEN);
        else
            setBackground(Color.BLACK);
    }
}
