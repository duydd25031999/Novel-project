package com.tutorial.novelproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.tutorial.novelproject.model.ChapterDetail;

import java.util.ArrayList;

public class ChapterDatabase extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "Novel.db";
    private static final String TABLE_CHAPTER = "chapter";
    private static final String TABLE_CHAPTER_CONTENT = "chapter_content";
    private static final String COLUMN_CHAPTER_ID = "chapter_id";
    private static final String COLUMN_CONTENT_ID = "content_id";
    private static final String COLUMN_URL = "url";
    private static final String COLUMN_PREV_URL = "prev_url";
    private static final String COLUMN_NEXT_URL = "next_url";
    private static final String COLUMN_CONTENT_TEXT = "content_text";

    public ChapterDatabase(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_CHAPTER =
            "CREATE TABLE " + TABLE_CHAPTER + "( "
                + COLUMN_CHAPTER_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_URL + " TEXT, "
                + COLUMN_PREV_URL + " TEXT, "
                + COLUMN_NEXT_URL + " TEXT)";

        String CREATE_TABLE_CHAPTER_CONTENT =
            "CREATE TABLE " + TABLE_CHAPTER_CONTENT + "( "
                + COLUMN_CONTENT_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_URL + " TEXT, "
                + COLUMN_CONTENT_TEXT + " TEXT)";
        db.execSQL(CREATE_TABLE_CHAPTER);
        db.execSQL(CREATE_TABLE_CHAPTER_CONTENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHAPTER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHAPTER_CONTENT);
        onCreate(db);
    }

    public long insertChapter(ChapterDetail chapterDetail) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues chapterValues = new ContentValues();
            ContentValues contentValues = new ContentValues();

            String url = chapterDetail.getUrl();

            chapterValues.put(COLUMN_URL, url);
            chapterValues.put(COLUMN_PREV_URL, chapterDetail.getPrevUrl());
            chapterValues.put(COLUMN_NEXT_URL, chapterDetail.getNextUrl());

            db.insert(TABLE_CHAPTER, null, chapterValues);

            for (String contentText : chapterDetail.getContent()) {
                contentValues.put(COLUMN_URL, url);
                contentValues.put(COLUMN_CONTENT_TEXT, contentText);
                db.insert(TABLE_CHAPTER_CONTENT, null, contentValues);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("insert " + TABLE_CHAPTER, e.getMessage());
            return -1l;
        }
        finally {
            db.endTransaction();
        }

        return 1l;
    }

    public boolean chapterExited(String url) {
        String query = "SELECT COUNT(*) FROM " + TABLE_CHAPTER
            + " WHERE " + COLUMN_URL + " LIKE '%" + url + "%'";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            int count = cursor.getInt(0);
            return count > 0;
        }

        return false;
    }

    public ArrayList<ChapterDetail> getAllDowloaded() {
        ArrayList<ChapterDetail> chapterDetails = new ArrayList<ChapterDetail>();
        String query = "SELECT " + COLUMN_URL + " FROM " + TABLE_CHAPTER;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            ChapterDetail chapterDetail = new ChapterDetail();
            chapterDetail.setUrl(cursor.getString(0));
            chapterDetails.add(chapterDetail);
        }

        return chapterDetails;
    }
}

