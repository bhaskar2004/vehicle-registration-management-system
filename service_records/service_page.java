package vehicle.registration.management.service_records;

import vehicle.registration.management.Main_class;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class service_page extends JFrame implements ActionListener {

    JButton back;

    public service_page() {

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/bg2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1120, 630, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(0, 0, 1120, 630);
        add(img);

        JLabel heading = new JLabel("SERVICE PAGE");
        heading.setBounds(340, 155, 400, 40);
        heading.setFont(new Font("Raleway", Font.BOLD, 25));
        img.add(heading);

        JButton addService = new JButton("Add Service");
        addService.setBounds(335, 270, 150, 40);
        addService.setForeground(Color.white);
        addService.setBackground(Color.black);
        addService.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new add_service(service_page.this);
                setVisible(false);
            }
        });
        img.add(addService);

        JButton viewService = new JButton("View Services");
        viewService.setBounds(565, 270, 150, 40);
        viewService.setForeground(Color.white);
        viewService.setBackground(Color.black);
        viewService.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new view_service();
            }
        });
        img.add(viewService);

        JButton updateService = new JButton("Update Service");
        updateService.setBounds(450, 360, 150, 40);
        updateService.setForeground(Color.white);
        updateService.setBackground(Color.black);
        updateService.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new update_service(service_page.this);
                setVisible(false);
            }
        });
        img.add(updateService);

        back = new JButton("BACK");
        back.setBounds(445, 450, 150, 40);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        img.add(back);

        setSize(1120, 630);
        setLocation(260, 100);
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
        new service_page();
    }
}
