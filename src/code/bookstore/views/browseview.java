package code.bookstore.views;

import javax.swing.*;
import java.awt.image.*;
import java.io.File;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import code.bookstore.models.books;

public class browseview {
    String output_search;
    public JPanel init_panel(){
        JPanel browse = new JPanel(new BorderLayout());
        
        JPanel search_bar = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.anchor = GridBagConstraints.CENTER;

        JTextField search_text = new JTextField(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                if(getText().isEmpty() && ! (FocusManager.getCurrentKeyboardFocusManager().getFocusOwner() == this)){
                    Graphics2D g2 = (Graphics2D)g.create();
                    g2.setFont(getFont().deriveFont(Font.ITALIC));
                    g2.setColor(Color.decode("#999999"));
                    g2.drawString("Enter book name", 10, 25);
                    g2.dispose();
                }
            }
        };
        search_text.setPreferredSize(new Dimension(280, 40));
        search_text.setFont(new Font("Lato", Font.PLAIN, 15));
        search_text.setToolTipText("Enter book name");
        gbc.fill = GridBagConstraints.VERTICAL;
        search_bar.add(search_text);

        JButton search_btn = new JButton("🔍");
        search_btn.setPreferredSize(new Dimension(50, 40));
        search_btn.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
        search_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gbc.gridx = 1;
        search_bar.add(search_btn);

        JPanel result_container = new JPanel(new BorderLayout());
        result_container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel search_result_text = new JLabel("Search result for:");
        search_result_text.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
        search_result_text.setFont(new Font("Lato", Font.BOLD, 25));
        result_container.add(search_result_text, BorderLayout.NORTH);

        JPanel result_list = new JPanel();
        result_list.setLayout(new BoxLayout(result_list, BoxLayout.Y_AXIS));
        result_list.setBackground(Color.WHITE);
        result_list.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        search_btn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                output_search = search_text.getText();
                search_result_text.setText("Search result for: " + "'" + output_search + "'");
                
                result_list.removeAll();
                
                List<Map<String, Object>> search_results = get_results(output_search);

                if(search_results.isEmpty()){
                    JLabel no_res = new JLabel("No books found.");
                    no_res.setFont(new Font("Lato", Font.BOLD, 18));
                    no_res.setForeground(Color.GRAY);
                    result_list.add(no_res);
                }
                else{
                    for(int i = 0; i < search_results.size(); i++){
                        Map<String, Object> book = search_results.get(i);

                        JPanel book_entry = create_book_entry(book);

                        result_list.add(book_entry);

                        if(i < search_results.size() - 1){
                            result_list.add(Box.createRigidArea(new Dimension(0, 10)));
                        }
                    }
                }
                
