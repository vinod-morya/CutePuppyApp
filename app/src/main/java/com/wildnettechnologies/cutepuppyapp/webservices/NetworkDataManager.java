package com.wildnettechnologies.cutepuppyapp.webservices;

import android.content.Context;

import com.wildnettechnologies.cutepuppyapp.models.RecepieModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NetworkDataManager
  {
	 private static final String TAG = NetworkDataManager.class.getSimpleName();
	 private static RecepieApiInterface apiClient;

	 private NetworkDataManager(Context context)
		{
		  // initialize the api client
		  apiClient = PuppyApiClient.getRecepieClient(context);
		}

	 public static NetworkDataManager getInstance(Context context)
		{
		  // if (instance == null)
		  return new NetworkDataManager(context);
		}

	  public static void getRecepies(final genericCallbacks callback, final DataManagerCallbacks dmc)
	  {
		  Call<RecepieModel> call = apiClient.getRecepies();
		  call.clone().enqueue(new Callback<RecepieModel>()
		  {
			  @Override
			  public void onResponse(Call<RecepieModel> call, Response<RecepieModel> response)
			  {
				  if(response.isSuccessful() && response.body() != null)
				  {
					  callback.onNetworkCallSuccess(response.body());
				  }
				  else
				  {
					  callback.onNetworkCallFailed(response.raw().toString(), "");
				  }
			  }

			  @Override
			  public void onFailure(Call<RecepieModel> call, Throwable t)
			  {
				  dmc.onError(t);
			  }
		  });
	  }


	 public interface DataManagerCallbacks
		{
		  void onError(Throwable error);
		}

	 public interface genericCallbacks
		{
		  void onNetworkCallSuccess(RecepieModel response);

		  void onNetworkCallFailed(String loginError, String message);
		}
  }
