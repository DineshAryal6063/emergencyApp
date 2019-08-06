package tgn.com.emergencyapp.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tgn.com.emergencyapp.R;
import tgn.com.emergencyapp.activity.AmbulanceSewa;
import tgn.com.emergencyapp.activity.BloodReq;
import tgn.com.emergencyapp.activity.CommunityAlert;
import tgn.com.emergencyapp.activity.GirlSafety;


public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> menuList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_option;
        public ImageView img_options;

        public MyViewHolder(View view) {
            super(view);
            tv_option = view.findViewById(R.id.tv_options);

            img_options = view.findViewById(R.id.img_options);
/*
            ivInfo = view.findViewById(R.id.ivInfo);
*/
        }
    }

    public MenuAdapter(Context mContext, List<String> menuList) {
        this.mContext = mContext;
        this.menuList = menuList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_dashboard, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        String menuName = menuList.get(position);
        holder.tv_option.setText(menuName);
        switch (menuName) {
            case "Hospital":
                holder.img_options.setImageResource(R.drawable.ic_hospital);
                break;
            case "BLOOD REQ":
                holder.img_options.setImageResource(R.drawable.ic_blood_drop);

                break;
            case "GIRLS SAFE":
                holder.img_options.setImageResource(R.drawable.ic_protection);
                break;
            case "COMMUNITY SIREN":
                holder.img_options.setImageResource(R.drawable.ic_megaphone);
                break;
            case "AMBULANCE":
                holder.img_options.setImageResource(R.drawable.ic_ambulance);
                break;
            case "ICE BOT":
                holder.img_options.setImageResource(R.drawable.ic_chat);
                break;
            default:
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(holder.tv_option.getText().toString());
            }
        });
    }

    private void launchActivity(String options) {
        Intent intent;
        switch (options) {
            case "Hospital":

                break;
            case "BLOOD REQ":
                intent = new Intent(mContext, BloodReq.class);
                mContext.startActivity(intent);
                break;
            case "GIRLS SAFE":
                intent = new Intent(mContext, GirlSafety.class);
                mContext.startActivity(intent);
                break;
            case "COMMUNITY SIREN":
                intent = new Intent(mContext, CommunityAlert.class);
                mContext.startActivity(intent);
                break;
            case "AMBULANCE":
                intent = new Intent(mContext, AmbulanceSewa.class);
                mContext.startActivity(intent);
                break;
            case "ICE BOT":
                intent = new Intent(mContext, AmbulanceSewa.class);
                mContext.startActivity(intent);
                break;
            default:
        }

    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

}