                search_text.setText("");
                search_text.revalidate();
                search_result_text.revalidate();
                result_list.revalidate();
                result_list.repaint();
            }
        });

        search_text.addActionListener(e ->{
            output_search = search_text.getText();
            search_result_text.setText("Search result for: " + "'" + output_search + "'");
            
            result_list.removeAll();
            
            List<Map<String, Object>> search_results = get_results(output_search);

            if(search_results.isEmpty()){
                JLabel no_res = new JLabel("No books found.");
                no_res.setFont(new Font("Lato", Font.BOLD, 18));
                no_res.setForeground(Color.GRAY);
                result_list.add(no_res);
            }
            else{
                for(int i = 0; i < search_results.size(); i++){
                    Map<String, Object> book = search_results.get(i);

                    JPanel book_entry = create_book_entry(book);

                    result_list.add(book_entry);

                    if(i < search_results.size() - 1){
                        result_list.add(Box.createRigidArea(new Dimension(0, 10)));
                    }
                }
            }
            
            search_text.setText("");
            search_text.revalidate();
            search_result_text.revalidate();
            result_list.revalidate();
            result_list.repaint();
        });;
        
        JScrollPane scroll_bar = new JScrollPane(result_list);
        scroll_bar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll_bar.getVerticalScrollBar().setUnitIncrement(10);
        scroll_bar.setBorder(null);

        result_container.add(scroll_bar, BorderLayout.CENTER);

        browse.add(search_bar, BorderLayout.NORTH);
        browse.add(result_container, BorderLayout.CENTER);

        return browse;
    }

    public List<Map<String, Object>> get_results(String text){
        try{
            return books.search_books(text.trim());
        } catch(Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    private JPanel create_book_entry(Map<String, Object> book){
        JPanel book_entry = new JPanel(new BorderLayout());
        book_entry.setBackground(Color.decode("#ffffff"));
        book_entry.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#e0e0e0")),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        book_entry.setMaximumSize(new Dimension(Integer.MAX_VALUE, 170));
        book_entry.setPreferredSize(new Dimension(0, 170));

        JLabel book_cover_label = new JLabel();
        try{
            BufferedImage book_cover = ImageIO.read(new File("src\\resources\\images\\"));
            Image scaled = book_cover.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            book_cover_label.setIcon(new ImageIcon(scaled));
        } catch(Exception ex){
            book_cover_label.setText("No img available");
            book_cover_label.setPreferredSize(new Dimension(150, 110));
            book_cover_label.setHorizontalAlignment(JLabel.CENTER);
            book_cover_label.setVerticalAlignment(JLabel.CENTER);
            book_cover_label.setOpaque(false);
        }
        book_cover_label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));

        // Book info panel
        JPanel book_info = new JPanel();
        book_info.setLayout(new BoxLayout(book_info, BoxLayout.Y_AXIS));
        book_info.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        book_info.setOpaque(false);

        JLabel book_title = new JLabel((String) book.get("bookName"));
        book_title.setFont(new Font("Lato", Font.PLAIN, 25));
        book_title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel book_author = new JLabel("by " + (String) book.get("authorName"));
        book_author.setFont(new Font("Lato", Font.ITALIC, 14));
        book_author.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel book_price = new JLabel("$" + String.format("%.2f", (Double) book.get("price")));
        book_price.setFont(new Font("Lato", Font.BOLD, 30));
        book_price.setForeground(Color.decode("#e74c3c"));
        book_price.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel book_pub = new JLabel("publisher:  " + (String) book.get("publisherName"));
        book_pub.setFont(new Font("Lato", Font.PLAIN, 12));
        book_pub.setAlignmentX(Component.LEFT_ALIGNMENT); 

        JPanel shop_panel = new JPanel();
        shop_panel.setLayout(new BoxLayout(shop_panel, BoxLayout.Y_AXIS));
        shop_panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 10));
        shop_panel.setPreferredSize(new Dimension(180, 160));
        shop_panel.setOpaque(false);

        // Quantity selection
        JPanel quantity_choose = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        quantity_choose.setAlignmentX(Component.LEFT_ALIGNMENT);
        quantity_choose.setOpaque(false);

        JLabel quantity_text = new JLabel("Quantity:");
        quantity_text.setFont(new Font("Lato", Font.PLAIN, 14));

        JSpinner quantity_scoller = new JSpinner(new SpinnerNumberModel(1, 1, 99 ,1));
        quantity_scoller.setFont(new Font("Lato", Font.PLAIN, 14));
        quantity_scoller.setPreferredSize(new Dimension(60, 30));

        quantity_choose.add(quantity_text);
        quantity_choose.add(Box.createRigidArea(new Dimension(2, 0)));
        quantity_choose.add(quantity_scoller);

        // Buy button
        JButton buy_btn = new JButton("Buy now");
        buy_btn.setMaximumSize(new Dimension(150 , 35));
        buy_btn.setBackground(Color.decode("#27ae60"));
        buy_btn.setForeground(Color.WHITE);
        buy_btn.setFont(new Font("Lato", Font.BOLD, 14));
        buy_btn.setFocusPainted(false);
        buy_btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        buy_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buy_btn.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Add to cart button
        JButton cart_btn = new JButton("Add to cart");
        cart_btn.setMaximumSize(new Dimension(150 , 35));
        cart_btn.setBackground(Color.decode("#3498db"));
        cart_btn.setForeground(Color.WHITE);
        cart_btn.setFont(new Font("Lato", Font.BOLD, 14));
        cart_btn.setFocusPainted(false);
        cart_btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        cart_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cart_btn.setAlignmentX((Component.LEFT_ALIGNMENT));

        buy_btn.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                //int qty = (Integer) quantity_scoller.getValue();
            }
        });

        cart_btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                //int qty = (Integer) quantity_scoller.getValue();
            }
        });

        shop_panel.add(quantity_choose);
        shop_panel.add(Box.createRigidArea(new Dimension(0, 3)));
        shop_panel.add(buy_btn);
        shop_panel.add(Box.createRigidArea(new Dimension(0, 3)));
        shop_panel.add(cart_btn);

        book_info.add(book_title);
        book_info.add(book_author);
        book_info.add(book_price);
        book_info.add(book_pub);

        book_entry.add(book_cover_label, BorderLayout.WEST);
        book_entry.add(book_info, BorderLayout.CENTER);
        book_entry.add(shop_panel, BorderLayout.EAST);

        return book_entry;
    }
}
