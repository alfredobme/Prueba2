package alfsoft.com.prueba2;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static android.support.constraint.Constraints.TAG;


public class Pagina_Posts extends Fragment {

    ListView lvPosts;
    ArrayList<Datos_Posts> ListaPosts;


    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "https://jsonplaceholder.typicode.com/posts";


    public Pagina_Posts() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewPosts = inflater.inflate(R.layout.pagina_posts, container, false);
        setHasOptionsMenu(true);

        lvPosts = viewPosts.findViewById(R.id.lvPostsListado);
        ListaPosts = new ArrayList<>();

        Adaptador_Posts Posts = new Adaptador_Posts(getActivity(),ListaPosts);
        lvPosts.setAdapter(Posts);

        ActualizarPosts();


        lvPosts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Datos_Posts obj = (Datos_Posts) parent.getItemAtPosition(position);

                Toast.makeText(getActivity(),Integer.toString(obj.getId()), Toast.LENGTH_LONG).show();
            }
        });



        FloatingActionButton fab = viewPosts.findViewById(R.id.fabActualizarPosts);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActualizarPosts();
            }
        });

        return viewPosts;
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        //Por aca solo se crea el menu cada vez que se abre este

        menu.clear();
        menu.add(0,0,0,"Todos");
        menu.add(0,0,0,"Favoritos");
    }


    private void ActualizarPosts() {
        //Verificar Internet
        ConnectivityManager connectivity = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info_Network = connectivity.getActiveNetworkInfo();

        if (info_Network == null) {
            Toast.makeText(getActivity(),"¡ No hay conexion a internet !\n\nVerifique conexiones\ne Intente de nuevo", Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(getActivity(),"¡ Actualizando Posts", Toast.LENGTH_SHORT).show();

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {
                        parseData(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                    }
                }
        );
        queue.add(getRequest);

    }

    public void parseData (JSONArray response){
        ListaPosts = new ArrayList<>();

        for (int i = 0; i < response.length(); i++) {
            try{
                JSONObject data = response.getJSONObject(i);
                ListaPosts.add(new Datos_Posts(
                        Integer.parseInt(data.get("id").toString()),
                        Integer.parseInt(data.get("userId").toString()),
                        data.get("title").toString(),
                        data.get("body").toString()
                ));

            }catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Adaptador_Posts Posts = new Adaptador_Posts(getActivity(),ListaPosts);
        lvPosts.setAdapter(Posts);

        lvPosts.setFocusableInTouchMode(true);
        lvPosts.requestFocus();
   }


    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.f_posts);
    }

}

