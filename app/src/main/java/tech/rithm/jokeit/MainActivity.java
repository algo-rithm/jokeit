package tech.rithm.jokeit;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;

import tech.rithm.jokefactory.JokeFactory;

/**
 * Created by rithm on 11/17/2016.
 */

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        new JokeEndPointAsyncTask().execute(new Pair<Context, String>(this, "Manfred"));

        Log.i("JokeMainActivity", "Here is the joke> " + JokeFactory.tellJoke() );
    }
}
