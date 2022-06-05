package com.braiso_22.upkeep_app.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.braiso_22.upkeep_app.model.dao.*;
import com.braiso_22.upkeep_app.model.db.UpkeepsRoomDatabase;
import com.braiso_22.upkeep_app.model.vo.*;
import com.braiso_22.upkeep_app.model.vo.users.*;

import java.util.List;

public class UpkeepsRepository {

    private OwnerDao ownerDao;
    private FleetDao fleetDao;
    private BoatDao boatDao;
    private ServiceDao serviceDao;
    private ManagerDao managerDao;
    private ComponentDao componentDao;
    private UpkeepDao upkeepDao;
    private TaskDao taskDao;
    private OperatorDao operatorDao;
    private StoreDao storeDao;

    private LiveData<List<Owner>> allOwners;
    private LiveData<List<Fleet>> allFleets;
    private LiveData<List<Boat>> allBoats;
    private LiveData<List<Service>> allServices;
    private LiveData<List<Manager>> allManagers;
    private LiveData<List<Component>> allComponents;
    private LiveData<List<Upkeep>> allUpkeeps;
    private LiveData<List<Task>> allTasks;
    private LiveData<List<Operator>> allOperators;
    private LiveData<List<Store>> allStores;

    public UpkeepsRepository(Application application) {
        UpkeepsRoomDatabase db = UpkeepsRoomDatabase.getDatabase(application);
        ownerDao = db.ownerDao();
        fleetDao = db.fleetDao();
        boatDao = db.boatDao();
        serviceDao = db.serviceDao();
        managerDao = db.managerDao();
        componentDao = db.componentDao();
        upkeepDao = db.upkeepDao();
        taskDao = db.taskDao();
        operatorDao = db.operatorDao();
        storeDao = db.storeDao();

        allOwners = ownerDao.getAll();
        allFleets = fleetDao.getAll();
        allBoats = boatDao.getAll();
        allServices = serviceDao.getAll();
        allManagers = managerDao.getAll();
        allComponents = componentDao.getAll();
        allUpkeeps = upkeepDao.getAll();
        allTasks = taskDao.getAll();
        allOperators = operatorDao.getAll();
        allStores = storeDao.getAll();
    }

    public LiveData<List<Owner>> getAllOwners() {
        return allOwners;
    }

    public LiveData<Owner> getOwnerByLogin(String login) {
        return ownerDao.getByLogin(login);
    }

    public void insert(Owner owner) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            ownerDao.insert(owner);
        });
    }

    public void deleteAllOwners() {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            ownerDao.deleteAll();
        });
    }

    public void update(Owner owner) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            ownerDao.update(owner);
        });
    }

    public LiveData<List<Fleet>> getAllFleets() {
        return allFleets;
    }

    public LiveData<List<Fleet>> getFleetByOwner(int id) {
        return fleetDao.getByOwner(id);
    }

    public void insert(Fleet fleet) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            fleetDao.insert(fleet);
        });
    }

    public void deleteAllFleets() {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            fleetDao.deleteAll();
        });
    }
    public void deleteFleet(int id) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            fleetDao.deleteById(id);
        });
    }

    public void update(Fleet fleet) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            fleetDao.update(fleet);
        });
    }

    public LiveData<List<Boat>> getAllBoats() {
        return allBoats;
    }

    public LiveData<List<Boat>> getBoatByFleet(int id) {
        return boatDao.getByFleet(id);
    }

    public void insert(Boat boat) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            boatDao.insert(boat);
        });
    }

    public void deleteAllBoats() {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            boatDao.deleteAll();
        });
    }
    public void deleteBoat(int id) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            boatDao.deleteById(id);
        });
    }

    public void update(Boat boat) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            boatDao.update(boat);
        });
    }

    public LiveData<List<Service>> getAllServices() {
        return allServices;
    }

    public LiveData<List<Service>> getServiceByBoat(int id) {
        return serviceDao.getByBoat(id);
    }

    public void insert(Service service) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            serviceDao.insert(service);
        });
    }

    public void deleteAllServices() {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            serviceDao.deleteAll();
        });
    }
    public void deleteService(int id) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            serviceDao.deleteById(id);
        });
    }

    public void update(Service service) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            serviceDao.update(service);
        });
    }

    public LiveData<List<Manager>> getAllManagers() {
        return allManagers;
    }

    public LiveData<Manager> getManagerByLogin(String login) {
        return managerDao.getByLogin(login);
    }

    public void insert(Manager manager) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            managerDao.insert(manager);
        });
    }

    public void deleteAllManagers() {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            managerDao.deleteAll();
        });
    }
    public void deleteManager(int id) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            managerDao.deleteById(id);
        });
    }

    public void update(Manager manager) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            managerDao.update(manager);
        });
    }

    public LiveData<List<Component>> getAllComponents() {
        return allComponents;
    }

    public LiveData<List<Component>> getComponentByService(int id) {
        return componentDao.getByService(id);
    }

    public void insert(Component component) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            componentDao.insert(component);
        });
    }

    public void deleteAllComponents() {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            componentDao.deleteAll();
        });
    }
    public void deleteComponent(int id) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            componentDao.deleteById(id);
        });
    }

    public void update(Component component) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            componentDao.update(component);
        });
    }

    public LiveData<List<Upkeep>> getAllUpkeeps() {
        return allUpkeeps;
    }

    public LiveData<List<Upkeep>> getUpkeepByComponent(int id) {
        return upkeepDao.getByComponent(id);
    }

    public void insert(Upkeep upkeep) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            upkeepDao.insert(upkeep);
        });
    }

    public void deleteAllUpkeeps() {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            upkeepDao.deleteAll();
        });
    }
    public void deleteUpkeep(int id) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            upkeepDao.deleteById(id);
        });
    }

    public void update(Upkeep upkeep) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            upkeepDao.update(upkeep);
        });
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public LiveData<List<Task>> getTaskByUpkeep(int id) {
        return taskDao.getByUpkeep(id);
    }

    public LiveData<List<Task>> getTaskByOperator(int id) {
        return taskDao.getByOperator(id);
    }

    public void insert(Task task) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            taskDao.insert(task);
        });
    }

    public void deleteAllTasks() {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            taskDao.deleteAll();
        });
    }
    public void deleteTask(int id) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            taskDao.deleteById(id);
        });
    }

    public void update(Task task) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            taskDao.update(task);
        });
    }

    public LiveData<List<Operator>> getAllOperators() {
        return allOperators;
    }

    public LiveData<Operator> getOperatorByLogin(String login) {
        return operatorDao.getByLogin(login);
    }

    public void insert(Operator operator) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            operatorDao.insert(operator);
        });
    }

    public void deleteAllOperators() {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            operatorDao.deleteAll();
        });
    }
    public void deleteOperator(int id) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            operatorDao.deleteById(id);
        });
    }

    public void update(Operator operator) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            operatorDao.update(operator);
        });
    }

    public LiveData<List<Store>> getAllStores() {
        return allStores;
    }

    public LiveData<List<Store>> getStoreByTask(int id) {
        return storeDao.getByTask(id);
    }

    public void insert(Store store) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            storeDao.insert(store);
        });
    }

    public void deleteAllStores() {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            storeDao.deleteAll();
        });
    }
    public void deleteStore(int id) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            storeDao.deleteById(id);
        });
    }

    public void update(Store store) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            storeDao.update(store);
        });
    }
}
