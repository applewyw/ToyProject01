package com.example.toyproject01;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int INCHEON = 101;
    public static final int SEOUL = 101;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        /*
        Button button = (Button) findViewById(R.id.button01);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 위치 정보 확인을 위해 정의한 메소드 호출
                startLocationService();
            }
        });
        */
    }

    public void onButton01Clicked(View v) {
        Intent intent = new Intent(getApplicationContext(), IncheonActivity.class);
        startActivityForResult(intent, INCHEON);
    }

    public void onButton02Clicked(View v) {
        Intent intent = new Intent(getApplicationContext(), SeoulActivity.class);
        startActivityForResult(intent, SEOUL);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    // 위치 정보 확인을 위해 정의한 메소드
    private void startLocationService() {
        // 위치 관리자 객체 참조
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // 위치 정보를 받을 리스너 생성
        GPSListener gpsListener = new GPSListener();
        long minTime = 10000;   // 10초
        float minDistance = 0;

        try {
            // GPS 이용한 위치 요청
            manager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    minTime,
                    minDistance,
                    gpsListener
            );

            // 네트워크를 이용한 위치 요청
            manager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    minTime,
                    minDistance,
                    gpsListener
            );

            // 위치 확인이 안되는 경우에도 최근에 확인된 위치 정보 먼저 확인
            Location lastLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastLocation != null) {
                Double latitude = lastLocation.getLatitude();
                Double longitude = lastLocation.getLongitude();

                textView.setText("내 위치 : " + latitude + ", " + longitude);
                Toast.makeText(getApplicationContext(), "Last Known Location : Latitude : " + latitude + "\nLongitude : " + longitude, Toast.LENGTH_LONG).show();
            }
        } catch(SecurityException ex) {
            ex.printStackTrace();
        }

        Toast.makeText(getApplicationContext(), "위치 확인이 시작되었습니다. 로그를 확인하세요.", Toast.LENGTH_SHORT).show();
    }

    // 리스너 클래스 정의
    private class GPSListener implements LocationListener {
        // 위치 정보가 확인될 때 자동 호출되는 메소드
        public void onLocationChanged(Location location) {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();

            String msg = "Latitude : " + latitude + "\nLongitude : " + longitude;
            Log.i("GPSListener", msg);

            textView.setText("내 위치 : " + latitude + ", " + longitude);
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        }

        public void onProviderDisabled(String provider) {

        }

        public void onProviderEnabled(String provider) {

        }

        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return super.findViewById(id);
    }
}
