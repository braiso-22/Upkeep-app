package com.braiso_22.upkeep_app.usecases.home.owner.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.model.vo.Fleet;
import com.braiso_22.upkeep_app.model.vo.Service;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    // List of Services
    private final List<Service> values;
    // LayoutInflater
    private LayoutInflater inflater;
    // Context
    private final Context context;
    // OnServiceClickListener
    final OnServiceClickListener listener;

    // Constructor with values, context and listener
    public ServiceAdapter(List<Service> values, Context context, OnServiceClickListener listener) {
        inflater = LayoutInflater.from(context);
        this.values = values;
        this.context = context;
        this.listener = listener;
    }

    // GetItemCount
    @Override
    public int getItemCount() {
        return values.size();
    }

    // OnCreateViewHolder with inflater and parent
    @Override
    public ServiceAdapter.ServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.service_item, parent, false);
        return new ServiceAdapter.ServiceViewHolder(view);
    }

    // OnBindViewHolder with holder and position
    @Override
    public void onBindViewHolder(ServiceAdapter.ServiceViewHolder holder, int position) {
       holder.bindData(values.get(position));
    }

    // Inner Interface OnServiceClickListener
    public interface OnServiceClickListener {
        void onServiceClick(Service service);
    }

    // Inner class ServiceViewHolder
    public class ServiceViewHolder extends RecyclerView.ViewHolder {
        // TextViews
        public TextView serviceId;
        public TextView serviceName;
        public TextView serviceCode;
        // Service
        public Service service;

        // ServiceViewHolder constructor with View
        public ServiceViewHolder(View view) {
            super(view);
            serviceId = view.findViewById(R.id.serviceIdText);
            serviceName = view.findViewById(R.id.serviceNameText);
            serviceCode = view.findViewById(R.id.serviceCodeText);
        }

        // bindData with onClickListener
        public void bindData(Service service) {
            this.service = service;
            serviceId.setText(String.valueOf(service.getId()));
            serviceName.setText(service.getName());
            serviceCode.setText(service.getCode());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onServiceClick(service);
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + serviceName.getText() + "'" + " '" + serviceCode.getText() + "'";
        }
    }

}
