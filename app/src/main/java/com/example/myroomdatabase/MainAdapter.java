package com.example.myroomdatabase;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHoder> {
//initialize variable
    List<MainData> list;
    RoomBD roomBD;
    Activity contex;

    //create contructor


    public MainAdapter(List<MainData> list, Activity contex) {
        this.list = list;
        this.contex = contex;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //initalize view
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_item, parent, false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHoder holder, int position) {
        //initalize data
        MainData mainData= list.get(position);
        //initalize database
        final RoomBD room= RoomBD.getInstan(contex);
        //set text on textview
        holder.textView.setText(mainData.getText());
        //set on click edit
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initalize main data
                MainData d= list.get(holder.getAdapterPosition());
                //get id
                final int sid = d.getId();
                //get text
                String text= d.getText();
                //create dialog
                final Dialog dialog = new Dialog(contex);
                //setContenView
                dialog.setContentView(R.layout.dialog_update);
                //initalize with
                int witdth= WindowManager.LayoutParams.MATCH_PARENT;
                //intialize height
                int height= WindowManager.LayoutParams.WRAP_CONTENT;
                //set diaglog
                dialog.getWindow().setLayout(witdth,height);
                dialog.show();

                // initalize and assign variable
                final EditText editText= dialog.findViewById(R.id.editTextN);
                Button btnUpdate= dialog.findViewById(R.id.btnUpdate);

                //
                editText.setText(text);
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //dismiss dialog
                        dialog.dismiss();
                        // call update
                        String utext = editText.getText().toString().trim();
                        room.mainDao().update(sid,utext);
                        list.clear();
                        list.addAll(room.mainDao().getAllData());
                        notifyDataSetChanged();
                    }
                });
            }
        });


        holder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainData d= list.get(holder.getAdapterPosition());
                room.mainDao().delete(d);
                list.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemChanged(holder.getAdapterPosition());
                Toast.makeText(contex, "delete"+holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        //initalize variable
        TextView textView;
        ImageView btnEdit, btnDel;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            //Assign variable
            textView= itemView.findViewById(R.id.textView);
            btnEdit= itemView.findViewById(R.id.imgEdit);
            btnDel= itemView.findViewById(R.id.imgDelete);

        }
    }

}
