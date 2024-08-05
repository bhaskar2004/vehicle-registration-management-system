package vehicle.registration.management.OWNER;

import vehicle.registration.management.Main_class;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class owner_page extends JFrame implements ActionListener {

    JButton add, back;

    public owner_page() {

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/bg5.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1120, 630, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(0, 0, 1120, 630);
        add(img);

        JLabel heading = new JLabel("OWNER PAGE");
        heading.setBounds(340, 155, 400, 40);
        heading.setFont(new Font("Raleway", Font.BOLD, 25));
        img.add(heading);

        JButton vhe = new JButton("Add owner ");
        vhe.setBounds(335, 270, 150, 40);
        vhe.setForeground(Color.white);
        vhe.setBackground(Color.black);
        vhe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new addowner(owner_page.this);
                setVisible(false);
            }
        });
        img.add(vhe);

        JButton own = new JButton("remove owner");
        own.setBounds(565, 270, 150, 40);
        own.setForeground(Color.white);
        own.setBackground(Color.black);
        own.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new remove_owner();
            }
        });
        img.add(own);

        JButton reg = new JButton("owner view ");
        reg.setBounds(335, 360, 150, 40);
        reg.setForeground(Color.white);
        reg.setBackground(Color.black);
        reg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new view_owner();
            }
        });
        img.add(reg);

        JButton ins = new JButton("update owner");
        ins.setBounds(565, 360, 150, 40);
        ins.setForeground(Color.white);
        ins.setBackground(Color.black);
        ins.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new update_owner(owner_page.this);
                setVisible(false);
            }
        });
        img.add(ins);

        back = new JButton("BACK");
        back.setBounds(445, 450, 150, 40);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        img.add(back);

        setSize(1120, 630);
        setLocation(250, 100);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            new Main_class();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new owner_page();
    }
}
