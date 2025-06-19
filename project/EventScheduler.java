// Copyright 2025 Tavaheed Tariq, All rights reserved 
//  This file is part of the JAVA Theory Project.
//  Written by Tavaheed Tariq < https://github.com/Tawheed-tariq

import java.awt.*;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/*
 * Serializable Interface: It has no methods, but it allows the class to be serialized.
 * This means instances of this class can be converted into a byte stream, which can then be
 * saved to a file or sent over a network.
 */

class Event implements Serializable {
    String title, location, category;
    LocalDateTime dateTime;
    boolean reminder;

    public Event(String title, LocalDateTime dateTime, String location, String category, boolean reminder) {
        this.title = title;
        this.dateTime = dateTime;
        this.location = location;
        this.category = category;
        this.reminder = reminder;
    }

    public String[] toRow() {
        return new String[]{title, dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), 
                           location, category, reminder ? "Yes" : "No"};
    }
}

/**
 * JFrame: provides the basic structure for a window, including the title bar, borders, 
 * and the ability to close, minimize, and maximize the window.

 * JTable: displays data in a tabular format, allowing for easy viewing and manipulation of data.

 * DefaultTableModel: provides a default implementation of the TableModel interface,
 * allowing for easy management of the data displayed in a JTable.
 */

public class EventScheduler extends JFrame {
    private List<Event> events = new ArrayList<>();
    private JTable table;
    private DefaultTableModel model;
    private javax.swing.Timer reminderTimer;
    private static final String FILE = "events.dat";
    private final Color PRIMARY = new Color(33, 150, 243); //blue
    private final Color ACCENT = new Color(76, 175, 80); //green
    private final Color BACKGROUND = new Color(250, 250, 250); //gray

    public EventScheduler() {
        initializeUI();
        loadEvents();
        refreshTable();
        startReminderService();
    }

    private void initializeUI() {
        setTitle("Event Scheduler by Tawheed");
        setDefaultCloseOperation(EXIT_ON_CLOSE); // present in javax.swing.JFrame take int as argument : https://www.clear.rice.edu/comp310/JavaResources/frame_close.html
        setSize(900, 700);
        setLocationRelativeTo(null);  //enters the window in the center of the screen, if any component is passed, it will be placed in the center of that component
        getContentPane().setBackground(BACKGROUND);

        JPanel header = createStyledPanel(PRIMARY);
        header.add(createStyledLabel("Event Scheduler", Font.BOLD, 24, Color.WHITE));

        JPanel inputPanel = createInputPanel();

        JScrollPane tablePanel = createTablePanel();

        JPanel controlPanel = createControlPanel();

        setLayout(new BorderLayout(10, 10));
        add(header, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.WEST);
        add(tablePanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        getRootPane().setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    private JPanel createInputPanel() {
        JPanel panel = createStyledPanel(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Add New Event"));
        panel.setPreferredSize(new Dimension(280, 0));

        JTextField titleField = createStyledTextField();
        JTextField dateField = createStyledTextField();
        dateField.setText(LocalDate.now().toString());
        JTextField timeField = createStyledTextField();
        timeField.setText("10:00");
        JTextField locationField = createStyledTextField();
        locationField.setText("Chattabal, Srinagar");
        JComboBox<String> categoryBox = new JComboBox<>(new String[]{"Work", "Home", "Other"});
        categoryBox.setFont(new Font("Arial", Font.PLAIN, 12));
        JCheckBox reminderBox = new JCheckBox("Enable Reminder");
        JButton addBtn = createStyledButton("Add Event", ACCENT);

        panel.add(createFieldPanel("Title:", titleField));
        panel.add(createFieldPanel("Date (YYYY-MM-DD):", dateField));
        panel.add(createFieldPanel("Time (HH:MM):", timeField));
        panel.add(createFieldPanel("Location:", locationField));
        panel.add(createFieldPanel("Category:", categoryBox));
        panel.add(Box.createVerticalStrut(10));
        panel.add(reminderBox);
        panel.add(Box.createVerticalStrut(15));
        panel.add(addBtn);

        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addEvent(titleField, dateField, timeField, locationField, categoryBox, reminderBox);
            }
        });
        return panel;
    }

