package vehicle.registration.management.registation;

import vehicle.registration.management.Main_class;
import vehicle.registration.management.conn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class registration_page extends JFrame implements ActionListener {

    JButton add, view, update, remove, back;

    public registration_page() {

        // Set the background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/LoginB.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1120, 630, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(0, 0, 1120, 630);
        add(img);

        // Add heading
        JLabel heading = new JLabel("REGISTRATION PAGE");
        heading.setBounds(340, 155, 400, 40);
        heading.setFont(new Font("Raleway", Font.BOLD, 25));
        img.add(heading);

        // Register Vehicle Button
        add = new JButton("Register Vehicle");
        add.setBounds(335, 270, 150, 40);
        add.setForeground(Color.white);
        add.setBackground(Color.black);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new add_registration(registration_page.this); // Open add_registration page
                setVisible(false);
            }
        });
        img.add(add);

        // View Vehicle Button
        view = new JButton("View Registrations");
        view.setBounds(565, 270, 150, 40);
        view.setForeground(Color.white);
        view.setBackground(Color.black);
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new view_registration(); // Open view_registration page
            }
        });
        img.add(view);

        // Update Vehicle Button
        update = new JButton("Update Registration");
        update.setBounds(335, 360, 150, 40);
        update.setForeground(Color.white);
        update.setBackground(Color.black);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new update_registration(registration_page.this); // Open update_registration page
                setVisible(false);
            }
        });
        img.add(update);

        // Remove Vehicle Button
        remove = new JButton("Remove Registration");
        remove.setBounds(565, 360, 150, 40);
        remove.setForeground(Color.white);
        remove.setBackground(Color.black);
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new remove_registration(); // Open remove_registration page
            }
        });
        img.add(remove);

        // Back Button
        back = new JButton("Back");
        back.setBounds(445, 450, 150, 40);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        img.add(back);

        // Frame settings
        setSize(1120, 630);
        setLocation(250, 100);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            new Main_class(); // Navigate back to main class
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new registration_page();
    }
}
