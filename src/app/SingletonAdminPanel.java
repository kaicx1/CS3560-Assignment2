package app;

import user.*;
import visitor.*;
import javax.swing.*;

import tree.*;

import java.awt.event.*;

public class SingletonAdminPanel extends JFrame implements ActionListener {

	// making all
	private static SingletonAdminPanel instance = new SingletonAdminPanel();
	private JTree tree;
	private JScrollPane treeJScrollPane;
	private JPanel treePanel, optionPanel;
	private JLabel treeTitle;
	private JButton addGroup, addUser, openUserView, showUserTotal, showGroupTotal, showMessagesTotal,
			showPositivePercentage, showIDVerification, showLastUpdatedUser;
	private JTextField userID, groupID;
	private NodeType root;
	private NodeTreeModel treeModel;

	// eager instance
	public static SingletonAdminPanel getInstance() {
		return instance;
	}

	// making GUI for admin panel
	public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setTitle("Mini Twitter");
		setSize(775, 400);

		// creating all panels, buttons, textfields, trees, etc
		treePanel = new JPanel();
		optionPanel = new JPanel();
		root = new Group(treeModel, "Root");
		treeModel = new NodeTreeModel(root);
		tree = new JTree(treeModel);
		treeJScrollPane = new JScrollPane(tree);
		treeTitle = new JLabel("Tree View");
		showUserTotal = new JButton("Show User Total");
		showGroupTotal = new JButton("Show Group Total");
		showMessagesTotal = new JButton("Show Messages Total");
		showPositivePercentage = new JButton("Show Positive Percentage");
		addUser = new JButton("Add User");
		addGroup = new JButton("Add Group");
		openUserView = new JButton("Open User View");
		showIDVerification = new JButton("ID Verification");
		showLastUpdatedUser = new JButton("Show Last Updated User");
		userID = new JTextField();
		groupID = new JTextField();

		// Styling all panels, buttons, textfields, trees, etc
		stylePanel(treePanel, 10, 10, 300, 350);
		stylePanel(optionPanel, 320, 10, 450, 350);
		add(treePanel);
		add(optionPanel);

		tree.setCellRenderer(new StyleNodeTree());
		styleTree(tree, 0, 0, 280, 300);

		treeJScrollPane.setBounds(10, 40, 280, 300);

		styleTitleLabel(treeTitle, 0, 5, 100, 30);
		styleButton(addUser, 230, 20, 210, 35);
		styleButton(addGroup, 230, 70, 210, 35);
		styleButton(openUserView, 10, 130, 430, 35);
		styleButton(showUserTotal, 10, 170, 210, 35);
		styleButton(showGroupTotal, 230, 170, 210, 35);
		styleButton(showMessagesTotal, 10, 220, 210, 35);
		styleButton(showPositivePercentage, 230, 220, 210, 35);
		styleButton(showIDVerification, 10, 270, 210, 35);
		styleButton(showLastUpdatedUser, 230, 270, 210, 35);
		userID.setBounds(10, 20, 210, 35);
		groupID.setBounds(10, 70, 210, 35);

