package code.bookstore.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class payinfoview {
    public JPanel init_panel(){
        JPanel payment_info = new JPanel(new BorderLayout());
        payment_info.setBackground(Color.WHITE);
        payment_info.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.decode("#e0e0e0"), 3),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel header = new JLabel("Payment Infomation");
        header.setFont(new Font("Lato", Font.BOLD, 15));
        header.setForeground(Color.BLACK);
        
        payment_info.add(header, BorderLayout.NORTH);

        JPanel info_form = new JPanel(new GridBagLayout());
        info_form.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        JLabel fname = new JLabel("First Name");
        fname.setFont(new Font("Lato", Font.BOLD, 13));
        info_form.add(fname, gbc);

        JTextField fname_in = new JTextField();
        fname_in.setPreferredSize(new Dimension(300, 30));
        fname_in.setFont(new Font("Lato", Font.PLAIN, 14));
        fname_in.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#e0e0e0"), 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
            ));
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        info_form.add(fname_in, gbc);

        JLabel lname = new JLabel("Last Name");
        lname.setFont(new Font("Lato", Font.BOLD, 13));
        gbc.gridx = 5;
        gbc.gridy = 0;
        info_form.add(lname, gbc);

        JTextField lname_in = new JTextField();
        lname_in.setPreferredSize(new Dimension(300, 30));
        lname_in.setFont(new Font("Lato", Font.PLAIN, 14));
        lname_in.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#e0e0e0"), 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
            ));
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        info_form.add(lname_in, gbc);

        JLabel address = new JLabel("Adress");
        address.setFont(new Font("Lato", Font.BOLD, 13));
        gbc.gridx = 0; 
        gbc.gridy = 2;
        info_form.add(address, gbc);

        JTextField address_in = new JTextField();
        address_in.setPreferredSize(new Dimension(400, 30));
        address_in.setFont(new Font("Lato", Font.PLAIN, 14));
        address_in.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#e0e0e0"), 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
            ));
        gbc.gridy = 3;
        gbc.gridwidth = 10;
        info_form.add(address_in, gbc);
        
        JLabel city = new JLabel("City");
        city.setFont(new Font("Lato", Font.BOLD, 13));
        gbc.gridx = 0; 
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        info_form.add(city, gbc);

        JTextField city_in = new JTextField();
        city_in.setPreferredSize(new Dimension(300, 30));
        city_in.setFont(new Font("Lato", Font.PLAIN, 14));
        city_in.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#e0e0e0"), 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
            ));
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        info_form.add(city_in, gbc);

        JLabel zip = new JLabel("Zip Code");
        zip.setFont(new Font("Lato", Font.BOLD, 13));
        gbc.gridx = 5; 
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        info_form.add(zip, gbc);

        JTextField zip_in = new JTextField();
        zip_in.setPreferredSize(new Dimension(300, 30));
        zip_in.setFont(new Font("Lato", Font.PLAIN, 14));
        zip_in.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#e0e0e0"), 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
            ));
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        info_form.add(zip_in, gbc);

        JLabel cc_num = new JLabel("Credit Card Number");
        cc_num.setFont(new Font("Lato", Font.BOLD, 13));
        gbc.gridx = 0; 
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        info_form.add(cc_num, gbc);

        JTextField cc_num_in = new JTextField();
        cc_num_in.setPreferredSize(new Dimension(300, 30));
        cc_num_in.setFont(new Font("Lato", Font.PLAIN, 14));
        cc_num_in.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#e0e0e0"), 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
            ));
        gbc.gridy = 7;
        gbc.gridwidth = 5;
        info_form.add(cc_num_in, gbc);

        payment_info.add(info_form, BorderLayout.WEST);

        return payment_info;
    } 
}
