import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel {

    boolean[][] data, data_next;
    CellPanel[][] board;
    int dataWidth, dataHeight;

    public GamePanel() {
        this(25, 25);
    }

    public GamePanel(int w, int h) {
        dataWidth = w;
        dataHeight = h;
        data = new boolean[dataHeight][dataWidth];
        data_next = new boolean[dataHeight][dataWidth];
        board = new CellPanel[dataHeight][dataWidth];
        setBackground(Color.black);
        setPreferredSize(new Dimension(dataWidth * 5, dataHeight * 5));
        setLayout(new GridLayout(dataHeight,dataWidth,1,1));
        for(int i=0;i<dataHeight;i++) {
            for(int j=0;j<dataWidth;j++) {
                board[i][j] = new CellPanel();
                add(board[i][j]);
            }
        }
        initRand();
        update();
    }

    void initRand() {
        for(int i=0;i<dataHeight;i++) {
            for(int j=0;j<dataWidth;j++) {
                data[i][j] = (Math.random() < 0.5);
            }
        }
    }

    void update() {
        for(int i=0;i<dataHeight;i++) {
            for(int j=0;j<dataWidth;j++) {
                board[i][j].fill(data[i][j]);
            }
        }
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
