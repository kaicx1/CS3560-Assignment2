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
	private long creationTime;
	private long lastUpdateTime;

	public User(NodeTreeModel treeModel, String uniqueID) {
		this.uniqueID = uniqueID;
		this.treeModel = treeModel;
		creationTime = System.currentTimeMillis();
		lastUpdateTime = System.currentTimeMillis();

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

	public long getCreationTime(){
		return creationTime;
	}

	// Not NodeType

	// return arraylist of tweets
	public long getLastUpdateTime(){
		return lastUpdateTime;
	}

	public void printLastUpdateTime(){
		System.out.println(uniqueID + " was updated at " + lastUpdateTime);
	}

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

	public void postTweet(String tweet) {
		tweets.add(tweet);
		notifyObservers();
	}

	public void postToNewsFeed(String tweet) {
		lastUpdateTime = System.currentTimeMillis();
		printLastUpdateTime();
		feed.add(tweet);
		userView.addTweetToNewsFeed(tweet);
	}

	public boolean followUser(String userID) {
		lastUpdateTime = System.currentTimeMillis();
		printLastUpdateTime();
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
