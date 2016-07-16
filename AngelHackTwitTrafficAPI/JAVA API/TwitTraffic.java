import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
public class TwitTraffic {
 
 
 public static void main(String[] args) throws ClientProtocolException, IOException {
  	TwitTraffic obj = new TwitTraffic();
  	System.out.println(obj.FetchTweet("blrcitytraffic")); //Enter the required Twitter Handle or a Hashtag
  	System.out.println(obj.HavenSentiment("Bad traffic in Brigade road and coles road please go with caution")); //Enter the required Twitter Handle or a Hashtag
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

 public String HavenSentiment(String Tweet) throws ClientProtocolException, IOException {
 	HttpClient httpclient = new DefaultHttpClient();
	HttpPost httppost = new HttpPost("https://api.havenondemand.com/1/api/sync/analyzesentiment/v1");
	List<NameValuePair> params = new ArrayList<NameValuePair>(2);
	params.add(new BasicNameValuePair("apikey", "a993aeb0-648c-424c-8c9b-ebc9f01accbe"));
	params.add(new BasicNameValuePair("text", Tweet));
	httppost.setEntity(new UrlEncodedFormEntity(params));
	//Execute and get the response.
	HttpResponse response = httpclient.execute(httppost);
	BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		return result.toString();
    }
}