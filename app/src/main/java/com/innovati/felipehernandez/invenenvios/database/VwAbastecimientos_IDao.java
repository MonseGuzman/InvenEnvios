package com.innovati.felipehernandez.invenenvios.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "VW_ABASTECIMIENTOS__I".
*/
public class VwAbastecimientos_IDao extends AbstractDao<VwAbastecimientos_I, Long> {

    public static final String TABLENAME = "VW_ABASTECIMIENTOS__I";

    /**
     * Properties of entity VwAbastecimientos_I.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Nombre = new Property(1, String.class, "nombre", false, "NOMBRE");
        public final static Property Cantidad = new Property(2, float.class, "cantidad", false, "CANTIDAD");
        public final static Property UnidadPrimaria = new Property(3, String.class, "unidadPrimaria", false, "UNIDAD_PRIMARIA");
        public final static Property Estatus = new Property(4, short.class, "estatus", false, "ESTATUS");
    }


    public VwAbastecimientos_IDao(DaoConfig config) {
        super(config);
    }
    
    public VwAbastecimientos_IDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"VW_ABASTECIMIENTOS__I\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"NOMBRE\" TEXT," + // 1: nombre
                "\"CANTIDAD\" REAL NOT NULL ," + // 2: cantidad
                "\"UNIDAD_PRIMARIA\" TEXT," + // 3: unidadPrimaria
                "\"ESTATUS\" INTEGER NOT NULL );"); // 4: estatus
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"VW_ABASTECIMIENTOS__I\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, VwAbastecimientos_I entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String nombre = entity.getNombre();
        if (nombre != null) {
            stmt.bindString(2, nombre);
        }
        stmt.bindDouble(3, entity.getCantidad());
 
        String unidadPrimaria = entity.getUnidadPrimaria();
        if (unidadPrimaria != null) {
            stmt.bindString(4, unidadPrimaria);
        }
        stmt.bindLong(5, entity.getEstatus());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, VwAbastecimientos_I entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String nombre = entity.getNombre();
        if (nombre != null) {
            stmt.bindString(2, nombre);
        }
        stmt.bindDouble(3, entity.getCantidad());
 
        String unidadPrimaria = entity.getUnidadPrimaria();
        if (unidadPrimaria != null) {
            stmt.bindString(4, unidadPrimaria);
        }
        stmt.bindLong(5, entity.getEstatus());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public VwAbastecimientos_I readEntity(Cursor cursor, int offset) {
        VwAbastecimientos_I entity = new VwAbastecimientos_I( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // nombre
            cursor.getFloat(offset + 2), // cantidad
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // unidadPrimaria
            cursor.getShort(offset + 4) // estatus
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, VwAbastecimientos_I entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setNombre(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCantidad(cursor.getFloat(offset + 2));
        entity.setUnidadPrimaria(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setEstatus(cursor.getShort(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(VwAbastecimientos_I entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(VwAbastecimientos_I entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(VwAbastecimientos_I entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
