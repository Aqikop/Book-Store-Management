package code.bookstore.views;

import javax.swing.*;
import java.awt.image.*;
import java.io.File;
import java.util.List;
import java.util.Map;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;

import code.bookstore.controllers.checkout_controller;

public class checkoutview {
    private CardLayout page_container;
    private JPanel page;
    private checkout_controller checkoutController;
    private JPanel checkoutPanel; // Store the main panel reference

    public checkoutview(CardLayout page_container, JPanel page, checkout_controller controller) {
        this.page_container = page_container;
        this.page = page;
        this.checkoutController = controller;
    }

    public JPanel init_panel(){
        checkoutPanel = new JPanel(new BorderLayout());
        refreshCheckoutView(); // Initial load
        return checkoutPanel;
    }

    // Refresh checkout view with new data
    public void refreshCheckoutView(){
        checkoutPanel.removeAll(); // Clear existing content
        
        checkoutPanel.setBackground(Color.WHITE);

        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#e0e0e0")),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
            ));
        JLabel header_text = new JLabel("Review order");
        header_text.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
        header_text.setFont(new Font("Lato", Font.BOLD, 18));
        header.add(header_text, BorderLayout.WEST);

        JPanel main_content = new JPanel(new BorderLayout());
        main_content.setBackground(Color.WHITE);

        JPanel left_side = new JPanel(new BorderLayout());
        left_side.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Get fresh data from controller
        List<Map<String, Object>> checkout_items = checkoutController.get_checkout_items();
        
        // Add debugging
        System.out.println("Refreshing checkout - Items count: " + checkout_items.size());
        for(int i = 0; i < checkout_items.size(); i++) {
            System.out.println("Item " + i + ": " + checkout_items.get(i));
        }

        JPanel review = new JPanel();
        review.setLayout(new BoxLayout(review, BoxLayout.Y_AXIS));
        review.setBackground(Color.WHITE);
        review.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.decode("#e0e0e0"), 3),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        // Handle empty cart vs items
        if(checkout_items.isEmpty()) {
            review.setPreferredSize(new Dimension(500, 200));
            JLabel no_items = new JLabel("No items in checkout");
            no_items.setFont(new Font("Lato", Font.BOLD, 18));
            no_items.setForeground(Color.GRAY);
            no_items.setHorizontalAlignment(JLabel.CENTER);
            no_items.setVerticalAlignment(JLabel.CENTER);
            review.add(Box.createVerticalGlue());
            review.add(no_items);
            review.add(Box.createVerticalGlue());
        } else {
            review.setPreferredSize(new Dimension(500, 180 * checkout_items.size()));
            
            // Create book entries from fresh data
            for(int i = 0; i < checkout_items.size(); i++){
                Map<String, Object> book = checkout_items.get(i);
                JPanel book_entry = create_book_entry(book);
                review.add(book_entry);

                if(i < checkout_items.size() - 1){
                    review.add(Box.createRigidArea(new Dimension(0, 10)));
                }
            }
        }
        
        JPanel pay_info_panel = payment_info();
        pay_info_panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(20, 0, 0, 0), 
            pay_info_panel.getBorder()
        ));

        JPanel review_payment = new JPanel();
        review_payment.setLayout(new BoxLayout(review_payment, BoxLayout.Y_AXIS));
        review_payment.setBackground(Color.WHITE);
        review_payment.add(review);
        review_payment.add(pay_info_panel);

        JScrollPane scroll_bar = new JScrollPane(review_payment);
        scroll_bar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll_bar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll_bar.getVerticalScrollBar().setUnitIncrement(16);
        scroll_bar.getVerticalScrollBar().setBlockIncrement(50);
        scroll_bar.setPreferredSize(new Dimension(600, 500));
        scroll_bar.setBorder(null);

        left_side.add(scroll_bar, BorderLayout.CENTER);

        // Create order summary with fresh data
        JPanel order_summary = createOrderSummary(checkout_items);

        main_content.add(left_side, BorderLayout.CENTER);
        main_content.add(order_summary, BorderLayout.EAST);

        checkoutPanel.add(header, BorderLayout.NORTH);
        checkoutPanel.add(main_content, BorderLayout.CENTER);
        
        // Refresh the display
        checkoutPanel.revalidate();
        checkoutPanel.repaint();
    }

    private JPanel createOrderSummary(List<Map<String, Object>> checkout_items) {
        JPanel order_summary = new JPanel(new BorderLayout());
        order_summary.setPreferredSize(new Dimension(280, 0));
        order_summary.setBackground(Color.WHITE);
        order_summary.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#e0e0e0"), 3),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
            ));
        JLabel summary_text = new JLabel("Order Summary");
        summary_text.setFont(new Font("Lato", Font.BOLD, 18));
        summary_text.setForeground(Color.decode("#2c3e50"));
        summary_text.setBorder(BorderFactory.createEmptyBorder(0 ,0, 10, 0));
        order_summary.add(summary_text, BorderLayout.NORTH);
        
        JPanel summary_content = new JPanel();
        summary_content.setLayout(new BoxLayout(summary_content, BoxLayout.Y_AXIS));
        summary_content.setOpaque(false);

        // Calculate actual totals
        double subtotal = calculateSubtotal(checkout_items);
        double shipping = 9.99;
        double total = subtotal + shipping;

        summary_content.add(price_row("Item (" + checkout_items.size() + "):", "$" + String.format("%.2f", subtotal)));
        summary_content.add(price_row("Shipping", "$" + String.format("%.2f", shipping)));

        JSeparator separator = new JSeparator();
        separator.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        summary_content.add(separator);

        JPanel total_row = new JPanel(new BorderLayout());
        total_row.setOpaque(false);
        total_row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        total_row.setBorder(BorderFactory.createEmptyBorder(5, 0, 15, 0));

        JLabel total_label = new JLabel("Total:");
        total_label.setFont(new Font("Lato", Font.PLAIN, 15));
        total_label.setForeground(Color.BLACK);

        JLabel total_price = new JLabel("$" + String.format("%.2f", total));
        total_price.setFont(new Font("Lato", Font.BOLD, 15));
        total_price.setForeground(Color.decode("#e74c3c"));

        total_row.add(total_label, BorderLayout.WEST);
        total_row.add(total_price, BorderLayout.EAST);
        summary_content.add(total_row);

        order_summary.add(summary_content, BorderLayout.CENTER);

        JButton checkout_btn = new JButton("Complete Payment");
        checkout_btn.setMaximumSize(new Dimension(240, 40));
        checkout_btn.setBackground(Color.decode("#0651c9"));
        checkout_btn.setForeground(Color.WHITE);
        checkout_btn.setFont(new Font("Lato", Font.BOLD, 15));
        checkout_btn.setFocusPainted(false);
        checkout_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkout_btn.setBorder(BorderFactory.createEmptyBorder(10 ,15, 10, 15));
        checkout_btn.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                page_container.show(page, "Receipt");
                page.revalidate();
            }
        });

        order_summary.add(checkout_btn, BorderLayout.SOUTH);
        
        return order_summary;
    }

    // Calculate total price
    private double calculateSubtotal(List<Map<String, Object>> items) {
        double subtotal = 0.0;
        for(Map<String, Object> item : items) {
            if(item.get("totalPrice") != null) {
                subtotal += (Double) item.get("totalPrice");
            }
        }
        return subtotal;
    }

    private JPanel price_row(String row, String price){
        JPanel price_row = new JPanel(new BorderLayout());
        price_row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        price_row.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        price_row.setOpaque(false);

        JLabel row_name = new JLabel(row);
        row_name.setFont(new Font("Lato", Font.PLAIN, 13));
        row_name.setForeground(Color.BLACK);

        JLabel row_price = new JLabel(price);
        row_price.setFont(new Font("Lato", Font.PLAIN, 13));
        row_price.setForeground(Color.BLACK);

        price_row.add(row_name, BorderLayout.WEST);
        price_row.add(row_price, BorderLayout.EAST);

        return price_row;
    }

    private JPanel payment_info(){
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

        JLabel shipping_types = new JLabel("Shipping Types");
        shipping_types.setFont(new Font("Lato", Font.BOLD, 13));
        gbc.gridx = 5; 
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        info_form.add(shipping_types, gbc);

        String[] ship_types = {"Standard", "Express"};
        JPanel shipping_types_panel = new JPanel(new BorderLayout());
        shipping_types_panel.setOpaque(false);
        gbc.gridx = 5; 
        gbc.gridy = 7;
        gbc.gridwidth = 2;

        JComboBox<String> cb = new JComboBox<>(ship_types);
        cb.setFont(new Font("Lato", Font.PLAIN, 13));
        cb.setOpaque(false);
        cb.addActionListener(e ->{
            String types = (String) cb.getSelectedItem();
            System.out.println("Choice: " + types);
        });
        shipping_types_panel.add(cb, BorderLayout.WEST);
        info_form.add(shipping_types_panel, gbc);

        payment_info.add(info_form, BorderLayout.WEST);

        return payment_info;
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
        } catch(Exception e){
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

        JLabel book_price = new JLabel("$" + String.format("%.2f", (Double) book.get("totalPrice")));
        book_price.setFont(new Font("Lato", Font.BOLD, 25));
        book_price.setForeground(Color.decode("#26ed5b"));
        book_price.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Quantity display
        JPanel quantity_display = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        quantity_display.setAlignmentX(Component.LEFT_ALIGNMENT);
        quantity_display.setOpaque(false);

        JLabel quantity_text = new JLabel("Quantity: " + book.get("quantity"));
        quantity_text.setFont(new Font("Lato", Font.PLAIN, 14));
        quantity_display.add(quantity_text);

        book_info.add(book_title);
        book_info.add(book_author);
        book_info.add(book_price);
        book_info.add(quantity_display);

        book_entry.add(book_cover_label, BorderLayout.WEST);
        book_entry.add(book_info, BorderLayout.CENTER);

        return book_entry;
    }
}