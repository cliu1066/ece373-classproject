package org.system.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;

import org.system.booking.*;
import org.system.listing.FlightListing;
import org.system.listing.HotelListing;
import org.system.payment.*;
import org.system.people.*;

/**
 * GUI for the ECE373 travel booking system.
 *
 * Top menu bar:
 *   File    : Save, Load, Close
 *   Admin   : Add Flight, Update Flight, Remove / Update Hotels
 *   Customer: Set Email, Search Flights & Hotels, Book, Pay,
 *             Booking History, Cancel Booking
 *
 * NOTE: For File -> Save/Load to fully work, BookingSystem and all
 * domain classes should implement java.io.Serializable. If they do
 * not, Save/Load will show an error dialog.
 */
public class BookingSystemGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private BookingSystem bookingSystem;

    private CardLayout cardLayout;
    private JPanel cardPanel;

    private SearchPanel searchPanel;
    private CustomerHistoryPanel historyPanel;

    private static final String CARD_WELCOME             = "WELCOME";
    private static final String CARD_ADMIN_ADD_FLIGHT    = "ADMIN_ADD_FLIGHT";
    private static final String CARD_ADMIN_UPDATE_FLIGHT = "ADMIN_UPDATE_FLIGHT";
    private static final String CARD_ADMIN_HOTELS        = "ADMIN_HOTELS";

    private static final String CARD_ADMIN_ADD_HOTEL     = "ADMIN_ADD_HOTEL";
    private static final String CARD_ADMIN_REMOVE_FLIGHT = "ADMIN_REMOVE_FLIGHT";

    private static final String CARD_CUST_SET_EMAIL      = "CUST_SET_EMAIL";
    private static final String CARD_CUST_SEARCH         = "CUST_SEARCH";
    private static final String CARD_CUST_BOOK           = "CUST_BOOK";
    private static final String CARD_CUST_PAY            = "CUST_PAY";
    private static final String CARD_CUST_HISTORY        = "CUST_HISTORY";
    private static final String CARD_CUST_CANCEL         = "CUST_CANCEL";

    public BookingSystemGUI(BookingSystem system) {
        super("ECE373 Travel Booking System");
        this.bookingSystem = system;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        initMenuBar();
        initCards();

        setVisible(true);
    }
       
    // MENU BAR

    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.addActionListener(e -> onSave());
        JMenuItem loadItem = new JMenuItem("Load");
        loadItem.addActionListener(e -> onLoad());
        JMenuItem closeItem = new JMenuItem("Close");
        closeItem.addActionListener(e -> dispose());
        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        fileMenu.addSeparator();
        fileMenu.add(closeItem);

        //  Admin 
        JMenu adminMenu = new JMenu("Admin");

        JMenuItem addFlightItem = new JMenuItem("Add Flight");
        addFlightItem.addActionListener(e -> showCard(CARD_ADMIN_ADD_FLIGHT));

        JMenuItem updateFlightItem = new JMenuItem("Update Flight");
        updateFlightItem.addActionListener(e -> showCard(CARD_ADMIN_UPDATE_FLIGHT));

        JMenuItem removeFlightItem = new JMenuItem("Remove Flight");
        removeFlightItem.addActionListener(e -> showCard(CARD_ADMIN_REMOVE_FLIGHT));

        JMenuItem addHotelItem = new JMenuItem("Add Hotel");               
        addHotelItem.addActionListener(e -> showCard(CARD_ADMIN_ADD_HOTEL));

        JMenuItem hotelsItem = new JMenuItem("Remove/Update Hotels");
        hotelsItem.addActionListener(e -> showCard(CARD_ADMIN_HOTELS));
        
        JMenuItem printItem = new JMenuItem("Print All Data");
        printItem.addActionListener(e -> showPrintWindow());

        adminMenu.add(addFlightItem);
        adminMenu.add(updateFlightItem);
        adminMenu.add(removeFlightItem);
        adminMenu.add(addHotelItem);       
        adminMenu.add(hotelsItem);
        adminMenu.addSeparator();
        adminMenu.add(printItem);

        //  Customer 
        JMenu customerMenu = new JMenu("Customer");
        JMenuItem setEmailItem = new JMenuItem("Set Email");
        setEmailItem.addActionListener(e -> showCard(CARD_CUST_SET_EMAIL));
        JMenuItem searchItem = new JMenuItem("Search Flights & Hotels");
        searchItem.addActionListener(e -> showCard(CARD_CUST_SEARCH));
        JMenuItem bookItem = new JMenuItem("Book Flights & Hotel");
        bookItem.addActionListener(e -> showCard(CARD_CUST_BOOK));
        JMenuItem payItem = new JMenuItem("Pay");
        payItem.addActionListener(e -> showCard(CARD_CUST_PAY));
        JMenuItem historyItem = new JMenuItem("Booking History");
        historyItem.addActionListener(e -> showCard(CARD_CUST_HISTORY));
        JMenuItem cancelItem = new JMenuItem("Cancel Booking");
        cancelItem.addActionListener(e -> showCard(CARD_CUST_CANCEL));
        JMenuItem printFlightsHotelsItem = new JMenuItem("Print Flights & Hotels");
        printFlightsHotelsItem.addActionListener(e -> showCustomerPrintWindow());

        customerMenu.add(setEmailItem);
        customerMenu.add(searchItem);
        customerMenu.add(bookItem);
        customerMenu.add(payItem);
        customerMenu.add(historyItem);
        customerMenu.add(cancelItem);
        customerMenu.addSeparator();
        customerMenu.add(printFlightsHotelsItem);

        menuBar.add(fileMenu);
        menuBar.add(adminMenu);
        menuBar.add(customerMenu);

        setJMenuBar(menuBar);
    }

    /* CARDS */

    private void initCards() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Welcome
        cardPanel.add(new WelcomePanel(), CARD_WELCOME);

        // Admin panels
        cardPanel.add(new AdminAddFlightPanel(), CARD_ADMIN_ADD_FLIGHT);
        cardPanel.add(new AdminUpdateFlightPanel(), CARD_ADMIN_UPDATE_FLIGHT);
        cardPanel.add(new AdminRemoveFlightPanel(), CARD_ADMIN_REMOVE_FLIGHT); 
        cardPanel.add(new AdminAddHotelPanel(), CARD_ADMIN_ADD_HOTEL);
        cardPanel.add(new AdminHotelsPanel(), CARD_ADMIN_HOTELS);

        // Customer panels
        cardPanel.add(new CustomerSetEmailPanel(), CARD_CUST_SET_EMAIL);
        searchPanel = new SearchPanel();
        cardPanel.add(searchPanel, CARD_CUST_SEARCH);
        cardPanel.add(new CustomerBookPanel(), CARD_CUST_BOOK);
        cardPanel.add(new CustomerPayPanel(), CARD_CUST_PAY);
        historyPanel = new CustomerHistoryPanel();
        cardPanel.add(historyPanel, CARD_CUST_HISTORY);
        cardPanel.add(new CustomerCancelPanel(), CARD_CUST_CANCEL);

        add(cardPanel, BorderLayout.CENTER);
        showCard(CARD_WELCOME);
    }

    private void showCard(String cardName) {
        if (CARD_CUST_SEARCH.equals(cardName) && searchPanel != null) {
            searchPanel.refreshFromSystem();
        }
        if (CARD_CUST_HISTORY.equals(cardName) && historyPanel != null) {
            historyPanel.clearOutput();
        }
        cardLayout.show(cardPanel, cardName);
    }
    
    private void onSave() {
    	BookingSystem.saveData(bookingSystem);
		JOptionPane.showMessageDialog(null,  "Booking system saved!");
    }

    private void onLoad() {
    	BookingSystem newBookingSystem = BookingSystem.loadData();
		if (newBookingSystem != null) {
			bookingSystem = newBookingSystem;
			JOptionPane.showMessageDialog(null, "Booking system loaded!");
		}
		else {
			JOptionPane.showMessageDialog(null, "No booking system found!");
		}
    }

    /** Find customer by email (case-insensitive). */
    private Customer findCustomerByEmail(String email) {
        for (Customer c : bookingSystem.getCustomers()) {
            if (c.getEmail() != null &&
                c.getEmail().equalsIgnoreCase(email)) {
                return c;
            }
        }
        return null;
    }

    /** Find or create a customer based on name + email. */
    private Customer findOrCreateCustomer(String name, String email) {
        Customer existing = findCustomerByEmail(email);
        if (existing != null) return existing;

        String uuid = "C" + System.currentTimeMillis();
        Customer created = new Customer(uuid, name, email);
        bookingSystem.addCustomer(created);
        return created;
    }

    /** Find booking by ID across all bookings. */
    private Booking findBookingById(String bookingId) {
        for (Booking b : bookingSystem.getBookings()) {
            if (b.getUUID().equals(bookingId)) {
                return b;
            }
        }
        return null;
    }

    private String shortBookingSummary(Booking b) {
        String customerEmail = (b.getCustomer() != null) ? b.getCustomer().getEmail() : "N/A";
        return "ID=" + b.getUUID() +
                ", Status=" + b.getStatus() +
                ", Total=" + b.getTotal() +
                ", Customer=" + customerEmail +
                ", Created=" + b.getCreatedDateTime();
    }

    private void showAllFlights() {
	    StringBuilder sb = new StringBuilder();
	
	    List<FlightListing> flights = bookingSystem.getFlights();
	    if (flights.isEmpty()) {
	        sb.append("There are currently no flights in the system.");
	    } else {
	        sb.append("All flights in the system:\n\n");
	        for (FlightListing f : flights) {
	            sb.append(f.toString()).append("\n\n");
	        }
	    }
	
	    JTextArea area = new JTextArea(sb.toString(), 20, 60);
	    area.setEditable(false);
	    JScrollPane scrollPane = new JScrollPane(area);
	
	    JOptionPane.showMessageDialog(
	            this,
	            scrollPane,
	            "All Flights",
	            JOptionPane.INFORMATION_MESSAGE
	    		);
    }
	    
	private void showAllHotels() {
		StringBuilder sb = new StringBuilder();
		
		List<HotelListing> hotels = bookingSystem.getHotels();
		if (hotels.isEmpty()) {
			sb.append("There are currently no hotels in the system.");
		} else {
			sb.append("All hotels in the system:\n\n");
			for (HotelListing h : hotels) {
				sb.append(h.toString()).append("\n\n");
			}
		}
		
		JTextArea area = new JTextArea(sb.toString(), 20, 60);
	    area.setEditable(false);
	    JScrollPane scrollPane = new JScrollPane(area);
	
	    JOptionPane.showMessageDialog(
	            this,
	            scrollPane,
	            "All Hotels",
	            JOptionPane.INFORMATION_MESSAGE
	    		);
	}
	
	private void showAllBookings() {
		StringBuilder sb = new StringBuilder();
		
		List<Booking> bookings = bookingSystem.getBookings();
		if (bookings.isEmpty()) {
			sb.append("There are currently no bookings in the system.");
		} else {
			sb.append("All bookings in the system:\n\n");
			for (Booking b : bookings) {
				sb.append(b.toString()).append("\n\n");
			}
		}
		
		JTextArea area = new JTextArea(sb.toString(), 20, 60);
	    area.setEditable(false);
	    JScrollPane scrollPane = new JScrollPane(area);
	
	    JOptionPane.showMessageDialog(
	            this,
	            scrollPane,
	            "All Bookings",
	            JOptionPane.INFORMATION_MESSAGE
	    		);
	}
	
	private void showPrintWindow() {
	    JFrame printFrame = new JFrame("Admin: Print All Data");
	    printFrame.setSize(900, 700);
	    printFrame.setLocationRelativeTo(this);
	    printFrame.setLayout(new BorderLayout());

	    JTextArea printArea = new JTextArea();
	    printArea.setEditable(false);
	    printArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
	    
	    StringBuilder sb = new StringBuilder();
	    
	    // Flights Section
	    sb.append("========================================\n");
	    sb.append("           ALL FLIGHTS\n");
	    sb.append("========================================\n\n");
	    
	    List<FlightListing> flights = bookingSystem.getFlights();
	    if (flights.isEmpty()) {
	        sb.append("No flights in the system.\n\n");
	    } else {
	        for (FlightListing f : flights) {
	            sb.append(f.toString()).append("\n");
	        }
	        sb.append("\nTotal Flights: ").append(flights.size()).append("\n\n");
	    }
	    
	    // Hotels Section
	    sb.append("========================================\n");
	    sb.append("           ALL HOTELS\n");
	    sb.append("========================================\n\n");
	    
	    List<HotelListing> hotels = bookingSystem.getHotels();
	    if (hotels.isEmpty()) {
	        sb.append("No hotels in the system.\n\n");
	    } else {
	        for (HotelListing h : hotels) {
	            sb.append(h.toString()).append("\n");
	        }
	        sb.append("\nTotal Hotels: ").append(hotels.size()).append("\n\n");
	    }
	    
	    // Customers Section
	    sb.append("========================================\n");
	    sb.append("           ALL CUSTOMERS\n");
	    sb.append("========================================\n\n");
	    
	    List<Customer> customers = bookingSystem.getCustomers();
	    if (customers.isEmpty()) {
	        sb.append("No customers in the system.\n\n");
	    } else {
	        for (Customer c : customers) {
	            sb.append("Customer ID: ").append(c.getUUID()).append("\n");
	            sb.append("  Name: ").append(c.getName()).append("\n");
	            sb.append("  Email: ").append(c.getEmail()).append("\n");
	            sb.append("  Number of Bookings: ").append(c.getBookingHistory().size()).append("\n\n");
	        }
	        sb.append("Total Customers: ").append(customers.size()).append("\n\n");
	    }
	    
	    // Bookings Section
	    sb.append("========================================\n");
	    sb.append("           ALL BOOKINGS\n");
	    sb.append("========================================\n\n");
	    
	    List<Booking> bookings = bookingSystem.getBookings();
	    if (bookings.isEmpty()) {
	        sb.append("No bookings in the system.\n\n");
	    } else {
	        for (Booking b : bookings) {
	            sb.append("Booking ID: ").append(b.getUUID()).append("\n");
	            sb.append("  Status: ").append(b.getStatus()).append("\n");
	            sb.append("  Total: $").append(String.format("%.2f", b.getTotal())).append("\n");
	            sb.append("  Customer: ").append(b.getCustomer() != null ? b.getCustomer().getEmail() : "N/A").append("\n");
	            sb.append("  Created: ").append(b.getCreatedDateTime()).append("\n");
	            
	            if (b instanceof FlightBooking) {
	                FlightBooking fb = (FlightBooking) b;
	                sb.append("  Type: Flight Booking\n");
	                sb.append("  Flight ID: ").append(fb.getFlightID()).append("\n");
	            } else if (b instanceof HotelBooking) {
	                HotelBooking hb = (HotelBooking) b;
	                sb.append("  Type: Hotel Booking\n");
	                sb.append("  Hotel ID: ").append(hb.getHotelID()).append("\n");
	                sb.append("  Nights: ").append(hb.getNights()).append("\n");
	            }
	            sb.append("\n");
	        }
	        sb.append("Total Bookings: ").append(bookings.size()).append("\n");
	    }
	    
	    printArea.setText(sb.toString());
	    printArea.setCaretPosition(0); // Scroll to top
	    
	    JScrollPane scrollPane = new JScrollPane(printArea);
	    printFrame.add(scrollPane, BorderLayout.CENTER);
	    
	    // Add a close button at the bottom
	    JPanel buttonPanel = new JPanel();
	    JButton closeButton = new JButton("Close");
	    closeButton.addActionListener(e -> printFrame.dispose());
	    buttonPanel.add(closeButton);
	    printFrame.add(buttonPanel, BorderLayout.SOUTH);
	    
	    printFrame.setVisible(true);
	}
	
	private void showCustomerPrintWindow() {
	    JFrame printFrame = new JFrame("Customer: Print Flights & Hotels");
	    printFrame.setSize(1000, 700);
	    printFrame.setLocationRelativeTo(this);
	    printFrame.setLayout(new BorderLayout());

	    JLabel title = new JLabel("All Available Flights & Hotels", SwingConstants.CENTER);
	    title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));
	    title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
	    printFrame.add(title, BorderLayout.NORTH);

	    JPanel tablesPanel = new JPanel(new GridLayout(2, 1, 0, 10));
	    tablesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

	    // Flights Table
	    List<FlightListing> flights = bookingSystem.getFlights();
	    List<Object[]> flightRows = new ArrayList<>();
	    
	    for (FlightListing f : flights) {
	        flightRows.add(new Object[]{
	            f.getUUID(),
	            f.getAirline(),
	            f.getDeparture(),
	            f.getDestination(),
	            f.getDate().toString(),
	            String.format("$%.2f", f.getPrice()),
	            f.isAvailable() ? "Y" : "N"
	        });
	    }
	    
	    String[] flightColumns = {"ID", "Airline", "Departure", "Destination", "Date", "Price", "Available"};
	    JTable flightsTable = new JTable(new javax.swing.table.DefaultTableModel(
	        flightRows.toArray(new Object[0][0]),
	        flightColumns
	    ) {
	        /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
	        public boolean isCellEditable(int row, int column) {
	            return false;
	        }
	    });
	    flightsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    JScrollPane flightsScrollPane = new JScrollPane(flightsTable);
	    flightsScrollPane.setBorder(BorderFactory.createTitledBorder("All Flights (" + flights.size() + " total)"));

	    // Hotels Table
	    List<HotelListing> hotels = bookingSystem.getHotels();
	    List<Object[]> hotelRows = new ArrayList<>();
	    
	    for (HotelListing h : hotels) {
	        hotelRows.add(new Object[]{
	            h.getUUID(),
	            h.getName(),
	            h.getLocation(),
	            String.format("%.1f", h.getRating()),
	            String.format("$%.2f", h.getPrice()),
	            h.isAvailable() ? "Y" : "N"
	        });
	    }
	    
	    String[] hotelColumns = {"ID", "Name", "Location", "Rating", "Price/Night", "Available"};
	    JTable hotelsTable = new JTable(new javax.swing.table.DefaultTableModel(
	        hotelRows.toArray(new Object[0][0]),
	        hotelColumns
	    ) {
	        /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
	        public boolean isCellEditable(int row, int column) {
	            return false;
	        }
	    });
	    hotelsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    JScrollPane hotelsScrollPane = new JScrollPane(hotelsTable);
	    hotelsScrollPane.setBorder(BorderFactory.createTitledBorder("All Hotels (" + hotels.size() + " total)"));

	    tablesPanel.add(flightsScrollPane);
	    tablesPanel.add(hotelsScrollPane);

	    printFrame.add(tablesPanel, BorderLayout.CENTER);

	    // Add a close button at the bottom
	    JPanel buttonPanel = new JPanel();
	    JButton closeButton = new JButton("Close");
	    closeButton.addActionListener(e -> printFrame.dispose());
	    buttonPanel.add(closeButton);
	    printFrame.add(buttonPanel, BorderLayout.SOUTH);

	    printFrame.setVisible(true);
	}

    /*  WELCOME PANEL  */

    private class WelcomePanel extends JPanel {
        WelcomePanel() {
            setLayout(new BorderLayout());
            JLabel label = new JLabel(
                    "Welcome to the ECE373 Travel Booking System",
                    SwingConstants.CENTER);
            label.setFont(label.getFont().deriveFont(Font.BOLD, 20f));
            add(label, BorderLayout.CENTER);
        }
    }

    /*  Admin: Add Flight  */
    private class AdminAddFlightPanel extends JPanel {

        private JTextField departureField;
        private JTextField destinationField;
        private JTextField dateField;   // yyyy-MM-dd
        private JTextField priceField;
        private JTextField airlineField;

        AdminAddFlightPanel() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel title = new JLabel("Admin: Add Flight");
            title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));

            gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
            add(title, gbc);

            gbc.gridwidth = 1;
            gbc.gridy++;
            add(new JLabel("Departure:"), gbc);
            gbc.gridx = 1;
            departureField = new JTextField(15);
            add(departureField, gbc);   

            gbc.gridx = 0; gbc.gridy++;
            add(new JLabel("Destination:"), gbc);
            gbc.gridx = 1;
            destinationField = new JTextField(15);
            add(destinationField, gbc);

            gbc.gridx = 0; gbc.gridy++;
            add(new JLabel("Date (yyyy-MM-dd):"), gbc);
            gbc.gridx = 1;
            dateField = new JTextField(15);
            add(dateField, gbc);

            gbc.gridx = 0; gbc.gridy++;
            add(new JLabel("Price:"), gbc);
            gbc.gridx = 1;
            priceField = new JTextField(15);
            add(priceField, gbc);

            gbc.gridx = 0; gbc.gridy++;
            add(new JLabel("Airline:"), gbc);
            gbc.gridx = 1;
            airlineField = new JTextField(15);
            add(airlineField, gbc);

            gbc.gridx = 0; gbc.gridy++;
            gbc.gridwidth = 2;
            JButton addButton = new JButton("Add Flight");
            addButton.addActionListener(e -> onAddFlight());
            add(addButton, gbc);
        }

        private void onAddFlight() {
            String departure  = departureField.getText().trim();
            String destination = destinationField.getText().trim();
            String dateText   = dateField.getText().trim();
            String priceText  = priceField.getText().trim();
            String airline    = airlineField.getText().trim();
            
            if (departure.isEmpty() || destination.isEmpty() || dateText.isEmpty()
                    || priceText.isEmpty() || airline.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }
            
            //departure and destination cannot be the same
            if (departure.equalsIgnoreCase(destination)) {
                JOptionPane.showMessageDialog(this,
                        "Departure and destination cannot be the same.",
                        "Invalid Flight", JOptionPane.ERROR_MESSAGE);
                return;
            }

            LocalDate date;
            try {
                date = LocalDate.parse(dateText);   // yyyy-MM-dd
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this,
                        "Date must be in format yyyy-MM-dd.",
                        "Invalid date", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            //Flight date must be in the future
            if (date.isBefore(LocalDate.now()) || date.isEqual(LocalDate.now())) {
                JOptionPane.showMessageDialog(this,
                        "Flight date must be in the future.",
                        "Invalid Date", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double price;
            try {
                price = Double.parseDouble(priceText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Price must be a number.",
                        "Invalid price", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int nextFlightNum = bookingSystem.getFlights().size() + 1;
            String uuid = "FL" + "0" + nextFlightNum;
            FlightListing flight = new FlightListing(uuid, departure, destination, date, price, airline);
            bookingSystem.getFlights().add(flight);

            JOptionPane.showMessageDialog(this,
                    "Flight added.\n\n" + flight.toString());
                    
            // Clear fields
            departureField.setText("");
            destinationField.setText("");
            dateField.setText("");
            priceField.setText("");
            airlineField.setText("");

            if (searchPanel != null) {
                searchPanel.refreshFromSystem();
            }
        }
    }

    /*  Admin: Update Flight  */

    private class AdminUpdateFlightPanel extends JPanel {

        private JTextField flightIdField;
        private JTextField newDestinationField;
        private JTextField newDateField;
        private JTextField newAirlineField;

        AdminUpdateFlightPanel() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel title = new JLabel("Admin: Update Flight");
            title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));

            gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
            add(title, gbc);

            gbc.gridwidth = 1;
            gbc.gridy++;
            add(new JLabel("Flight ID:"), gbc);
            gbc.gridx = 1;
            flightIdField = new JTextField(20);
            add(flightIdField, gbc);

            gbc.gridx = 0; gbc.gridy++;
            add(new JLabel("New Destination (blank = unchanged):"), gbc);
            gbc.gridx = 1;
            newDestinationField = new JTextField(20);
            add(newDestinationField, gbc);

            gbc.gridx = 0; gbc.gridy++;
            add(new JLabel("New Date yyyy-MM-dd (blank = unchanged):"), gbc);
            gbc.gridx = 1;
            newDateField = new JTextField(20);
            add(newDateField, gbc);

            gbc.gridx = 0; gbc.gridy++;
            add(new JLabel("New Airline (blank = unchanged):"), gbc);
            gbc.gridx = 1;
            newAirlineField = new JTextField(20);
            add(newAirlineField, gbc);

            gbc.gridx = 0; gbc.gridy++;
            gbc.gridwidth = 2;
            JButton updateButton = new JButton("Update Flight");
            updateButton.addActionListener(e -> onUpdateFlight());
            add(updateButton, gbc);
        }

        private void onUpdateFlight() {
            String id = flightIdField.getText().trim();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a Flight ID.");
                return;
            }

            FlightListing flight = bookingSystem.getFlightByID(id);
            if (flight == null) {
                JOptionPane.showMessageDialog(this,
                        "No flight found with ID " + id,
                        "Not found", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String newDest = newDestinationField.getText().trim();
            String newDate = newDateField.getText().trim();
            String newAirline = newAirlineField.getText().trim();

            if (!newDest.isEmpty()) {
                flight.setDestination(newDest);
            }
            if (!newDate.isEmpty()) {
                try {
                    LocalDate date = LocalDate.parse(newDate);
                    flight.setDate(date);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(this,
                            "New date must be yyyy-MM-dd.", "Invalid date",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            if (!newAirline.isEmpty()) {
                flight.setAirline(newAirline);
            }

            JOptionPane.showMessageDialog(this,
                    "Flight updated:\n\n" + flight.toString());

            if (searchPanel != null) {
                searchPanel.refreshFromSystem();
            }
        }
    }

    /*  Admin: Remove Flight */ 

    private class AdminRemoveFlightPanel extends JPanel {

        private final JTextField flightIdField;
        private final JTextArea outputArea;

        AdminRemoveFlightPanel() {
            setLayout(new BorderLayout());

            JLabel title = new JLabel("Admin: Remove Flight", SwingConstants.CENTER);
            title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));
            add(title, BorderLayout.NORTH);

            JPanel form = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            gbc.gridx = 0; gbc.gridy = 0;
            form.add(new JLabel("Flight ID:"), gbc);

            gbc.gridx = 1;
            flightIdField = new JTextField(20);
            form.add(flightIdField, gbc);

            gbc.gridx = 0; gbc.gridy++;
            gbc.gridwidth = 2;
            JButton removeButton = new JButton("Remove Flight");
            removeButton.addActionListener(e -> onRemoveFlight());
            form.add(removeButton, gbc);

            add(form, BorderLayout.CENTER);

            outputArea = new JTextArea(6, 40);
            outputArea.setEditable(false);
            add(new JScrollPane(outputArea), BorderLayout.SOUTH);
        }

        private void onRemoveFlight() {
            String id = flightIdField.getText().trim();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a Flight ID.");
                return;
            }

            FlightListing flight = bookingSystem.getFlightByID(id);
            if (flight == null) {
                JOptionPane.showMessageDialog(this,
                        "No flight found with ID " + id,
                        "Not found", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if there is any paid booking for this flight
            if (hasPaidBookingForFlight(flight)) {
                JOptionPane.showMessageDialog(this,
                        "This flight has at least one paid booking and cannot be removed.",
                        "Cannot remove", JOptionPane.ERROR_MESSAGE);
                return;
            }

            bookingSystem.getFlights().remove(flight);
            outputArea.setText("Removed flight:\n\n" + flight.toString());

            if (searchPanel != null) {
                searchPanel.refreshFromSystem();
            }
        }

        private boolean hasPaidBookingForFlight(FlightListing flight) {
            for (Booking b : bookingSystem.getBookings()) {
                if (b instanceof FlightBooking) {
                    FlightBooking fb = (FlightBooking) b;
                    String listingId = fb.getFlightID();
                    if (listingId.equals(flight.getUUID())
                            && b.getStatus() == BookingStatus.PAID) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    //  Admin: Add Hotel 
    private class AdminAddHotelPanel extends JPanel {

        private final JTextField locationField;
        private final JTextField ratingField;
        private final JTextField priceField;
        private final JTextField nameField;
        private final JTextArea outputArea;

        AdminAddHotelPanel() {
            setLayout(new BorderLayout());

            JLabel title = new JLabel("Admin: Add Hotel", SwingConstants.CENTER);
            title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));
            add(title, BorderLayout.NORTH);

            JPanel form = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            gbc.gridx = 0; gbc.gridy = 0;
            form.add(new JLabel("Location:"), gbc);
            gbc.gridx = 1;
            locationField = new JTextField(20);
            form.add(locationField, gbc);

            gbc.gridx = 0; gbc.gridy++;
            form.add(new JLabel("Rating (0–5):"), gbc);
            gbc.gridx = 1;
            ratingField = new JTextField(10);
            form.add(ratingField, gbc);

            gbc.gridx = 0; gbc.gridy++;
            form.add(new JLabel("Price per night:"), gbc);
            gbc.gridx = 1;
            priceField = new JTextField(10);
            form.add(priceField, gbc);

            gbc.gridx = 0; gbc.gridy++;
            form.add(new JLabel("Hotel Name:"), gbc);
            gbc.gridx = 1;
            nameField = new JTextField(20);
            form.add(nameField, gbc);

            gbc.gridx = 0; gbc.gridy++;
            gbc.gridwidth = 2;
            JButton addButton = new JButton("Add Hotel");
            addButton.addActionListener(e -> onAddHotel());
            form.add(addButton, gbc);

            add(form, BorderLayout.CENTER);

            outputArea = new JTextArea(6, 40);
            outputArea.setEditable(false);
            add(new JScrollPane(outputArea), BorderLayout.SOUTH);
        }

        private void onAddHotel() {
            String location = locationField.getText().trim();
            String ratingText = ratingField.getText().trim();
            String priceText  = priceField.getText().trim();
            String name       = nameField.getText().trim();

            if (location.isEmpty() || ratingText.isEmpty()
                    || priceText.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Fill in the fields.");
                return;
            }

            double rating;
            try {
                rating = Double.parseDouble(ratingText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Rating must be a number.",
                        "Invalid rating", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double price;
            try {
                price = Double.parseDouble(priceText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Price must be a number.",
                        "Invalid price", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int nextHotelNum = bookingSystem.getHotels().size() + 1;
            String uuid = "HL" + "0" + nextHotelNum;
            HotelListing hotel = new HotelListing(uuid, location, rating, price, name);
            bookingSystem.getHotels().add(hotel);

            outputArea.setText("Added hotel:\n\n" + hotel.toString());

            // Clear fields
            locationField.setText("");
            ratingField.setText("");
            priceField.setText("");
            nameField.setText("");

            if (searchPanel != null) {
                searchPanel.refreshFromSystem();
            }
        }
    }

    /*  Admin: Hotels (remove / update)  */

    private class AdminHotelsPanel extends JPanel {

        private JTextField hotelIdField;
        private JTextField newNameField;
        private JTextField newLocationField;
        private JTextField newRatingField;
        private JTextField newPriceField;
        private JTextArea outputArea;

        AdminHotelsPanel() {
            setLayout(new BorderLayout());

            JLabel title = new JLabel("Admin: Remove/Update Hotels", SwingConstants.CENTER);
            title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));
            add(title, BorderLayout.NORTH);

            JPanel form = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            gbc.gridx = 0; gbc.gridy = 0;
            form.add(new JLabel("Hotel ID:"), gbc);
            gbc.gridx = 1;
            hotelIdField = new JTextField(20);
            form.add(hotelIdField, gbc);

            gbc.gridx = 0; gbc.gridy++;
            form.add(new JLabel("New Name (blank = unchanged):"), gbc);
            gbc.gridx = 1;
            newNameField = new JTextField(20);
            form.add(newNameField, gbc);

            gbc.gridx = 0; gbc.gridy++;
            form.add(new JLabel("New Location (blank = unchanged):"), gbc);
            gbc.gridx = 1;
            newLocationField = new JTextField(20);
            form.add(newLocationField, gbc);

            gbc.gridx = 0; gbc.gridy++;
            form.add(new JLabel("New Rating (blank = unchanged):"), gbc);
            gbc.gridx = 1;
            newRatingField = new JTextField(20);
            form.add(newRatingField, gbc);
            
            gbc.gridx = 0; gbc.gridy++;
            form.add(new JLabel("New Price (blank = unchanged):"), gbc);
            gbc.gridx = 1;
            newPriceField = new JTextField(20);
            form.add(newPriceField, gbc);

            gbc.gridx = 0; gbc.gridy++;
            gbc.gridwidth = 1;
            JButton updateButton = new JButton("Update");
            updateButton.addActionListener(e -> onUpdateHotel());
            form.add(updateButton, gbc);

            gbc.gridx = 1;
            JButton removeButton = new JButton("Remove");
            removeButton.addActionListener(e -> onRemoveHotel());
            form.add(removeButton, gbc);

            add(form, BorderLayout.CENTER);

            outputArea = new JTextArea(6, 40);
            outputArea.setEditable(false);
            add(new JScrollPane(outputArea), BorderLayout.SOUTH);
        }

        private HotelListing findHotelById(String id) {
            for (HotelListing h : bookingSystem.getHotels()) {
                if (h.getUUID().equals(id)) return h;
            }
            return null;
        }

        private void onUpdateHotel() {
            String id = hotelIdField.getText().trim();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a Hotel ID.");
                return;
            }
            HotelListing hotel = findHotelById(id);
            if (hotel == null) {
                JOptionPane.showMessageDialog(this,
                        "No hotel found with ID " + id,
                        "Not found", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String newName = newNameField.getText().trim();
            String newLoc = newLocationField.getText().trim();
            String newRatingText = newRatingField.getText().trim();
            String newPriceText = newPriceField.getText().trim();

            if (!newName.isEmpty()) hotel.setName(newName);
            if (!newLoc.isEmpty()) hotel.setLocation(newLoc);
            if (!newRatingText.isEmpty()) {
                try {
                    double r = Double.parseDouble(newRatingText);
                    hotel.setRating(r);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this,
                            "Rating must be numeric.", "Invalid rating",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            if (!newPriceText.isEmpty()) {
                try {
                    double p = Double.parseDouble(newPriceText);
                    hotel.setPrice(p);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this,
                            "Price must be numeric.", "Invalid price",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            outputArea.setText("Updated hotel:\n" + hotel.toString());
        }

        private void onRemoveHotel() {
            String id = hotelIdField.getText().trim();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a Hotel ID.");
                return;
            }
            HotelListing hotel = findHotelById(id);
            if (hotel == null) {
                JOptionPane.showMessageDialog(this,
                        "No hotel found with ID " + id,
                        "Not found", JOptionPane.ERROR_MESSAGE);
                return;
            }
            bookingSystem.getHotels().remove(hotel);
            outputArea.setText("Removed hotel:\n" + hotel.toString());

            if (searchPanel != null) {
                searchPanel.refreshFromSystem();
            }
        }
    }

    /*  Customer: Set Email  */

    private class CustomerSetEmailPanel extends JPanel {

        private JTextField nameField;
        private JTextField emailField;

        CustomerSetEmailPanel() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel title = new JLabel("Customer: Set Email");
            title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));

            gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
            add(title, gbc);

            gbc.gridwidth = 1;
            gbc.gridy++;
            add(new JLabel("Customer Name:"), gbc);
            gbc.gridx = 1;
            nameField = new JTextField(20);
            add(nameField, gbc);

            gbc.gridx = 0; gbc.gridy++;
            add(new JLabel("Email:"), gbc);
            gbc.gridx = 1;
            emailField = new JTextField(20);
            add(emailField, gbc);

            gbc.gridx = 0; gbc.gridy++;
            gbc.gridwidth = 2;
            JButton setButton = new JButton("Create / Update");
            setButton.addActionListener(e -> onSetEmail());
            add(setButton, gbc);
        }

        private void onSetEmail() {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();

            if (name.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both name and email.");
                return;
            }

            Customer customer = findOrCreateCustomer(name, email);
            customer.setEmail(email);

            JOptionPane.showMessageDialog(this,
                    "Customer record updated.\n\nEmail: " + customer.getEmail());
        }
    }

    /*  Customer: Search Flights & Hotels  */

    /*  Customer: Search Flights & Hotels  */

    private class SearchPanel extends JPanel {

        private JTable flightsTable;
        private JTable hotelsTable;
        private JTextField flightDestFilterField;
        private JTextField hotelLocFilterField;

        SearchPanel() {
            setLayout(new BorderLayout());

            JLabel title = new JLabel("Customer: Search Flights & Hotels", SwingConstants.CENTER);
            title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));
            add(title, BorderLayout.NORTH);

            JPanel filters = new JPanel();
            filters.add(new JLabel("Flight destination:"));
            flightDestFilterField = new JTextField(10);
            filters.add(flightDestFilterField);
            filters.add(new JLabel("Hotel location:"));
            hotelLocFilterField = new JTextField(10);
            filters.add(hotelLocFilterField);
            JButton applyButton = new JButton("Apply Filters");
            applyButton.addActionListener(e -> refreshFromSystem());
            filters.add(applyButton);
            add(filters, BorderLayout.SOUTH);

            JPanel tablesPanel = new JPanel(new GridLayout(1, 2, 10, 0));

            // Flights Table
            String[] flightColumns = {"ID", "Airline", "Departure", "Destination", "Date", "Price", "Available"};
            flightsTable = new JTable(new Object[0][0], flightColumns);
            flightsTable.setEnabled(false);
            flightsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            JScrollPane flightsScrollPane = new JScrollPane(flightsTable);
            flightsScrollPane.setBorder(BorderFactory.createTitledBorder("Flights"));

            // Hotels Table
            String[] hotelColumns = {"ID", "Name", "Location", "Rating", "Price/Night", "Available"};
            hotelsTable = new JTable(new Object[0][0], hotelColumns);
            hotelsTable.setEnabled(false);
            hotelsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            JScrollPane hotelsScrollPane = new JScrollPane(hotelsTable);
            hotelsScrollPane.setBorder(BorderFactory.createTitledBorder("Hotels"));

            tablesPanel.add(flightsScrollPane);
            tablesPanel.add(hotelsScrollPane);

            add(tablesPanel, BorderLayout.CENTER);

            refreshFromSystem();
        }

        void refreshFromSystem() {
            String fFilter = flightDestFilterField.getText().trim();
            String hFilter = hotelLocFilterField.getText().trim();

            // Update Flights Table
            List<FlightListing> flights = bookingSystem.getFlights();
            List<Object[]> flightRows = new ArrayList<>();
            
            for (FlightListing f : flights) {
                if (fFilter.isEmpty() || f.getDestination().equalsIgnoreCase(fFilter)) {
                    flightRows.add(new Object[]{
                        f.getUUID(),
                        f.getAirline(),
                        f.getDeparture(),
                        f.getDestination(),
                        f.getDate().toString(),
                        String.format("$%.2f", f.getPrice()),
                        f.isAvailable() ? "Y" : "N"
                    });
                }
            }
            
            String[] flightColumns = {"ID", "Airline", "Departure", "Destination", "Date", "Price", "Available"};
            flightsTable.setModel(new javax.swing.table.DefaultTableModel(
                flightRows.toArray(new Object[0][0]),
                flightColumns
            ) {
                /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });

            // Update Hotels Table
            List<HotelListing> hotels = bookingSystem.getHotels();
            List<Object[]> hotelRows = new ArrayList<>();
            
            for (HotelListing h : hotels) {
                if (hFilter.isEmpty() || h.getLocation().equalsIgnoreCase(hFilter)) {
                    hotelRows.add(new Object[]{
                        h.getUUID(),
                        h.getName(),
                        h.getLocation(),
                        String.format("%.1f", h.getRating()),
                        String.format("$%.2f", h.getPrice()),
                        h.isAvailable() ? "Y" : "N"
                    });
                }
            }
            
            String[] hotelColumns = {"ID", "Name", "Location", "Rating", "Price/Night", "Available"};
            hotelsTable.setModel(new javax.swing.table.DefaultTableModel(
                hotelRows.toArray(new Object[0][0]),
                hotelColumns
            ) {
                /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
        }
    }

    /*  Customer: Book Flight & Hotel  */

    private class CustomerBookPanel extends JPanel {

        private JTextField nameField;
        private JTextField emailField;
        private JTextField flightIdField;
        private JTextField hotelIdField;
        private JTextField nightsField;
        private JTextArea outputArea;

        CustomerBookPanel() {
            setLayout(new BorderLayout());

            JLabel title = new JLabel("Customer: Book Flights & Hotels", SwingConstants.CENTER);
            title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));
            add(title, BorderLayout.NORTH);

            JPanel form = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            gbc.gridx = 0; gbc.gridy = 0;
            form.add(new JLabel("Customer Name:"), gbc);
            gbc.gridx = 1;
            nameField = new JTextField(20);
            form.add(nameField, gbc);

            gbc.gridx = 0; gbc.gridy++;
            form.add(new JLabel("Customer Email:"), gbc);
            gbc.gridx = 1;
            emailField = new JTextField(20);
            form.add(emailField, gbc);

            gbc.gridx = 0; gbc.gridy++;
            form.add(new JLabel("Flight ID (optional):"), gbc);
            gbc.gridx = 1;
            flightIdField = new JTextField(20);
            form.add(flightIdField, gbc);

            gbc.gridx = 0; gbc.gridy++;
            form.add(new JLabel("Hotel ID (optional):"), gbc);
            gbc.gridx = 1;
            hotelIdField = new JTextField(20);
            form.add(hotelIdField, gbc);

            gbc.gridx = 0; gbc.gridy++;
            form.add(new JLabel("Nights (for hotel):"), gbc);
            gbc.gridx = 1;
            nightsField = new JTextField(10);
            form.add(nightsField, gbc);

            gbc.gridx = 0; gbc.gridy++;
            JButton bookFlightButton = new JButton("Book Flight");
            bookFlightButton.addActionListener(e -> onBookFlight());
            form.add(bookFlightButton, gbc);

            gbc.gridx = 1;
            JButton bookHotelButton = new JButton("Book Hotel");
            bookHotelButton.addActionListener(e -> onBookHotel());
            form.add(bookHotelButton, gbc);

            add(form, BorderLayout.CENTER);

            outputArea = new JTextArea(6, 40);
            outputArea.setEditable(false);
            add(new JScrollPane(outputArea), BorderLayout.SOUTH);
        }

        private Customer ensureCustomer() {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            if (name.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please enter customer name and email.");
                return null;
            }
            return findOrCreateCustomer(name, email);
        }

        private void onBookFlight() {
            Customer customer = ensureCustomer();
            if (customer == null) return;

            String flightId = flightIdField.getText().trim();
            if (flightId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a Flight ID.");
                return;
            }

            FlightListing flight = bookingSystem.getFlightByID(flightId);
            if (flight == null) {
                JOptionPane.showMessageDialog(this,
                        "No flight found with ID " + flightId,
                        "Not found", JOptionPane.ERROR_MESSAGE);
                return;
            }

            FlightBooking booking = customer.bookFlight(flight, bookingSystem);
            outputArea.append("Created flight booking:\n" +
                    shortBookingSummary(booking) + "\n\n");
        }

        private void onBookHotel() {
            Customer customer = ensureCustomer();
            if (customer == null) return;

            String hotelId = hotelIdField.getText().trim();
            if (hotelId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a Hotel ID.");
                return;
            }

            int nights;
            try {
                nights = Integer.parseInt(nightsField.getText().trim());
                if (nights <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Nights must be a positive integer.",
                        "Invalid nights", JOptionPane.ERROR_MESSAGE);
                return;
            }

            HotelListing hotel = bookingSystem.getHotelByID(hotelId);
            if (hotel == null) {
                JOptionPane.showMessageDialog(this,
                        "No hotel found with ID " + hotelId,
                        "Not found", JOptionPane.ERROR_MESSAGE);
                return;
            }

            HotelBooking booking = customer.bookHotel(hotel, nights, bookingSystem);
            outputArea.append("Created hotel booking:\n" +
                    shortBookingSummary(booking) + "\n\n");
        }
    }

    /*  Customer: Pay */

    private class CustomerPayPanel extends JPanel {

        private JTextField emailField;
        private JTextField bookingIdField;
        private JTextField cardNumberField;
        private JComboBox<CardType> cardTypeBox;
        private JTextField expiryField;
        private JTextArea outputArea;

        CustomerPayPanel() {
            setLayout(new BorderLayout());

            JLabel title = new JLabel("Customer: Pay", SwingConstants.CENTER);
            title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));
            add(title, BorderLayout.NORTH);

            JPanel form = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            gbc.gridx = 0; gbc.gridy = 0;
            form.add(new JLabel("Customer Email:"), gbc);
            gbc.gridx = 1;
            emailField = new JTextField(20);
            form.add(emailField, gbc);

            gbc.gridx = 0; gbc.gridy++;
            form.add(new JLabel("Booking ID:"), gbc);
            gbc.gridx = 1;
            bookingIdField = new JTextField(20);
            form.add(bookingIdField, gbc);

            gbc.gridx = 0; gbc.gridy++;
            form.add(new JLabel("Card Number:"), gbc);
            gbc.gridx = 1;
            cardNumberField = new JTextField(20);
            form.add(cardNumberField, gbc);

            gbc.gridx = 0; gbc.gridy++;
            form.add(new JLabel("Card Type:"), gbc);
            gbc.gridx = 1;
            cardTypeBox = new JComboBox<>(CardType.values());
            form.add(cardTypeBox, gbc);

            gbc.gridx = 0; gbc.gridy++;
            form.add(new JLabel("Expiry (yyyy-MM-dd):"), gbc);
            gbc.gridx = 1;
            expiryField = new JTextField(15);
            form.add(expiryField, gbc);

            gbc.gridx = 0; gbc.gridy++;
            gbc.gridwidth = 2;
            JButton payButton = new JButton("Process Payment");
            payButton.addActionListener(e -> onPay());
            form.add(payButton, gbc);

            add(form, BorderLayout.CENTER);

            outputArea = new JTextArea(6, 40);
            outputArea.setEditable(false);
            add(new JScrollPane(outputArea), BorderLayout.SOUTH);
        }

        private void onPay() {
            String email = emailField.getText().trim();
            String bookingId = bookingIdField.getText().trim();
            String cardNumber = cardNumberField.getText().trim();
            String expiryText = expiryField.getText().trim();

            if (email.isEmpty() || bookingId.isEmpty()
                    || cardNumber.isEmpty() || expiryText.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill all fields.");
                return;
            }

            Customer customer = findCustomerByEmail(email);
            if (customer == null) {
                JOptionPane.showMessageDialog(this,
                        "No customer found with email " + email,
                        "Not found", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Booking booking = null;
            for (Booking b : customer.getBookingHistory()) {
                if (b.getUUID().equals(bookingId)) {
                    booking = b;
                    break;
                }
            }
            if (booking == null) {
                booking = findBookingById(bookingId);
            }
            if (booking == null) {
                JOptionPane.showMessageDialog(this,
                        "No booking found with ID " + bookingId,
                        "Not found", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            //Prevent payment for already paid bookings
            if (booking.getStatus() == BookingStatus.PAID) {
                JOptionPane.showMessageDialog(this,
                        "This booking has already been paid for.\nBooking ID: " + bookingId,
                        "Already Paid", JOptionPane.WARNING_MESSAGE);
                outputArea.append("Payment attempt REJECTED for booking " + booking.getUUID() +
                        "\nReason: Booking already paid" +
                        "\nTime: " + LocalDateTime.now() +
                        "\n\n");
                return;
            }

            LocalDate expiry;
            try {
                expiry = LocalDate.parse(expiryText);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this,
                        "Expiry date must be yyyy-MM-dd.",
                        "Invalid date", JOptionPane.ERROR_MESSAGE);
                return;
            }

            CardType type = (CardType) cardTypeBox.getSelectedItem();
            Card card = new Card(cardNumber, type, expiry);

            Payment payment = customer.pay(booking, card);
            outputArea.append("Payment attempted for booking " + booking.getUUID() +
                    "\nResult status: " + booking.getStatus() +
                    "\nPayment object: " + payment +
                    "\nTime: " + LocalDateTime.now() +
                    "\n\n");
        }
    }

    /*  Customer: Booking History  */

    private class CustomerHistoryPanel extends JPanel {

        private JTextField emailField;
        private JTextArea outputArea;

        CustomerHistoryPanel() {
            setLayout(new BorderLayout());

            JLabel title = new JLabel("Customer: Booking History", SwingConstants.CENTER);
            title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));
            add(title, BorderLayout.NORTH);

            JPanel top = new JPanel();
            top.add(new JLabel("Customer Email:"));
            emailField = new JTextField(20);
            top.add(emailField);
            JButton showButton = new JButton("Show History");
            showButton.addActionListener(e -> onShowHistory());
            top.add(showButton);
            add(top, BorderLayout.NORTH);

            outputArea = new JTextArea();
            outputArea.setEditable(false);
            add(new JScrollPane(outputArea), BorderLayout.CENTER);
        }

        void clearOutput() {
            if (outputArea != null) {
                outputArea.setText("");
            }
        }

        private void onShowHistory() {
            String email = emailField.getText().trim();
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please enter an email.");
                return;
            }

            Customer customer = findCustomerByEmail(email);
            if (customer == null) {
                JOptionPane.showMessageDialog(this,
                        "No customer found with email " + email,
                        "Not found", JOptionPane.ERROR_MESSAGE);
                return;
            }

            List<Booking> history = customer.getBookingHistory();
            if (history.isEmpty()) {
                outputArea.setText("No bookings for this customer.");
                return;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("Booking history for ").append(email).append(":\n\n");
            for (Booking b : history) {
                sb.append(shortBookingSummary(b)).append("\n\n");
            }
            outputArea.setText(sb.toString());
        }
    }

    /* Customer: Cancel Booking  */

    private class CustomerCancelPanel extends JPanel {

        private JTextField emailField;
        private JTextField bookingIdField;

        CustomerCancelPanel() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel title = new JLabel("Customer: Cancel Booking");
            title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));

            gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
            add(title, gbc);

            gbc.gridwidth = 1;
            gbc.gridy++;
            add(new JLabel("Customer Email:"), gbc);
            gbc.gridx = 1;
            emailField = new JTextField(20);
            add(emailField, gbc);

            gbc.gridx = 0; gbc.gridy++;
            add(new JLabel("Booking ID:"), gbc);
            gbc.gridx = 1;
            bookingIdField = new JTextField(20);
            add(bookingIdField, gbc);

            gbc.gridx = 0; gbc.gridy++;
            gbc.gridwidth = 2;
            JButton cancelButton = new JButton("Cancel Booking");
            cancelButton.addActionListener(e -> onCancel());
            add(cancelButton, gbc);
        }

        private void onCancel() {
            String email = emailField.getText().trim();
            String bookingId = bookingIdField.getText().trim();

            if (email.isEmpty() || bookingId.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please enter both email and booking ID.");
                return;
            }

            Customer customer = findCustomerByEmail(email);
            if (customer == null) {
                JOptionPane.showMessageDialog(this,
                        "No customer found with email " + email,
                        "Not found", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean result = customer.cancelBooking(bookingId);
            if (result) {
                JOptionPane.showMessageDialog(this,
                        "Booking " + bookingId + " cancelled.");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Unable to cancel booking. It may not exist or is already paid/cancelled.",
                        "Cancel failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // MAIN METHOD TO RUN THE GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BookingSystem system = new BookingSystem();
            new BookingSystemGUI(system);
        });
    }
}


// 