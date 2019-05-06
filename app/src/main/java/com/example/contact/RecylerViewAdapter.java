package com.example.contact;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.ViewHolder>  {



    private ArrayList<UserData> userDataArrayList = new ArrayList<>();
    Context context;
    ViewHolder myViewHolder;
    LinearLayout linearLayout;
    UserData myListData;

    public RecylerViewAdapter(Context context, ArrayList<UserData> userDataArrayList) {
        this.userDataArrayList = userDataArrayList;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.row_list,parent,false);

        myViewHolder = new ViewHolder(listItem);


        return myViewHolder;
    }

    @Override
    public void onBindViewHolder (final ViewHolder holder, int position) {
        myListData = userDataArrayList.get(position);
        holder.username.setText(myListData.getUsername());
        holder.phone_number.setText(myListData.getPhone_number());

    }


    @Override
    public int getItemCount() {
        return userDataArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView username;
        public TextView phone_number;

       // public ImageView del_img; //
       public Button del_img;


        public ViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            phone_number= itemView.findViewById(R.id.phone_number);
            linearLayout = itemView.findViewById(R.id.click_relative);
            del_img = itemView.findViewById(R.id.delete_contact);

            itemView.setOnClickListener(this);
//////Deleting data row 
            del_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseHelper databaseHelper = new DatabaseHelper(context);
                    databaseHelper.deleteContact(myListData);

                    // Refresh contact list- by calling the method loadCantactlist() of ContactsView class
                    ContactsView contactsView = (ContactsView)context;
                    contactsView.loadContactList();

                }

            });
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, ContactDetails.class);


            intent.putExtra("username", myListData.getUsername());
            intent.putExtra("phone_number", myListData.getPhone_number());
            context.startActivity(intent);
        }
    }

}
