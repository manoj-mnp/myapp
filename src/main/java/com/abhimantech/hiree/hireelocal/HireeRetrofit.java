package com.abhimantech.hiree.hireelocal;

import java.net.CookieHandler;
import java.net.HttpCookie;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RequestInterceptor.RequestFacade;
import retrofit.client.OkClient;
import retrofit.http.Body;
import retrofit.http.POST;

import retrofit.Callback;
import com.squareup.okhttp.OkHttpClient;

public class HireeRetrofit {
    public static final int TYPE_PRODUCTION = 0;
    public static final int TYPE_DEV = 1;
    public static final int TYPE_DEV_LOCAL = 2;
    public static final int TYPE_PREVIEW = 3;

    // Change: the type of server here, production or dev
    public static final int TYPE = TYPE_PRODUCTION;

    // Change: address of the production server
    public static final String SERVER_PRODUCTION = "hiree.com";
    public static final String SERVER_PREVIEW = "preview.hiree.com";
    // Change: address of the dev server
    public static final String SERVER_DEV = "stagemnp.com";
    public static final String SERVER_DEV_LOCAL = "192.168.0.143:8080/mvc";
    private static final String API_URL_REST = "/app";
    public static String API_URL = "https://";
    public static String API_URL_NORMAL = "http://";

    public static String LOGIN_URL = "";

    // retrofit api singleton
    private static MNPApiInterface sApi;

    public static String cookieValue;


    public static MNPApiInterface getApi() {
        if (sApi == null) {
            final MyCookieManager myCookieManager = new MyCookieManager();
            CookieHandler.setDefault(myCookieManager);

            OkHttpClient httpClient = new OkHttpClient();

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(API_URL)
                    .setClient(new OkClient(httpClient))
                    .setRequestInterceptor(new RequestInterceptor() {
						
						public void intercept(RequestFacade requestFacade) {
							 for (HttpCookie cookie : myCookieManager.getCookieStore().getCookies()) {
	                                // Set up expiration in format desired by cookies
	                                // (arbitrarily one hour from now).
	                                Date expiration = new Date(System.currentTimeMillis() + 60 * 60 * 1000);
	                                String expires = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz")
	                                        .format(expiration);

	                                String cookieValue = cookie.getName() + "=" + cookie.getValue() + "; " +
	                                        "path=" + cookie.getPath() + "; " +
	                                        "domain=" + cookie.getDomain() + ";" +
	                                        "expires=" + expires;
	                                requestFacade.addHeader("Cookie", cookieValue);
	                            }
						}
					})
                    .build();
         
            restAdapter.setLogLevel(RestAdapter.LogLevel.FULL);

            sApi = restAdapter.create(MNPApiInterface.class);
        }
        return sApi;
    }

    static {
        // create the API_URL at runtime based on the type
        String server = SERVER_DEV;
        switch (TYPE) {
            case TYPE_PRODUCTION:
                server = SERVER_PRODUCTION;
                break;
            case TYPE_DEV:
                server = SERVER_DEV;
                break;
            case TYPE_DEV_LOCAL:
                server = SERVER_DEV_LOCAL;
                API_URL = API_URL_NORMAL;
                break;
            case TYPE_PREVIEW:
                server = SERVER_PREVIEW;
                API_URL = API_URL_NORMAL;
                break;
        }
        LOGIN_URL = API_URL + server + "/app/login.rest";
        API_URL += server;
    }

    public interface MNPApiInterface {
    	 @POST("/app/login.rest")
         void login(@Body LoginRequest loginRequest, Callback<LoginResponse> loginResponseCallback);
    }
}
