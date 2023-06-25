package user;

import java.util.ArrayList;

import observer.*;
import tree.*;
import visitor.*;


public class User extends Subject implements Observer, NodeType {

	private NodeTreeModel treeModel;
	private UserPanel userView;

	private String uniqueID;
	private ArrayList<String> tweets, feed;
	private ArrayList<User> following;

	public User(NodeTreeModel treeModel, String uniqueID) {
		this.uniqueID = uniqueID;
		this.treeModel = treeModel;

		following = new ArrayList<User>();
		tweets = new ArrayList<String>();
		feed = new ArrayList<String>();
		userView = new UserPanel(this);

		addObserver(this);
		following.add(this);
		userView.init();
	}

	//Required NodeType

	//get uniqueID of user
	public String getUniqueID() {
		return uniqueID;
	}

	// none cant add child to user
	public void add(NodeType node){
	}

	// user cant have child return null
	public NodeType getChild(int i) {
		return null;
	}

	// user cant have child return 0
	public int getIndexOfChild(NodeType elem) {
		return 0;
	}

	//user cant haev child return 0
	public int getChildCount() {
		return 0;
	}

	// accept visitor
	public void accept(Visitor vis) {
		vis.visit(this);
	}

	//opening user view for selected user 
	public void openUserView() {
		userView.setVisible(true);
	}

	// Not NodeType

	// return arraylist of tweets
	public ArrayList<String> getTweets() {
		return tweets;
	}

	// gets the last tweeted msg
	public String getLatestTweet() {
		return tweets.get(tweets.size() - 1);
	}

	public void update(Subject subject) {
		if (subject instanceof User) {
			postToNewsFeed(((User) subject).getUniqueID() + ": " + ((User) subject).getLatestTweet());
		}
	}

	public void postToNewsFeed(String tweet) {
		feed.add(tweet);
		userView.addTweetToNewsFeed(tweet);
	}

	public void postTweet(String tweet) {
		tweets.add(tweet);
		notifyObservers();
	}

	public boolean followUser(String userID) {
		User followUser = (User) treeModel.findNodeByID((NodeType) treeModel.getRoot(), userID);
		if (followUser != null) {
			if(!following.contains(followUser)){
				followUser.addObserver(this);
				following.add(followUser);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
