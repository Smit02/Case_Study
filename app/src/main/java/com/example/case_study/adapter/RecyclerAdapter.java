package com.example.case_study.adapter;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.case_study.R;
import com.example.case_study.model.User;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private Context mcontext;
    private List<User> mUsers;
    final private FragmentCommunication mCommunicator;
    public RecyclerAdapter(Context context, List<User> mUsers,FragmentCommunication communication) {
    this.mcontext=context;
    this.mUsers=mUsers;
    mCommunicator=communication;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textId;
        TextView textName;
        TextView textEmail;
        TextView textCity;
        TextView textZipcode;
        TextView textPhone;
        TextView textWebsite;
        TextView textCompany;
        LinearLayout linearLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout=itemView.findViewById(R.id.root_layout_id);
            textId=itemView.findViewById(R.id.text_view_id);
            textName=itemView.findViewById(R.id.text_name);
            textEmail=itemView.findViewById(R.id.text_email);
            textCity=itemView.findViewById(R.id.text_city);
            textZipcode=itemView.findViewById(R.id.text_zipcode);
            textPhone=itemView.findViewById(R.id.text_phone);
            textWebsite=itemView.findViewById(R.id.text_website);
            textCompany=itemView.findViewById(R.id.text_company);
            linearLayout.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            mCommunicator.delete(mUsers.get(getAdapterPosition()).getId());
        }
    }
    public interface FragmentCommunication{

        void delete(int id );


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.recycler_item,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.textId.setText(String.valueOf(mUsers.get(i).getId()));
        myViewHolder.textName.setText(mUsers.get(i).getName());
        myViewHolder.textEmail.setText(mUsers.get(i).getEmail());
        myViewHolder.textCity.setText(mUsers.get(i).getAddress().getCity());
        myViewHolder.textZipcode.setText(mUsers.get(i).getAddress().getZipcode());
        myViewHolder.textPhone.setText(mUsers.get(i).getPhone());
        myViewHolder.textWebsite.setText(mUsers.get(i).getWebsite());
        myViewHolder.textCompany.setText(mUsers.get(i).getCompany().getName());

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

}
