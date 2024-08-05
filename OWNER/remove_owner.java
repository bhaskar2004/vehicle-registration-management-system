package vehicle.registration.management.OWNER;

import vehicle.registration.management.conn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class remove_owner extends JFrame implements ActionListener {
    Choice choiceOwnerId;
    JButton delete, back;

    public remove_owner() {
        setTitle("Remove Owner");

        JLabel label = new JLabel("Owner ID");
        label.setBounds(50, 50, 100, 30);
        label.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(label);

        choiceOwnerId = new Choice();
        choiceOwnerId.setBounds(200, 50, 150, 30);
        add(choiceOwnerId);

        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from owner");
            while (resultSet.next()) {
                choiceOwnerId.add(resultSet.getString("oid"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel labelName = new JLabel("Name");
        labelName.setBounds(50, 100, 100, 30);
        labelName.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(labelName);

        JLabel textName = new JLabel();
        textName.setBounds(200, 100, 100, 30);
        add(textName);

        JLabel labelAddress = new JLabel("Address");
        labelAddress.setBounds(50, 150, 100, 30);
        labelAddress.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(labelAddress);

        JLabel textAddress = new JLabel();
        textAddress.setBounds(200, 150, 100, 30);
        add(textAddress);

        JLabel labelPhoneNo = new JLabel("Phone No");
        labelPhoneNo.setBounds(50, 200, 100, 30);
        labelPhoneNo.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(labelPhoneNo);

        JLabel textPhoneNo = new JLabel();
        textPhoneNo.setBounds(200, 200, 100, 30);
        add(textPhoneNo);

        JLabel labelLicenseNo = new JLabel("License No");
        labelLicenseNo.setBounds(50, 250, 100, 30);
        labelLicenseNo.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(labelLicenseNo);

        JLabel textLicenseNo = new JLabel();
        textLicenseNo.setBounds(200, 250, 100, 30);
        add(textLicenseNo);

        choiceOwnerId.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    conn c = new conn();
                    ResultSet resultSet = c.statement.executeQuery("select * from owner where oid='" + choiceOwnerId.getSelectedItem() + "'");
                    while (resultSet.next()) {
                        textName.setText(resultSet.getString("oname"));
                        textAddress.setText(resultSet.getString("address"));
                        textPhoneNo.setText(resultSet.getString("phno"));
                        textLicenseNo.setText(resultSet.getString("lino"));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        delete = new JButton("Delete");
        delete.setBounds(80, 350, 100, 30);
        delete.setBackground(Color.black);
        delete.setForeground(Color.white);
        delete.addActionListener(this);
        add(delete);

        back = new JButton("Back");
        back.setBounds(220, 350, 100, 30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/delete.png"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(400, 90, 200, 200);
        add(img);

        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("icons/rback.png"));
        Image i22 = i11.getImage().getScaledInstance(1120, 630, Image.SCALE_DEFAULT);
        ImageIcon i33 = new ImageIcon(i22);
        JLabel imgg = new JLabel(i33);
        imgg.setBounds(0, 0, 1120, 630);
        add(imgg);

        setSize(800, 500);
        setLocation(300, 150);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == delete) {
            try {
                conn c = new conn();
                // First, delete the records from the registration table
                String deleteRegistrations = "DELETE FROM registration WHERE oid='" + choiceOwnerId.getSelectedItem() + "'";
                c.statement.executeUpdate(deleteRegistrations);

                // Then, delete the owner
                String deleteOwner = "DELETE FROM owner WHERE oid='" + choiceOwnerId.getSelectedItem() + "'";
                c.statement.executeUpdate(deleteOwner);

                JOptionPane.showMessageDialog(null, "Owner deleted successfully");
                setVisible(false);
                new remove_owner(); // Optionally, refresh the page

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error deleting owner: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        } else if (e.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new remove_owner();
    }
}
