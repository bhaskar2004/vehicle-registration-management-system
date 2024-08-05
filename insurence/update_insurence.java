package vehicle.registration.management.insurence;

import vehicle.registration.management.conn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class update_insurence extends JFrame implements ActionListener {
    Choice choiceInsuid;
    JTextField tvid, tpolicyno, tprovider, tamt, texpiredate;
    JButton load, update, back;
    JFrame parentFrame;

    public update_insurence(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        getContentPane().setBackground(new Color(163, 255, 188));

        JLabel heading = new JLabel("Update Insurance Details");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 25));
        add(heading);

        // Insurance ID
        JLabel insuid = new JLabel("Insurance ID");
        insuid.setBounds(50, 100, 150, 30);
        insuid.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(insuid);

        choiceInsuid = new Choice();
        choiceInsuid.setBounds(250, 100, 150, 30);
        add(choiceInsuid);
        loadInsuranceIds();

        // Load Button
        load = new JButton("LOAD");
        load.setBounds(450, 100, 100, 30);
        load.setBackground(Color.BLACK);
        load.setForeground(Color.WHITE);
        load.addActionListener(this);
        add(load);

        // Vehicle ID
        JLabel vid = new JLabel("Vehicle ID");
        vid.setBounds(50, 150, 150, 30);
        vid.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(vid);

        tvid = new JTextField();
        tvid.setBounds(250, 150, 150, 30);
        tvid.setBackground(new Color(177, 252, 197));
        add(tvid);

        // Policy Number
        JLabel policyno = new JLabel("Policy Number");
        policyno.setBounds(50, 200, 150, 30);
        policyno.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(policyno);

        tpolicyno = new JTextField();
        tpolicyno.setBounds(250, 200, 150, 30);
        tpolicyno.setBackground(new Color(177, 252, 197));
        add(tpolicyno);

        // Provider
        JLabel provider = new JLabel("Provider");
        provider.setBounds(50, 250, 150, 30);
        provider.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(provider);

        tprovider = new JTextField();
        tprovider.setBounds(250, 250, 150, 30);
        tprovider.setBackground(new Color(177, 252, 197));
        add(tprovider);

        // Amount
        JLabel amt = new JLabel("Amount");
        amt.setBounds(50, 300, 150, 30);
        amt.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(amt);

        tamt = new JTextField();
        tamt.setBounds(250, 300, 150, 30);
        tamt.setBackground(new Color(177, 252, 197));
        add(tamt);

        // Expiry Date
        JLabel expiredate = new JLabel("Expiry Date");
        expiredate.setBounds(50, 350, 150, 30);
        expiredate.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(expiredate);

        texpiredate = new JTextField();
        texpiredate.setBounds(250, 350, 150, 30);
        texpiredate.setBackground(new Color(177, 252, 197));
        add(texpiredate);

        update = new JButton("UPDATE");
        update.setBounds(450, 450, 150, 40);
        update.setBackground(Color.black);
        update.setForeground(Color.white);
        update.addActionListener(this);
        add(update);

        back = new JButton("BACK");
        back.setBounds(250, 450, 150, 40);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);

        setLayout(null);
        setSize(900, 700);
        setLocation(300, 50);
        setVisible(true);
    }

    private void loadInsuranceIds() {
        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("SELECT insuid FROM insurance");
            while (rs.next()) {
                choiceInsuid.add(rs.getString("insuid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadInsuranceDetails(String insuid) {
        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("SELECT * FROM insurance WHERE insuid='" + insuid + "'");
            if (rs.next()) {
                tvid.setText(rs.getString("vid"));
                tpolicyno.setText(rs.getString("policyno"));
                tprovider.setText(rs.getString("provider"));
                tamt.setText(rs.getString("amt"));
                texpiredate.setText(rs.getString("expiredate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == load) {
            String insuid = choiceInsuid.getSelectedItem();
            loadInsuranceDetails(insuid);
        } else if (e.getSource() == update) {
            String insuid = choiceInsuid.getSelectedItem();
            String vid = tvid.getText();
            String policyno = tpolicyno.getText();
            String provider = tprovider.getText();
            String amt = tamt.getText();
            String expiredate = texpiredate.getText();

            try {
                conn c = new conn();
                String query = "UPDATE insurance SET vid='" + vid + "', policyno='" + policyno + "', provider='" + provider + "', amt='" + amt + "', expiredate='" + expiredate + "' WHERE insuid='" + insuid + "'";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details updated successfully");
                setVisible(false);
                parentFrame.setVisible(true); // Show the previous insurance page
            } catch (Exception E) {
                JOptionPane.showMessageDialog(null, "Error updating insurance: " + E.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                E.printStackTrace();
            }

        } else if (e.getSource() == back) {
            setVisible(false);
            parentFrame.setVisible(true); // Show the previous insurance page
        }
    }

    public static void main(String[] args) {
        new update_insurence(new JFrame());
    }
}
