package com.example.finalexam;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private  int Resource;
    public List<Model> modelList;
    private String local;

    public MyAdapter(Context mcontext, int resource, List<Model> ModelList) {
        this.context = mcontext;
        Resource = resource;
        this.modelList = ModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

            final Model model = modelList.get(i);
            viewHolder.credit.setText(model.getProduct_name());
            viewHolder.description.setText(model.getPrice()+"");
            viewHolder.btn_drescripton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,derciptionActivity.class);
                    intent.putExtra("local",model.getKey());
                    intent.putExtra("price",model.getPrice()+"");
                    intent.putExtra("description",model.getDescription());
                    intent.putExtra("id",model.getId()+"");
                    intent.putExtra("producer",model.getProducer());
                    intent.putExtra("product_name",model.getProduct_name());
                    context.startActivity(intent);
                }
            });
            viewHolder.btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    local = model.getKey();
                    MainActivity.myRef.child(local).removeValue();
                    MainActivity.adapter.notifyDataSetChanged();
                    MainActivity.modelList.clear();
                    Toast.makeText(context, "Delete Success", Toast.LENGTH_SHORT).show();

                }
            });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView credit,description;
        private ImageView btn_delete;
        private LinearLayout btn_drescripton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            btn_drescripton  = itemView.findViewById(R.id.btn_description);
            credit = itemView.findViewById(R.id.item_edt_credits);
            description = itemView.findViewById(R.id.item_edt_description);

        }
    }
}
