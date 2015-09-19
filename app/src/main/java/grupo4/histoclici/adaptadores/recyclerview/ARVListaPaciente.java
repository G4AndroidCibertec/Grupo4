package grupo4.histoclici.adaptadores.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import grupo4.histoclici.R;
import grupo4.histoclici.dao.PacienteDAO;
import grupo4.histoclici.entidad.Paciente;

/**
 * Created by pedro_jx on 18/09/2015.
 */
public class ARVListaPaciente extends RecyclerView.Adapter<ARVListaPaciente.ARVListaPacienteHolder> {

    private ArrayList<Paciente> alPaciente;
    ARVListaPacienteListener iARVListaPacienteListener;

    public interface ARVListaPacienteListener{
        void ieditarPaciente(Paciente paciente);
    }

    public ARVListaPaciente(ARVListaPacienteListener parametroARVListaCitaListener) {
        iARVListaPacienteListener = parametroARVListaCitaListener;
        listarPaciente();
    }

    static class ARVListaPacienteHolder extends RecyclerView.ViewHolder {
        TextView itvIdPaciente, itvPaciente, itvGenero,itvTelefono, itvCelular, itvDomicilio, itvLatitud, itvAltitud;

        public ARVListaPacienteHolder(View itemView) {
            super(itemView);
            itvIdPaciente = (TextView)itemView.findViewById(R.id.itvIdPaciente);
            itvPaciente = (TextView)itemView.findViewById(R.id.itvPaciente);
            itvGenero = (TextView)itemView.findViewById(R.id.itvGenero);
            itvTelefono = (TextView)itemView.findViewById(R.id.itvTelefono);
            itvCelular = (TextView)itemView.findViewById(R.id.itvCelular);
            itvDomicilio = (TextView)itemView.findViewById(R.id.itvDomicilio);
            itvLatitud = (TextView)itemView.findViewById(R.id.itvLatitud);
            itvAltitud = (TextView)itemView.findViewById(R.id.itvAltitud);
        }
    }

    @Override
    public ARVListaPacienteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ARVListaPacienteHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_paciente, parent, false));
    }

    @Override
    public void onBindViewHolder(ARVListaPacienteHolder holder, int position) {
        Paciente paciente = alPaciente.get(position);
        holder.itvIdPaciente.setText(String.valueOf(paciente.getidPaciente()));
        holder.itvPaciente.setText(paciente.getPaciente());
        holder.itvGenero.setText(paciente.getGenero());
        holder.itvTelefono.setText(paciente.getTelefono());
        holder.itvCelular.setText(paciente.getCelular());
        holder.itvDomicilio.setText(paciente.getDomicilio());
        holder.itvLatitud.setText(paciente.getLatitud());
        holder.itvAltitud.setText(paciente.getAltitud());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(editarPaciente);
    }

    @Override
    public int getItemCount() {
        return alPaciente.size();
    }

    View.OnClickListener editarPaciente = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(iARVListaPacienteListener != null)
                iARVListaPacienteListener.ieditarPaciente(alPaciente.get((int)v.getTag()));
        }
    };

    public void listarPaciente(){
        alPaciente = new ArrayList<>();
        alPaciente.addAll(new PacienteDAO().listarPacientes());
        notifyDataSetChanged();
    }
}