		// adding all panels, buttons, textfields, trees, etc
		treePanel.add(treeJScrollPane);
		treePanel.add(treeTitle);
		optionPanel.add(showUserTotal);
		optionPanel.add(showGroupTotal);
		optionPanel.add(showMessagesTotal);
		optionPanel.add(showPositivePercentage);
		optionPanel.add(addUser);
		optionPanel.add(userID);
		optionPanel.add(addGroup);
		optionPanel.add(groupID);
		optionPanel.add(openUserView);
		optionPanel.add(showIDVerification);
		optionPanel.add(showLastUpdatedUser);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent clicked) {
		if (clicked.getSource() == addUser) {
			addUser();
		} else if (clicked.getSource() == addGroup) {
			addGroup();
		} else if (clicked.getSource() == openUserView) {
			openUserView();
		} else if (clicked.getSource() == showUserTotal) {
			getUserTotal();
		} else if (clicked.getSource() == showGroupTotal) {
			getGroupTotal();
		} else if (clicked.getSource() == showMessagesTotal) {
			getMessagesTotal();
		} else if (clicked.getSource() == showPositivePercentage) {
			getPositivePercentage();
		} else if (clicked.getSource() == showIDVerification) {
			getIDVerification();
		} else if (clicked.getSource() == showLastUpdatedUser){
			getLastUpdatedUser();
		}
	}

	private void addUser() {
		String id = userID.getText();
		if (id.equals("")) {
			JOptionPane.showMessageDialog(this, "Error: User cannot have no name.", "User Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			NodeType parent = getSelectedNodeType();
			if (treeModel.findNodeByID(root, id) == null) {
				treeModel.addNode(parent, new User(treeModel, id));
			} else {
				JOptionPane.showMessageDialog(this, "Error: ID already in uses.", "User Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void addGroup() {
		String id = groupID.getText();
		if (id.equals("")) {
			JOptionPane.showMessageDialog(this, "Error: Group cannot have no name.", "Group Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			NodeType parent = getSelectedNodeType();
			if (treeModel.findNodeByID(root, id) == null) {
				treeModel.addNode(parent, new Group(treeModel, id));
			} else {
				JOptionPane.showMessageDialog(this, "Error: ID already in use.", "Group Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void openUserView() {
		NodeType elem = getSelectedNodeType();
		elem.openUserView();
	}

	private void getUserTotal() {
		int result;
		FindUserTotalVisitor vis = new FindUserTotalVisitor();
		root.accept(vis);
		result = vis.total;
		JOptionPane.showMessageDialog(this, "Total count of Users: " + result, "Total Messages",
				JOptionPane.PLAIN_MESSAGE);
	}

	private void getGroupTotal() {
		int result;
		FindGroupTotalVisitor vis = new FindGroupTotalVisitor();
		root.accept(vis);
		result = vis.total;
		JOptionPane.showMessageDialog(this, "Total count of Groups: " + result, "Total Groups",
				JOptionPane.PLAIN_MESSAGE);
	}

	private void getMessagesTotal() {
		int result;
		FindMessagesTotalVisitor vis = new FindMessagesTotalVisitor();
		root.accept(vis);
		result = vis.total;
		JOptionPane.showMessageDialog(this, "Total count of Tweets: " + result, "Total Messages",
				JOptionPane.PLAIN_MESSAGE);
	}

	private void getPositivePercentage() {
		double result;
		FindPositiveMessagesTotalVisitor posTotalVis = new FindPositiveMessagesTotalVisitor();
		FindMessagesTotalVisitor messagesTotalVis = new FindMessagesTotalVisitor();
		root.accept(posTotalVis);
		root.accept(messagesTotalVis);

		result = (double) posTotalVis.total / (double) messagesTotalVis.total * 100.0;

		JOptionPane.showMessageDialog(this, "Percentage of Tweets containing positive messages: " + result + "%",
				"Positive Percentage", JOptionPane.PLAIN_MESSAGE);
	}

	private void getIDVerification(){
		boolean result;
		FindIDVerification vis = new FindIDVerification();
		root.accept(vis);
		result = vis.IDboolean;
		if (result == true){
			JOptionPane.showMessageDialog(this, "All IDs are valid", "ID validation", JOptionPane.PLAIN_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "All IDs are not valid", "ID validation", JOptionPane.PLAIN_MESSAGE);
		}

	}

	private void getLastUpdatedUser(){
		String result;
		FindLastUpdatedUser vis = new FindLastUpdatedUser();
		root.accept(vis);
		result = vis.lastUpdated;
		JOptionPane.showMessageDialog(this, "Last updated user: " + result, "Updated User", JOptionPane.PLAIN_MESSAGE);
	}

	private NodeType getSelectedNodeType() {
		NodeType result = ((NodeType) tree.getLastSelectedPathComponent());
		if (result == null) {
			result = root;
		}
		return result;
	}



	// Creating styling to keep everything uniform
	protected void styleButton(JButton b, int x, int y, int w, int h) {
		b.setBounds(x, y, w, h);
		b.addActionListener(this);
	}

	protected void stylePanel(JPanel p, int x, int y, int w, int h) {
		p.setBounds(x, y, w, h);
		p.setLayout(null);
	}

	protected void styleTitleLabel(JLabel l, int x, int y, int w, int h) {
		l.setBounds(x, y, w, h);
		l.setHorizontalAlignment(SwingConstants.CENTER);
	}

	protected void styleTree(JTree t, int x, int y, int w, int h) {
		t.setBounds(x, y, w, h);
	}

	protected void styleTextField(JTextField tf, int  x, int y, int w , int h){
		tf.setBounds(x, y, w, h);
	}

	protected void styleScrollPane(JScrollPane sp, int x, int y, int w, int h){
		sp.setBounds(x, y, w, h);
	}
}
