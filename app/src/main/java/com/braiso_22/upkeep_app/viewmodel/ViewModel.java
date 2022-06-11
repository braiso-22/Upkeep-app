package com.braiso_22.upkeep_app.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.braiso_22.upkeep_app.model.vo.*;
import com.braiso_22.upkeep_app.model.vo.users.*;
import com.braiso_22.upkeep_app.repository.UpkeepsRepository;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    private UpkeepsRepository repository;
    private final LiveData<List<Owner>> allOwners;
    private final LiveData<List<Fleet>> allFleets;
    private final LiveData<List<Boat>> allBoats;
    private final LiveData<List<Service>> allServices;
    private final LiveData<List<Manager>> allManagers;
    private final LiveData<List<Component>> allComponents;
    private final LiveData<List<Upkeep>> allUpkeeps;
    private final LiveData<List<Task>> allTasks;
    private final LiveData<List<Operator>> allOperators;
    private final LiveData<List<Store>> allStores;

    public ViewModel(Application application) {
        super(application);
        repository = new UpkeepsRepository(application);

        allOwners = repository.getAllOwners();
        allFleets = repository.getAllFleets();
        allBoats = repository.getAllBoats();
        allServices = repository.getAllServices();
        allManagers = repository.getAllManagers();
        allComponents = repository.getAllComponents();
        allUpkeeps = repository.getAllUpkeeps();
        allTasks = repository.getAllTasks();
        allOperators = repository.getAllOperators();
        allStores = repository.getAllStores();
    }

    public LiveData<List<Owner>> getAllOwners() {
        return allOwners;
    }

    public LiveData<Owner> getOwnerByLogin(String login) {
        return repository.getOwnerByLogin(login);
    }

    public void insert(Owner owner) {
        repository.insert(owner);
    }

    public void deleteAllOwners() {
        repository.deleteAllOwners();
    }

    public void update(Owner owner) {
        repository.update(owner);
    }

    public LiveData<List<Fleet>> getAllFleets() {
        return allFleets;
    }

    public LiveData<List<Fleet>> getFleetsByOwner(int id) {
        return repository.getFleetByOwner(id);
    }

    public void insert(Fleet fleet) {
        repository.insert(fleet);
    }

    public void deleteAllFleets() {
        repository.deleteAllFleets();
    }

    public void deleteFleet(Fleet fleet) {
        repository.deleteFleet(fleet.getId());
    }

    public void deleteFleetByOwner(Owner owner) {
        repository.deleteFleetByOwner(owner.getId());
    }

    public void update(Fleet fleet) {
        repository.update(fleet);
    }

    public LiveData<List<Boat>> getAllBoats() {
        return allBoats;
    }

    public LiveData<List<Boat>> getBoatByFleet(int fleet) {
        return repository.getBoatByFleet(fleet);
    }

    public void insert(Boat boat) {
        repository.insert(boat);
    }

    public void deleteAllBoats() {
        repository.deleteAllBoats();
    }

    public void deleteBoat(Boat boat) {
        repository.deleteBoat(boat.getId());
    }

    public void deleteBoatByFleet(Fleet fleet) {
        repository.deleteBoatByFleet(fleet.getId());
    }

    public void update(Boat boat) {
        repository.update(boat);
    }

    public LiveData<List<Service>> getAllServices() {
        return allServices;
    }

    public LiveData<List<Service>> getServiceByBoat(int boat) {
        return repository.getServiceByBoat(boat);
    }

    public void insert(Service service) {
        repository.insert(service);
    }

    public void deleteAllServices() {
        repository.deleteAllServices();
    }

    public void deleteService(Service service) {
        repository.deleteService(service.getId());
    }

    public void deleteServiceByBoat(Boat boat) {
        repository.deleteServiceByBoat(boat.getId());
    }

    public void update(Service service) {
        repository.update(service);
    }

    public LiveData<List<Manager>> getAllManagers() {
        return allManagers;
    }

    public LiveData<Manager> getManagerByLogin(String login) {
        return repository.getManagerByLogin(login);
    }

    public LiveData<List<Manager>> getManagerByOwner(int owner) {
        return repository.getManagerByOwner(owner);
    }

    public void insert(Manager manager) {
        repository.insert(manager);
    }

    public void deleteAllManagers() {
        repository.deleteAllManagers();
    }

    public void deleteManager(Manager manager) {
        repository.deleteManager(manager.getId());
    }

    public void update(Manager manager) {
        repository.update(manager);
    }

    public LiveData<List<Component>> getAllComponents() {
        return allComponents;
    }

    public LiveData<List<Component>> getComponentByService(int service) {
        return repository.getComponentByService(service);
    }

    public void insert(Component component) {
        repository.insert(component);
    }

    public void deleteAllComponents() {
        repository.deleteAllComponents();
    }

    public void deleteComponent(Component component) {
        repository.deleteComponent(component.getId());
    }

    public void deleteComponentByService(Service service) {
        repository.deleteComponentByService(service.getId());
    }

    public void update(Component component) {
        repository.update(component);
    }

    public LiveData<List<Upkeep>> getAllUpkeeps() {
        return allUpkeeps;
    }

    public LiveData<List<Upkeep>> getUpkeepByComponent(int component) {
        return repository.getUpkeepByComponent(component);
    }

    public void insert(Upkeep upkeep) {
        repository.insert(upkeep);
    }

    public void deleteAllUpkeeps() {
        repository.deleteAllUpkeeps();
    }

    public void deleteUpkeep(Upkeep upkeep) {
        repository.deleteUpkeep(upkeep.getId());
    }

    public void deleteUpkeepByComponent(Component component) {
        repository.deleteUpkeepByComponent(component.getId());
    }

    public void update(Upkeep upkeep) {
        repository.update(upkeep);
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public LiveData<List<Task>> getTaskByUpkeep(int upkeep) {
        return repository.getTaskByUpkeep(upkeep);
    }

    public LiveData<List<Task>> getTaskByOperator(int operator) {
        return repository.getTaskByOperator(operator);
    }

    public void insert(Task task) {
        repository.insert(task);
    }

    public void deleteAllTasks() {
        repository.deleteAllTasks();
    }

    public void deleteTask(Task task) {
        repository.deleteTask(task.getId());
    }

    public void deleteTaskByUpkeep(Upkeep upkeep) {
        repository.deleteTaskByUpkeep(upkeep.getId());
    }

    public void update(Task task) {
        repository.update(task);
    }

    public LiveData<List<Operator>> getAllOperators() {
        return allOperators;
    }

    public LiveData<Operator> getOperatorByLogin(String login) {
        return repository.getOperatorByLogin(login);
    }

    public LiveData<List<Operator>> getOperatorByOwner(int owner) {
        return repository.getOperatorByOwner(owner);
    }

    public void insert(Operator operator) {
        repository.insert(operator);
    }

    public void deleteAllOperators() {
        repository.deleteAllOperators();
    }

    public void deleteOperator(Operator operator) {
        repository.deleteOperator(operator.getId());
    }

    public void update(Operator operator) {
        repository.update(operator);
    }

    public LiveData<List<Store>> getAllStores() {
        return allStores;
    }

    public LiveData<List<Store>> getStoreByTask(int task) {
        return repository.getStoreByTask(task);
    }

    public void insert(Store store) {
        repository.insert(store);
    }

    public void deleteAllStores() {
        repository.deleteAllStores();
    }

    public void deleteStore(Store store) {
        repository.deleteStore(store.getId());
    }

    public void deleteStoreByTask(Task task) {
        repository.deleteStoreByTask(task.getId());
    }

    public void update(Store store) {
        repository.update(store);
    }
}
