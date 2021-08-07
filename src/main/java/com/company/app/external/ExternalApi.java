package com.company.app.external;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface ExternalApi {

    @GET("/todos")
    Call<List<ExternalTodo>> loadTodos();

}
