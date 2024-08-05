package vehicle.registration.management.insurence;

import vehicle.registration.management.conn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class add_insurance extends JFrame implements ActionListener {
    private JTextField tpolicyno, tprovider, tamt, texpiredate;
    private Choice choiceVid;
    private JButton submit, back;
    private JFrame parentFrame;

    public add_insurance(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        setTitle("Add Insurance");
        setLayout(null);
        getContentPane().setBackground(new Color(255, 255, 204));

        JLabel heading = new JLabel("Add Insurance");
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

        JLabel lblPolicyno = new JLabel("Policy No");
        lblPolicyno.setBounds(50, 150, 100, 30);
        lblPolicyno.setFont(new Font("Sans Serif", Font.BOLD, 16));
        add(lblPolicyno);

        tpolicyno = new JTextField();
        tpolicyno.setBounds(200, 150, 150, 30);
        add(tpolicyno);

        JLabel lblProvider = new JLabel("Provider");
        lblProvider.setBounds(50, 200, 100, 30);
        lblProvider.setFont(new Font("Sans Serif", Font.BOLD, 16));
        add(lblProvider);

        tprovider = new JTextField();
        tprovider.setBounds(200, 200, 150, 30);
        add(tprovider);

        JLabel lblAmt = new JLabel("Amount");
        lblAmt.setBounds(50, 250, 100, 30);
        lblAmt.setFont(new Font("Sans Serif", Font.BOLD, 16));
        add(lblAmt);

        tamt = new JTextField();
        tamt.setBounds(200, 250, 150, 30);
        add(tamt);

        JLabel lblExpiredate = new JLabel("Expire Date");
        lblExpiredate.setBounds(50, 300, 150, 30);
        lblExpiredate.setFont(new Font("Sans Serif", Font.BOLD, 16));
        add(lblExpiredate);

        texpiredate = new JTextField();
        texpiredate.setBounds(200, 300, 150, 30);
        add(texpiredate);

        submit = new JButton("Submit");
        submit.setBounds(100, 350, 100, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        back = new JButton("Back");
        back.setBounds(250, 350, 100, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        setSize(500, 450);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String vid = choiceVid.getSelectedItem();
            String policyno = tpolicyno.getText();
            String provider = tprovider.getText();
            String amt = tamt.getText();
            String expiredate = texpiredate.getText();

            try {
                conn c = new conn();
                String query = "INSERT INTO insurance (vid, policyno, provider, amt, expiredate) VALUES ('" +
                        vid + "', '" +
                        policyno + "', '" +
                        provider + "', '" +
                        amt + "', '" +
                        expiredate + "')";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Insurance added successfully");
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
        new add_insurance(new JFrame());
    }
}
