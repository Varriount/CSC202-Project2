import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window.Type;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import food.CategoryList;
import food.FoodCategory;
import food.FoodItem;

public class VenderWindow {

    JFrame frame;
    private JTextField textField;
    private JTable table;
    int lastSearchIndex = -1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    VenderWindow window = new VenderWindow();
		    window.frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the application.
     */
    public VenderWindow() {
	initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
	frame = new JFrame();
	frame.setType(Type.UTILITY);
	frame.setBounds(100, 100, 450, 300);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	@SuppressWarnings("serial")
	DefaultTableModel model = new DefaultTableModel(new Object[][] {},
		new String[] { "Category", "Name", "Description",
			"Special Orders", "Price", "Quantity", "Size" }) {
	    Class[] columnTypes = new Class[] { String.class, String.class,
		    String.class, String.class, Double.class, Integer.class,
		    Integer.class };

	    @SuppressWarnings("unchecked")
	    public Class getColumnClass(int columnIndex) {
		return columnTypes[columnIndex];
	    }
	};

	JScrollPane scrollPane = new JScrollPane();
	frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

	table = new JTable();
	table.setModel(model);
	table.getColumnModel().getColumn(3).setPreferredWidth(83);
	table.setCellSelectionEnabled(true);
	scrollPane.setViewportView(table);

	JPanel searchPanel = new JPanel();
	frame.getContentPane().add(searchPanel, BorderLayout.NORTH);
	searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));

	textField = new JTextField();
	searchPanel.add(textField);
	textField.setColumns(10);

	JButton btnSearch = new JButton("Search");
	btnSearch.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		String key = textField.getText();
		if (!key.isEmpty()) {
		    int index = -1;
		    Vector<Vector<Object>> contents = model.getDataVector();
		    for (Vector<?> row : contents) {
			index++;
			String name = (String) row.get(1);
			if (index > lastSearchIndex) {
			    if (name.contains(key)) {
				table.changeSelection(index, 0, false, false);
			    }
			}
		    }
		}
	    }
	});
	searchPanel.add(btnSearch);

	JPanel btnPanel = new JPanel();
	frame.getContentPane().add(btnPanel, BorderLayout.SOUTH);
	btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));

	JButton btnUpdate = new JButton("Update");
	btnUpdate.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		lastSearchIndex = -1;
		CategoryList categories = new CategoryList();
		Vector<Vector<Object>> contents = model.getDataVector();
		for (Vector<?> row : contents) {
		    FoodItem item = new FoodItem();

		    String categoryName = (String) row.get(0);
		    item.setName((String) row.get(1));
		    item.setDescription((String) row.get(2));
		    item.setSpecialOrder((String) row.get(3));
		    item.setPrice(new BigDecimal((Double) row.get(4)));
		    item.setQuantity((Integer) row.get(5));
		    item.setSize((Integer) row.get(6));
		    System.out.println(item);

		    FoodCategory category = categories
			    .searchForCategory(categoryName);
		    if (category == null) {
			category = new FoodCategory();
			category.setName(categoryName);
			categories.add(category);
		    }
		    category.getItems().add(item);
		}

		try {
		    categories.writeToJsonFile("hope.json");
		} catch (IOException e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}
	    }
	});
	btnPanel.add(btnUpdate);

	JButton btnAdd = new JButton("Add");
	btnAdd.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		lastSearchIndex = -1;
		model.addRow(new Object[] { "", "", "", "", new BigDecimal(0),
			new Integer(0), new Integer(0) });
	    }
	});
	btnPanel.add(btnAdd);

	JButton btnRemove = new JButton("Remove");
	btnRemove.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		lastSearchIndex = -1;
		model.removeRow(table.getSelectedRow());
	    }
	});
	btnPanel.add(btnRemove);

    }
}
