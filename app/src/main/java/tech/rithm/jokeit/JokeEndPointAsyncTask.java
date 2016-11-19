package tech.rithm.jokeit;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import tech.rithm.myapplication.backend.myApi.MyApi;

/**
 * Created by rithm on 11/18/2016.
 */

public class JokeEndPointAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {

    private static MyApi myApiService = null;
    private Context mContext;

    @Override
    protected String doInBackground(Pair<Context, String>... params){
        if(myApiService == null){
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }

        mContext = params[0].first;
        String name = params[0].second;

        try{
            return myApiService.sayHi(name).execute().getData();
        } catch (IOException e){
            return e.getMessage();
        }

    }

    @Override
    protected void onPostExecute(String result){
        Toast.makeText(mContext, result, Toast.LENGTH_LONG).show();
    }
}