    private JScrollPane createTablePanel() {
        model = new DefaultTableModel(new String[]{"Title", "Date & Time", "Location", "Category", "Reminder"}, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setRowHeight(25);
        table.setGridColor(new Color(230, 230, 230)); // light gray for grid lines
        table.setSelectionBackground(new Color(225, 245, 254)); //light blue for selected cell

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createTitledBorder("Scheduled Events"));
        return scroll;
    }

    private JPanel createControlPanel() {
        JPanel panel = createStyledPanel(BACKGROUND);
        JTextField searchField = createStyledTextField();
        searchField.setPreferredSize(new Dimension(200, 32));
        JButton searchBtn = createStyledButton("Search", PRIMARY);
        JButton deleteBtn = createStyledButton("Delete Selected", new Color(244, 67, 54));
        JButton clearBtn = createStyledButton("Clear Search", new Color(158, 158, 158));

        panel.add(new JLabel("Search:"));
        panel.add(searchField);
        panel.add(searchBtn);
        panel.add(clearBtn);
        panel.add(Box.createHorizontalStrut(30));
        panel.add(deleteBtn);

        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchEvents(searchField.getText());
            }
        });
        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchField.setText("");
                refreshTable();
            }
        });
        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteSelected();
            }
        });

        return panel;
    }

/**
 *  This function creates a JPanel with a label and a field, using BorderLayout for layout management.
 *  It sets the label at the top and the field in the center, with an EmptyBorder for spacing.
 */
    private JPanel createFieldPanel(String label, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.add(new JLabel(label), BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);
        panel.setBorder(new EmptyBorder(5, 0, 5, 0));
        return panel;
    }

/**
 * This function creates a styled JPanel with a specified background color.
 * It uses FlowLayout for layout management.
 */
    private JPanel createStyledPanel(Color bg) {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(bg);
        return panel;
    }

/**
 *  This function creates a styled JLabel with specified text, font style, size, and color.
 */
    private JLabel createStyledLabel(String text, int style, int size, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", style, size));
        label.setForeground(color);
        return label;
    }

/**
 *  This function creates a styled JTextField with a specific font and border.
 *  It uses a compound border to combine a line border with an empty border for padding.
 *  The line border is set to a light gray color, and the empty border provides padding
 *  around the text field.
 */
    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Arial", Font.PLAIN, 12));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            new EmptyBorder(5, 8, 5, 8)));
        return field;
    }

/**
 *  This function creates a styled JButton with specified text and background color.
 *  The setFocusPainted method is used to disable the focus rectangle around the text on click or hover.
 */
    private JButton createStyledButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setBorder(new EmptyBorder(8, 15, 8, 15));
        btn.setFocusPainted(false);
        return btn;
    }

