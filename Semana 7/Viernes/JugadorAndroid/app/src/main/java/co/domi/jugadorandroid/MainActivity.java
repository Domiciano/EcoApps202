package co.domi.jugadorandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private TCPSingleton tcp;
    private Button enviarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enviarBtn = findViewById(R.id.enviarBtn);

        tcp = TCPSingleton.getInstance();
        tcp.setCliente(this);
        tcp.start();

        enviarBtn.setOnClickListener(this);

    }

    public void cuandoLlegueElmensaje(String mensaje){
        runOnUiThread(
                ()->{

                }
        );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.enviarBtn:
                tcp.enviar("Hola desde jugador Android");
                break;

        }
    }
}