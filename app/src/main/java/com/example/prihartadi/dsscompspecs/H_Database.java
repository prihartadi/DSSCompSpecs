package com.example.prihartadi.dsscompspecs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prihartadi on 10/07/2017.
 */

public class H_Database extends SQLiteOpenHelper {

    static final String LOG = H_Database.class.getName();
    static final int DATABASE_VERSION = 1;
    //db name
    static final String DATABASE_NAME = "CompDB";
    //table names
    static final String TABLE_PROCESSOR = "processor";
    static final String TABLE_MOTHERBOARD = "motherboard";
    static final String TABLE_RAM = "ram";
    static final String TABLE_GPU = "gpu";
    static final String TABLE_POWER = "power";
    static final String TABLE_STORAGE = "storage";
    static final String TABLE_MONITOR = "monitor";
    static final String TABLE_CASING = "casing";
    static final String TABLE_MOUSE = "mouse";
    static final String TABLE_KEYBOARD = "keyboard";
    //common column names
    static final String KEY_ID = "id";
    static final String KEY_BRAND = "brand";
    static final String KEY_CODE = "code";
    static final String KEY_PRICE = "price";
    static final String KEY_PERFORMANCE = "performance";
    //cpu/mbo
    static final String KEY_SOCKET = "socket";
    //mbo/ram
    static final String KEY_RAM_TYPE = "ram_type";
    //mbo/gpu
    static final String KEY_PCIE = "pcie";
    //mbo/mem
    static final String KEY_MEM_CONN = "mem_conn";
    //mbo/cas
    static final String KEY_FORM = "form";
    //cpu/ram
    static final String KEY_SPEED = "speed";
    //cpu/gpu
    static final String KEY_TDP = "tdp";
    static final String KEY_BENCHMARK = "benchmark";
    //ram/mem
    static final String KEY_SIZE = "size";
    //mbo/gpu
    static final String KEY_CHIPSET = "chipset";

    //cpu column names
    static final String KEY_CORE = "core";
    static final String KEY_CACHE = "cache";
    //ram column names
    static final String KEY_PIECE = "piece";
    static final String KEY_TOTAL_SIZE = "total_size";
    //gpu column names
    static final String KEY_VRAM_TYPE = "vram_type";
    static final String KEY_VRAM_SIZE = "vram_size";
    static final String KEY_BITRATE = "bitrate";
    //psu column names
    static final String KEY_POWER = "power";
    static final String KEY_CERTIFICATION = "certification";
    //mem column names
    static final String KEY_TYPE = "type";
    //dsp column names
    static final String KEY_WIDE = "wide";
    static final String KEY_RES_X = "res_x";
    static final String KEY_RES_Y = "res_y";

