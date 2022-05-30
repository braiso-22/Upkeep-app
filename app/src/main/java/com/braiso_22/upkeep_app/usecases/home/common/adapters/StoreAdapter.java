package com.braiso_22.upkeep_app.usecases.home.common.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.model.vo.Store;

import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {

    private final List<Store> values;
    private OnStoreClickListener listener;
    private final Context context;
    private LayoutInflater inflater;

    // Constructor
    public StoreAdapter(Context context, List<Store> values, OnStoreClickListener listener) {
        this.context = context;
        this.values = values;
        this.listener = listener;
        this.inflater = LayoutInflater.from(context);
    }

    // OnCreateViewHolder
    @Override
    public StoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.store_item, parent, false);
        return new StoreViewHolder(view);
    }

    // OnBindViewHolder
    @Override
    public void onBindViewHolder(StoreViewHolder holder, int position) {
        holder.bindData(values.get(position));
    }

    // GetItemCount
    @Override
    public int getItemCount() {
        return values.size();
    }

    // Inner interface to handle the click event
    public interface OnStoreClickListener {
        void onStoreClick(Store store);
        void onStoreLongClick(Store store, View view);
    }

    // Inner class ViewHolder
    public class StoreViewHolder extends RecyclerView.ViewHolder {
        // TextViews for id , name, brand, model, serialNumber, observation, code
        TextView id, name, brand, model, serialNumber, observation, code, numStock,minStock;
        
        private Store store;
        
        public StoreViewHolder(View view) {
            super(view);
            id = view.findViewById(R.id.storeIdText);
            name = view.findViewById(R.id.storeNameText);
            brand = view.findViewById(R.id.storeBrandText);
            model = view.findViewById(R.id.storeModelText);
            serialNumber = view.findViewById(R.id.storeSerialNumberText);
            observation = view.findViewById(R.id.storeObservationsText);
            code = view.findViewById(R.id.storeCodeText);
            numStock = view.findViewById(R.id.storeNumStockText);
            minStock = view.findViewById(R.id.storeMinStockText);
        }
        
        // BindData with store
        public void bindData(Store store) {
            this.store = store;
            id.setText(String.valueOf(store.getId()));
            name.setText(store.getName());
            brand.setText(store.getBrand());
            model.setText(store.getModel());
            serialNumber.setText(store.getSerialNumber());
            observation.setText(store.getObservations());
            code.setText(store.getCode());
            numStock.setText(String.valueOf(store.getNumStock()));
            minStock.setText(String.valueOf(store.getMinStock()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onStoreClick(store);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onStoreLongClick(store, v);
                    return true;
                }
            });
        }
    }
}
