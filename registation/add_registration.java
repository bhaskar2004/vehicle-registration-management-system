package vehicle.registration.management.registation;

import vehicle.registration.management.conn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class add_registration extends JFrame implements ActionListener {
    private JTextField tdateOfRegistration, texpiredate;
    private Choice choiceVid, choiceOid;
    private JButton submit, back;
    private JFrame parentFrame;

    public add_registration(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        setTitle("Add Registration");
        setLayout(null);
        getContentPane().setBackground(new Color(255, 255, 204));

        JLabel heading = new JLabel("Add Registration");
        heading.setBounds(150, 30, 250, 30);
        heading.setFont(new Font("Serif", Font.BOLD, 24));
        add(heading);

        JLabel lblVid = new JLabel("Vehicle ID");
        lblVid.setBounds(50, 100, 100, 30);
        lblVid.setFont(new Font("Sans Serif", Font.BOLD, 16));
        add(lblVid);

        choiceVid = new Choice();
        choiceVid.setBounds(200, 100, 150, 30);
        add(choiceVid);
        loadVehicleIds();

        JLabel lblOid = new JLabel("Owner ID");
        lblOid.setBounds(50, 150, 100, 30);
        lblOid.setFont(new Font("Sans Serif", Font.BOLD, 16));
        add(lblOid);

        choiceOid = new Choice();
        choiceOid.setBounds(200, 150, 150, 30);
        add(choiceOid);
        loadOwnerIds();

        JLabel lblDateOfRegistration = new JLabel("Date of Registration");
        lblDateOfRegistration.setBounds(50, 200, 150, 30);
        lblDateOfRegistration.setFont(new Font("Sans Serif", Font.BOLD, 16));
        add(lblDateOfRegistration);

        tdateOfRegistration = new JTextField();
        tdateOfRegistration.setBounds(200, 200, 150, 30);
        add(tdateOfRegistration);

        JLabel lblExpiredate = new JLabel("Expire Date");
        lblExpiredate.setBounds(50, 250, 150, 30);
        lblExpiredate.setFont(new Font("Sans Serif", Font.BOLD, 16));
        add(lblExpiredate);

        texpiredate = new JTextField();
        texpiredate.setBounds(200, 250, 150, 30);
        add(texpiredate);

        submit = new JButton("Submit");
        submit.setBounds(100, 300, 100, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        back = new JButton("Back");
        back.setBounds(250, 300, 100, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        setSize(500, 400);
        setLocation(300, 150);
        setVisible(true);
    }

    private void loadVehicleIds() {
        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("SELECT vid FROM vehicle");
            while (rs.next()) {
                choiceVid.add(rs.getString("vid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadOwnerIds() {
        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("SELECT oid FROM owner");
            while (rs.next()) {
                choiceOid.add(rs.getString("oid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String vid = choiceVid.getSelectedItem();
            String oid = choiceOid.getSelectedItem();
            String dateOfRegistration = tdateOfRegistration.getText();
            String expiredate = texpiredate.getText();

            try {
                conn c = new conn();
                String query = "INSERT INTO registration (vid, oid, date_of_registration, expiredate) VALUES ('" +
                        vid + "', '" +
                        oid + "', '" +
                        dateOfRegistration + "', '" +
                        expiredate + "')";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Registration added successfully");
                setVisible(false);
                parentFrame.setVisible(true); // Show the previous page
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == back) {
            setVisible(false);
            parentFrame.setVisible(true); // Show the previous page
        }
    }

    public static void main(String[] args) {
        new add_registration(new JFrame());
    }
}
