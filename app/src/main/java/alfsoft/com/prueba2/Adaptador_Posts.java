package alfsoft.com.prueba2;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Pattern;

public class Adaptador_Posts extends BaseAdapter {
    Context context;
    List<Datos_Posts> ListaPosts;

    public Adaptador_Posts(Context context, List<Datos_Posts> listaPosts) {
        this.context = context;
        ListaPosts = listaPosts;
    }

    @Override
    public int getCount() {
        return ListaPosts.size();
    }

    @Override
    public Object getItem(int position) {
        return ListaPosts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ListaPosts.get(position).getId();
    }

    @Override
    public View getView(int position, View VistaPosts, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        VistaPosts = inflater.inflate(R.layout.items_posts,null);

        TextView id = VistaPosts.findViewById(R.id.tvId);
        TextView userid = VistaPosts.findViewById(R.id.tvUserId);
        TextView title = VistaPosts.findViewById(R.id.tvTitle);
        TextView body = VistaPosts.findViewById(R.id.tvBody);
        ImageView marcar = VistaPosts.findViewById(R.id.ivMarcar);

        id.setText(Integer.toString(ListaPosts.get(position).getId()));
        userid.setText(Integer.toString(ListaPosts.get(position).getUserId()));
        title.setText(ListaPosts.get(position).getTitle());
        body.setText(ListaPosts.get(position).getBody());
        marcar.setVisibility(View.INVISIBLE);

        return VistaPosts;
    }
}
