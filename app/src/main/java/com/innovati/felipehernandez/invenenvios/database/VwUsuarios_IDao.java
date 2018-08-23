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
 * DAO for table "VW_USUARIOS__I".
*/
public class VwUsuarios_IDao extends AbstractDao<VwUsuarios_I, Long> {

    public static final String TABLENAME = "VW_USUARIOS__I";

    /**
     * Properties of entity VwUsuarios_I.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "Id", true, "_id");
        public final static Property IdUsuario = new Property(1, String.class, "idUsuario", false, "ID_USUARIO");
        public final static Property Clave = new Property(2, String.class, "clave", false, "CLAVE");
        public final static Property NickName = new Property(3, String.class, "nickName", false, "NICK_NAME");
        public final static Property Password = new Property(4, String.class, "password", false, "PASSWORD");
        public final static Property Status = new Property(5, short.class, "status", false, "STATUS");
        public final static Property FechaActualizacion = new Property(6, java.util.Date.class, "fechaActualizacion", false, "FECHA_ACTUALIZACION");
        public final static Property IdUsuarioActualizacion = new Property(7, String.class, "idUsuarioActualizacion", false, "ID_USUARIO_ACTUALIZACION");
        public final static Property Sucursal = new Property(8, String.class, "sucursal", false, "SUCURSAL");
    }


    public VwUsuarios_IDao(DaoConfig config) {
        super(config);
    }
    
    public VwUsuarios_IDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"VW_USUARIOS__I\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: Id
                "\"ID_USUARIO\" TEXT," + // 1: idUsuario
                "\"CLAVE\" TEXT," + // 2: clave
                "\"NICK_NAME\" TEXT," + // 3: nickName
                "\"PASSWORD\" TEXT," + // 4: password
                "\"STATUS\" INTEGER NOT NULL ," + // 5: status
                "\"FECHA_ACTUALIZACION\" INTEGER," + // 6: fechaActualizacion
                "\"ID_USUARIO_ACTUALIZACION\" TEXT," + // 7: idUsuarioActualizacion
                "\"SUCURSAL\" TEXT);"); // 8: sucursal
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"VW_USUARIOS__I\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, VwUsuarios_I entity) {
        stmt.clearBindings();
 
        Long Id = entity.getId();
        if (Id != null) {
            stmt.bindLong(1, Id);
        }
 
        String idUsuario = entity.getIdUsuario();
        if (idUsuario != null) {
            stmt.bindString(2, idUsuario);
        }
 
        String clave = entity.getClave();
        if (clave != null) {
            stmt.bindString(3, clave);
        }
 
        String nickName = entity.getNickName();
        if (nickName != null) {
            stmt.bindString(4, nickName);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(5, password);
        }
        stmt.bindLong(6, entity.getStatus());
 
        java.util.Date fechaActualizacion = entity.getFechaActualizacion();
        if (fechaActualizacion != null) {
            stmt.bindLong(7, fechaActualizacion.getTime());
        }
 
        String idUsuarioActualizacion = entity.getIdUsuarioActualizacion();
        if (idUsuarioActualizacion != null) {
            stmt.bindString(8, idUsuarioActualizacion);
        }
 
        String sucursal = entity.getSucursal();
        if (sucursal != null) {
            stmt.bindString(9, sucursal);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, VwUsuarios_I entity) {
        stmt.clearBindings();
 
        Long Id = entity.getId();
        if (Id != null) {
            stmt.bindLong(1, Id);
        }
 
        String idUsuario = entity.getIdUsuario();
        if (idUsuario != null) {
            stmt.bindString(2, idUsuario);
        }
 
        String clave = entity.getClave();
        if (clave != null) {
            stmt.bindString(3, clave);
        }
 
        String nickName = entity.getNickName();
        if (nickName != null) {
            stmt.bindString(4, nickName);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(5, password);
        }
        stmt.bindLong(6, entity.getStatus());
 
        java.util.Date fechaActualizacion = entity.getFechaActualizacion();
        if (fechaActualizacion != null) {
            stmt.bindLong(7, fechaActualizacion.getTime());
        }
 
        String idUsuarioActualizacion = entity.getIdUsuarioActualizacion();
        if (idUsuarioActualizacion != null) {
            stmt.bindString(8, idUsuarioActualizacion);
        }
 
        String sucursal = entity.getSucursal();
        if (sucursal != null) {
            stmt.bindString(9, sucursal);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public VwUsuarios_I readEntity(Cursor cursor, int offset) {
        VwUsuarios_I entity = new VwUsuarios_I( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // Id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // idUsuario
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // clave
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // nickName
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // password
            cursor.getShort(offset + 5), // status
            cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)), // fechaActualizacion
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // idUsuarioActualizacion
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // sucursal
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, VwUsuarios_I entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setIdUsuario(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setClave(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setNickName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPassword(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setStatus(cursor.getShort(offset + 5));
        entity.setFechaActualizacion(cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)));
        entity.setIdUsuarioActualizacion(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setSucursal(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(VwUsuarios_I entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(VwUsuarios_I entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(VwUsuarios_I entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
