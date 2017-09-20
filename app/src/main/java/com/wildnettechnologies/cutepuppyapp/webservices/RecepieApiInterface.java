package com.wildnettechnologies.cutepuppyapp.webservices;

import com.wildnettechnologies.cutepuppyapp.Constants.PuppyConstants;
import com.wildnettechnologies.cutepuppyapp.models.RecepieModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * * Interface through which all the api calls will be performed
 */
public interface RecepieApiInterface {

    //@formatter:off
    @POST(PuppyConstants.PUPPY_API)
    Call<RecepieModel> getRecepies();
}

