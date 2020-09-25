package com.fenixinnovation.api.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.fenixinnovation.api.R;
import com.fenixinnovation.api.ui.adapters.CaseAdapter;
import com.fenixinnovation.api.ui.objects.Case;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.fenixinnovation.api.ui.utils.Constants.API_LINK_V1;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private Handler mHandler;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = root.findViewById(R.id.rv_lsit);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));

        mHandler = new Handler(Looper.getMainLooper());

        fefchCases();

        return root;
    }

    private void fefchCases() {

        String uri = API_LINK_V1;

        Log.v("EXECUTING", uri);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(uri)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Request Failed", "Message : " + e.getMessage());
                //No network
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String res = Objects.requireNonNull(response.body()).string();
                Log.v("RESPONSE", res + " ");

                mHandler.post(() -> {
                    try {

                        JSONArray items = new JSONArray(res);
                        int itemsSize = items.length();

                        if (itemsSize > 0) {
                            Case model;
                            List<Case> casesModelList = new ArrayList<>();
                            JSONObject object;

                            try {
                                for (int i = 0; i < itemsSize; i++) {

                                    object = items.getJSONObject(i);

                                    String name = object.getString("nome_paciente");
                                    String nickname = object.getString("sobrenome_paciente");
                                    String birthday = object.getString("nascimento_paciente");
                                    String city =object.getString("municipio_paciente");
                                    String province=object.getString("provincia_paciente");
                                    String status=object.getString("estado_caso");
                                    String createdAt=object.getString("created_at");

                                    model = new Case();

                                    model.setName(name);
                                    model.setNickname(nickname);
                                    model.setBirthday(birthday);
                                    model.setCity(city);
                                    model.setProvince(province);
                                    model.setStatus(status);
                                    model.setCreatedAt(createdAt);
                                    casesModelList.add(model);
                                }
                                //Passing the data list to the adapter
                                recyclerView.setAdapter(new CaseAdapter(getContext(), casesModelList));


                            } catch (JSONException je) {
                                je.printStackTrace();
                                //No Connection
                            }

                        } else {
                            Toast.makeText(getContext(), "Sem dados", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        //No network
                        Log.e("ERROR : ", e.getMessage() + " ");
                    }
                });
            }
        });


    }
}