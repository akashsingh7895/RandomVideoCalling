package com.avssolution.videocalling_app.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.avssolution.videocalling_app.Activity.GroupActivity;
import com.avssolution.videocalling_app.R;
import com.bumptech.glide.Glide;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.Viewolder> {

    int[] arrImgs ={R.drawable.img,R.drawable.img_1,R.drawable.img_2,R.drawable.img_4,R.drawable.img_5,R.drawable.img_6};
    String[] arrNames = {"India Video Chats..", "Pakistan Video Chats..", "USA Video Chats...", "Canada Video Chats..", "Japan Video Chats..", "China Video Chats.."};
    Context context;

    public ChatListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Viewolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        return new Viewolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewolder holder, int position) {
        holder.name.setText(arrNames[position]);
        Glide.with(context).load(Integer.valueOf(this.arrImgs[position])).into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, GroupActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrImgs.length;
    }

    public class Viewolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView name;
        public Viewolder(@NonNull View itemView) {
            super(itemView);

            this.img = (ImageView) itemView.findViewById(R.id.circularImageView);
            this.name = (TextView) itemView.findViewById(R.id.name);


        }
    }
}
