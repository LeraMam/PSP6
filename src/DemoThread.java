/*Изображение домика с окном и трубой.
При нажатии на кнопку «Старт» в окне загорается свет,
а из трубы начинает идти дым.*/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;
import javax.swing.*;

public class DemoThread extends JFrame{
    private static BufferedImage buffImg1;
    private static Image background;
    private static Image lightHouse;
    private static Image smoke;
    private static Image snowflake;
    JButton button;
    private int timeFalling = 150;
    private static int lightHouseHeight = 0, lightHouseWidth = 0;
    private static int smokeWidth = 0, smokeHeight = 0, smokeBank = 460, smokeBankY = 250;
    private static int snowflakeX = 200, snowflakeY = 120, snowflakeX1 = 500, snowflakeY1 = 505, snowflakeX2 = 550, snowflakeY2 = 505,snowflakeX3 = 640, snowflakeY3 = 415, snowflakeX4 = 752, snowflakeY4 = 420,
    snowflakeX5 = 670, snowflakeY5 = 385,  snowflakeX6 = 480, snowflakeY6 = 5, snowflakeX7 = 10, snowflakeY7 = 5, snowflakeX8 = 900, snowflakeY8 = 30, snowflakeX9 = 50, snowflakeY9 = 50;

    public static void main(String[] args) throws IOException {
        new DemoThread().setVisible(true);
    }
    public DemoThread() {
        setTitle("Demo app");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        setContentPane(new Background()); // панель устанавливается как contentPane в наследнике JFrame
        Container content = getContentPane(); //теперь все элементы интерфейса будут на этой панели.

        button = new JButton("Нажмите для анимации");
        button.setBackground(Color.white);
        button.setForeground(Color.BLACK);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button.setVisible(false);
                Thread smokeMove = new Thread(new SmokeThread());
                smokeMove.start();
                Thread snowFlake = new Thread(new SnowFlakeThread());
                snowFlake.start();
            }
        });
        add(button);
        content.add(button);
        content.add(new SmokeMake());
    }

    private static class Background extends JPanel{ // отрисовка нового фона
        @Override
        protected void paintComponent(Graphics g){
            int newWidth = 1000; // Новая ширина изображения
            int newHeight = 400;
            super.paintComponent(g);
            try {
                background = ImageIO.read(new File("Фон.png"));
                Image scaledImage = background.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                buffImg1 = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = buffImg1.createGraphics();
                g2d.drawImage(scaledImage, 0, 0, null);
                g2d.dispose();
            } catch (IOException e) {e.printStackTrace();}
            g.drawImage(background,0,0,null);
        }
    }

    private class SmokeMake extends JPanel{

        public SmokeMake() {
            setOpaque(false);
            setPreferredSize(new Dimension(1000, 600));
            try{
                lightHouse = ImageIO.read(new File("Фон со светом1.png"));
                smoke = ImageIO.read(new File("Дым.png"));
                snowflake =  ImageIO.read(new File("D:Снежинка круглая.png"));
            }
            catch (IOException e) {e.printStackTrace();}

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D = (Graphics2D)g;
            graphics2D.drawImage(lightHouse, 173, 220, lightHouseWidth, lightHouseHeight, this);
            graphics2D.drawImage(smoke, smokeBank, smokeBankY, smokeWidth, smokeHeight, this);
            graphics2D.drawImage(snowflake,snowflakeX ,snowflakeY, 10, 10, this);
            graphics2D.drawImage(snowflake, snowflakeX1,snowflakeY1, 10, 10, this);
            graphics2D.drawImage(snowflake, snowflakeX2,snowflakeY2, 10, 10, this);
            graphics2D.drawImage(snowflake,  snowflakeX3,snowflakeY3, 10, 10, this);
            graphics2D.drawImage(snowflake,  snowflakeX4,snowflakeY4, 10, 10, this);
            graphics2D.drawImage(snowflake,  snowflakeX5,snowflakeY5,  10, 10, this);
            graphics2D.drawImage(snowflake,  snowflakeX6,snowflakeY6,  10, 10, this);
            graphics2D.drawImage(snowflake,  snowflakeX7,snowflakeY7,  10, 10, this);
            graphics2D.drawImage(snowflake,  snowflakeX8,snowflakeY8,  10, 10, this);
            graphics2D.drawImage(snowflake,  snowflakeX9,snowflakeY9,  10, 10, this);
        }
    }

    public class SmokeThread implements Runnable{
        @Override
        public void run() {
            lightHouseHeight = 315;
            lightHouseWidth = 495;
            while (timeFalling > 0) {
                timeFalling -= 2;
                smokeHeight +=4;
                smokeWidth +=1;
                smokeBankY -=4;
                repaint();
                try {
                    Thread.sleep(130);
                }
                catch (Exception exc) {};
            }
        }
    }
    public class SnowFlakeThread implements Runnable{
        @Override
        public void run() {
            while (timeFalling > 0) {
                snowflakeX = ThreadLocalRandom.current().nextInt(0, 1000 + 5);
                snowflakeY = ThreadLocalRandom.current().nextInt(100, 210);

                snowflakeX1 = ThreadLocalRandom.current().nextInt(0, 900 + 5);
                snowflakeY1 = ThreadLocalRandom.current().nextInt(100, 150);

                snowflakeX2 = ThreadLocalRandom.current().nextInt(0, 800 + 5);
                snowflakeY2 = ThreadLocalRandom.current().nextInt(120, 190);

                snowflakeX3 = ThreadLocalRandom.current().nextInt(0, 1000 + 5);
                snowflakeY3 = ThreadLocalRandom.current().nextInt(140, 400);

                snowflakeX4 = ThreadLocalRandom.current().nextInt(0, 900 + 5);
                snowflakeY4 = ThreadLocalRandom.current().nextInt(200, 420);

                snowflakeX5 = ThreadLocalRandom.current().nextInt(0, 1000 + 5);
                snowflakeY5 = ThreadLocalRandom.current().nextInt(170, 180);

                snowflakeX6= ThreadLocalRandom.current().nextInt(0, 1000 + 5);
                snowflakeY6 = ThreadLocalRandom.current().nextInt(110, 150);

                snowflakeX7= ThreadLocalRandom.current().nextInt(0, 1000 + 5);
                snowflakeY7 = ThreadLocalRandom.current().nextInt(1, 50);

                snowflakeX8= ThreadLocalRandom.current().nextInt(0, 300 + 5);
                snowflakeY8 = ThreadLocalRandom.current().nextInt(10, 100);

                snowflakeX9= ThreadLocalRandom.current().nextInt(0, 500 + 5);
                snowflakeY9 = ThreadLocalRandom.current().nextInt(1, 100);
                try {
                    Thread.sleep(150);
                }
                catch (Exception exc) {};
            }
        }
    }
}
