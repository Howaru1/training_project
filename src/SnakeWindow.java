import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class SnakeWindow extends JPanel implements ActionListener {

    public static JFrame jFrame;
    public static final int SCALE = 32;
    public static final int WIDHT = 16;
    public static final int HEIGHT = 16;
    public static int speed = 10;
    Timer timer = new Timer(1000 / speed, this);
    Apple apple = new Apple(Math.abs((int)(Math.random() * SnakeWindow.WIDHT - 1)),
            Math.abs((int)(Math.random() * SnakeWindow.HEIGHT - 1)));
    Snake s = new Snake(5,6,5,5);
    public SnakeWindow() {
        timer.start();
        addKeyListener(new KeyBoard());
        setFocusable(true);
    }

    public void paint (Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(0,0, WIDHT * SCALE, HEIGHT * SCALE);
        for (int x = 0; x < WIDHT*SCALE; x += SCALE) {
            g.setColor(Color.gray);
            g.drawLine(x,0,x,HEIGHT * SCALE);
        }
        for (int y = 0; y < HEIGHT * SCALE; y += SCALE) {
            g.setColor(Color.gray);
            g.drawLine(0,y,WIDHT * SCALE, y);
        }
        g.setColor(Color.red);
        g.fillOval(apple.posX * SCALE + 1, apple.posY * SCALE + 1, SCALE - 1, SCALE - 1);
        for (int l = 0; l < s.length; l++) {
            g.setColor(Color.green);
            g.fillRect(s.sX[l] * SCALE + 1, s.sY[l] * SCALE + 1,SCALE - 1,SCALE - 1);
        }
    }

    public static void main(String[] args) {
        jFrame = new JFrame("Змея");
        jFrame.add(new SnakeWindow());
        jFrame.setSize(WIDHT * SCALE + 17, HEIGHT * SCALE + 8);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setIconImage(Toolkit.getDefaultToolkit().
                getImage("C:\\projects\\Snake\\src\\1696549248_gas-kvas-com-p-kartinki-zmeika-7.jpg"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        s.move();
        if ((s.sX[0] == apple.posX) && (s.sY[0] == apple.posY)) {
            apple.setRandomPosition();
            s.length++;
        }
        repaint();
    }

    public class KeyBoard extends KeyAdapter {
        public void KeyPressed(KeyEvent event) {
            int key = event.getKeyCode();
            if (key == KeyEvent.VK_UP) s.direction = 0;
            if (key == KeyEvent.VK_DOWN) s.direction = 2;
            if (key == KeyEvent.VK_LEFT) s.direction = 3;
            if (key == KeyEvent.VK_RIGHT) s.direction = 1;
        }
    }
}
