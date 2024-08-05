package vehicle.registration.management.registation;

import vehicle.registration.management.conn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class update_registration extends JFrame implements ActionListener {

    JTextField trid, tvid, toid, tdateOfRegistration, texpireDate;
    JButton load, update, back;
    JFrame parentFrame;

    public update_registration(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        setTitle("Update Registration");
        setLayout(null);
        getContentPane().setBackground(new Color(240, 240, 255));

        JLabel lblSelectId = new JLabel("Registration ID");
        lblSelectId.setBounds(20, 20, 200, 30);
        lblSelectId.setFont(new Font("Sans Serif", Font.BOLD, 15));
        add(lblSelectId);

        trid = new JTextField();
        trid.setBounds(220, 20, 150, 30);
        add(trid);

        load = new JButton("LOAD");
        load.setBounds(380, 20, 100, 30);
        load.setBackground(Color.black);
        load.setForeground(Color.white);
        load.addActionListener(this);
        add(load);

        // Vehicle ID
        JLabel lblVid = new JLabel("Vehicle ID");
        lblVid.setBounds(50, 80, 150, 30);
        lblVid.setFont(new Font("Sans Serif", Font.BOLD, 15));
        add(lblVid);

        tvid = new JTextField();
        tvid.setBounds(220, 80, 150, 30);
        tvid.setBackground(new Color(200, 255, 200));
        add(tvid);

        // Owner ID
        JLabel lblOid = new JLabel("Owner ID");
        lblOid.setBounds(50, 130, 150, 30);
        lblOid.setFont(new Font("Sans Serif", Font.BOLD, 15));
        add(lblOid);

        toid = new JTextField();
        toid.setBounds(220, 130, 150, 30);
        toid.setBackground(new Color(200, 255, 200));
        add(toid);

        // Date of Registration
        JLabel lblDateOfRegistration = new JLabel("Date of Registration");
        lblDateOfRegistration.setBounds(50, 180, 150, 30);
        lblDateOfRegistration.setFont(new Font("Sans Serif", Font.BOLD, 15));
        add(lblDateOfRegistration);

        tdateOfRegistration = new JTextField();
        tdateOfRegistration.setBounds(220, 180, 150, 30);
        tdateOfRegistration.setBackground(new Color(200, 255, 200));
        add(tdateOfRegistration);

        // Expire Date
        JLabel lblExpireDate = new JLabel("Expire Date");
        lblExpireDate.setBounds(50, 230, 150, 30);
        lblExpireDate.setFont(new Font("Sans Serif", Font.BOLD, 15));
        add(lblExpireDate);

        texpireDate = new JTextField();
        texpireDate.setBounds(220, 230, 150, 30);
        texpireDate.setBackground(new Color(200, 255, 200));
        add(texpireDate);

        // Update Button
        update = new JButton("Update");
        update.setBounds(80, 300, 120, 30);
        update.setBackground(Color.black);
        update.setForeground(Color.white);
        update.addActionListener(this);
        add(update);

        // Back Button
        back = new JButton("Back");
        back.setBounds(220, 300, 120, 30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);

        setSize(550, 400);
        setLocation(300, 150);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == load) {
            String rid = trid.getText();
            String query = "SELECT * FROM registration WHERE rid = '" + rid + "'";
            try {
                conn c = new conn();
                ResultSet rs = c.statement.executeQuery(query);
                if (rs.next()) {
                    tvid.setText(rs.getString("vid"));
                    toid.setText(rs.getString("oid"));
                    tdateOfRegistration.setText(rs.getString("dateof_registration"));
                    texpireDate.setText(rs.getString("expiredate"));
                } else {
                    JOptionPane.showMessageDialog(null, "Registration ID not found");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error loading registration details: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == update) {
            String rid = trid.getText();
            String vid = tvid.getText();
            String oid = toid.getText();
            String dateOfRegistration = tdateOfRegistration.getText();
            String expireDate = texpireDate.getText();

            String query = "UPDATE registration SET vid = '" + vid + "', oid = '" + oid + "', " +
                    "dateof_registration = '" + dateOfRegistration + "', expiredate = '" + expireDate + "' " +
                    "WHERE rid = '" + rid + "'";
            try {
                conn c = new conn();
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Registration updated successfully");
                setVisible(false);
                parentFrame.setVisible(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == back) {
            setVisible(false);
            parentFrame.setVisible(true); // Show the previous page
        }
    }

    public static void main(String[] args) {
        new update_registration(new JFrame());
    }
}
