package RestPost;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;



public class RestPost{
	
	public  void executeRequest(HttpPost httpPost) {
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

	
	public  void executeMultiPartRequest(String urlString, File file, String accessToken) throws IOException {
	    HttpPost postRequest = new HttpPost(urlString);
	    postRequest = addHeader(postRequest,accessToken );
	   	    		
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

	private  HttpPost addHeader(HttpPost httpPost, String accessToken) {
//		httpPost.setHeader("Content-Type", "multipart/form-data");
		httpPost.setHeader("Authorization", "Bearer " + accessToken);
	    return httpPost;
	}
}

