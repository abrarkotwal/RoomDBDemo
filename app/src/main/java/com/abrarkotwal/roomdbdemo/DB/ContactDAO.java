package com.abrarkotwal.roomdbdemo.DB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.abrarkotwal.roomdbdemo.Models.Contact;

import java.util.List;

@Dao
public interface ContactDAO {

    @Insert
    public void insert(Contact... contacts);

    @Update
    public void update(Contact... contacts);

    @Delete
    public void delete(Contact contact);

    @Query("SELECT * FROM contact")
    public List<Contact> getContacts();

    @Query("SELECT * FROM contact WHERE phoneNumber = :number")
    public Contact getContactWithId(String number);

    @Query("UPDATE contact SET firstName= :firstName, lastName= :lastName where phoneNumber = :phoneNumber")
    public void updateContactDetail(String firstName,String lastName, String phoneNumber);
}

