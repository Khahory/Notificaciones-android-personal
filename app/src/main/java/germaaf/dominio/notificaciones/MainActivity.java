package germaaf.dominio.notificaciones;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //variables
    private PendingIntent pendingIntent;
    private final static String CHANNEL_ID = "NOTIFICACION";
    private final static int NOTIFICACION_ID = 0;

    Button boton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boton = findViewById(R.id.btn_id);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrearNotificacionChannel();
                MostrarNotificacion(true, 1);
            }
        });
    }

    //para android 7 menor, este metodo se pone arriba de MostrarNotificacion
    public void CrearNotificacionChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Notificacion name";
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    //para android 7 adelante
    public void MostrarNotificacion(Boolean estado, int cancelar){
        //hora del evento
        long hora = System.currentTimeMillis();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setContentTitle("Titulo");
        builder.setContentText("Cuerpo");
        builder.setWhen(hora);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        //para poner la notificacion permanente
        builder.setOngoing(estado);

        //construir la notificacion
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICACION_ID, builder.build());

        //para quitar la notificacion
        notificationManagerCompat.cancel(cancelar);



    }

    //eliminar notificacion
    public void Eliminar(View view){
        MostrarNotificacion(false, 0);
    }
}
