package com.ydh.weile.net;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.util.List;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by liujianying on 14-9-28.
 */
public class HttpUitl {

    private static final String API_URL = "https://api.github.com";


//

     public static Response get()throws IOException {
            OkHttpClient client = new OkHttpClient();

             Request request = new Request.Builder()
                     .url("http://allseeing-i.com")
                     .build();

             Response response = client.newCall(request).execute();
             return response;

     }

    static class Contributor {
        String login;
        int contributions;
    }


    interface GitHub {
        @GET("/repos/{owner}/{repo}/contributors")
        List<Contributor> contributors(
                @Path("owner") String owner,
                @Path("repo") String repo
        );
    }

    public static void geta() {

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .build();

        // Create an instance of our GitHub API interface.
        GitHub github = restAdapter.create(GitHub.class);

        // Fetch and print a list of the contributors to this library.
        List<Contributor> contributors = github.contributors("square", "retrofit");
        for (Contributor contributor : contributors) {
            System.out.println(contributor.login + " (" + contributor.contributions + ")");
        }

    }

}
