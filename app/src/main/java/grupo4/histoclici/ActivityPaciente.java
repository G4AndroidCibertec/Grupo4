package grupo4.histoclici;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import grupo4.histoclici.dao.PacienteDAO;
import grupo4.histoclici.entidad.Paciente;

public class ActivityPaciente extends AppCompatActivity {
    TextView tvIdPaciente;
    EditText etPaciente, etTelefono, etCelular, etDomicilio;
    RadioButton rbF, rbM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paciente);
        tvIdPaciente = (TextView)findViewById(R.id.tvIdPaciente);
        etPaciente = (EditText)findViewById(R.id.etPaciente);
        rbF = (RadioButton)findViewById(R.id.rbF);
        rbM = (RadioButton)findViewById(R.id.rbM);
        etTelefono = (EditText)findViewById(R.id.etTelefono);
        etCelular = (EditText)findViewById(R.id.etCelular);
        etDomicilio = (EditText)findViewById(R.id.etDomicilio);

        if(getIntent().getExtras() != null){
            Paciente paciente = getIntent().getParcelableExtra(ActivityListaPaciente.ARG_PACIENTE);
            tvIdPaciente.setText(String.valueOf(paciente.getidPaciente()));
            etPaciente.setText(paciente.getPaciente());
            if(paciente.getGenero().equals("F"))
                rbF.setChecked(true);
            else if(paciente.getGenero().equals("M"))
                rbM.setChecked(true);
            etTelefono.setText(paciente.getTelefono());
            etCelular.setText(paciente.getCelular());
            etDomicilio.setText(paciente.getDomicilio());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_paciente, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.a_GuardarPaciente) {

            if(etPaciente.getText().toString().length() == 0) {
                etPaciente.getText().clear();
                etPaciente.setHint(R.string.error_paciente);
                return false;
            }
            if(!rbF.isChecked() && !rbM.isChecked()){
                Toast.makeText(ActivityPaciente.this, R.string.error_genero, Toast.LENGTH_SHORT).show();
                return false;
            }

            Paciente paciente = new Paciente();
            paciente.setPaciente(etPaciente.getText().toString().trim());
            if(rbF.isChecked())
                paciente.setGenero("F");
            else if(rbM.isChecked())
                paciente.setGenero("M");
            paciente.setTelefono(etTelefono.getText().toString().trim());
            paciente.setCelular(etCelular.getText().toString().trim());
            paciente.setDomicilio(etDomicilio.getText().toString().trim());

            if(getIntent().getExtras() == null) {
                new PacienteDAO().insertarPaciente(paciente);
                Toast.makeText(ActivityPaciente.this, R.string.ok_insertar_paciente, Toast.LENGTH_SHORT).show();
            }
            else{
                paciente.setidPaciente(Integer.parseInt(tvIdPaciente.getText().toString().trim()));
                new PacienteDAO().actualizarPaciente(paciente);
                Intent intent = new Intent(ActivityPaciente.this, ActivityListaCita.class);
                setResult(RESULT_OK, intent);
            }
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
