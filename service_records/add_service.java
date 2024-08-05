package vehicle.registration.management.service_records;

import vehicle.registration.management.conn;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class add_service extends JFrame implements ActionListener {
    JTextField service_id, service_date, cost, sdescription;
    JComboBox<String> vidComboBox;
    JButton add, back;
    JFrame parentFrame;

    public add_service(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        getContentPane().setBackground(new Color(163, 255, 188));

        JLabel heading = new JLabel("Add Service Record");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 25));
        add(heading);

        // Service ID
        JLabel lblServiceID = new JLabel("Service ID");
        lblServiceID.setBounds(50, 150, 150, 30);
        lblServiceID.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(lblServiceID);

        service_id = new JTextField();
        service_id.setBounds(200, 150, 150, 30);
        service_id.setBackground(new Color(177, 252, 197));
        add(service_id);

        // Vehicle ID
        JLabel lblVID = new JLabel("Vehicle ID");
        lblVID.setBounds(50, 200, 150, 30);
        lblVID.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(lblVID);

        vidComboBox = new JComboBox<>();
        vidComboBox.setBounds(200, 200, 150, 30);
        vidComboBox.setBackground(new Color(177, 252, 197));
        add(vidComboBox);

        loadVehicleIDs();

        // Service Date
        JLabel lblServiceDate = new JLabel("Service Date");
        lblServiceDate.setBounds(50, 250, 150, 30);
        lblServiceDate.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(lblServiceDate);

        service_date = new JTextField();
        service_date.setBounds(200, 250, 150, 30);
        service_date.setBackground(new Color(177, 252, 197));
        add(service_date);

        // Cost
        JLabel lblCost = new JLabel("Cost");
        lblCost.setBounds(50, 300, 150, 30);
        lblCost.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(lblCost);

        cost = new JTextField();
        cost.setBounds(200, 300, 150, 30);
        cost.setBackground(new Color(177, 252, 197));
        add(cost);

        // Description
        JLabel lblDescription = new JLabel("Description");
        lblDescription.setBounds(50, 350, 150, 30);
        lblDescription.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(lblDescription);

        sdescription = new JTextField();
        sdescription.setBounds(200, 350, 150, 30);
        sdescription.setBackground(new Color(177, 252, 197));
        add(sdescription);

        add = new JButton("ADD");
        add.setBounds(450, 450, 150, 40);
        add.setBackground(Color.black);
        add.setForeground(Color.white);
        add.addActionListener(this);
        add(add);

        back = new JButton("BACK");
        back.setBounds(250, 450, 150, 40);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);

        setLayout(null);
        setSize(900, 600);
        setLocation(300, 50);
        setVisible(true);
    }

    private void loadVehicleIDs() {
        try {
            conn c = new conn();
            String query = "SELECT vid FROM vehicle";
            ResultSet rs = c.statement.executeQuery(query);
            while (rs.next()) {
                vidComboBox.addItem(rs.getString("vid"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading vehicle IDs: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            String serviceID = service_id.getText();
            String vid = (String) vidComboBox.getSelectedItem();
            String serviceDate = service_date.getText();
            String serviceCost = cost.getText();
            String description = sdescription.getText();

            try {
                conn c = new conn();
                String query = "INSERT INTO service_records (service_id, vid, service_date, cost, sdescription) VALUES ('" + serviceID + "', '" + vid + "', '" + serviceDate + "', '" + serviceCost + "', '" + description + "')";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Service record added successfully");
                setVisible(false);
                parentFrame.setVisible(true); // Show the previous service page
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error adding service record: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == back) {
            setVisible(false);
            parentFrame.setVisible(true); // Show the previous service page
        }
    }

    public static void main(String[] args) {
        new add_service(new JFrame());
    }
}
