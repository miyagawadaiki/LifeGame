import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel {

    boolean[][] data, data_next;
    int dataWidth, dataHeight;
    Image offScreen;
    Graphics offg;

    public GamePanel() {
        this(25, 25);
    }

    public GamePanel(int w, int h) {
        dataWidth = w;
        dataHeight = h;
        data = new boolean[dataHeight][dataWidth];
        data_next = new boolean[dataHeight][dataWidth];
        setBackground(Color.black);
        setPreferredSize(new Dimension(dataWidth * 5, dataHeight * 5));
        initRand();
        repaint();
    }

    void initRand() {
        for(int i=0;i<dataHeight;i++) {
            for(int j=0;j<dataWidth;j++) {
                data[i][j] = (Math.random() < 0.5);
            }
        }
    }

    public void paint(Graphics g) {
        if(offScreen == null) {
            offScreen = createImage(dataWidth * 5, dataHeight * 5);
            offg = offScreen.getGraphics();
        }
        offg.setColor(Color.BLACK);
        offg.fillRect(0,0,dataWidth * 5, dataHeight * 5);
        offg.setColor(Color.GREEN);
        for(int i=0;i<dataHeight;i++) {
            for(int j=0;j<dataWidth;j++)
                if(data[i][j] == true)
                    offg.fillRect(j * 5, i * 5, 4, 4);
        }

        g.clearRect(0,0,getSize().width,getSize().height);
        g.drawImage(offScreen,0,0,this);
    }

    void translateCell(int y0, int x0) {
        int s = 0;
        for(int i=-1;i<=1;i++)
            for(int j=-1;j<=1;j++) {
                if(i==0 && j==0) continue;
                int x = x0 + j;
                int y = y0 + i;
                if(x == -1) x = dataWidth - 1;
                if(x == dataWidth) x = 0;
                if(y == -1) y = dataHeight - 1;
                if(y == dataHeight) y = 0;
                if(data[y][x] == true)
                    s++;
            }
        if(data[y0][x0] == true)
            data_next[y0][x0] = (s == 2 || s == 3);
        else
            data_next[y0][x0] = (s == 3);
    }

    public void translate() {
        for(int i=0;i<dataHeight;i++)
            for(int j=0;j<dataWidth;j++)
                translateCell(i,j);
        boolean[][] tmp = data;
        data = data_next;
        data_next = tmp;
    }
}
