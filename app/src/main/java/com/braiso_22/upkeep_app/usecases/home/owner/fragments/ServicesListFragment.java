package com.braiso_22.upkeep_app.usecases.home.owner.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.model.vo.Boat;
import com.braiso_22.upkeep_app.model.vo.Service;
import com.braiso_22.upkeep_app.model.vo.users.Owner;
import com.braiso_22.upkeep_app.usecases.creation.ServiceCreationActivity;
import com.braiso_22.upkeep_app.usecases.home.OwnerHomeActivity;
import com.braiso_22.upkeep_app.usecases.home.common.fragments.ComponentListFragment;
import com.braiso_22.upkeep_app.usecases.home.owner.adapters.ServiceAdapter;
import com.braiso_22.upkeep_app.utils.CRUDToolbarMenu;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

public class ServicesListFragment extends Fragment {
    ViewModel vm;
    Boat boat;

    public ServicesListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_services_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vm = new ViewModel(this.getActivity().getApplication());
        RecyclerView recycler = this.getView().findViewById(R.id.servicesRecyclerView);
        inflateRecycler(recycler);
        Toolbar toolbar = this.getView().findViewById(R.id.serviceToolbar);
        CRUDToolbarMenu.menuOnClick(toolbar, new CRUDToolbarMenu.DeleteMethod() {
            @Override
            public void delete() {
                vm.deleteServiceByBoat(boat);
            }
        }, new CRUDToolbarMenu.CreateMethod() {
            @Override
            public void create() {
                Intent intent = new Intent(getActivity(), ServiceCreationActivity.class);
                startActivity(intent);
            }
        });
    }

    private void inflateRecycler(RecyclerView recycler) {
        if (boat == null) {
            vm.getAllServices().observe(this.getActivity(), services -> {
                if (getActivity() != null)
                    recycler.setAdapter(new ServiceAdapter(services, this.getActivity(), new ServiceAdapter.OnServiceClickListener() {
                        @Override
                        public void onServiceClick(Service service) {
                            goToComponentList(service);
                        }

                        @Override
                        public void onServiceLongClick(Service service, View view) {
                            showPopupMenu(service, view);
                        }
                    }));

            });
        } else {
            vm.getServiceByBoat(boat.getId()).observe(this.getActivity(), services -> {
                if (getActivity() != null)
                    recycler.setAdapter(new ServiceAdapter(services, this.getActivity(), new ServiceAdapter.OnServiceClickListener() {
                        @Override
                        public void onServiceClick(Service service) {
                            goToComponentList(service);
                        }

                        @Override
                        public void onServiceLongClick(Service service, View view) {
                            showPopupMenu(service, view);
                        }
                    }));
            });
        }

        recycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }


    private void goToComponentList(Service service) {
        ComponentListFragment fragment = new ComponentListFragment();
        fragment.setService(service);
        this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragment).addToBackStack(null).commit();
    }

    private void showPopupMenu(Service service, View view) {
        PopupMenu popup = new PopupMenu(this.getActivity(), view);
        popup.getMenuInflater().inflate(R.menu.crud_options2_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.editOption:
                    goToServiceCreation(service);
                    break;
                case R.id.deleteOneOption:
                    vm.deleteService(service);
                    break;
            }
            return false;
        });
        popup.show();
    }

    private void goToServiceCreation(Service service) {
        Intent intent = new Intent(this.getActivity(), ServiceCreationActivity.class);
        intent.putExtra("service", service);
        startActivity(intent);
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }


}