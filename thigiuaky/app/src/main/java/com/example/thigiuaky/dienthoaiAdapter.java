package com.example.thigiuaky;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class dienthoaiAdapter extends RecyclerView.Adapter<dienthoaiAdapter.ViewHolder> {
    private Context context;
    private int resource;
    private List<dienthoaiModel> dienthoaiModelList;

    public dienthoaiAdapter(Context context, int resource, List<dienthoaiModel> dienthoaiModelList) {
        this.context = context;
        this.resource = resource;
        this.dienthoaiModelList = dienthoaiModelList;
    }

    @NonNull
    @Override
    public dienthoaiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(resource,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull dienthoaiAdapter.ViewHolder viewHolder, int i) {
        final dienthoaiModel dienthoai = dienthoaiModelList.get(i);
        viewHolder.tv_product_name.setText(dienthoai.getProduct_name());
        viewHolder.tv_price.setText(dienthoai.getPrice()+"");

        viewHolder.btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("Id",dienthoai.getId()+"");
                intent.putExtra("productname",dienthoai.getProduct_name());
                intent.putExtra("price",dienthoai.getPrice()+"");
                intent.putExtra("Des",dienthoai.getDescription());
                intent.putExtra("producer",dienthoai.getProducer());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dienthoaiModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_product_name,tv_price,tv_des,tv_producer;
        private Button btn_dres;
        LinearLayout  btn_detail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.btn_detail = itemView.findViewById(R.id.liner_click);

            this.tv_product_name = itemView.findViewById(R.id.tv_product_name);
            this.tv_price = itemView.findViewById(R.id.tv_price);

        }
    }
}
