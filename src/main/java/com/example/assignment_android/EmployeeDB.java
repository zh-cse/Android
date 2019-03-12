package com.example.assignment_android;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {EmployeeModel.class},version = 1,exportSchema = false)
public abstract class EmployeeDB extends RoomDatabase {
    public abstract EmployeeDao employeeDao();
}
