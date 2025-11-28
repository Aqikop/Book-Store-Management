package code.bookstore.views;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.image.*;
import java.io.File;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;

public class checkoutview {
    

    public JPanel init_panel(){
        JPanel checkout = new JPanel(new BorderLayout());
        checkout.setBackground(Color.WHITE);

        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#e0e0e0")),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
            ));
        JLabel header_text = new JLabel("Review order");
        header_text.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
        header_text.setFont(new Font("Lato", Font.BOLD, 18));
        header.add(header_text, BorderLayout.WEST);

        // Sample data 
        String[] titles = {"The Heart-Shaped Tin", "To Kill a Mockingbird", "Abc"};
        String[] authors = {"Bee Wilson", "Harper Lee", "Bac"};
        String[] publishers = {"Penguin Books", "Harper Perennial"};
        double[] prices = {19.99, 15.99, 99.99};

        int[] quantities = new int[titles.length];
        double[] itemTotals = new double[titles.length];

        for(int i = 0; i < titles.length; i++){
            quantities[i] = 1;
            itemTotals[i] = prices[i] * quantities[i];
        }


        JPanel review = new JPanel();
        review.setLayout(new BoxLayout(review, BoxLayout.Y_AXIS));
        review.setBackground(Color.WHITE);
        review.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.decode("#e0e0e0"), 3),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        review.setPreferredSize(new Dimension(500, 180 * titles.length));

        JPanel order_summary = new JPanel(new BorderLayout());
        order_summary.setPreferredSize(new Dimension(280, 0));
        order_summary.setBackground(Color.white);
        order_summary.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.decode("#e0e0e0"),3),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        JLabel summary_text = new JLabel("Order Summary");
        summary_text.setFont(new Font("Lato", Font.BOLD, 18));
        summary_text.setForeground(Color.decode("#2c3e50"));
        summary_text.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        order_summary.add(summary_text, BorderLayout.NORTH);

        JPanel summary_content = new JPanel();
        summary_content.setLayout(new BoxLayout(summary_content, BoxLayout.Y_AXIS));
        summary_content.setOpaque(false);

        // Create labels for item subtotal and shipping and total
        JLabel itemSubtotalLabel = new JLabel("$" + String.format("%.2f", sumArray(itemTotals)));
        summary_content.add(price_row("Items:", itemSubtotalLabel));

        JLabel shippingLabel = new JLabel("$" + String.format("%.2f", 9.99));
        summary_content.add(price_row("Shipping", shippingLabel));

        JSeparator separator = new JSeparator();
        separator.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        summary_content.add(separator);

        JPanel total_row = new JPanel(new BorderLayout());
        total_row.setOpaque(false);
        total_row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        total_row.setBorder(BorderFactory.createEmptyBorder(5, 0, 15, 0));

        JLabel total = new JLabel("Total:");
        total.setFont(new Font("Lato", Font.PLAIN, 15));
        total.setForeground(Color.BLACK);

        JLabel totalPriceLabel = new JLabel("$" + String.format("%.2f", sumArray(itemTotals) + 9.99));
        totalPriceLabel.setFont(new Font("Lato", Font.BOLD, 15));
        totalPriceLabel.setForeground(Color.BLACK);

        total_row.add(total, BorderLayout.WEST);
        total_row.add(totalPriceLabel, BorderLayout.EAST);
        summary_content.add(total_row);

        order_summary.add(summary_content, BorderLayout.CENTER);

        JButton checkout_btn = new JButton("Confirm and Pay");
        checkout_btn.setMaximumSize(new Dimension(240, 40));
        checkout_btn.setBackground(Color.decode("#0651c9"));
        checkout_btn.setForeground(Color.WHITE);
        checkout_btn.setFont(new Font("Lato", Font.BOLD, 15));
        checkout_btn.setFocusPainted(false);
        checkout_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkout_btn.setBorder(BorderFactory.createEmptyBorder(10 ,15, 10, 15));

        order_summary.add(checkout_btn, BorderLayout.SOUTH);


        // Iterate through search result (i < n)
        for(int i = 0; i < titles.length; i++){
            final int index = i;

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
                // try to load a specific image per book if exists; otherwise exception handled
                File imgFile = new File("src\\resources\\images\\" + (titles[i].replaceAll("\\s+", "_") + ".jpg"));
                if (!imgFile.exists()) {
                    // fallback general file or skip
                    throw new Exception("No image file");
                }
                BufferedImage book_cover = ImageIO.read(imgFile);
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

            JLabel book_title = new JLabel(titles[i]);
            book_title.setFont(new Font("Lato", Font.PLAIN, 25));
            book_title.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel book_author = new JLabel("by " + authors[i]);
            book_author.setFont(new Font("Lato", Font.ITALIC, 14));
            book_author.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel book_price = new JLabel("$" + String.format("%.2f", prices[i]));
            book_price.setFont(new Font("Lato", Font.BOLD, 25));
            book_price.setForeground(Color.decode("#26ed5b"));
            book_price.setAlignmentX(Component.LEFT_ALIGNMENT);

            // Quantity selection
            JPanel quantity_choose = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            quantity_choose.setAlignmentX(Component.LEFT_ALIGNMENT);
            quantity_choose.setOpaque(false);

            JLabel quantity_text = new JLabel("Quantity:");
            quantity_text.setFont(new Font("Lato", Font.PLAIN, 14));

            JSpinner quantity_scoller = new JSpinner(new SpinnerNumberModel(1, 1, 99 ,1));
            quantity_scoller.setFont(new Font("Lato", Font.PLAIN, 14));
            quantity_scoller.setPreferredSize(new Dimension(60, 30));

            JLabel update_price = new JLabel("Update");
            update_price.setFont(new Font("Lato", Font.PLAIN, 14));

            quantity_choose.add(quantity_text);
            quantity_choose.add(Box.createRigidArea(new Dimension(2, 0)));
            quantity_choose.add(quantity_scoller);
            quantity_choose.add(Box.createRigidArea(new Dimension(1, 0)));
            quantity_choose.add(update_price);

            book_info.add(book_title);
            book_info.add(book_author);
            book_info.add(book_price);
            book_info.add(quantity_choose);

            book_entry.add(book_cover_label, BorderLayout.WEST);
            book_entry.add(book_info, BorderLayout.CENTER);

             // Add change listener to update item total and summary
            quantity_scoller.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    int q = (int) quantity_scoller.getValue();
                    quantities[index] = q;
                    itemTotals[index] = prices[index] * q;
                    book_price.setText("$" + String.format("%.2f", itemTotals[index]));

                    // update summary labels
                    double subtotal = sumArray(itemTotals);
                    itemSubtotalLabel.setText("$" + String.format("%.2f", subtotal));
                    totalPriceLabel.setText("$" + String.format("%.2f", subtotal + 9.99));
                }
            });



            review.add(book_entry);

            if(i < titles.length - 1){
                review.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }
       
        JScrollPane scroll_bar = new JScrollPane(review);
        scroll_bar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll_bar.getVerticalScrollBar().setUnitIncrement(16);
        scroll_bar.setBorder(null);

        checkout.add(header, BorderLayout.NORTH);
        checkout.add(scroll_bar, BorderLayout.CENTER);
        checkout.add(order_summary, BorderLayout.EAST);

        return checkout;
    }

    private JPanel price_row(String row, JLabel priceLabel){
        JPanel price_row = new JPanel(new BorderLayout());
        price_row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        price_row.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        price_row.setOpaque(false);

        JLabel row_name = new JLabel(row);
        row_name.setFont(new Font("Lato", Font.PLAIN, 13));
        row_name.setForeground(Color.BLACK);

        JLabel row_price = priceLabel;
        row_price.setFont(new Font("Lato", Font.PLAIN, 13));
        row_price.setForeground(Color.BLACK);

        price_row.add(row_name, BorderLayout.WEST);
        price_row.add(row_price, BorderLayout.EAST);

        return price_row;
    }
     // helper to sum array
    private double sumArray(double[] arr){
        double s = 0;
        if (arr == null) return 0;
        for(double v : arr) s += v;
        return s;
    }
}