    public H_Database (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    static final String CREATE_PROCESSOR_TABLE = "CREATE TABLE " + TABLE_PROCESSOR +
            "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_BRAND + " TEXT COLLATE NOCASE," +
            KEY_CODE + " TEXT COLLATE NOCASE," +
            KEY_SOCKET + " TEXT COLLATE NOCASE," +
            KEY_CORE + " TEXT COLLATE NOCASE," +
            KEY_CACHE + " TEXT COLLATE NOCASE," +
            KEY_SPEED + " TEXT COLLATE NOCASE," +
            KEY_TDP + " TEXT COLLATE NOCASE," +
            KEY_BENCHMARK + " TEXT COLLATE NOCASE," +
            KEY_PRICE + " LONG," +
            KEY_PERFORMANCE + " DOUBLE" +
            ")";

    static final String CREATE_MOTHERBOARD_TABLE = "CREATE TABLE " + TABLE_MOTHERBOARD +
            "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_BRAND + " TEXT COLLATE NOCASE," +
            KEY_CODE + " TEXT COLLATE NOCASE," +
            KEY_SOCKET + " TEXT COLLATE NOCASE," +
            KEY_CHIPSET + " TEXT COLLATE NOCASE," +
            KEY_RAM_TYPE + " TEXT COLLATE NOCASE," +
            KEY_PCIE + " TEXT COLLATE NOCASE," +
            KEY_MEM_CONN + " TEXT COLLATE NOCASE," +
            KEY_FORM + " TEXT COLLATE NOCASE," +
            KEY_PRICE + " LONG," +
            KEY_PERFORMANCE + " DOUBLE" +
            ")";

    static final String CREATE_RAM_TABLE = "CREATE TABLE " + TABLE_RAM +
            "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_BRAND + " TEXT COLLATE NOCASE," +
            KEY_CODE + " TEXT COLLATE NOCASE," +
            KEY_RAM_TYPE + " TEXT COLLATE NOCASE," +
            KEY_PIECE + " TEXT COLLATE NOCASE," +
            KEY_SIZE + " TEXT COLLATE NOCASE," +
            KEY_TOTAL_SIZE + " TEXT COLLATE NOCASE," +
            KEY_SPEED + " TEXT COLLATE NOCASE," +
            KEY_PRICE + " LONG," +
            KEY_PERFORMANCE + " DOUBLE" +
            ")";

    static final String CREATE_GPU_TABLE = "CREATE TABLE " + TABLE_GPU +
            "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_BRAND + " TEXT COLLATE NOCASE," +
            KEY_CODE + " TEXT COLLATE NOCASE," +
            KEY_CHIPSET + " TEXT COLLATE NOCASE," +
            KEY_PCIE + " TEXT COLLATE NOCASE," +
            KEY_VRAM_TYPE + " TEXT COLLATE NOCASE," +
            KEY_VRAM_SIZE + " TEXT COLLATE NOCASE," +
            KEY_BITRATE + " TEXT COLLATE NOCASE," +
            KEY_TDP + " TEXT COLLATE NOCASE," +
            KEY_BENCHMARK + " TEXT COLLATE NOCASE," +
            KEY_PRICE + " LONG," +
            KEY_PERFORMANCE + " DOUBLE" +
            ")";

    static final String CREATE_POWER_TABLE = "CREATE TABLE " + TABLE_POWER +
            "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_BRAND + " TEXT COLLATE NOCASE," +
            KEY_CODE + " TEXT COLLATE NOCASE," +
            KEY_POWER + " TEXT COLLATE NOCASE," +
            KEY_CERTIFICATION + " TEXT COLLATE NOCASE," +
            KEY_PRICE + " LONG," +
            KEY_PERFORMANCE + " DOUBLE" +
            ")";

    static final String CREATE_STORAGE_TABLE = "CREATE TABLE " + TABLE_STORAGE +
            "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_BRAND + " TEXT COLLATE NOCASE," +
            KEY_CODE + " TEXT COLLATE NOCASE," +
            KEY_TYPE + " TEXT COLLATE NOCASE," +
            KEY_MEM_CONN + " TEXT COLLATE NOCASE," +
            KEY_SIZE + " TEXT COLLATE NOCASE," +
            KEY_PRICE + " LONG," +
            KEY_PERFORMANCE + " DOUBLE" +
            ")";

    static final String CREATE_MONITOR_TABLE = "CREATE TABLE " + TABLE_MONITOR +
            "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_BRAND + " TEXT COLLATE NOCASE," +
            KEY_CODE + " TEXT COLLATE NOCASE," +
            KEY_WIDE + " TEXT COLLATE NOCASE," +
            KEY_RES_X + " TEXT COLLATE NOCASE," +
            KEY_RES_Y + " TEXT COLLATE NOCASE," +
            KEY_PRICE + " LONG," +
            KEY_PERFORMANCE + " DOUBLE" +
            ")";

    static final String CREATE_CASING_TABLE = "CREATE TABLE " + TABLE_CASING +
            "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_BRAND + " TEXT COLLATE NOCASE," +
            KEY_CODE + " TEXT COLLATE NOCASE," +
            KEY_FORM + " TEXT COLLATE NOCASE," +
            KEY_PRICE + " LONG," +
            KEY_PERFORMANCE + " DOUBLE" +
            ")";

    static final String CREATE_MOUSE_TABLE = "CREATE TABLE " + TABLE_MOUSE +
            "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_BRAND + " TEXT COLLATE NOCASE," +
            KEY_CODE + " TEXT COLLATE NOCASE," +
            KEY_PRICE + " LONG," +
            KEY_PERFORMANCE + " DOUBLE" +
            ")";

    static final String CREATE_KEYBOARD_TABLE = "CREATE TABLE " + TABLE_KEYBOARD +
            "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_BRAND + " TEXT COLLATE NOCASE," +
            KEY_CODE + " TEXT COLLATE NOCASE," +
            KEY_PRICE + " LONG," +
            KEY_PERFORMANCE + " DOUBLE" +
            ")";

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROCESSOR_TABLE);
        db.execSQL(CREATE_MOTHERBOARD_TABLE);
        db.execSQL(CREATE_RAM_TABLE);
        db.execSQL(CREATE_GPU_TABLE);
        db.execSQL(CREATE_POWER_TABLE);
        db.execSQL(CREATE_STORAGE_TABLE);
        db.execSQL(CREATE_MONITOR_TABLE);
        db.execSQL(CREATE_CASING_TABLE);
        db.execSQL(CREATE_MOUSE_TABLE);
        db.execSQL(CREATE_KEYBOARD_TABLE);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS cpu");
        this.onCreate(db);
    }

    public void deleteAllTable(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete("processor",null,null);
        db.delete("motherboard",null,null);
        db.delete("ram",null,null);
        db.delete("gpu",null,null);
        db.delete("power",null,null);
        db.delete("storage",null,null);
        db.delete("monitor",null,null);
        db.delete("casing",null,null);
        db.delete("mouse",null,null);
        db.delete("keyboard",null,null);
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    public long createProcessor(C_Processor processor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_BRAND, processor.getBrand());
        values.put(KEY_CODE, processor.getCode());
        values.put(KEY_SOCKET, processor.getSocket());
        values.put(KEY_CORE, processor.getCore());
        values.put(KEY_CACHE, processor.getCache());
        values.put(KEY_SPEED, processor.getSpeed());
        values.put(KEY_TDP, processor.getTdp());
        values.put(KEY_BENCHMARK, processor.getBenchmark());
        values.put(KEY_PRICE, processor.getPrice());
        values.put(KEY_PERFORMANCE, processor.getPerformance());
        long processor_id = db.insert(TABLE_PROCESSOR, null, values);
        return processor_id;
    }

    public C_Processor getProcessor(String query){
        SQLiteDatabase db = this.getReadableDatabase();
//        String selectQuery = "SELECT  * FROM " + TABLE_PROCESSOR + " WHERE "
//                + KEY_ID + " = " + processor_id;
        String selectQuery = query;
        C_Processor processor = new C_Processor();
        Cursor c = null;
        try
        {
            c = db.rawQuery(selectQuery, null);
            if (c != null && c.getCount() > 0)
            {
                c.moveToFirst();
                processor.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                processor.setBrand(c.getString(c.getColumnIndex(KEY_BRAND)));
                processor.setCode(c.getString(c.getColumnIndex(KEY_CODE)));
                processor.setSocket(c.getString(c.getColumnIndex(KEY_SOCKET)));
                processor.setCore(c.getString(c.getColumnIndex(KEY_CORE)));
                processor.setCache(c.getString(c.getColumnIndex(KEY_CACHE)));
                processor.setSpeed(c.getString(c.getColumnIndex(KEY_SPEED)));
                processor.setTdp(c.getString(c.getColumnIndex(KEY_TDP)));
                processor.setBenchmark(c.getString(c.getColumnIndex(KEY_BENCHMARK)));
                processor.setPrice(c.getLong(c.getColumnIndex(KEY_PRICE)));
                processor.setPerformance(c.getDouble(c.getColumnIndex(KEY_PERFORMANCE)));
            }
        } catch (SQLException e) {
            Log.e("SQL_eror", "sql exception in dbio_count", e);
        } catch(Exception ex) {
            Log.e("SQL_eror", "other exception in dbio_count", ex);
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return processor;
    }

    public List<C_Processor> getAllProcessors(String query){
        List<C_Processor> allProcessors = new ArrayList<C_Processor>();
//        String selectQuery = "SELECT  * FROM " + TABLE_PROCESSOR;
        String selectQuery = query;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        try
        {
            c = db.rawQuery(selectQuery, null);
            if (c != null && c.getCount() > 0)
            {
                c.moveToFirst();
                if (c.moveToFirst()) {
                    do {
                        C_Processor processor = new C_Processor();
                        processor.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                        processor.setBrand(c.getString(c.getColumnIndex(KEY_BRAND)));
                        processor.setCode(c.getString(c.getColumnIndex(KEY_CODE)));
                        processor.setSocket(c.getString(c.getColumnIndex(KEY_SOCKET)));
                        processor.setCore(c.getString(c.getColumnIndex(KEY_CORE)));
                        processor.setCache(c.getString(c.getColumnIndex(KEY_CACHE)));
                        processor.setSpeed(c.getString(c.getColumnIndex(KEY_SPEED)));
                        processor.setTdp(c.getString(c.getColumnIndex(KEY_TDP)));
                        processor.setBenchmark(c.getString(c.getColumnIndex(KEY_BENCHMARK)));
                        processor.setPrice(c.getLong(c.getColumnIndex(KEY_PRICE)));
                        processor.setPerformance(c.getDouble(c.getColumnIndex(KEY_PERFORMANCE)));
                        allProcessors.add(processor);
                    } while (c.moveToNext());
                }
            }
        } catch (SQLException e) {
            Log.e("SQL_eror", "sql exception in dbio_count", e);
        } catch(Exception ex) {
            Log.e("SQL_eror", "other exception in dbio_count", ex);
        } finally {
            if (c != null) {
                c.close();
            }
        }
        closeDB();
        return allProcessors;
    }

    public long createMotherboard(C_Motherboard motherboard) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_BRAND, motherboard.getBrand());
        values.put(KEY_CODE, motherboard.getCode());
        values.put(KEY_SOCKET, motherboard.getSocket());
        values.put(KEY_CHIPSET, motherboard.getChipset());
        values.put(KEY_RAM_TYPE, motherboard.getRamType());
        values.put(KEY_PCIE, motherboard.getPcie());
        values.put(KEY_MEM_CONN, motherboard.getMemConn());
        values.put(KEY_FORM, motherboard.getForm());
        values.put(KEY_PRICE, motherboard.getPrice());
        values.put(KEY_PERFORMANCE, motherboard.getPerformance());
        long motherboard_id = db.insert(TABLE_MOTHERBOARD, null, values);
        return motherboard_id;
    }

    public C_Motherboard getMotherboard(String query){
        SQLiteDatabase db = this.getReadableDatabase();
//        String selectQuery = "SELECT  * FROM " + TABLE_MOTHERBOARD + " WHERE "
//                + KEY_ID + " = " + mbo_id;
        String selectQuery = query;
        C_Motherboard motherboard = new C_Motherboard();
        Cursor c = null;
        try
        {
            c = db.rawQuery(selectQuery, null);
            if (c != null && c.getCount() > 0)
            {
                c.moveToFirst();
                motherboard.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                motherboard.setBrand(c.getString(c.getColumnIndex(KEY_BRAND)));
                motherboard.setCode(c.getString(c.getColumnIndex(KEY_CODE)));
                motherboard.setSocket(c.getString(c.getColumnIndex(KEY_SOCKET)));
                motherboard.setChipset(c.getString(c.getColumnIndex(KEY_CHIPSET)));
                motherboard.setRamType(c.getString(c.getColumnIndex(KEY_RAM_TYPE)));
                motherboard.setPcie(c.getString(c.getColumnIndex(KEY_PCIE)));
                motherboard.setMemConn(c.getString(c.getColumnIndex(KEY_MEM_CONN)));
                motherboard.setForm(c.getString(c.getColumnIndex(KEY_FORM)));
                motherboard.setPrice(c.getLong(c.getColumnIndex(KEY_PRICE)));
                motherboard.setPerformance(c.getDouble(c.getColumnIndex(KEY_PERFORMANCE)));
            }
        } catch (SQLException e) {
            Log.e("SQL_eror", "sql exception in dbio_count", e);
        } catch(Exception ex) {
            Log.e("SQL_eror", "other exception in dbio_count", ex);
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return motherboard;
    }

    public List<C_Motherboard> getAllMotherboards(String query){
        List<C_Motherboard> allMotherboards = new ArrayList<C_Motherboard>();
//        String selectQuery = "SELECT  * FROM " + TABLE_MOTHERBOARD;
        String selectQuery = query;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        try
        {
            c = db.rawQuery(selectQuery, null);
            if (c != null && c.getCount() > 0)
            {
                c.moveToFirst();
                if (c.moveToFirst()) {
                    do {
                        C_Motherboard motherboard = new C_Motherboard();
                        motherboard.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                        motherboard.setBrand(c.getString(c.getColumnIndex(KEY_BRAND)));
                        motherboard.setCode(c.getString(c.getColumnIndex(KEY_CODE)));
                        motherboard.setSocket(c.getString(c.getColumnIndex(KEY_SOCKET)));
                        motherboard.setChipset(c.getString(c.getColumnIndex(KEY_CHIPSET)));
                        motherboard.setRamType(c.getString(c.getColumnIndex(KEY_RAM_TYPE)));
                        motherboard.setPcie(c.getString(c.getColumnIndex(KEY_PCIE)));
                        motherboard.setMemConn(c.getString(c.getColumnIndex(KEY_MEM_CONN)));
                        motherboard.setForm(c.getString(c.getColumnIndex(KEY_FORM)));
                        motherboard.setPrice(c.getLong(c.getColumnIndex(KEY_PRICE)));
                        motherboard.setPerformance(c.getDouble(c.getColumnIndex(KEY_PERFORMANCE)));
                        allMotherboards.add(motherboard);
                    } while (c.moveToNext());
                }
            }
        } catch (SQLException e) {
            Log.e("SQL_eror", "sql exception in dbio_count", e);
        } catch(Exception ex) {
            Log.e("SQL_eror", "other exception in dbio_count", ex);
        } finally {
            if (c != null) {
                c.close();
            }
        }
        closeDB();
        return allMotherboards;
    }

    public long createRam(C_Ram ram) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_BRAND, ram.getBrand());
        values.put(KEY_CODE, ram.getCode());
        values.put(KEY_RAM_TYPE, ram.getRamType());
        values.put(KEY_PIECE, ram.getPiece());
        values.put(KEY_SIZE, ram.getSize());
        values.put(KEY_TOTAL_SIZE, ram.getTotalSize());
        values.put(KEY_SPEED, ram.getSpeed());
        values.put(KEY_PRICE, ram.getPrice());
        values.put(KEY_PERFORMANCE, ram.getPerformance());
        long ram_id = db.insert(TABLE_RAM, null, values);
        return ram_id;
    }

    public C_Ram getRam(String query){
        SQLiteDatabase db = this.getReadableDatabase();
//        String selectQuery = "SELECT  * FROM " + TABLE_RAM + " WHERE "
//                + KEY_ID + " = " + ram_id;
        String selectQuery = query;
        C_Ram ram = new C_Ram();
        Cursor c = null;
        try
        {
            c = db.rawQuery(selectQuery, null);
            if (c != null && c.getCount() > 0)
            {
                c.moveToFirst();
                ram.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                ram.setBrand(c.getString(c.getColumnIndex(KEY_BRAND)));
                ram.setCode(c.getString(c.getColumnIndex(KEY_CODE)));
                ram.setRamType(c.getString(c.getColumnIndex(KEY_RAM_TYPE)));
                ram.setPiece(c.getString(c.getColumnIndex(KEY_PIECE)));
                ram.setSize(c.getString(c.getColumnIndex(KEY_SIZE)));
                ram.setTotalSize(c.getString(c.getColumnIndex(KEY_TOTAL_SIZE)));
                ram.setSpeed(c.getString(c.getColumnIndex(KEY_SPEED)));
                ram.setPrice(c.getLong(c.getColumnIndex(KEY_PRICE)));
                ram.setPerformance(c.getDouble(c.getColumnIndex(KEY_PERFORMANCE)));
            }
        } catch (SQLException e) {
            Log.e("SQL_eror", "sql exception in dbio_count", e);
        } catch(Exception ex) {
            Log.e("SQL_eror", "other exception in dbio_count", ex);
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return ram;
    }

    public List<C_Ram> getAllRams(String query){
        List<C_Ram> allRams = new ArrayList<C_Ram>();
//        String selectQuery = "SELECT  * FROM "+ TABLE_RAM;
        String selectQuery = query;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        try
        {
            c = db.rawQuery(selectQuery, null);
            if (c != null && c.getCount() > 0)
            {
                c.moveToFirst();
                if (c.moveToFirst()) {
                    do {
                        C_Ram ram = new C_Ram();
                        ram.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                        ram.setBrand(c.getString(c.getColumnIndex(KEY_BRAND)));
                        ram.setCode(c.getString(c.getColumnIndex(KEY_CODE)));
                        ram.setRamType(c.getString(c.getColumnIndex(KEY_RAM_TYPE)));
                        ram.setPiece(c.getString(c.getColumnIndex(KEY_PIECE)));
                        ram.setSize(c.getString(c.getColumnIndex(KEY_SIZE)));
                        ram.setTotalSize(c.getString(c.getColumnIndex(KEY_TOTAL_SIZE)));
                        ram.setSpeed(c.getString(c.getColumnIndex(KEY_SPEED)));
                        ram.setPrice(c.getLong(c.getColumnIndex(KEY_PRICE)));
                        ram.setPerformance(c.getDouble(c.getColumnIndex(KEY_PERFORMANCE)));
                        allRams.add(ram);
                    } while (c.moveToNext());
                }
            }
        } catch (SQLException e) {
            Log.e("SQL_eror", "sql exception in dbio_count", e);
        } catch(Exception ex) {
            Log.e("SQL_eror", "other exception in dbio_count", ex);
        } finally {
            if (c != null) {
                c.close();
            }
        }
        closeDB();
        return allRams;
    }

    public long createGpu(C_Gpu gpu) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_BRAND, gpu.getBrand());
        values.put(KEY_CODE, gpu.getCode());
        values.put(KEY_CHIPSET, gpu.getChipset());
        values.put(KEY_PCIE, gpu.getPcie());
        values.put(KEY_VRAM_TYPE, gpu.getVramType());
        values.put(KEY_VRAM_SIZE, gpu.getVramSize());
        values.put(KEY_BITRATE, gpu.getBitrate());
        values.put(KEY_TDP, gpu.getTdp());
        values.put(KEY_BENCHMARK, gpu.getBenchmark());
        values.put(KEY_PRICE, gpu.getPrice());
        values.put(KEY_PERFORMANCE, gpu.getPerformance());
        long gpu_id = db.insert(TABLE_GPU, null, values);
        return gpu_id;
    }

    public C_Gpu getGpu(String query){
        SQLiteDatabase db = this.getReadableDatabase();
//        String selectQuery = "SELECT  * FROM " + TABLE_GPU + " WHERE "
//                + KEY_ID + " = " + gpu_id;
        String selectQuery = query;
        C_Gpu gpu = new C_Gpu();
        Cursor c = null;
        try
        {
            c = db.rawQuery(selectQuery, null);
            if (c != null && c.getCount() > 0)
            {
                c.moveToFirst();
                gpu.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                gpu.setBrand(c.getString(c.getColumnIndex(KEY_BRAND)));
                gpu.setCode(c.getString(c.getColumnIndex(KEY_CODE)));
                gpu.setChipset(c.getString(c.getColumnIndex(KEY_CHIPSET)));
                gpu.setPcie(c.getString(c.getColumnIndex(KEY_PCIE)));
                gpu.setVramType(c.getString(c.getColumnIndex(KEY_VRAM_TYPE)));
                gpu.setVramSize(c.getString(c.getColumnIndex(KEY_VRAM_SIZE)));
                gpu.setBitrate(c.getString(c.getColumnIndex(KEY_BITRATE)));
                gpu.setTdp(c.getString(c.getColumnIndex(KEY_TDP)));
                gpu.setBenchmark(c.getString(c.getColumnIndex(KEY_BENCHMARK)));
                gpu.setPrice(c.getLong(c.getColumnIndex(KEY_PRICE)));
                gpu.setPerformance(c.getDouble(c.getColumnIndex(KEY_PERFORMANCE)));
            }
        } catch (SQLException e) {
            Log.e("SQL_eror", "sql exception in dbio_count", e);
        } catch(Exception ex) {
            Log.e("SQL_eror", "other exception in dbio_count", ex);
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return gpu;
    }

    public List<C_Gpu> getAllGpus(String query){
        List<C_Gpu> allGpus = new ArrayList<C_Gpu>();
//        String selectQuery = "SELECT  * FROM "+ TABLE_GPU;
        String selectQuery = query;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        try
        {
            c = db.rawQuery(selectQuery, null);
            if (c != null && c.getCount() > 0)
            {
                c.moveToFirst();
                if (c.moveToFirst()) {
                    do {
                        C_Gpu gpu = new C_Gpu();
                        gpu.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                        gpu.setBrand(c.getString(c.getColumnIndex(KEY_BRAND)));
                        gpu.setCode(c.getString(c.getColumnIndex(KEY_CODE)));
                        gpu.setChipset(c.getString(c.getColumnIndex(KEY_CHIPSET)));
                        gpu.setPcie(c.getString(c.getColumnIndex(KEY_PCIE)));
                        gpu.setVramType(c.getString(c.getColumnIndex(KEY_VRAM_TYPE)));
                        gpu.setVramSize(c.getString(c.getColumnIndex(KEY_VRAM_SIZE)));
                        gpu.setBitrate(c.getString(c.getColumnIndex(KEY_BITRATE)));
                        gpu.setTdp(c.getString(c.getColumnIndex(KEY_TDP)));
                        gpu.setBenchmark(c.getString(c.getColumnIndex(KEY_BENCHMARK)));
                        gpu.setPrice(c.getLong(c.getColumnIndex(KEY_PRICE)));
                        gpu.setPerformance(c.getDouble(c.getColumnIndex(KEY_PERFORMANCE)));
                        allGpus.add(gpu);
                    } while (c.moveToNext());
                }
            }
        } catch (SQLException e) {
            Log.e("SQL_eror", "sql exception in dbio_count", e);
        } catch(Exception ex) {
            Log.e("SQL_eror", "other exception in dbio_count", ex);
        } finally {
            if (c != null) {
                c.close();
            }
        }
        closeDB();
        return allGpus;
    }

    public long createPower(C_Power power) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_BRAND, power.getBrand());
        values.put(KEY_CODE, power.getCode());
        values.put(KEY_POWER, power.getPower());
        values.put(KEY_CERTIFICATION, power.getCertification());
        values.put(KEY_PRICE, power.getPrice());
        values.put(KEY_PERFORMANCE, power.getPerformance());
        long psu_id = db.insert(TABLE_POWER, null, values);
        return psu_id;
    }

    public C_Power getPower(String query){
        SQLiteDatabase db = this.getReadableDatabase();
//        String selectQuery = "SELECT  * FROM " + TABLE_POWER + " WHERE "
//                + KEY_ID + " = " + psu_id;
        String selectQuery = query;
        C_Power power = new C_Power();
        Cursor c = null;
        try
        {
            c = db.rawQuery(selectQuery, null);
            if (c != null && c.getCount() > 0)
            {
                c.moveToFirst();
                power.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                power.setBrand(c.getString(c.getColumnIndex(KEY_BRAND)));
                power.setCode(c.getString(c.getColumnIndex(KEY_CODE)));
                power.setPower(c.getString(c.getColumnIndex(KEY_POWER)));
                power.setCertification(c.getString(c.getColumnIndex(KEY_CERTIFICATION)));
                power.setPrice(c.getLong(c.getColumnIndex(KEY_PRICE)));
                power.setPerformance(c.getDouble(c.getColumnIndex(KEY_PERFORMANCE)));
            }
        } catch (SQLException e) {
            Log.e("SQL_eror", "sql exception in dbio_count", e);
        } catch(Exception ex) {
            Log.e("SQL_eror", "other exception in dbio_count", ex);
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return power;
    }

    public List<C_Power> getAllPowers(String query){
        List<C_Power> allPowers = new ArrayList<C_Power>();
//        String selectQuery = "SELECT  * FROM "+ TABLE_POWER;
        String selectQuery = query;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        try
        {
            c = db.rawQuery(selectQuery, null);
            if (c != null && c.getCount() > 0)
            {
                c.moveToFirst();
                if (c.moveToFirst()) {
                    do {
                        C_Power power = new C_Power();
                        power.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                        power.setBrand(c.getString(c.getColumnIndex(KEY_BRAND)));
                        power.setCode(c.getString(c.getColumnIndex(KEY_CODE)));
                        power.setPower(c.getString(c.getColumnIndex(KEY_POWER)));
                        power.setCertification(c.getString(c.getColumnIndex(KEY_CERTIFICATION)));
                        power.setPrice(c.getLong(c.getColumnIndex(KEY_PRICE)));
                        power.setPerformance(c.getDouble(c.getColumnIndex(KEY_PERFORMANCE)));
                        allPowers.add(power);
                    } while (c.moveToNext());
                }
            }
        } catch (SQLException e) {
            Log.e("SQL_eror", "sql exception in dbio_count", e);
        } catch(Exception ex) {
            Log.e("SQL_eror", "other exception in dbio_count", ex);
        } finally {
            if (c != null) {
                c.close();
            }
        }
        closeDB();
        return allPowers;
    }

    public long createStorage(C_Storage storage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_BRAND, storage.getBrand());
        values.put(KEY_CODE, storage.getCode());
        values.put(KEY_TYPE, storage.getType());
        values.put(KEY_MEM_CONN, storage.getMemConn());
        values.put(KEY_SIZE, storage.getSize());
        values.put(KEY_PRICE, storage.getPrice());
        values.put(KEY_PERFORMANCE, storage.getPerformance());
        long storage_id = db.insert(TABLE_STORAGE, null, values);
        return storage_id;
    }

    public C_Storage getStorage(String query){
        SQLiteDatabase db = this.getReadableDatabase();
//        String selectQuery = "SELECT  * FROM " + TABLE_STORAGE + " WHERE "
//                + KEY_ID + " = " + storage_id;
        String selectQuery = query;
        C_Storage storage = new C_Storage();
        Cursor c = null;
        try
        {
            c = db.rawQuery(selectQuery, null);
            if (c != null && c.getCount() > 0)
            {
                c.moveToFirst();
                storage.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                storage.setBrand(c.getString(c.getColumnIndex(KEY_BRAND)));
                storage.setCode(c.getString(c.getColumnIndex(KEY_CODE)));
                storage.setType(c.getString(c.getColumnIndex(KEY_TYPE)));
                storage.setMemConn(c.getString(c.getColumnIndex(KEY_MEM_CONN)));
                storage.setSize(c.getString(c.getColumnIndex(KEY_SIZE)));
                storage.setPrice(c.getLong(c.getColumnIndex(KEY_PRICE)));
                storage.setPerformance(c.getDouble(c.getColumnIndex(KEY_PERFORMANCE)));
            }
        } catch (SQLException e) {
            Log.e("SQL_eror", "sql exception in dbio_count", e);
        } catch(Exception ex) {
            Log.e("SQL_eror", "other exception in dbio_count", ex);
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return storage;
    }

    public List<C_Storage> getAllStorages(String query){
        List<C_Storage> allStorages = new ArrayList<C_Storage>();
//        String selectQuery = "SELECT  * FROM "+ TABLE_STORAGE;
        String selectQuery = query;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        try
        {
            c = db.rawQuery(selectQuery, null);
            if (c != null && c.getCount() > 0)
            {
                c.moveToFirst();
                if (c.moveToFirst()) {
                    do {
                        C_Storage storage = new C_Storage();
                        storage.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                        storage.setBrand(c.getString(c.getColumnIndex(KEY_BRAND)));
                        storage.setCode(c.getString(c.getColumnIndex(KEY_CODE)));
                        storage.setType(c.getString(c.getColumnIndex(KEY_TYPE)));
                        storage.setMemConn(c.getString(c.getColumnIndex(KEY_MEM_CONN)));
                        storage.setSize(c.getString(c.getColumnIndex(KEY_SIZE)));
                        storage.setPrice(c.getLong(c.getColumnIndex(KEY_PRICE)));
                        storage.setPerformance(c.getDouble(c.getColumnIndex(KEY_PERFORMANCE)));
                        allStorages.add(storage);
                    } while (c.moveToNext());
                }
            }
        } catch (SQLException e) {
            Log.e("SQL_eror", "sql exception in dbio_count", e);
        } catch(Exception ex) {
            Log.e("SQL_eror", "other exception in dbio_count", ex);
        } finally {
            if (c != null) {
                c.close();
            }
        }
        closeDB();
        return allStorages;
    }

    public long createMonitor(C_Monitor monitor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_BRAND, monitor.getBrand());
        values.put(KEY_CODE, monitor.getCode());
        values.put(KEY_WIDE, monitor.getWide());
        values.put(KEY_RES_X, monitor.getResX());
        values.put(KEY_RES_Y, monitor.getResY());
        values.put(KEY_PRICE, monitor.getPrice());
        values.put(KEY_PERFORMANCE, monitor.getPerformance());
        long monitor_id = db.insert(TABLE_MONITOR, null, values);
        return monitor_id;
    }

    public C_Monitor getMonitor(String query){
        SQLiteDatabase db = this.getReadableDatabase();
//        String selectQuery = "SELECT  * FROM " + TABLE_MONITOR + " WHERE "
//                + KEY_ID + " = " + monitor_id;
        String selectQuery = query;
        C_Monitor monitor = new C_Monitor();
        Cursor c = null;
        try
        {
            c = db.rawQuery(selectQuery, null);
            if (c != null && c.getCount() > 0)
            {
                c.moveToFirst();
                monitor.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                monitor.setBrand(c.getString(c.getColumnIndex(KEY_BRAND)));
                monitor.setCode(c.getString(c.getColumnIndex(KEY_CODE)));
                monitor.setSize(c.getString(c.getColumnIndex(KEY_WIDE)));
                monitor.setResX(c.getString(c.getColumnIndex(KEY_RES_X)));
                monitor.setResY(c.getString(c.getColumnIndex(KEY_RES_Y)));
                monitor.setPrice(c.getLong(c.getColumnIndex(KEY_PRICE)));
                monitor.setPerformance(c.getDouble(c.getColumnIndex(KEY_PERFORMANCE)));
            }
        } catch (SQLException e) {
            Log.e("SQL_eror", "sql exception in dbio_count", e);
        } catch(Exception ex) {
            Log.e("SQL_eror", "other exception in dbio_count", ex);
        } finally {
            if (c != null) {
                c.close();
            }
        }

        return monitor;
    }

    public List<C_Monitor> getAllMonitors(String query){
        List<C_Monitor> allMonitors = new ArrayList<C_Monitor>();
//        String selectQuery = "SELECT  * FROM "+ TABLE_MONITOR;
        String selectQuery = query;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        try
        {
            c = db.rawQuery(selectQuery, null);
            if (c != null && c.getCount() > 0)
            {
                c.moveToFirst();
                if (c.moveToFirst()) {
                    do {
                        C_Monitor monitor = new C_Monitor();
                        monitor.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                        monitor.setBrand(c.getString(c.getColumnIndex(KEY_BRAND)));
                        monitor.setCode(c.getString(c.getColumnIndex(KEY_CODE)));
                        monitor.setSize(c.getString(c.getColumnIndex(KEY_WIDE)));
                        monitor.setResX(c.getString(c.getColumnIndex(KEY_RES_X)));
                        monitor.setResY(c.getString(c.getColumnIndex(KEY_RES_Y)));
                        monitor.setPrice(c.getLong(c.getColumnIndex(KEY_PRICE)));
                        monitor.setPerformance(c.getDouble(c.getColumnIndex(KEY_PERFORMANCE)));
                        allMonitors.add(monitor);
                    } while (c.moveToNext());
                }
            }
        } catch (SQLException e) {
            Log.e("SQL_eror", "sql exception in dbio_count", e);
        } catch(Exception ex) {
            Log.e("SQL_eror", "other exception in dbio_count", ex);
        } finally {
            if (c != null) {
                c.close();
            }
        }
        closeDB();
        return allMonitors;
    }

    public long createCasing(C_Casing casing) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_BRAND, casing.getBrand());
        values.put(KEY_CODE, casing.getCode());
        values.put(KEY_FORM, casing.getForm());
        values.put(KEY_PRICE, casing.getPrice());
        values.put(KEY_PERFORMANCE, casing.getPerformance());
        long casing_id = db.insert(TABLE_CASING, null, values);
        return casing_id;
    }

    public C_Casing getCasing(String query){
        SQLiteDatabase db = this.getReadableDatabase();
//        String selectQuery = "SELECT  * FROM " + TABLE_CASING + " WHERE "
//                + KEY_ID + " = " + casing_id;
        String selectQuery = query;
        C_Casing casing = new C_Casing();
        Cursor c = null;
        try
        {
            c = db.rawQuery(selectQuery, null);
            if (c != null && c.getCount() > 0)
            {
                c.moveToFirst();
                casing.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                casing.setBrand(c.getString(c.getColumnIndex(KEY_BRAND)));
                casing.setCode(c.getString(c.getColumnIndex(KEY_CODE)));
                casing.setForm(c.getString(c.getColumnIndex(KEY_FORM)));
                casing.setPrice(c.getLong(c.getColumnIndex(KEY_PRICE)));
                casing.setPerformance(c.getDouble(c.getColumnIndex(KEY_PERFORMANCE)));
            }
        } catch (SQLException e) {
            Log.e("SQL_eror", "sql exception in dbio_count", e);
        } catch(Exception ex) {
            Log.e("SQL_eror", "other exception in dbio_count", ex);
        } finally {
            if (c != null) {
                c.close();
            }
        }

        return casing;
    }

    public List<C_Casing> getAllCasings(String query){
        List<C_Casing> allCasings = new ArrayList<C_Casing>();
//        String selectQuery = "SELECT  * FROM "+ TABLE_CASING;
        String selectQuery = query;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        try
        {
            c = db.rawQuery(selectQuery, null);
            if (c != null && c.getCount() > 0)
            {
                c.moveToFirst();
                if (c.moveToFirst()) {
                    do {
                        C_Casing casing = new C_Casing();
                        casing.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                        casing.setBrand(c.getString(c.getColumnIndex(KEY_BRAND)));
                        casing.setCode(c.getString(c.getColumnIndex(KEY_CODE)));
                        casing.setForm(c.getString(c.getColumnIndex(KEY_FORM)));
                        casing.setPrice(c.getLong(c.getColumnIndex(KEY_PRICE)));
                        casing.setPerformance(c.getDouble(c.getColumnIndex(KEY_PERFORMANCE)));
                        allCasings.add(casing);
                    } while (c.moveToNext());
                }
            }
        } catch (SQLException e) {
            Log.e("SQL_eror", "sql exception in dbio_count", e);
        } catch(Exception ex) {
            Log.e("SQL_eror", "other exception in dbio_count", ex);
        } finally {
            if (c != null) {
                c.close();
            }
        }
        closeDB();
        return allCasings;
    }

    public long createMouse(C_Mouse mouse) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_BRAND, mouse.getBrand());
        values.put(KEY_CODE, mouse.getCode());
        values.put(KEY_PRICE, mouse.getPrice());
        values.put(KEY_PERFORMANCE, mouse.getPerformance());
        long mouse_id = db.insert(TABLE_MOUSE, null, values);
        return mouse_id;
    }

    public C_Mouse getMouse(String query){
        SQLiteDatabase db = this.getReadableDatabase();
//        String selectQuery = "SELECT  * FROM " + TABLE_MOUSE + " WHERE "
//                + KEY_ID + " = " + mouse_id;
        String selectQuery = query;
        C_Mouse mouse = new C_Mouse();
        Cursor c = null;
        try
        {
            c = db.rawQuery(selectQuery, null);
            if (c != null && c.getCount() > 0)
            {
                c.moveToFirst();
                mouse.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                mouse.setBrand(c.getString(c.getColumnIndex(KEY_BRAND)));
                mouse.setCode(c.getString(c.getColumnIndex(KEY_CODE)));
                mouse.setPrice(c.getLong(c.getColumnIndex(KEY_PRICE)));
                mouse.setPerformance(c.getDouble(c.getColumnIndex(KEY_PERFORMANCE)));
            }
        } catch (SQLException e) {
            Log.e("SQL_eror", "sql exception in dbio_count", e);
        } catch(Exception ex) {
            Log.e("SQL_eror", "other exception in dbio_count", ex);
        } finally {
            if (c != null) {
                c.close();
            }
        }

        return mouse;
    }

    public List<C_Mouse> getAllMouses(String query){
        List<C_Mouse> allMouses = new ArrayList<C_Mouse>();
//        String selectQuery = "SELECT  * FROM "+ TABLE_MOUSE;
        String selectQuery = query;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        try
        {
            c = db.rawQuery(selectQuery, null);
            if (c != null && c.getCount() > 0)
            {
                c.moveToFirst();
                if (c.moveToFirst()) {
                    do {
                        C_Mouse mouse = new C_Mouse();
                        mouse.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                        mouse.setBrand(c.getString(c.getColumnIndex(KEY_BRAND)));
                        mouse.setCode(c.getString(c.getColumnIndex(KEY_CODE)));
                        mouse.setPrice(c.getLong(c.getColumnIndex(KEY_PRICE)));
                        mouse.setPerformance(c.getDouble(c.getColumnIndex(KEY_PERFORMANCE)));
                        allMouses.add(mouse);
                    } while (c.moveToNext());
                }
            }
        } catch (SQLException e) {
            Log.e("SQL_eror", "sql exception in dbio_count", e);
        } catch(Exception ex) {
            Log.e("SQL_eror", "other exception in dbio_count", ex);
        } finally {
            if (c != null) {
                c.close();
            }
        }
        closeDB();
        return allMouses;
    }

    public long createKeyboard(C_Keyboard keyboard) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_BRAND, keyboard.getBrand());
        values.put(KEY_CODE, keyboard.getCode());
        values.put(KEY_PRICE, keyboard.getPrice());
        values.put(KEY_PERFORMANCE, keyboard.getPerformance());
        long keyboard_id = db.insert(TABLE_KEYBOARD, null, values);
        return keyboard_id;
    }

    public C_Keyboard getKeyboard(String query){
        SQLiteDatabase db = this.getReadableDatabase();
//        String selectQuery = "SELECT  * FROM " + TABLE_KEYBOARD + " WHERE "
//                + KEY_ID + " = " + keyboard_id;
        String selectQuery = query;
        C_Keyboard keyboard = new C_Keyboard();
        Cursor c = null;
        try
        {
            c = db.rawQuery(selectQuery, null);
            if (c != null && c.getCount() > 0)
            {
                c.moveToFirst();
                keyboard.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                keyboard.setBrand(c.getString(c.getColumnIndex(KEY_BRAND)));
                keyboard.setCode(c.getString(c.getColumnIndex(KEY_CODE)));
                keyboard.setPrice(c.getLong(c.getColumnIndex(KEY_PRICE)));
                keyboard.setPerformance(c.getDouble(c.getColumnIndex(KEY_PERFORMANCE)));
            }
        } catch (SQLException e) {
            Log.e("SQL_eror", "sql exception in dbio_count", e);
        } catch(Exception ex) {
            Log.e("SQL_eror", "other exception in dbio_count", ex);
        } finally {
            if (c != null) {
                c.close();
            }
        }

        return keyboard;
    }

    public List<C_Keyboard> getAllKeyboards(String query){
        List<C_Keyboard> allKeyboards = new ArrayList<C_Keyboard>();
//
        String selectQuery = query;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        try
        {
            c = db.rawQuery(selectQuery, null);
            if (c != null && c.getCount() > 0)
            {
                c.moveToFirst();
                if (c.moveToFirst()) {
                    do {
                        C_Keyboard keyboard = new C_Keyboard();
                        keyboard.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                        keyboard.setBrand(c.getString(c.getColumnIndex(KEY_BRAND)));
                        keyboard.setCode(c.getString(c.getColumnIndex(KEY_CODE)));
                        keyboard.setPrice(c.getLong(c.getColumnIndex(KEY_PRICE)));
                        keyboard.setPerformance(c.getDouble(c.getColumnIndex(KEY_PERFORMANCE)));
                        allKeyboards.add(keyboard);
                    } while (c.moveToNext());
                }
            }
        } catch (SQLException e) {
            Log.e("SQL_eror", "sql exception in dbio_count", e);
        } catch(Exception ex) {
            Log.e("SQL_eror", "other exception in dbio_count", ex);
        } finally {
            if (c != null) {
                c.close();
            }
        }
        closeDB();
        return allKeyboards;
    }


}
