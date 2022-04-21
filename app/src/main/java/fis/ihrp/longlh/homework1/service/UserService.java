package fis.ihrp.longlh.homework1.service;

import com.google.gson.JsonObject;

import fis.ihrp.longlh.homework1.model.FindEmployeeRequest;
import fis.ihrp.longlh.homework1.model.ListFuncionRequest;
import fis.ihrp.longlh.homework1.model.LoginRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {

    @POST("api/v1/login")
    Call<JsonObject> loginUser(@Body LoginRequest loginmodel);

//    @POST("api/v1/add")
//    Call<LoginRequest> addUser(@Body LoginRequest loginmodel);
//
//    @POST("api/v1/delete")
//    Call<LoginRequest> DeleteUser(@Body LoginRequest loginmodel);

    @POST("api/v1/function/employee/find")
    Call<JsonObject> findEmployee(@Body FindEmployeeRequest findEmployeeRequest);

    @POST("api/v1/function/property/list")
    Call<JsonObject> listFuncion(@Body ListFuncionRequest listFuncionRequest);

}
