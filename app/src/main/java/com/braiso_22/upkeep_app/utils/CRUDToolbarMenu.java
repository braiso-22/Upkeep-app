package com.braiso_22.upkeep_app.utils;

import androidx.appcompat.widget.Toolbar;

import com.braiso_22.upkeep_app.R;


public class CRUDToolbarMenu {
    public interface DeleteMethod {
        void delete();
    }
    public interface CreateMethod {
        void create();
    }

    /**
     * Sets the menu's toolbar onClick
     * @param toolbar
     */
    public static void menuOnClick(Toolbar toolbar, DeleteMethod deleteMethod,CreateMethod createMethod) {
        toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.deleteAllOption:
                    deleteMethod.delete();
                    break;
                case R.id.createOption:
                    createMethod.create();
                    break;
            }
            return true;
        });
    }
}
