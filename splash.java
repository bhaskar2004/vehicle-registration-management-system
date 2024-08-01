package vehicle.registration.management;


import javax.swing.*;
import java.awt.*;

public class splash extends JFrame {
    splash() {

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/p1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1190,700, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image =new JLabel(i3);
        image.setBounds(0,0,1190,700);
        add(image);

        setSize(1190,700); // Corrected method name to setSize
        setLocation(200,50);
        setLayout(null);
        setVisible(true);


        try{
            Thread.sleep(5000);
            setVisible(false);
            new login();


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new splash();
    }
}
