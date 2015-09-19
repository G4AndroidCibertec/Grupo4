package grupo4.histoclici.dao;

import android.database.Cursor;

import java.util.ArrayList;

import grupo4.histoclici.entidad.Atencion;

/**
 * Created by pedro_jx on 18/09/2015.
 */
public class AtencionDAO {



    public ArrayList<Atencion> listarAtencionXPaciente(int IdPaciente){
        Cursor cursor = null;
        ArrayList<Atencion> alAtencion = new ArrayList<>();

        try {
            cursor = DataBaseHelper.myDataBase.query("Atencion", new String[]{"IdAtencion", "FechaAtencion", "Motivo", "Tratamiento"}, "IdPaciente = ?", new String[]{String.valueOf(IdPaciente)}, null, null, "substr(FechaAtencion,7) desc, substr(FechaAtencion, 4, 2) desc, substr(FechaAtencion, 1, 2) desc");
            if(cursor.moveToFirst()){
                do{
                    Atencion atencion = new Atencion();
                    atencion.setIdAtencion(cursor.isNull(cursor.getColumnIndex("IdAtencion")) ? 0 : cursor.getInt(cursor.getColumnIndex("IdAtencion")));
                    atencion.setIdPaciente(IdPaciente);
                    atencion.setFechaAtencion(cursor.isNull(cursor.getColumnIndex("FechaAtencion")) ? "" : cursor.getString(cursor.getColumnIndex("FechaAtencion")));
                    atencion.setMotivo(cursor.isNull(cursor.getColumnIndex("Motivo")) ? "" : cursor.getString(cursor.getColumnIndex("Motivo")));
                    atencion.setTratamiento(cursor.isNull(cursor.getColumnIndex("Tratamiento")) ? "" : cursor.getString(cursor.getColumnIndex("Tratamiento")));
                    alAtencion.add(atencion);
                }
                while (cursor.moveToNext());
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            if(cursor != null)
                cursor.close();
        }
        return alAtencion;
    }

}
