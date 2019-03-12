package com.example.assignment_android;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
@Dao
public interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addAllEmployee(List<EmployeeModel> user);

    @Query("Select * from employeeTable")
    public List<EmployeeModel> ShowAllEmployee();

}
