function fetchTweet()
{
	var tweets = httpGet("https://api.twitter.com/1.1/search/tweets.json?q=%40blrcitytraffic&until=2016-07-16");
	console.log(tweets);
}

function httpGet(theUrl)
{
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", theUrl); // false for synchronous request
    xmlHttp.setRequestHeader("Authorization", "Bearer AAAAAAAAAAAAAAAAAAAAAANowAAAAAAAlprc%2Fj8Ng%2F1YCA83uZfztCOB3GE%3DK6qNqSXwYfrmHIqE2KQgYmwGQdDMSBYJU5W2gejqCUaqiHltaT");
    xmlHttp.send( null );
    return xmlHttp.responseText;
}
