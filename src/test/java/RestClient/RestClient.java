package RestClient;

import java.io.IOException;


import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.*;


public class RestClient {
	//Get Method
	public int get(String url) throws ClientProtocolException,IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget=new HttpGet(url);
		CloseableHttpResponse  closeableHttpResponse = httpClient.execute(httpget);
		int statCode = closeableHttpResponse.getStatusLine().getStatusCode();
		return statCode;
	}

}
