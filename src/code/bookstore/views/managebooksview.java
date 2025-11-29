package code.bookstore.views;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.util.Map;

import javax.imageio.*;
import javax.swing.*;

import code.bookstore.controllers.book_controller;

public class managebooksview {
    private CardLayout page_container;
    private JPanel page;
    private book_controller bookController;

    public managebooksview(CardLayout page_container, JPanel page) {
        this.page_container = page_container;
        this.page = page;
        this.bookController = new book_controller();
    }
    
    public JPanel init_panel(){
        JPanel manage_books = new JPanel();
        manage_books.setLayout(new BoxLayout(manage_books, BoxLayout.Y_AXIS));
        manage_books.setBackground(Color.WHITE);
        manage_books.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.decode("#e0e0e0"), 3),
            BorderFactory.createEmptyBorder(5, 20, 10, 20)
        ));

        JPanel header_panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        header_panel.setBackground(Color.WHITE);
        header_panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        header_panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        JLabel book_cover_label = new JLabel();
            try{
                BufferedImage book_cover = ImageIO.read(new File("src\\resources\\images\\book_open.jpg"));
                Image scaled = book_cover.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                book_cover_label.setIcon(new ImageIcon(scaled));
            } catch(Exception e){
                book_cover_label.setText("No img available");
                book_cover_label.setPreferredSize(new Dimension(70, 70));
                book_cover_label.setHorizontalAlignment(JLabel.CENTER);
                book_cover_label.setVerticalAlignment(JLabel.CENTER);
                book_cover_label.setOpaque(false);
            }
            book_cover_label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));
        
        JLabel head_text = new JLabel("Book Entry Form");
        head_text.setFont(new Font("Lato", Font.BOLD, 20));
        head_text.setVerticalAlignment(JLabel.CENTER);

        header_panel.add(book_cover_label);
        header_panel.add(head_text);

        JSeparator separator = new JSeparator();
        separator.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));

        JPanel book_entry_panel = new JPanel(new BorderLayout());
        book_entry_panel.setBackground(Color.WHITE);
        book_entry_panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.decode("#e0e0e0"), 0),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JPanel info_form = new JPanel(new GridBagLayout());
        info_form.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        JLabel book_title = new JLabel("Title");
        book_title.setFont(new Font("Lato", Font.BOLD, 13));
        info_form.add(book_title, gbc);

        JTextField book_title_in = new JTextField();
        book_title_in.setPreferredSize(new Dimension(300, 30));
        book_title_in.setFont(new Font("Lato", Font.PLAIN, 14));
        book_title_in.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#e0e0e0"), 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
            ));
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        info_form.add(book_title_in, gbc);

        JLabel publisher_name = new JLabel("Publisher");
        publisher_name.setFont(new Font("Lato", Font.BOLD, 13));
        gbc.gridx = 5;
        gbc.gridy = 0;
        info_form.add(publisher_name, gbc);

        JTextField publisher_name_in = new JTextField();
        publisher_name_in.setPreferredSize(new Dimension(300, 30));
        publisher_name_in.setFont(new Font("Lato", Font.PLAIN, 14));
        publisher_name_in.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#e0e0e0"), 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
            ));
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        info_form.add(publisher_name_in, gbc);

        JLabel author_name = new JLabel("Author");
        author_name.setFont(new Font("Lato", Font.BOLD, 13));
        gbc.gridx = 0; 
        gbc.gridy = 2;
        info_form.add(author_name, gbc);

        JTextField author_name_in = new JTextField();
        author_name_in.setPreferredSize(new Dimension(400, 30));
        author_name_in.setFont(new Font("Lato", Font.PLAIN, 14));
        author_name_in.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#e0e0e0"), 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
            ));
        gbc.gridy = 3;
        gbc.gridwidth = 10;
        info_form.add(author_name_in, gbc);
        
        JLabel price = new JLabel("Price");
        price.setFont(new Font("Lato", Font.BOLD, 13));
        gbc.gridx = 0; 
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        info_form.add(price, gbc);

        JTextField price_in = new JTextField();
        price_in.setPreferredSize(new Dimension(300, 30));
        price_in.setFont(new Font("Lato", Font.PLAIN, 14));
        price_in.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#e0e0e0"), 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
            ));
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        info_form.add(price_in, gbc);

        JButton submit_btn = new JButton("Submit");
        submit_btn.setMaximumSize(new Dimension(340, 40));
        submit_btn.setBackground(Color.decode("#15d428"));
        submit_btn.setForeground(Color.WHITE);
        submit_btn.setFont(new Font("Lato", Font.BOLD, 15));
        submit_btn.setFocusPainted(false);
        submit_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submit_btn.setBorder(BorderFactory.createEmptyBorder(10 ,15, 10, 15));
        gbc.gridy = 6;
        gbc.gridwidth = 10;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        info_form.add(submit_btn, gbc);

        submit_btn.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                String title = book_title_in.getText();
                String author = author_name_in.getText();
                String publisher = publisher_name_in.getText();
                
                String str_price = price_in.getText();
                Double price = Double.parseDouble(str_price);

                submit_btn.setText("Adding book...");
                submit_btn.setEnabled(false);

                SwingWorker<Map<String, Object>, Void> worker = new SwingWorker<Map<String, Object>,Void>() {
                @Override
                protected Map<String, Object> doInBackground(){
                    return bookController.addBook(title, author, publisher, price);
                }

                @Override
                protected void done() {
                    try {
                        Map<String, Object> res = get();

                        submit_btn.setText("Submit");
                        submit_btn.setEnabled(true);

                        if(res != null && (Boolean) res.get("state")){
                            book_title_in.setText("");
                            author_name_in.setText("");
                            publisher_name_in.setText("");
                            price_in.setText("");

                            JOptionPane.showMessageDialog(manage_books,
                                "Book added successfully",
                                "Insert success",
                                JOptionPane.INFORMATION_MESSAGE
                            );
                        } else{
                            book_title_in.setText("");
                            author_name_in.setText("");
                            publisher_name_in.setText("");
                            price_in.setText("");

                            JOptionPane.showMessageDialog(manage_books,
                                "Failed to add book",
                                "Insert unsuccessful",
                                JOptionPane.INFORMATION_MESSAGE
                            );
                        }
                    } catch (Exception e) {
                        
                    }
                }
            };
            worker.execute();
            }
        });

        book_entry_panel.add(info_form, BorderLayout.CENTER);

        JScrollPane scroll_bar = new JScrollPane(book_entry_panel);
        scroll_bar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll_bar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll_bar.getVerticalScrollBar().setUnitIncrement(16);
        scroll_bar.getVerticalScrollBar().setBlockIncrement(50);
        scroll_bar.setPreferredSize(new Dimension(600, 500));
        scroll_bar.setBorder(null);

        manage_books.add(header_panel);
        manage_books.add(separator);
        manage_books.add(scroll_bar);

        return manage_books;
    }
}
