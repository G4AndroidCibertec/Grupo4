package grupo4.histoclici;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import grupo4.histoclici.adaptadores.recyclerview.ARVListaPaciente;
import grupo4.histoclici.entidad.Paciente;

public class ActivityListaPaciente extends AppCompatActivity implements ARVListaPaciente.ARVListaPacienteListener{
    static final String ARG_PACIENTE = "ARG_PACIENTE";
    static final int REQUEST_CODE_EDITAR_PACIENTE = 1;
    private RecyclerView rvPaciente;
    private ARVListaPaciente arvListaPaciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_paciente);
        rvPaciente = (RecyclerView)findViewById(R.id.rvPaciente);
        rvPaciente.setHasFixedSize(true);
        rvPaciente.setLayoutManager(new LinearLayoutManager(ActivityListaPaciente.this));
        arvListaPaciente = new ARVListaPaciente(ActivityListaPaciente.this);
        rvPaciente.setAdapter(arvListaPaciente);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_paciente, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.a_CancelarListaPaciente) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void ieditarPaciente(Paciente paciente) {
        Intent intent = new Intent(ActivityListaPaciente.this, ActivityPaciente.class);
        intent.putExtra(ARG_PACIENTE, paciente);
        startActivityForResult(intent, REQUEST_CODE_EDITAR_PACIENTE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_EDITAR_PACIENTE && resultCode == RESULT_OK){
            arvListaPaciente.listarPaciente();
            Toast.makeText(ActivityListaPaciente.this, R.string.ok_actualizar_paciente, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(ActivityListaPaciente.this, R.string.calcel_actualizar_paciente, Toast.LENGTH_SHORT).show();
        }
    }
}