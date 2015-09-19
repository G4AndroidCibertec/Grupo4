package grupo4.histoclici;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import grupo4.histoclici.adaptadores.spinner.ASPaciente;
import grupo4.histoclici.dao.CitaDAO;
import grupo4.histoclici.entidad.Cita;
import grupo4.histoclici.entidad.Paciente;


public class ActivityCita extends AppCompatActivity {

    private ASPaciente asPaciente;

    private Spinner sPaciente;
    private EditText etFechaCita;
    private TimePicker tpInicio, tpFin;
    private CheckBox chDomicilio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cita);

        sPaciente = (Spinner) findViewById(R.id.sPaciente);
        etFechaCita = (EditText) findViewById(R.id.etFechaCita);
        tpInicio = (TimePicker) findViewById(R.id.tpInicio);
        tpFin = (TimePicker) findViewById(R.id.tpFin);
        chDomicilio = (CheckBox) findViewById(R.id.chDomicilio);

        asPaciente = new ASPaciente(ActivityCita.this, new CitaDAO().listarPaciente());
        sPaciente.setAdapter(asPaciente);
        etFechaCita.setText(getIntent().getStringExtra(ActivityListaCita.ARG_FECHA));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cita, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.a_GuardaCita) {
            if (etFechaCita.getText().toString().trim().length() < 10) {
                etFechaCita.getText().clear();
                etFechaCita.setHint(R.string.error_fecha);
                return false;
            }

            if(tpInicio.getCurrentHour() > tpFin.getCurrentHour()) {
                Toast.makeText(ActivityCita.this, R.string.validaHoraFin, Toast.LENGTH_SHORT).show();
                return false;
            }else if(tpInicio.getCurrentHour() == tpFin.getCurrentHour() && tpInicio.getCurrentMinute() >= tpFin.getCurrentMinute()){
                Toast.makeText(ActivityCita.this, R.string.validaHoraFin, Toast.LENGTH_SHORT).show();
                return false;
            }

            Cita cita = new Cita();
            cita.setIdPaciente(((Paciente) sPaciente.getSelectedItem()).getidPaciente());
            cita.setFechaCita(etFechaCita.getText().toString().trim());
            cita.setInicio(String.format("%02d:%02d",tpInicio.getCurrentHour(),tpInicio.getCurrentMinute()));
            cita.setFin(String.format("%02d:%02d",tpFin.getCurrentHour(),tpFin.getCurrentMinute()));
            if (!chDomicilio.isChecked())
                cita.setPregunta("N");
            else
                cita.setPregunta("D");

            new CitaDAO().insertarCita(cita);
            Intent intent = new Intent();
            intent.putExtra(ActivityListaCita.ARG_FECHA, getIntent().getStringExtra(ActivityListaCita.ARG_FECHA));
            setResult(RESULT_OK, intent);
        }
        finish();
        return true;
    }


}