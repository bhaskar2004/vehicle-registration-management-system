package vehicle.registration.management;

import vehicle.registration.management.OWNER.owner_page;
import vehicle.registration.management.VEHICLE.veh_page;
import vehicle.registration.management.insurence.insurance_page;
import vehicle.registration.management.registation.registration_page;
import vehicle.registration.management.service_records.service_page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_class extends JFrame {

    public Main_class() {

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/bg1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1120, 630, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(0, 0, 1120, 630);
        add(img);

        JLabel heading = new JLabel("Vehicle Registration System");
        heading.setBounds(340, 155, 400, 40);
        heading.setFont(new Font("Raleway", Font.BOLD, 25));
        img.add(heading);

        JButton vhe = new JButton(" Vehicle ");
        vhe.setBounds(335, 270, 150, 40);
        vhe.setForeground(Color.white);
        vhe.setBackground(Color.black);
        vhe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new veh_page();
                setVisible(false);
            }
        });
        img.add(vhe);

        JButton own = new JButton("Owner info");
        own.setBounds(565, 270, 150, 40);
        own.setForeground(Color.white);
        own.setBackground(Color.black);
        own.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new owner_page();
                setVisible(false);
            }
        });
        img.add(own);

        JButton reg = new JButton("Registration info");
        reg.setBounds(335, 360, 150, 40);
        reg.setForeground(Color.white);
        reg.setBackground(Color.black);
        reg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new registration_page();
                setVisible(false);
            }
        });
        img.add(reg);

        JButton ins = new JButton("Insurance info");
        ins.setBounds(565, 360, 150, 40);
        ins.setForeground(Color.white);
        ins.setBackground(Color.black);
        ins.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new insurance_page();
                setVisible(false);
            }
        });
        img.add(ins);

        JButton ser = new JButton("Service info");
        ser.setBounds(450, 450, 150, 40); // Corrected position to be consistent
        ser.setForeground(Color.white);
        ser.setBackground(Color.black);
        ser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new service_page();
                setVisible(false);
            }
        });
        img.add(ser);

        setSize(1120, 630);
        setLocation(250, 100);
        setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main_class();
    }
}
