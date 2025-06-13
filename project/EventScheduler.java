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

public class EventScheduler extends JFrame {
    private List<Event> events = new ArrayList<>();
    private JTable table;
    private DefaultTableModel model;
    private javax.swing.Timer reminderTimer;
    private static final String FILE = "events.dat";
    private final Color PRIMARY = new Color(33, 150, 243);
    private final Color ACCENT = new Color(76, 175, 80);
    private final Color BACKGROUND = new Color(250, 250, 250);

    public EventScheduler() {
        initializeUI();
        loadEvents();
        refreshTable();
        startReminderService();
    }

    private void initializeUI() {
        setTitle("Event Scheduler Pro");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BACKGROUND);

        // Header
        JPanel header = createStyledPanel(PRIMARY);
        header.add(createStyledLabel("Event Scheduler", Font.BOLD, 24, Color.WHITE));

        // Input Panel
        JPanel inputPanel = createInputPanel();

        // Table Panel
        JScrollPane tablePanel = createTablePanel();

        // Control Panel
        JPanel controlPanel = createControlPanel();

        // Layout
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
        timeField.setText("12:00");
        JTextField locationField = createStyledTextField();
        JComboBox<String> categoryBox = new JComboBox<>(new String[]{"Work", "Personal", "Meeting", "Other"});
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
        table.setGridColor(new Color(230, 230, 230));
        table.setSelectionBackground(new Color(225, 245, 254));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createTitledBorder("Scheduled Events"));
        return scroll;
    }

    private JPanel createControlPanel() {
        JPanel panel = createStyledPanel(BACKGROUND);
        JTextField searchField = createStyledTextField();
        searchField.setPreferredSize(new Dimension(200, 30));
        JButton searchBtn = createStyledButton("Search", PRIMARY);
        JButton deleteBtn = createStyledButton("Delete Selected", new Color(244, 67, 54));
        JButton clearBtn = createStyledButton("Clear Search", new Color(158, 158, 158));

        panel.add(new JLabel("Search:"));
        panel.add(searchField);
        panel.add(searchBtn);
        panel.add(clearBtn);
        panel.add(Box.createHorizontalStrut(20));
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

    private JPanel createFieldPanel(String label, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.add(new JLabel(label), BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);
        panel.setBorder(new EmptyBorder(5, 0, 5, 0));
        return panel;
    }

    private JPanel createStyledPanel(Color bg) {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(bg);
        return panel;
    }

    private JLabel createStyledLabel(String text, int style, int size, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", style, size));
        label.setForeground(color);
        return label;
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Arial", Font.PLAIN, 12));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            new EmptyBorder(5, 8, 5, 8)));
        return field;
    }

    private JButton createStyledButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setBorder(new EmptyBorder(8, 15, 8, 15));
        btn.setFocusPainted(false);
        return btn;
    }

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

    private void clearFields(JTextField titleField, JTextField dateField, JTextField timeField,
                           JTextField locationField, JCheckBox reminderBox) {
        titleField.setText("");
        dateField.setText(LocalDate.now().toString());
        timeField.setText("12:00");
        locationField.setText("");
        reminderBox.setSelected(false);
    }

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

    private void saveEvents() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(events);
        } catch (IOException ignored) {}
    }

    @SuppressWarnings("unchecked")
    private void loadEvents() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {
            events = (List<Event>) ois.readObject();
        } catch (Exception ignored) {}
    }

    private void startReminderService() {
        reminderTimer = new javax.swing.Timer(30000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkReminders();
            }
        });
        reminderTimer.start();
    }

    private void checkReminders() {
        LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);
        for (Event event : events) {
            if (event.reminder && event.dateTime.equals(now)) {
                showReminder(event);
            }
        }
    }

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EventScheduler().setVisible(true);
            }
        });
    }
}