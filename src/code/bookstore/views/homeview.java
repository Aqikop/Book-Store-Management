package code.bookstore.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class homeview {
    public JPanel init_panel(){
        JPanel panel = new JPanel(new GridBagLayout()) {
            private BufferedImage bg;
            {
                try {
                    bg = ImageIO.read(new File("src\\resources\\images\\book_bg.jpg"));
                } catch(IOException e){
                    e.printStackTrace();
                    bg = null;
                }
            }
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                if(bg != null){
                    g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
                }
                else{
                    g.setColor(Color.decode("#1e1e1e"));
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        panel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel quote1 = new JLabel("Unlock Your Imagination,");
        quote1.setFont(new Font("Lato", Font.BOLD, 30));
        quote1.setForeground(Color.decode("#1e1e1e"));
        panel.add(quote1, gbc);

        //JLabel quote2 = new JLabel("One Page at a Time ");
        JLabel quote2 = new JLabel(
            "<html><font color='#f29f3f'>One Page </font>at a Time</html>"
        );
        quote2.setFont(new Font("Lato", Font.BOLD, 30));
        quote2.setForeground(Color.decode("#1e1e1e"));
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10, 10, 10);
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(quote2, gbc);

        JLabel text1 = new JLabel("Find your next favorite story in our selected ");
        text1.setFont(new Font("Lato", Font.PLAIN, 15));
        text1.setForeground(Color.decode("#1e1e1e"));
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 5, 10);
        panel.add(text1, gbc);

        JLabel text2 = new JLabel("collection of timeless and trendy titles");
        text2.setFont(new Font("Lato", Font.PLAIN, 15));
        text2.setForeground(Color.decode("#1e1e1e"));
        gbc.gridy = 3;
        gbc.insets = new Insets(5, 10, 20, 10);
        panel.add(text2, gbc);

        JButton browse_button = new JButton("Browse Now");
        browse_button.setBackground(Color.decode("#2e2e2e"));
        browse_button.setForeground(Color.decode("#D9D9D9"));
        browse_button.setFont(new Font("Lato", Font.PLAIN, 14));
        browse_button.setFocusPainted(false);
        browse_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gbc.gridy = 4;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(browse_button, gbc);

        browse_button.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
            System.out.println("a"); //Place holder
            }
        });

        return panel;
    }
    public static void main(String args[]){
        new template();
    }
}
