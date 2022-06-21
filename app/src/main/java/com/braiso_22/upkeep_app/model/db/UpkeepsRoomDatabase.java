package com.braiso_22.upkeep_app.model.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;

import com.braiso_22.upkeep_app.model.dao.*;
import com.braiso_22.upkeep_app.model.vo.*;
import com.braiso_22.upkeep_app.model.vo.users.*;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Owner.class, Fleet.class, Boat.class, Service.class,
        Component.class, Upkeep.class, Task.class, Manager.class,
        Operator.class, Store.class},
        version = 1, exportSchema = false)
public abstract class UpkeepsRoomDatabase extends RoomDatabase {

    public abstract OwnerDao ownerDao();

    public abstract FleetDao fleetDao();

    public abstract BoatDao boatDao();

    public abstract ManagerDao managerDao();

    public abstract ServiceDao serviceDao();

    public abstract ComponentDao componentDao();

    public abstract UpkeepDao upkeepDao();

    public abstract TaskDao taskDao();

    public abstract OperatorDao operatorDao();

    public abstract StoreDao storeDao();


    private static volatile UpkeepsRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 10;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static UpkeepsRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UpkeepsRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    UpkeepsRoomDatabase.class, "upkeeps_database")
                            .addCallback(roomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background

                // Owner
                OwnerDao ownerDao = INSTANCE.ownerDao();
                ownerDao.deleteAll();

                Owner owner = new Owner("brais", "1", "54157611V", "Brais", "Fernandez", "braisfv22@gmail.com");
                ownerDao.insert(owner);
                owner = new Owner("adrian", "2", "54157612A", "Adrián", "Fernandez Vázquez", "adrianfv07@gmail.com", "Rc4GdB3y6WevnFQIOM+FBA==\n");
                ownerDao.insert(owner);
                // Fleet
                FleetDao fleetDao = INSTANCE.fleetDao();
                fleetDao.deleteAll();

                Fleet fleet = new Fleet("Flota Carlos", 1);
                fleetDao.insert(fleet);
                fleet = new Fleet("StarFox", 2);
                fleetDao.insert(fleet);
                fleet = new Fleet("DreamLand", 2);
                fleetDao.insert(fleet);

                // Boat
                BoatDao boatDao = INSTANCE.boatDao();
                boatDao.deleteAll();

                Boat boat = new Boat("1", "Santa catalina", "123", 1);
                boatDao.insert(boat);
                boat = new Boat("1", "Falcon", "112233AB", 2);
                boatDao.insert(boat);
                boat = new Boat("2", "Wolf", "223344BC", 2);
                boatDao.insert(boat);
                boat = new Boat("1", "Metallic", "445566CD", 3);
                boatDao.insert(boat);
                boat = new Boat("2", "Penguin", "556677DE", 3);
                boatDao.insert(boat);

                // Service
                ServiceDao serviceDao = INSTANCE.serviceDao();
                serviceDao.deleteAll();

                Service service = new Service("123", "Motores", 1);
                serviceDao.insert(service);
                service = new Service("1", "Motores", 2);
                serviceDao.insert(service);
                service = new Service("1", "Motores", 3);
                serviceDao.insert(service);
                service = new Service("1", "Motores", 4);
                serviceDao.insert(service);
                service = new Service("1", "Motores", 5);
                serviceDao.insert(service);
                service = new Service("2", "Luces", 2);
                serviceDao.insert(service);
                service = new Service("2", "Luces", 3);
                serviceDao.insert(service);
                service = new Service("2", "Luces", 4);
                serviceDao.insert(service);
                service = new Service("2", "Luces", 5);
                serviceDao.insert(service);

                // Manager
                ManagerDao managerDao = INSTANCE.managerDao();
                managerDao.deleteAll();

                Manager manager = new Manager("adriano", "1", "54157612F", "Adriano", "Liviano ", "adrianol18@gmail.com", 1, 1);
                managerDao.insert(manager);
                manager = new Manager("marta", "1", "65710359Y", "Marta", "Vazquez Lopez ", "martavl12@gmail.com", 2, 2);
                managerDao.insert(manager);
                manager = new Manager("maria", "2", "1234359N", "Maria", "Iglesias Taboada ", "mariait12@gmail.com", 3, 2);
                managerDao.insert(manager);
                manager = new Manager("pepe", "3", "11111111H", "Pepe", "Fernandez", "pepef12@gmail.com", 4, 2);
                managerDao.insert(manager);
                manager = new Manager("juan", "4", "22222222V", "Juan", "Lopez", "juanl12@gmail.com", 5, 2);
                managerDao.insert(manager);

                // Component
                ComponentDao componentDao = INSTANCE.componentDao();
                componentDao.deleteAll();

                Component component = new Component("1", "motor principal", "rambeirg",
                        "14j", "1324", "buen estado", 1);
                componentDao.insert(component);

                component = new Component("1", "Motor principal", "Cummins",
                        "QSK 60", "0283022VX02", "Revisión atrasada", 2);
                componentDao.insert(component);
                component = new Component("2", "Motor popa", "Cummins",
                        "QSM11", "1243022VX14", "Buen estado", 2);
                componentDao.insert(component);
                component = new Component("1", "Motor principal", "Cummins",
                        "QSK 60", "0283098DJ02", "Buen estado", 3);
                componentDao.insert(component);
                component = new Component("1", "Motor principal", "Cummins",
                        "QSK 60", "0291022AX02", "Hace ruidos", 4);
                componentDao.insert(component);
                component = new Component("1", "Motor principal", "Cummins",
                        "QSK 60", "0283044DX62", "Buen estado", 5);
                componentDao.insert(component);

                // Upkeep
                UpkeepDao upkeepDao = INSTANCE.upkeepDao();
                upkeepDao.deleteAll();

                Upkeep upkeep = new Upkeep("2022-02-24", "20:31", 1);
                upkeepDao.insert(upkeep);
                upkeep = new Upkeep("2018-02-12", "20:21", 2);
                upkeepDao.insert(upkeep);
                upkeep = new Upkeep("2018-04-24", "17:31", 2);
                upkeepDao.insert(upkeep);
                upkeep = new Upkeep("2018-06-15", "12:00", 2);
                upkeepDao.insert(upkeep);
                upkeep = new Upkeep("2019-01-01", "05:25", 2);
                upkeepDao.insert(upkeep);
                upkeep = new Upkeep("2019-03-07", "12:56", 2);
                upkeepDao.insert(upkeep);
                upkeep = new Upkeep("2019-06-13", "14:56", 2);
                upkeepDao.insert(upkeep);
                upkeep = new Upkeep("2019-09-02", "16:56", 2);
                upkeepDao.insert(upkeep);
                upkeep = new Upkeep("2019-12-29", "17:56", 2);
                upkeepDao.insert(upkeep);
                upkeep = new Upkeep("2020-03-04", "22:56", 2);
                upkeepDao.insert(upkeep);
                upkeep = new Upkeep("2020-05-30", "02:56", 2);
                upkeepDao.insert(upkeep);
                upkeep = new Upkeep("2020-07-21", "05:56", 2);
                upkeepDao.insert(upkeep);
                upkeep = new Upkeep("2020-11-11", "15:06", 2);
                upkeepDao.insert(upkeep);
                upkeep = new Upkeep("2021-06-30", "06:50", 2);
                upkeepDao.insert(upkeep);
                upkeep = new Upkeep("2021-10-15", "18:43", 2);
                upkeepDao.insert(upkeep);
                upkeep = new Upkeep("2022-02-14", "22:02", 2);
                upkeepDao.insert(upkeep);
                upkeep = new Upkeep("2022-03-29", "01:07", 2);
                upkeepDao.insert(upkeep);
                upkeep = new Upkeep("2022-05-31", "08:52", 2);
                upkeepDao.insert(upkeep);
                upkeep = new Upkeep("2022-06-19", "15:36", 2);
                upkeepDao.insert(upkeep);

                // Operator
                OperatorDao operatorDao = INSTANCE.operatorDao();
                operatorDao.deleteAll();

                Operator operator = new Operator("camilo", "1", "54347643K", "Camilo",
                        "Fernández Rodriguez", "camilofr14@gmail.com", 1, 1);
                operatorDao.insert(operator);
                operator = new Operator("angel", "1", "12345431R", "Angel",
                        "Caamaño Martinez", "angelcm19@gmail.com", 2, 2);
                operatorDao.insert(operator);
                operator = new Operator("marcos", "2", "42335491P", "Marcos",
                        "Marquez Martinez", "marcosmm29@gmail.com", 2, 2);
                operatorDao.insert(operator);
                operator = new Operator("joel", "3", "444545431U", "Joel",
                        "Campanas Campos", "joelcc15@gmail.com", 3, 2);
                operatorDao.insert(operator);
                operator = new Operator("miguel", "4", "44454123M", "Miguel",
                        "Rios Verde", "miguelrv23@gmail.com", 4, 2);
                operatorDao.insert(operator);

                // Task
                TaskDao taskDao = INSTANCE.taskDao();
                taskDao.deleteAll();

                Task task = new Task(20, "Cambio de gomas", "Se cambiaron las antiguas gomas porque estaban gastadas y era peligroso", 1, 1);
                taskDao.insert(task);
                task = new Task(30, "Cambio de gomas", "Se cambiaron las antiguas gomas porque estaban gastadas y era peligroso", 19, 2);
                taskDao.insert(task);
                task = new Task(25, "Cambio de aceite", "El aceite estaba por debajo del nivel, se vació y puso uno nuevo", 19, 3);
                taskDao.insert(task);
                // Store
                StoreDao storeDao = INSTANCE.storeDao();
                storeDao.deleteAll();

                Store store = new Store("1", "tornillo", "bosch", "2N",
                        "12", "tornillos de estrella", 20, 10, 1);
                storeDao.insert(store);
                store = new Store("1", "Goma de motor", "SWAG", "07606",
                        "4123MG12", "Ya usado", 4, 2, 2);
                storeDao.insert(store);
                store = new Store("2", "Aceite de motor", "Castrol", "5W-40",
                        "1234", "Se usó media botella", 5, 3, 3);
                storeDao.insert(store);

            });
        }
    };
}
