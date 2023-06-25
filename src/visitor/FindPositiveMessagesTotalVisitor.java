package visitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import user.*;

public class FindPositiveMessagesTotalVisitor implements Visitor {

	// finds positive messages total by looking for positive words
	// used to find percentage of positive messages along with
	// FindMessagesTotalVisitor
	public int total;

	private final List<String> positive = Arrays.asList("fantastic", "adore", "great", "amazing", "awesome",
			"stupendous", "positive", "magnificent");

	public void visit(User temp) {
		ArrayList<String> tweets = temp.getTweets();
		for (int i = 0; i < tweets.size(); i++) {
			String tempTweet = tweets.get(i);
			String lowercaseTweet = tempTweet.toLowerCase();
			for (String word : lowercaseTweet.split(" ")) {
				if (positive.contains(word)) {
					total++;
				}
			}
		}
	}

	public void visit(Group temp) {
	}
}