package com.ichi2.utils;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

import com.ichi2.anki1.AnkiDroidApp;

public class HttpUtility {
	public static Boolean postReport(String url, List<NameValuePair> values) {
    	HttpClient httpClient = new DefaultHttpClient();  
        HttpPost httpPost = new HttpPost(url);  
      
        try {  
        	httpPost.setEntity(new UrlEncodedFormEntity(values));  
            HttpResponse response = httpClient.execute(httpPost);  
            
            switch(response.getStatusLine().getStatusCode()) {
	            case 200:
	            	Log.e(AnkiDroidApp.TAG, String.format("feedback report posted to %s", url));
	            	return true;
	            	
            	default:
            		Log.e(AnkiDroidApp.TAG, String.format("feedback report posted to %s message", url));
            		Log.e(AnkiDroidApp.TAG, String.format("%d: %s", response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase()));
	            	break;
            }
        } catch (ClientProtocolException ex) {  
        	Log.e(AnkiDroidApp.TAG, ex.toString());
        } catch (IOException ex) {  
        	Log.e(AnkiDroidApp.TAG, ex.toString());  
        }
        
        return false;
	}
}
