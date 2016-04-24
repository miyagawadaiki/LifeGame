import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LifeGame extends JFrame implements ActionListener, Runnable {
    GamePanel gpanel;
    JButton button1, button2, button3, button4;
    Thread runner;
    boolean running_flag = false;

    public static void main(String[] args) {
        new LifeGame().setVisible(true);
    }

    public LifeGame() {
        this.setSize(530,500);
        setBackground(Color.pink);

        setLayout(null);
        button1 = new JButton();
        button1.setText("Start");
        button1.setBounds(10,420,70,20);
        getContentPane().add(button1);

        button2 = new JButton();
        button2.setText("Stop");
        button2.setBounds(90,420,70,20);
        getContentPane().add(button2);

        button3 = new JButton();
        button3.setText("Step");
        button3.setBounds(170,420,70,20);
        getContentPane().add(button3);

        button4 = new JButton();
        button4.setText("Init");
        button4.setBounds(250,420,70,20);
        getContentPane().add(button4);

        gpanel = new GamePanel(100,80);
        gpanel.setBounds(10,10,500,400);
        getContentPane().add(gpanel);

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void startLG() {
        if(runner == null) {
            runner = new Thread(this);
            runner.start();
            running_flag = true;
        }
    }

    void stopLG() {
        if(running_flag == true) {
            running_flag = false;
        }
    }

    void stepLG() {
        gpanel.translate();
        gpanel.repaint();
    }

    void initLG() {
        stopLG();
        gpanel.initRand();
        gpanel.repaint();
    }

    public void actionPerformed(ActionEvent ev) {
        Object temp = ev.getSource();
        if(temp == button1)
            startLG();
        else if(temp == button2)
            stopLG();
        else if(temp == button3)
            stepLG();
        else
            initLG();
    }

    public void run() {
        while(running_flag == true) {
            stepLG();
            try {
                runner.sleep(100L);
            }
            catch(InterruptedException e) {}
        }
        runner = null;
    }
}
