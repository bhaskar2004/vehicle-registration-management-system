package vehicle.registration.management.VEHICLE;

import vehicle.registration.management.conn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addvehicle extends JFrame implements ActionListener {
    JTextField tvid, tlpno, tcname, tmodel, ttype, tyear;
    JButton add, back;
    JFrame parentFrame;

    public addvehicle(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        getContentPane().setBackground(new Color(163, 255, 188));

        JLabel heading = new JLabel("Add vehicle details");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 25));
        add(heading);

        // Vehicle ID
        JLabel vid = new JLabel("Vehicle ID");
        vid.setBounds(50, 150, 150, 30);
        vid.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(vid);

        tvid = new JTextField();
        tvid.setBounds(200, 150, 150, 30);
        tvid.setBackground(new Color(177, 252, 197));
        add(tvid);

        // Plate number
        JLabel lpno = new JLabel("Plate no");
        lpno.setBounds(50, 200, 150, 30);
        lpno.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(lpno);

        tlpno = new JTextField();
        tlpno.setBounds(200, 200, 150, 30);
        tlpno.setBackground(new Color(177, 252, 197));
        add(tlpno);

        // Company name
        JLabel cname = new JLabel("company name");
        cname.setBounds(50, 250, 150, 30);
        cname.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(cname);

        tcname = new JTextField();
        tcname.setBounds(200, 250, 150, 30);
        tcname.setBackground(new Color(177, 252, 197));
        add(tcname);

        // Vehicle model
        JLabel model = new JLabel("Vehicle model");
        model.setBounds(50, 300, 150, 30);
        model.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(model);

        tmodel = new JTextField();
        tmodel.setBounds(200, 300, 150, 30);
        tmodel.setBackground(new Color(177, 252, 197));
        add(tmodel);

        // Vehicle type
        JLabel vtype = new JLabel("vehicle type");
        vtype.setBounds(50, 350, 150, 30);
        vtype.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(vtype);

        ttype = new JTextField();
        ttype.setBounds(200, 350, 150, 30);
        ttype.setBackground(new Color(177, 252, 197));
        add(ttype);

        // Vehicle year
        JLabel vyear = new JLabel("Year");
        vyear.setBounds(50, 400, 150, 30);
        vyear.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(vyear);

        tyear = new JTextField();
        tyear.setBounds(200, 400, 150, 30);
        tyear.setBackground(new Color(177, 252, 197));
        add(tyear);

        add = new JButton("ADD");
        add.setBounds(450, 550, 150, 40);
        add.setBackground(Color.black);
        add.setForeground(Color.white);
        add.addActionListener(this);
        add(add);

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
        if (e.getSource() == add) {
            String vid = tvid.getText();
            String lpno = tlpno.getText();
            String cname = tcname.getText();
            String model = tmodel.getText();
            String vtype = ttype.getText();
            String vyear = tyear.getText();

            try {
                conn c = new conn();
                String query = "insert into vehicle values('" + vid + "','" + lpno + "','" + cname + "','" + model + "','" + vtype + "','" + vyear + "')";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details added successfully");
                setVisible(false);
                parentFrame.setVisible(true); // Show the previous vehicle page
            } catch (Exception E) {
                E.printStackTrace();
            }

        } else if (e.getSource() == back) {
            setVisible(false);
            parentFrame.setVisible(true); // Show the previous vehicle page
        }
    }

    public static void main(String[] args) {
        new addvehicle(new JFrame());
    }
}
