package com.braiso_22.upkeep_app.usecases.home.common.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.model.vo.Component;
import com.braiso_22.upkeep_app.model.vo.Service;
import com.braiso_22.upkeep_app.model.vo.users.Manager;
import com.braiso_22.upkeep_app.model.vo.users.Operator;
import com.braiso_22.upkeep_app.model.vo.users.User;
import com.braiso_22.upkeep_app.usecases.creation.ComponentCreationActivity;
import com.braiso_22.upkeep_app.usecases.home.common.adapters.ComponentAdapter;
import com.braiso_22.upkeep_app.utils.CRUDToolbarMenu;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

public class ComponentListFragment extends Fragment {
    ViewModel vm;
    Service service;
    User user;

    // Empty Constructor
    public ComponentListFragment() {
    }

    // OnCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_component_list, container, false);
    }

    // OnViewCreated with ViewModel Observer for adapter
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vm = new ViewModel(this.getActivity().getApplication());
        RecyclerView recycler = this.getView().findViewById(R.id.componentRecyclerView);
        inflateRecycler(recycler);
        Toolbar toolbar = this.getView().findViewById(R.id.componentToolbar);
        CRUDToolbarMenu.menuOnClick(toolbar, new CRUDToolbarMenu.DeleteMethod() {
            @Override
            public void delete() {
                vm.deleteAllComponents();
            }
        }, new CRUDToolbarMenu.CreateMethod() {
            @Override
            public void create() {
                Intent intent = new Intent(getActivity(), ComponentCreationActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Get components from database with live data and set it to the recycler view
     *
     * @param recycler
     */
    private void inflateRecycler(RecyclerView recycler) {
        if (service == null && user == null) {
            vm.getAllComponents().observe(this.getActivity(), components -> {
                if (getActivity() != null)
                    recycler.setAdapter(new ComponentAdapter(components, this.getActivity(), new ComponentAdapter.OnComponentClickListener() {
                        @Override
                        public void onComponentClick(Component component) {
                            goToUpkeepList(component);
                        }

                        @Override
                        public void onComponentLongClick(Component component, View view) {
                            showPopupMenu(component, view);
                        }
                    }));
            });
        } else if (service != null) {
            vm.getComponentByService(service.getId()).observe(this.getActivity(), components -> {
                if (getActivity() != null)
                    recycler.setAdapter(new ComponentAdapter(components, this.getActivity(), new ComponentAdapter.OnComponentClickListener() {
                        @Override
                        public void onComponentClick(Component component) {
                            goToUpkeepList(component);
                        }

                        @Override
                        public void onComponentLongClick(Component component, View view) {
                            showPopupMenu(component, view);
                        }
                    }));
            });
        } else {
            int serviceId = 0;
            if (user instanceof Manager) serviceId = ((Manager) user).getService();
            else if (user instanceof Operator) serviceId = ((Operator) user).getService();
            vm.getComponentByService(serviceId).observe(this.getActivity(), components -> {
                if (getActivity() != null)
                    recycler.setAdapter(new ComponentAdapter(components, this.getActivity(), new ComponentAdapter.OnComponentClickListener() {
                        @Override
                        public void onComponentClick(Component component) {
                            goToUpkeepList(component);
                        }

                        @Override
                        public void onComponentLongClick(Component component, View view) {
                            showPopupMenu(component, view);
                        }
                    }));
            });
        }

        recycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }

    private void goToUpkeepList(Component component) {
        UpkeepListFragment upkeepListFragment = new UpkeepListFragment();
        upkeepListFragment.setComponent(component);
        this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, upkeepListFragment).addToBackStack(null).commit();
    }

    private void showPopupMenu(Component component, View view) {
        PopupMenu popup = new PopupMenu(this.getActivity(), view);
        popup.getMenuInflater().inflate(R.menu.crud_options2_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.deleteOneOption:
                            vm.deleteComponent(component);
                            return true;
                        case R.id.editOption:
                            goToComponentCreation(component);
                            return true;
                        default:
                            return false;
                    }
                });
        popup.show();
    }

    private void goToComponentCreation(Component component) {
        Intent intent = new Intent(this.getActivity(), ComponentCreationActivity.class);
        intent.putExtra("component", component);
        startActivity(intent);
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void setUser(User user) {
        this.user = user;
    }

}