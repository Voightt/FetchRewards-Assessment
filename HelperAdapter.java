package com.example.fetchrewards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HelperAdapter extends RecyclerView.Adapter<HelperAdapter.MyViewClass> {

    ArrayList<String> name;
    ArrayList<String> id;
    ArrayList<String> listId;
    Context context;

    public HelperAdapter(ArrayList<String> name, ArrayList<String> id, ArrayList<String> listId, Context context)
    {
        this.name = name;
        this.id = id;
        this.listId = listId;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        MyViewClass myViewClass=new MyViewClass(view);
        return myViewClass;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewClass holder, int position) {
        holder.name.setText(name.get(position));
        holder.id.setText(id.get(position));
        holder.listId.setText(listId.get(position));

        holder.itemView.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Item Clicked", Toast.LENGTH_LONG).show();
            }
        }));
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class MyViewClass extends RecyclerView.ViewHolder{

        TextView name;
        TextView id;
        TextView listId;



        public MyViewClass(@NonNull View itemView) {
            super(itemView);

            name=(TextView)itemView.findViewById(R.id.name);
            id=(TextView)itemView.findViewById(R.id.id);
            listId=(TextView)itemView.findViewById(R.id.listId);
        }
    }
}
