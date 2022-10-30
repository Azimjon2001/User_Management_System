package com.electsofte.parser.users.get_delete_update;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.electsofte.parser.R;

import java.util.ArrayList;

/**
 * @Author: Azimjon Hakimov
 * @CreateDate: 17.10.2022 5:07
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserAdapterVH> {
    private ArrayList<Data> userResponseList;
    //private List<UserResponseGET> userResponseList;
    private Context context;
    private ClickedItem clickedItem;
    public UserAdapter(ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }
    public void setData(ArrayList<Data> userResponseList) {
        this.userResponseList = userResponseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new UserAdapter.UserAdapterVH(LayoutInflater.from(context).inflate(R.layout.row_users,parent,false));
    }
    @SuppressLint("SetTextI18n")
        @Override
    public void onBindViewHolder(@NonNull UserAdapterVH holder, int position) {
        Data userResponse = userResponseList.get(position);
        String username = userResponse.getFirstName() + " " + userResponse.getLastName();
        String userFirstnamePrefix=userResponse.getFirstName();
        String userLastnamePrefix=userResponse.getLastName();
        holder.prefix.setText(userFirstnamePrefix.substring(0,1)+userLastnamePrefix.substring(0,1));
        holder.username.setText(username);
        holder.username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedItem.ClickedUser(userResponse, position);
            }
        });

        holder.imageMore.setOnClickListener(new View.OnClickListener() {
         @Override
            public void onClick(View view) {
                clickedItem.ClickedUser(userResponse, position);
            }
        });

        holder.prefix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedItem.ClickedUser(userResponse, position);
            }
        });
    }
    public interface ClickedItem{
        public void ClickedUser(Data userResponseGET, int position);
    }
    @Override
    public int getItemCount() {
        return userResponseList.size();
    }

    public class UserAdapterVH extends RecyclerView.ViewHolder {
        TextView username;
        TextView prefix;
        ImageView imageMore;
        RecyclerView recyclerView;
        public UserAdapterVH(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            recyclerView = itemView.findViewById(R.id.recyclerview);
            prefix = itemView.findViewById(R.id.prefix);
            imageMore = itemView.findViewById(R.id.imageMore);
        }
    }
}
