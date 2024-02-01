package carlos.robert.ejercicio1sp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import carlos.robert.ejercicio1sp.configuracion.Constantes;
import carlos.robert.ejercicio1sp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sp = getSharedPreferences(Constantes.USUARIOS, MODE_PRIVATE);

        comprobarLogin();

        binding.btnLoginMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.txtUsuarioMain.getText().toString().equalsIgnoreCase("Progresa")
                        && binding.txtContrasenyaMain.getText().toString().equals("Alumno")) {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString(Constantes.USUARIO, binding.txtUsuarioMain.getText().toString());
                    editor.putString(Constantes.CONTRASENYA, binding.txtContrasenyaMain.getText().toString());
                    editor.apply();

                    startActivity(new Intent(MainActivity.this, SecondActivity.class));
                    binding.txtUsuarioMain.setText("");
                    binding.txtContrasenyaMain.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "DATOS INCORRECTOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void comprobarLogin() {
        /*if(sp.getString(Constantes.USUARIO, "").equalsIgnoreCase("Progresa")
        && sp.getString(Constantes.CONTRASENYA, "").equals("Alumno")){
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        }*/

        if(sp.contains(Constantes.USUARIO) && sp.contains(Constantes.CONTRASENYA)){
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        }
    }
}