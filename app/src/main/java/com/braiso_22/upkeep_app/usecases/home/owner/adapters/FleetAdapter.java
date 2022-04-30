package com.braiso_22.upkeep_app.usecases.home.owner.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.model.vo.Fleet;

import java.util.List;

public class FleetAdapter extends RecyclerView.Adapter<FleetAdapter.FleetViewHolder> {

    private final List<Fleet> values;
    private LayoutInflater inflater;
    private final Context context;

    public FleetAdapter(List<Fleet> values, Context context) {
        this.inflater= LayoutInflater.from(context);
        this.values = values;
        this.context = context;
    }

   @Override
   public int getItemCount() {
       return values.size();
   }

   @Override
   public FleetAdapter.FleetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = inflater.inflate(R.layout.fleet_item, parent, false);
    return new FleetAdapter.FleetViewHolder(view);
   }

   @Override
   public void onBindViewHolder(final FleetAdapter.FleetViewHolder holder, final int position) {
        holder.bindData(values.get(position));
   }



    public class FleetViewHolder extends RecyclerView.ViewHolder {
        public final TextView fleetId;
        public final TextView fleetName;
        public Fleet fleet;

        public FleetViewHolder(View view) {
            super(view);
            fleetId = view.findViewById(R.id.fleetIdText);
            fleetName = view.findViewById(R.id.fleetNameText);
        }

        public void bindData(Fleet fleet) {
            this.fleet = fleet;
            fleetId.setText(String.valueOf(fleet.getId()));
            fleetName.setText(fleet.getName());
        }

        @Override
        public String toString() {
            return super.toString() + " '" + fleetId.getText() + "'";
        }
    }
}

