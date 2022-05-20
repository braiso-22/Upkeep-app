package com.braiso_22.upkeep_app.usecases.home.owner.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.model.vo.users.Manager;
import com.braiso_22.upkeep_app.model.vo.users.Operator;
import com.braiso_22.upkeep_app.model.vo.users.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    // List of Users
    private final List<User> values;
    // LayoutInflater
    private LayoutInflater inflater;
    // Context
    private final Context context;
    // OnUserClickListener
    final OnUserClickListener listener;

    public UserAdapter(List<User> values, Context context, OnUserClickListener listener) {
        this.values = values;
        this.context = context;
        this.listener = listener;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public UserViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserAdapter.UserViewHolder holder, int position) {
        holder.bindData(values.get(position));
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    // Inner Interface OnUserClickListener
    public interface OnUserClickListener {
        void onUserClick(User user);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        // TextViews
        private TextView userId;
        private TextView userLogin;
        private TextView userName;
        private TextView userEmail;
        private TextView userSurnames;
        private TextView userCode;
        private TextView userIdentification;
        private TextView userType;
        private TextView userService;
        private User user;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userId = itemView.findViewById(R.id.userIdText);
            userLogin = itemView.findViewById(R.id.userLoginText);
            userName = itemView.findViewById(R.id.userNameText);
            userEmail = itemView.findViewById(R.id.userEmailText);
            userSurnames = itemView.findViewById(R.id.userSurnamesText);
            userCode = itemView.findViewById(R.id.userCodeText);
            userIdentification = itemView.findViewById(R.id.userIdentificationText);
            userType = itemView.findViewById(R.id.userTypeText);
            userService = itemView.findViewById(R.id.userServiceText);
        }

        // bindData with onClickListener
        public void bindData(User user) {
            this.user = user;
            userId.setText(String.valueOf( user.getId()));
            userLogin.setText(user.getLogin());
            userName.setText(user.getName());
            userEmail.setText(user.getEmail());
            userSurnames.setText(user.getSurnames());
            userCode.setText(user.getCode());
            userIdentification.setText(user.getIdentification());
            userType.setText(user instanceof Manager ? "Manager" :
                    (user instanceof Operator ? "Operator" : ""));
            userService.setText(user instanceof Manager ? String.valueOf(((Manager) user).getService()) :
                    (user instanceof Operator ? String.valueOf(((Operator) user).getService()) : ""));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onUserClick(user);
                }
            });
        }
        @Override
        public String toString() {
            return user.toString();
        }
    }
}

