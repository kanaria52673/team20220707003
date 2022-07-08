package web;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class Json05 {

	static answer get(String s) 
			throws IOException, URISyntaxException, InterruptedException {
		Gson gson = new Gson();
		String url = "https://r04jk3a01-prediction.cognitiveservices.azure.com/luis/prediction/v3.0/apps/db98dbd1-b400-400d-b12d-ff96fd32a14a/slots/production/predict?"
				+ "verbose=true&"
				+ "show-all-intents=true&"
				+ "log=true&"
				+ "subscription-key=82eb631f649a4635ad1ca961f0571e87&"
				+ "query='"+s+"'";
		InetSocketAddress proxy =new InetSocketAddress("172.17.0.2", 80);
		JsonReader reader = WebApiConnector.getJsonReader(url,proxy);
		//JsonReader reader = WebApiConnector.getJsonReader(url);
		answer message = null;
		if (reader != null) {
			message = gson.fromJson(reader, answer.class);
			reader.close();
		}
		return message;
	}

}
class answer{
	String query;
	Prediction prediction;
}
class Prediction{
	String topIntent;
	Object intents;
}
