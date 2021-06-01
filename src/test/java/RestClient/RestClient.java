package RestClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpResponse;

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
	//Post Method
	public void ExecuteTest(String endpoint, String tkn, String filepath) throws IOException {
		// Sending get request
        URL url = new URL(endpoint);
        File xmlFile = new File(filepath);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestProperty("Authorization","Bearer "+tkn);
        //e.g. bearer token= eyJhbGciOiXXXzUxMiJ9.eyJzdWIiOiPyc2hhcm1hQHBsdW1zbGljZS5jb206OjE6OjkwIiwiZXhwIjoxNTM3MzQyNTIxLCJpYXQiOjE1MzY3Mzc3MjF9.O33zP2l_0eDNfcqSQz29jUGJC-_THYsXllrmkFnk85dNRbAw66dyEKBP5dVcFUuNTA8zhA83kk3Y41_qZYx43T

        conn.setRequestProperty("Content-Type","application/json");
       
        conn.getResponseMessage();
       
        
				
				
				
	}
	
	
	
	

}
