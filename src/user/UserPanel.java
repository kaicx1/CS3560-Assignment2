package user;

import java.awt.event.ActionEvent;
import javax.swing.*;

import app.SingletonAdminPanel;

public class UserPanel extends SingletonAdminPanel {

	//declaring all 
	private User user;
	private JPanel followPanel, newsFeedPanel;
	private JButton followUser, postTweet;
	private JTextField uniqueIDTF, tweetTF;
	private JList<String> followingsList, newsFeedList;
	private DefaultListModel<String> followingsLM, newsFeedLM;
	private JScrollPane followingsSP, newsFeedSP;

	public UserPanel(User user) {
		this.user = user;
	}

	public void init() {
		getContentPane().setLayout(null);
		setTitle("User View - " + user.getUniqueID());
		setSize(470, 500);

		// setting all 
		followPanel = new JPanel();
		newsFeedPanel = new JPanel();
		uniqueIDTF = new JTextField();
		tweetTF = new JTextField();
		followUser = new JButton("Follow User");
		postTweet = new JButton("Post Tweet");

		followingsLM = new DefaultListModel<String>();
		followingsList = new JList<String>(followingsLM);
		followingsSP = new JScrollPane(followingsList);

		newsFeedLM = new DefaultListModel<String>();
		newsFeedList = new JList<String>(newsFeedLM);
		newsFeedSP = new JScrollPane(newsFeedList);

		// follow panel styling
		stylePanel(followPanel, 10, 10, 450, 235);
		stylePanel(newsFeedPanel, 10, 245, 450, 235);
		add(followPanel);
		add(newsFeedPanel);

		styleButton(followUser, 230, 10, 210, 35);
		styleTextField(uniqueIDTF, 10, 10, 210, 35);
		styleScrollPane(followingsSP, 10, 55, 420, 150);

		followPanel.add(followUser);
		followPanel.add(uniqueIDTF);
		followPanel.add(followingsSP);

		// news feed styling
		styleButton(postTweet, 230, 10, 210, 35);
		styleTextField(tweetTF, 10, 10, 210, 35);
		styleScrollPane(newsFeedSP, 10, 55, 430, 150);

		newsFeedPanel.add(postTweet);
		newsFeedPanel.add(tweetTF);
		newsFeedPanel.add(newsFeedSP);
	}

	// Check what action to do when button is clicked
	public void actionPerformed(ActionEvent clicked) {
		if (clicked.getSource() == postTweet) {
			sendTweet();
		} else if (clicked.getSource() == followUser) {
			followUser();
		}
	}

	// sending or publishing tweet
	public void sendTweet() {
		if (!tweetTF.getText().equals("")) {
			user.postTweet(tweetTF.getText());
		} else {
			JOptionPane.showMessageDialog(this, "Error: Can not post blank", "Tweet Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// following user based on UniqueID
	public void followUser() {
		String UniqueID = uniqueIDTF.getText();
		if (!UniqueID.equals("")) {
			if (user.followUser(UniqueID) == true) {
				followingsLM.add(0, UniqueID);
			} else {
				JOptionPane.showMessageDialog(this, "Error: User can not be followed.", "User Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Error: Can not follow blank", "Follow Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// Adding tweets to the users news feed
	public void addTweetToNewsFeed(String tempTweet) {
		newsFeedLM.add(0, tempTweet);
	}
}
