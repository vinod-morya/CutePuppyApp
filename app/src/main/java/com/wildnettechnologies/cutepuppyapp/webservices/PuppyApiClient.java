package com.wildnettechnologies.cutepuppyapp.webservices;

import android.content.Context;
import android.util.Log;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PuppyApiClient
  {
	 private static final String BASE_URL = "http://www.recipepuppy.com/api/";
	 private static RecepieApiInterface sRecepieApiService;

	  public static RecepieApiInterface getRecepieClient(final Context context)
	  {
		  Interceptor interceptor = new Interceptor()
		  {
			  @Override
			  public Response intercept(Chain chain) throws IOException
			  {
				  Request newRequest = chain.request().newBuilder().build();
				  return chain.proceed(newRequest);
			  }
		  };

		  OkHttpClient.Builder client = new OkHttpClient.Builder();
		  client.interceptors().add(interceptor);

		  if(sRecepieApiService == null)
		  {
			  Retrofit.Builder builder = new Retrofit.Builder();
			  builder.baseUrl(BASE_URL);
			  builder.client(client.build());
			  builder.addConverterFactory(GsonConverterFactory.create());
			  Retrofit retrofit = builder.build();

			  sRecepieApiService = retrofit.create(RecepieApiInterface.class);
		  }
		  return sRecepieApiService;
	  }

	 static String bodyToString(final Request request)
		{
		  try
		  {
			 final Request copy = request.newBuilder().build();
			 final Buffer buffer = new Buffer();
			 copy.body().writeTo(buffer);
			 return buffer.readUtf8();
		  }
		  catch(final IOException e)
		  {
			 return "did not work";
		  }
		}
  }
