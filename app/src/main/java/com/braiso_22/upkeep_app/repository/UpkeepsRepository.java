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

    public LiveData<List<Owner>> getAllOwners(){return allOwners;}

    public LiveData<List<Owner>> getByLogin(String login){return ownerDao.getByLogin(login);}

    public void insert(Owner owner){
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() ->{
            ownerDao.insert(owner);
        });
    }

    public void deleteAllOwners(){
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() ->{
            ownerDao.deleteAll();
        });
    }

    public void update(Owner owner){
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() ->{
            ownerDao.update(owner);
        });
    }

    public LiveData<List<Fleet>> getAllFleets() {
        return allFleets;
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

    public void update(Fleet fleet) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            fleetDao.update(fleet);
        });
    }

    public LiveData<List<Boat>> getAllBoats() {
        return allBoats;
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

    public void update(Boat boat) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            boatDao.update(boat);
        });
    }

    public LiveData<List<Service>> getAllServices() {
        return allServices;
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

    public void update(Service service) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            serviceDao.update(service);
        });
    }

    public LiveData<List<Manager>> getAllManagers(){return allManagers;}

    public void insert(Manager manager){
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() ->{
            managerDao.insert(manager);
        });
    }

    public void deleteAllManagers(){
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() ->{
            managerDao.deleteAll();
        });
    }

    public void update(Manager manager){
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() ->{
            managerDao.update(manager);
        });
    }

    public LiveData<List<Component>> getAllComponents() {
        return allComponents;
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

    public void update(Component component) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            componentDao.update(component);
        });
    }

    public LiveData<List<Upkeep>> getAllUpkeeps() {
        return allUpkeeps;
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

    public void update(Upkeep upkeep) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            upkeepDao.update(upkeep);
        });
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
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

    public void update(Task task) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            taskDao.update(task);
        });
    }

    public LiveData<List<Operator>> getAllOperators() {
        return allOperators;
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

    public void update(Operator operator) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            operatorDao.update(operator);
        });
    }

    public LiveData<List<Store>> getAllStores() {
        return allStores;
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

    public void update(Store store) {
        UpkeepsRoomDatabase.databaseWriteExecutor.execute(() -> {
            storeDao.update(store);
        });
    }
}
