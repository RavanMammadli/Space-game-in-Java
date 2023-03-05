import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class Ates
{
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Ates(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class Oyun extends JPanel implements KeyListener, ActionListener {

    public int time = 0;
    public int fire = 0;
    public BufferedImage image;
    public ArrayList<Ates> atesler = new ArrayList<Ates>();
    Timer timer = new Timer(5 , this);
    public int atesdirY = 1;
    public int topX = 0;
    public int topdurX = 2;
    public int uzaygemisiX = 0;
    public int diruzayX = 20;

    public boolean check()
    {
        for (Ates ates : atesler){
        if(new Rectangle(ates.getX() , ates.getY() , 10 ,15).intersects(new Rectangle(topX , 0 , 20 , 20)))
            return true;
    }return false;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        time += 5;
        g.setColor(Color.red);
        g.fillOval(topX , 0 , 20 ,20);
        g.drawImage(image,uzaygemisiX , 490 , image.getWidth() / 20 , image.getHeight() / 20 , this);
        for (Ates ates: atesler)
            if (ates.getY() < 0)
                atesler.remove(ates);

        g.setColor(Color.blue);
        for (Ates ates: atesler)
        {
            g.fillRect(ates.getX() , ates.getY() , 5 , 25);
        }
        if(check())
        {
            timer.stop();
            String message = "You are winner!!!!!\n" + "Number of Fire : " + fire + "\nTime : " + time / 1000.0 + " second";
            JOptionPane.showMessageDialog(null , message);
            System.exit(0);

        }

    }


    @Override
    public void repaint() {
        super.repaint();

    }

    public Oyun() {
        try {
            image = ImageIO.read(new FileImageInputStream(new File("src/rocket.img.png")));
        } catch (IOException e) {

        }
        setBackground(Color.black);

        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Ates ates : atesler)
        {
            ates.setY(ates.getY() - atesdirY);
        }
        topX += topdurX;
        if (topX >= 745)
            topdurX = -topdurX;
        if (topX <= 0)
            topdurX = -topdurX;
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if (c == KeyEvent.VK_LEFT)
            if (uzaygemisiX <= 0)
            uzaygemisiX = 0;
            else
                uzaygemisiX -= diruzayX;
        else if (c == KeyEvent.VK_RIGHT)
            if (uzaygemisiX >= 710)
            uzaygemisiX = 710;
            else
                uzaygemisiX += diruzayX;
        else if (c == KeyEvent.VK_CONTROL) {
            atesler.add(new Ates(uzaygemisiX + 20, 470));
                    fire++;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
