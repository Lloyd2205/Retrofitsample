package zomatoapi.yml.com.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import zomatoapi.yml.com.adapter.RestaurentCategoryAdapter;
import zomatoapi.yml.com.interfaces.ApiInterface;
import zomatoapi.yml.com.models.RestaurentCategory;
import zomatoapi.yml.com.retrofit.ApiClient;
import zomatoapi.yml.com.zomatoapi.R;

public class MainActivity extends AppCompatActivity {
    private ApiInterface apiInterface;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<RestaurentCategory> restaurentCategoryCall = apiInterface.getCategories("333887d4420e1e8886f3ebaf9f88c64c");
        restaurentCategoryCall.enqueue(new Callback<RestaurentCategory>() {
            @Override
            public void onResponse(Call<RestaurentCategory> call, Response<RestaurentCategory> response) {
                if (response != null) {
                    showToast("response not null");
                    RestaurentCategory restaurentCategory = response.body();
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    RestaurentCategoryAdapter adapter = new RestaurentCategoryAdapter(restaurentCategory);
                    mRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<RestaurentCategory> call, Throwable t) {
                showToast("failure response");
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
