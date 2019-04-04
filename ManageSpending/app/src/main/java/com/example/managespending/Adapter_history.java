package com.example.managespending;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter_history extends ArrayAdapter<Model_if> {
    private Context mcontext;
    private  int layout;
    private  List<Model_if> model_ifs;
    public Adapter_history( Context context, int resource,  List<Model_if> objects) {
        super(context, resource, objects);
        this.mcontext = context;
        this.layout = resource;
        this.model_ifs = objects;
    }

    public class viewHolder
    {
        ImageView btn_update;
        ImageButton btn_delete;
        TextView tv_note,tv_cost,tv_product,tv_currency,tv_date,tv_location;
    }
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        viewHolder holder = null;
        if(holder == null) {
            convertView = LayoutInflater.from(mcontext).inflate(layout, parent, false);
            holder = new viewHolder();
            holder.btn_delete = convertView.findViewById(R.id.btn_delete);
            holder.btn_update = convertView.findViewById(R.id.btn_update);
            holder.tv_location = convertView.findViewById(R.id.tv_location);
            holder.tv_date = convertView.findViewById(R.id.tv_date);
            holder.tv_product = convertView.findViewById(R.id.tv_product);
            holder.tv_currency = convertView.findViewById(R.id.tv_currency);
            holder.tv_cost = convertView.findViewById(R.id.tv_price);
            holder.tv_note = convertView.findViewById(R.id.tv_note);
            convertView.setTag(holder);
        }
        else
            {
                holder  = (viewHolder) convertView.getTag();
            }
        final  Model_if model = model_ifs.get(position);
        holder.tv_cost.setText(model.getCost());
        holder.tv_note.setText(model.getNote());
        holder.tv_product.setText(model.getProduct());
        holder.tv_date.setText(model.getTime());
        holder.tv_location.setText(model.getLocal());
        holder.tv_currency.setText(model.getCurrency());
        return convertView;
    }
}
