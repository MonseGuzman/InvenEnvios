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
 * DAO for table "VW_CLIENTES".
*/
public class VwClientesDao extends AbstractDao<VwClientes, Long> {

    public static final String TABLENAME = "VW_CLIENTES";

    /**
     * Properties of entity VwClientes.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "Id", true, "_id");
        public final static Property Clave = new Property(1, String.class, "clave", false, "CLAVE");
        public final static Property Nombre = new Property(2, String.class, "nombre", false, "NOMBRE");
        public final static Property Rfc = new Property(3, String.class, "rfc", false, "RFC");
        public final static Property Calle = new Property(4, String.class, "calle", false, "CALLE");
        public final static Property NumeroExterior = new Property(5, String.class, "numeroExterior", false, "NUMERO_EXTERIOR");
        public final static Property NumeroInterior = new Property(6, String.class, "numeroInterior", false, "NUMERO_INTERIOR");
        public final static Property Colonia = new Property(7, String.class, "colonia", false, "COLONIA");
        public final static Property Telefono = new Property(8, String.class, "telefono", false, "TELEFONO");
    }


    public VwClientesDao(DaoConfig config) {
        super(config);
    }
    
    public VwClientesDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"VW_CLIENTES\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: Id
                "\"CLAVE\" TEXT," + // 1: clave
                "\"NOMBRE\" TEXT," + // 2: nombre
                "\"RFC\" TEXT," + // 3: rfc
                "\"CALLE\" TEXT," + // 4: calle
                "\"NUMERO_EXTERIOR\" TEXT," + // 5: numeroExterior
                "\"NUMERO_INTERIOR\" TEXT," + // 6: numeroInterior
                "\"COLONIA\" TEXT," + // 7: colonia
                "\"TELEFONO\" TEXT);"); // 8: telefono
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"VW_CLIENTES\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, VwClientes entity) {
        stmt.clearBindings();
 
        Long Id = entity.getId();
        if (Id != null) {
            stmt.bindLong(1, Id);
        }
 
        String clave = entity.getClave();
        if (clave != null) {
            stmt.bindString(2, clave);
        }
 
        String nombre = entity.getNombre();
        if (nombre != null) {
            stmt.bindString(3, nombre);
        }
 
        String rfc = entity.getRfc();
        if (rfc != null) {
            stmt.bindString(4, rfc);
        }
 
        String calle = entity.getCalle();
        if (calle != null) {
            stmt.bindString(5, calle);
        }
 
        String numeroExterior = entity.getNumeroExterior();
        if (numeroExterior != null) {
            stmt.bindString(6, numeroExterior);
        }
 
        String numeroInterior = entity.getNumeroInterior();
        if (numeroInterior != null) {
            stmt.bindString(7, numeroInterior);
        }
 
        String colonia = entity.getColonia();
        if (colonia != null) {
            stmt.bindString(8, colonia);
        }
 
        String telefono = entity.getTelefono();
        if (telefono != null) {
            stmt.bindString(9, telefono);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, VwClientes entity) {
        stmt.clearBindings();
 
        Long Id = entity.getId();
        if (Id != null) {
            stmt.bindLong(1, Id);
        }
 
        String clave = entity.getClave();
        if (clave != null) {
            stmt.bindString(2, clave);
        }
 
        String nombre = entity.getNombre();
        if (nombre != null) {
            stmt.bindString(3, nombre);
        }
 
        String rfc = entity.getRfc();
        if (rfc != null) {
            stmt.bindString(4, rfc);
        }
 
        String calle = entity.getCalle();
        if (calle != null) {
            stmt.bindString(5, calle);
        }
 
        String numeroExterior = entity.getNumeroExterior();
        if (numeroExterior != null) {
            stmt.bindString(6, numeroExterior);
        }
 
        String numeroInterior = entity.getNumeroInterior();
        if (numeroInterior != null) {
            stmt.bindString(7, numeroInterior);
        }
 
        String colonia = entity.getColonia();
        if (colonia != null) {
            stmt.bindString(8, colonia);
        }
 
        String telefono = entity.getTelefono();
        if (telefono != null) {
            stmt.bindString(9, telefono);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public VwClientes readEntity(Cursor cursor, int offset) {
        VwClientes entity = new VwClientes( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // Id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // clave
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // nombre
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // rfc
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // calle
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // numeroExterior
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // numeroInterior
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // colonia
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // telefono
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, VwClientes entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setClave(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setNombre(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setRfc(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setCalle(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setNumeroExterior(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setNumeroInterior(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setColonia(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setTelefono(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(VwClientes entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(VwClientes entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(VwClientes entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
