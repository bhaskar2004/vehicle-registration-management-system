package vehicle.registration.management.service_records;

import vehicle.registration.management.conn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class update_service extends JFrame implements ActionListener {
    JTextField tserviceId, tvid, tserviceDate, tcost, tdescription;
    JButton update, back;
    JFrame parentFrame;

    public update_service(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        getContentPane().setBackground(new Color(163, 255, 188));

        JLabel heading = new JLabel("Update Service Record");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 25));
        add(heading);

        // Service ID
        JLabel serviceId = new JLabel("Service ID");
        serviceId.setBounds(50, 150, 150, 30);
        serviceId.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(serviceId);

        tserviceId = new JTextField();
        tserviceId.setBounds(200, 150, 150, 30);
        tserviceId.setBackground(new Color(177, 252, 197));
        add(tserviceId);

        // Vehicle ID
        JLabel vid = new JLabel("Vehicle ID");
        vid.setBounds(50, 200, 150, 30);
        vid.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(vid);

        tvid = new JTextField();
        tvid.setBounds(200, 200, 150, 30);
        tvid.setBackground(new Color(177, 252, 197));
        add(tvid);

        // Service Date
        JLabel serviceDate = new JLabel("Service Date");
        serviceDate.setBounds(50, 250, 150, 30);
        serviceDate.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(serviceDate);

        tserviceDate = new JTextField();
        tserviceDate.setBounds(200, 250, 150, 30);
        tserviceDate.setBackground(new Color(177, 252, 197));
        add(tserviceDate);

        // Cost
        JLabel cost = new JLabel("Cost");
        cost.setBounds(50, 300, 150, 30);
        cost.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(cost);

        tcost = new JTextField();
        tcost.setBounds(200, 300, 150, 30);
        tcost.setBackground(new Color(177, 252, 197));
        add(tcost);

        // Description
        JLabel sdescription = new JLabel("Description");
        sdescription.setBounds(50, 350, 150, 30);
        sdescription.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(sdescription);

        tdescription = new JTextField();
        tdescription.setBounds(200, 350, 150, 30);
        tdescription.setBackground(new Color(177, 252, 197));
        add(tdescription);

        update = new JButton("UPDATE");
        update.setBounds(450, 550, 150, 40);
        update.setBackground(Color.black);
        update.setForeground(Color.white);
        update.addActionListener(this);
        add(update);

        back = new JButton("BACK");
        back.setBounds(250, 550, 150, 40);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);

        setLayout(null);
        setSize(900, 700);
        setLocation(300, 50);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == update) {
            String serviceId = tserviceId.getText();
            String vid = tvid.getText();
            String serviceDate = tserviceDate.getText();
            String cost = tcost.getText();
            String sdescription = tdescription.getText();

            try {
                conn c = new conn();
                String query = "UPDATE service_records SET vid = '" + vid + "', service_date = '" + serviceDate + "', cost = '" + cost + "', sdescription = '" + sdescription + "' WHERE service_id = '" + serviceId + "'";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Service record updated successfully");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error updating service record: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == back) {
            setVisible(false);
            parentFrame.setVisible(true);
        }
    }

    public static void main(String[] args) {

        new update_service(new JFrame());
    }
}
