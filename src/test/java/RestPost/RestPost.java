package RestPost;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;



public class RestPost{
	
	public static void main(String[] args) throws IOException {
		executeMultiPartRequest("https://api.adaptavist.io/tm4j/v2/automations/executions/junit?projectKey=AD&autoCreateTestCases=true", new File("C:\\Users\\nilanjan.islam\\eclipse-workspace\\Demo\\Demo\\target\\surefire-reports\\junitreports\\TEST-com.mydemo.SauceTestUpdated.xml"));
		
	}
	private static void executeRequest(HttpPost httpPost) {
	    try {
	        HttpClient client  = HttpClientBuilder.create().build();
	        HttpResponse response = client.execute(httpPost);
	        System.out.println(httpPost);
	        System.out.println("Response Code:  " + response.getStatusLine());
	    }  catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    } catch (ClientProtocolException e) {
	        e.printStackTrace();
	    } catch (IllegalStateException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	
	public static void executeMultiPartRequest(String urlString, File file) throws IOException {
	    HttpPost postRequest = new HttpPost(urlString);
	    postRequest = addHeader(postRequest, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIwMDYwZTkwOS01OWRlLTM1NmEtOTJjOS1hYTIxZTg0NTM0NzkiLCJjb250ZXh0Ijp7ImJhc2VVcmwiOiJodHRwczpcL1wvYWRtcmxkZW1vLmF0bGFzc2lhbi5uZXQiLCJ1c2VyIjp7ImFjY291bnRJZCI6IjVmMzEzZjgzZTExNTQyMDA0NjJkMjkwZiJ9fSwiaXNzIjoiY29tLmthbm9haC50ZXN0LW1hbmFnZXIiLCJleHAiOjE2NTI4ODIxMjAsImlhdCI6MTYyMTM0NjEyMH0.-2t8fRDKlm8BZDkSJ9TTwn7QZXKGx6qiRWnwdCnxJuk");
	   	    		
	    try {
	    	HttpEntity entity = MultipartEntityBuilder.create().addPart("file", new FileBody(file)).build();
	    	System.out.println("File is: "+ entity.getContentType());
	    	postRequest.setEntity(entity);
//	    	 postRequest.setEntity(new FileEntity(file));
	    	} catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    executeRequest(postRequest);
	}

	private static HttpPost addHeader(HttpPost httpPost, String accessToken) {
//		httpPost.setHeader("Content-Type", "multipart/form-data");
		httpPost.setHeader("Authorization", "Bearer " + accessToken);
	    return httpPost;
	}
}

