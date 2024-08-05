package vehicle.registration.management.registation;

import vehicle.registration.management.conn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class remove_registration extends JFrame implements ActionListener {

    Choice choiceRegistrationId;
    JButton delete, back;
    JLabel lblVid, lblOid, lblDateOfRegistration, lblExpireDate;
    JTextField txtVid, txtOid, txtDateOfRegistration, txtExpireDate;

    public remove_registration() {
        setTitle("Remove Registration");
        setLayout(null);
        getContentPane().setBackground(new Color(255, 222, 222));

        JLabel lblSelectId = new JLabel("Select Registration ID");
        lblSelectId.setBounds(20, 20, 200, 30);
        lblSelectId.setFont(new Font("Sans Serif", Font.BOLD, 15));
        add(lblSelectId);

        choiceRegistrationId = new Choice();
        choiceRegistrationId.setBounds(220, 20, 150, 30);
        add(choiceRegistrationId);

        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("SELECT rid FROM registration");
            while (rs.next()) {
                choiceRegistrationId.add(rs.getString("rid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JLabel lblVid = new JLabel("Vehicle ID");
        lblVid.setBounds(50, 80, 150, 30);
        lblVid.setFont(new Font("Sans Serif", Font.BOLD, 15));
        add(lblVid);

        txtVid = new JTextField();
        txtVid.setBounds(200, 80, 150, 30);
        txtVid.setEditable(false);
        add(txtVid);

        JLabel lblOid = new JLabel("Owner ID");
        lblOid.setBounds(50, 130, 150, 30);
        lblOid.setFont(new Font("Sans Serif", Font.BOLD, 15));
        add(lblOid);

        txtOid = new JTextField();
        txtOid.setBounds(200, 130, 150, 30);
        txtOid.setEditable(false);
        add(txtOid);

        JLabel lblDateOfRegistration = new JLabel("Date of Registration");
        lblDateOfRegistration.setBounds(50, 180, 200, 30);
        lblDateOfRegistration.setFont(new Font("Sans Serif", Font.BOLD, 15));
        add(lblDateOfRegistration);

        txtDateOfRegistration = new JTextField();
        txtDateOfRegistration.setBounds(200, 180, 150, 30);
        txtDateOfRegistration.setEditable(false);
        add(txtDateOfRegistration);

        JLabel lblExpireDate = new JLabel("Expire Date");
        lblExpireDate.setBounds(50, 230, 150, 30);
        lblExpireDate.setFont(new Font("Sans Serif", Font.BOLD, 15));
        add(lblExpireDate);

        txtExpireDate = new JTextField();
        txtExpireDate.setBounds(200, 230, 150, 30);
        txtExpireDate.setEditable(false);
        add(txtExpireDate);

        choiceRegistrationId.addItemListener(e -> {
            try {
                conn c = new conn();
                String selectedId = choiceRegistrationId.getSelectedItem();
                ResultSet rs = c.statement.executeQuery("SELECT * FROM registration WHERE rid = '" + selectedId + "'");
                if (rs.next()) {
                    txtVid.setText(rs.getString("vid"));
                    txtOid.setText(rs.getString("oid"));
                    txtDateOfRegistration.setText(rs.getString("date_of_registration"));
                    txtExpireDate.setText(rs.getString("expire_date"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        delete = new JButton("Delete");
        delete.setBounds(80, 300, 100, 30);
        delete.setBackground(Color.black);
        delete.setForeground(Color.white);
        delete.addActionListener(this);
        add(delete);

        back = new JButton("Back");
        back.setBounds(200, 300, 100, 30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);

        setSize(400, 400);
        setLocation(300, 150);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == delete) {
            try {
                conn c = new conn();
                String selectedId = choiceRegistrationId.getSelectedItem();
                String query = "DELETE FROM registration WHERE rid = '" + selectedId + "'";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Registration deleted successfully");
                setVisible(false);
                new remove_registration(); // Optionally, refresh the page
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error deleting registration: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        } else if (e.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new remove_registration();
    }
}
