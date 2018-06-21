package com.project.dmitry.retrofit2.retrofitSteps;

import java.io.File;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by DmitryPC on 21.06.2018.
 */

public interface Step2 {
    //это класс с описанием API
    @GET("users/list")
    Call<User> getUsersList();//User - pojo

    @GET("users/list?sort=desc")
    Call<User> getUsersListWithSort();//User - pojo

    //    A replacement block is an alphanumeric string surrounded by { and }.
//    A corresponding parameter must be annotated with @Path using the same string.
    @GET("group/{id}/users")
    Call<List<User>> groupList(@Path("id") int groupId);

    @GET("group/{id}/users")
    Call<List<User>> groupList(@Path("id") int groupId, @Query("sort") String sort);//добавит один доп параметр

    //baseUrl/group/5(это id)/users?sort=(то, что передали)
//    For complex query parameter combinations a Map can be used.
    @GET("group/{id}/users")
    Call<List<User>> groupList(@Path("id") int groupId, @QueryMap Map<String, String> options);

//    An object can be specified for use as an HTTP request body with the @Body annotation.

    @POST("users/new")
    Call<User> createUser(@Body User user);
//        The object will also be converted using a converter specified on the Retrofit instance.
// If no converter is added, only RequestBody can be used.

    //    @FormUrlEncoded - Используется при использовании пары "имя/значение" в POST-запросах
// Each key-value pair is annotated with @Field containing the name and the object providing the value.
    @FormUrlEncoded
    @POST("user/edit")
    Call<User> updateUser(@Field("first_name") String first, @Field("last_name") String last);

//Multipart requests are used when @Multipart is present on the method. Parts are declared using the @Part annotation.
    //    Используется при загрузке файлов или изображений

    @Multipart
    @PUT("user/photo")
    Call<User> updateUser(@Part("photo") RequestBody photo, @Part("description") RequestBody description);

    //    You can set static headers for a method using the @Headers annotation.
    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })
    @GET("users/{username}")
    Call<User> getUser(@Path("username") String username);

//    A request Header can be updated dynamically using the
//    @Header annotation. A corresponding parameter must be provided to the @Header.

    @GET("user")
    Call<User> getUser(@Header("Authorization") String authorization);
//    Headers that need to be added to every request can be specified using an !!OkHttp interceptor!!.


//    @Url - url как параметр
@GET
public Call<File> getZipFile(@Url String url);

//    SYNCHRONOUS VS. ASYNCHRONOUS
//    Call instances can be executed either synchronously or asynchronously. Each instance can only be used once, but calling clone() will create a new instance that can be used.
//
//    On Android, callbacks will be executed on the main thread. On the JVM, callbacks will happen on the same thread that executed the HTTP request.
}