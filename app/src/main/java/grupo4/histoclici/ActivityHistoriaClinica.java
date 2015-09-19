package grupo4.histoclici;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import grupo4.histoclici.adaptadores.recyclerview.ARVHistoriaClinica;

public class ActivityHistoriaClinica extends AppCompatActivity {

    private ARVHistoriaClinica arvHistoriaClinica;

    TextView tvPacienteHC, tvIdPacienteHC;
    RecyclerView rvHistoriaClinica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historia_clinica);

        tvPacienteHC = (TextView)findViewById(R.id.tvPacienteHC);
        tvIdPacienteHC = (TextView)findViewById(R.id.tvIdPacienteHC);
        rvHistoriaClinica = (RecyclerView)findViewById(R.id.rvHistoriaClinica);

        tvIdPacienteHC.setText(String.valueOf(getIntent().getIntExtra(ActivityListaCita.ARG_IDPACIENTE, 0)));
        tvPacienteHC.setText(getIntent().getStringExtra(ActivityListaCita.ARG_PACIENTE));

        rvHistoriaClinica.setHasFixedSize(true);
        rvHistoriaClinica.setLayoutManager(new LinearLayoutManager(ActivityHistoriaClinica.this));

        arvHistoriaClinica = new ARVHistoriaClinica(getIntent().getIntExtra(ActivityListaCita.ARG_IDPACIENTE, 0));
        rvHistoriaClinica.setAdapter(arvHistoriaClinica);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_historia_clinica, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.a_NuevaAtencion) {
            return true;
        }
        else if (item.getItemId() == R.id.a_CancelarHC){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
