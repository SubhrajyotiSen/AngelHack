import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
public class TwitTraffic {
 
 
 public static void main(String[] args) throws ClientProtocolException, IOException {
  TwitTraffic obj = new TwitTraffic();
  System.out.println(obj.FetchTweet("blrcitytraffic")); //Enter the required Twitter Handle or a Hashtag
 }

 
 //Java API to Fetch Tweets
 public String FetchTweet(String TweetHandler) throws ClientProtocolException, IOException {
 	  HttpClient client = new DefaultHttpClient();
 	  HttpGet request = new HttpGet("https://api.twitter.com/1.1/search/tweets.json?q=%40"+TweetHandler+"&until=2016-07-16");
	  request.setHeader("Authorization","Bearer AAAAAAAAAAAAAAAAAAAAAANowAAAAAAAlprc%2Fj8Ng%2F1YCA83uZfztCOB3GE%3DK6qNqSXwYfrmHIqE2KQgYmwGQdDMSBYJU5W2gejqCUaqiHltaT");
	  HttpResponse response = client.execute(request);
	  BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
	  String line = "";
	  line = rd.readLine();
	  return line;
 }

}