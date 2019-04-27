package com.example.thigiuaky;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
   private Context context;
   private List<model> modelList;
   private int resource;
   private List<dienthoaiModel> dienthoaiModelList;

    public MyAdapter(Context context, List<model> modelList, int resource) {
        this.context = context;
        this.modelList = modelList;
        this.resource = resource;
    }

    public MyAdapter(Context context, int resource, List<dienthoaiModel> dienthoaiModelList) {
        this.context = context;
        this.resource = resource;
        this.dienthoaiModelList = dienthoaiModelList;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(resource,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        model models = modelList.get(i);

        viewHolder.edt_mssv.setText(models.getMssv());
        viewHolder.edt_tensv.setText(models.getHoten());
        viewHolder.edt_tuoi.setText(models.getTuoi()+"");
        viewHolder.edt_lop.setText(models.getLop());
        viewHolder.edt_email.setText(models.getEmail());


    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private   EditText edt_mssv,edt_tensv ,edt_tuoi,edt_lop,edt_email;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            this.edt_mssv = itemView.findViewById(R.id.edt_Mssv);
            this.edt_tensv = itemView.findViewById(R.id.edt_hoten);
            this.edt_tuoi = itemView.findViewById(R.id.edt_tuoi);
            this.edt_lop = itemView.findViewById(R.id.edt_lop);
            this.edt_email = itemView.findViewById(R.id.edt_email);

        }
    }
}
