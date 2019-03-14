package com.example.dangtrungduc_1706020011;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class adapter_Mh extends ArrayAdapter<Mh_model> {
    private Context mcontext;
    private  int Layout;
    private List<Mh_model> modelList;
    public adapter_Mh( Context context, int resource, List<Mh_model> objects) {
        super(context, resource, objects);
        this.mcontext = context;
        this.Layout = resource;
        this.modelList = objects;
    }

    public  class  viewholder
    {
        TextView tv_TenMonHoc,tv_maMonHoc,tv_soTinChi;
        ImageView img;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewholder holder = null;
        if (holder == null)
        {
            convertView = LayoutInflater.from(mcontext).inflate(Layout,parent,false);
            holder = new viewholder();
            holder.tv_TenMonHoc =convertView.findViewById(R.id.tv_tenMonHoc);
            holder.tv_maMonHoc = convertView.findViewById(R.id.tv_MaMonHoc);
            holder.tv_soTinChi = convertView.findViewById(R.id.tv_SoTinChi);
            holder.img = convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        }
        else
        {
            holder = (viewholder) convertView.getTag();
        }
        final Mh_model model = modelList.get(position);
        holder.tv_TenMonHoc.setText(model.getNameMh());
        holder.tv_maMonHoc.setText(model.getIdMh());
        holder.tv_soTinChi.setText(model.getSoChi());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext,Detail_Activity.class);
                intent.putExtra("model",model);
                mcontext.startActivity(intent);
            }
        });

        return convertView;
    }
}
