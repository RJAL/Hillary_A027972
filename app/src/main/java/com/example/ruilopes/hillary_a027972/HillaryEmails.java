package com.example.ruilopes.hillary_a027972;

import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Rui Lopes on 13/11/2016.
 */
public class HillaryEmails extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            String JsonStr = null;

            try {
                URL url = new URL("http://graphics.wsj.com/hillary-clinton-email-documents/api/search.php?subject=&text=&to=&from=&start=&sort=docDate&order=desc&docid=&limit="+params[0]+"&offset=0");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }
                JsonStr = buffer.toString();
                return JsonStr;
            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                return null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }
                }
            }
        }

        /*@Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //tView.setText(s);
            Log.i("json", s);
        }*/
    }

