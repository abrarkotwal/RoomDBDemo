package com.abrarkotwal.roomdbdemo.DB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.abrarkotwal.roomdbdemo.DB.TypeConverters.DateTypeConverter;
import com.abrarkotwal.roomdbdemo.Models.Contact;


@Database(entities = {Contact.class}, version = 1)
@TypeConverters({DateTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContactDAO getContactDAO();
}