/**
 *   This function adds a new event to the list based on the input fields.
 */
    private void addEvent(JTextField titleField, JTextField dateField, JTextField timeField,
                         JTextField locationField, JComboBox<String> categoryBox, JCheckBox reminderBox) {
        try {
            String title = titleField.getText().trim();
            if (title.isEmpty()) throw new IllegalArgumentException("Title is required");

            LocalDate date = LocalDate.parse(dateField.getText().trim());
            LocalTime time = LocalTime.parse(timeField.getText().trim());
            LocalDateTime dateTime = LocalDateTime.of(date, time);

            if (dateTime.isBefore(LocalDateTime.now())) {
                JOptionPane.showMessageDialog(this, "Cannot schedule events in the past", "Invalid Date", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String location = locationField.getText().trim();
            String category = categoryBox.getSelectedItem().toString();
            boolean reminder = reminderBox.isSelected();

            events.add(new Event(title, dateTime, location, category, reminder));
            saveEvents();
            refreshTable();
            clearFields(titleField, dateField, timeField, locationField, reminderBox);
            JOptionPane.showMessageDialog(this, "Event added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

/**
 *  This function clears the input fields after an event is added.
 */
    private void clearFields(JTextField titleField, JTextField dateField, JTextField timeField,
                           JTextField locationField, JCheckBox reminderBox) {
        titleField.setText("");
        dateField.setText(LocalDate.now().toString());
        timeField.setText("12:00");
        locationField.setText("");
        reminderBox.setSelected(false);
    }

/**
 *  This function deletes the selected event from the table.
 *  It checks if a row is selected, removes the event from the list, saves the updated list to the file,
 *  refreshes the table, and shows a confirmation message.
 */
    private void deleteSelected() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            events.remove(row);
            saveEvents();
            refreshTable();
            JOptionPane.showMessageDialog(this, "Event deleted successfully!", "Deleted", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an event to delete", "No Selection", JOptionPane.WARNING_MESSAGE);
        }
    }

/**
 *  This function searches for events based on the query entered in the search field.
 *  It filters the events list and updates the table model with matching events.
 */
    private void searchEvents(String query) {
        if (query.trim().isEmpty()) { 
            refreshTable(); 
            return; 
        }
        model.setRowCount(0);
        for (Event e : events) {
            if (e.title.toLowerCase().contains(query.toLowerCase()) ||
                e.location.toLowerCase().contains(query.toLowerCase()) ||
                e.category.toLowerCase().contains(query.toLowerCase())) {
                model.addRow(e.toRow());
            }
        }
    }

/**
 *  This function refreshes the table by clearing the existing rows and sorting the events list based on date and time.
 *  It then adds each event to the table model as a new row.
 */
    private void refreshTable() {
        model.setRowCount(0);
        Collections.sort(events, new Comparator<Event>() {
            public int compare(Event e1, Event e2) {
                return e1.dateTime.compareTo(e2.dateTime);
            }
        });
        for (Event e : events) {
            model.addRow(e.toRow());
        }
    }

/**
 *  This function saves the list of events to a file using ObjectOutputStream.
 */
    private void saveEvents() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(events);
        } catch (IOException ignored) {}
    }

/**
 *   This function loads the list of events from a file using ObjectInputStream.
 *   It attempts to read the file and deserialize the list of events.
  
 * SupressWarnings("unchecked") is used to suppress warnings about unchecked type casting,
 * as the ObjectInputStream reads the data as a raw Object type and we cast it to List<Event>.
 * This is necessary because Java's generics do not retain type information at runtime,
 * so we cannot directly read a List<Event> without casting.
 */
    @SuppressWarnings("unchecked")
    private void loadEvents() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {
            events = (List<Event>) ois.readObject();
        } catch (Exception ignored) {}
    }

/**
 *  This function starts a timer that checks for reminders every 30 seconds.
 */
    private void startReminderService() {
        reminderTimer = new javax.swing.Timer(30000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkReminders();
            }
        });
        reminderTimer.start();
    }

/**
 *  This function checks the list of events for any reminders that are due at the current time.
 *  It compares the current time with the event's dateTime and shows a reminder dialog if
 *  the reminder is enabled and the event's dateTime matches the current time.
 */
    private void checkReminders() {
        LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);
        for (Event event : events) {
            if (event.reminder && event.dateTime.equals(now)) {
                showReminder(event);
            }
        }
    }

/**
 *  This function shows a reminder dialog for the specified event.
 *  It uses SwingUtilities.invokeLater to ensure that the dialog is shown on the Event Dispatch Thread (EDT),
 *  which is necessary for thread safety in Swing applications.
 */
    private void showReminder(Event event) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                String message = String.format("Event: %s\nTime: %s\nLocation: %s", 
                    event.title, 
                    event.dateTime.format(DateTimeFormatter.ofPattern("HH:mm")),
                    event.location.isEmpty() ? "No location" : event.location);
                
                JOptionPane.showMessageDialog(EventScheduler.this, message, "Event Reminder", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }


/**
 * As swing is not thread safe , we use SwingUtilities.invokeLater to ensure that the GUI is created on the Event Dispatch Thread (EDT).
 * EDT is the thread responsible for handling all GUI-related tasks in Swing applications.
 * This is important to avoid potential threading issues when updating the GUI.
 */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EventScheduler().setVisible(true);
            }
        });
    }
}