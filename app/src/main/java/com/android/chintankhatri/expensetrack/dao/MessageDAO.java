package com.android.chintankhatri.expensetrack.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

/**
 * Created by sruthimaddineni on 11/15/16.
 */

public class MessageDAO {
    private SQLiteDatabase db;
    public MessageDAO() {
        db = SQLiteDatabase.openOrCreateDatabase("", null);
    }

    public boolean saveMessage(final Message msg, final String user)    {
        try {
            // final String createStmt = "create table if not exists " +
            final String insertStmt = "insert into " + getTableName(user) + " values ('" +
                    msg.getBank() + "', '" + msg.getLocation() + "', " + String.valueOf(msg.getAmount()) +
                    ", '" + msg.getCoordinatesStr() + "', '" + msg.getTimestamp().toString() + "')";
            db.beginTransaction();
            db.execSQL(insertStmt);
            db.endTransaction();
        }   catch (Exception e) {
            return false;
        }
        return true;
    }

    public Message[] readAll(final String location, final String user)   {
        try {
            Cursor cursor = db.query(true, getTableName(user), columns, "", null, null, null, null, null);
        }   catch (Exception e) {
        }
        return null;
    }

    public Message[] readAll(final Date timestamp)    {
        return readAll(START_DATE, timestamp);
    }

    public Message[] readAll(final Date timestamp1, final Date timestamp2)    {
        return null;
    }

    public Message[] readAll()  {
        return null;
    }

    private String getTableName(final String user)   {
        return user + "_expenses";
    }
}
