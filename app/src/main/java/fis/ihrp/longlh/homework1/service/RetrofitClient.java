package fis.ihrp.longlh.homework1.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static UserService getClient() {
        UserService userService = new Retrofit.Builder()
                .baseUrl("https://ihrp.fis.vn/bke_mob_v10_pos/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserService.class);
        return userService;
    }

}
