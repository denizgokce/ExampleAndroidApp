package com.example.deniz.exampleandroidapp.model.LocalDatabase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.deniz.exampleandroidapp.model.Person;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by deniz.gokce on 3.01.2018.
 */
@Dao
public interface PersonDao {
    /**
     * Get entity by itemId. For this App, we will pass in an ID when the detail Activity starts;
     * therefore we need not use LiveData as the Data will not change during the Activity's
     * Lifecycle.
     *
     * @param id A Unique identifier for a given record within the Database.
     * @return
     */
    @Query("SELECT * FROM Person WHERE id = :id")
    LiveData<Person> getPerson(String id);

    /**
     * Get all entities of type ListItem
     *
     * @return
     */
    @Query("SELECT * FROM Person")
    LiveData<List<Person>> getListItems();


    /**
     * Insert a new ListItem
     *
     * @param listItem
     */
    @Insert(onConflict = REPLACE)
    Long insertPerson(Person person);

    /**
     * Delete a given ListItem
     *
     * @param listItem
     */
    @Delete
    void deletePerson(Person person);

}
