package com.example.project_map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    MarkerOptions options;
    GoogleMap mMap;

    int Mark_Img;
    Item item;
    ArrayList<Item> itemArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;


        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(false);
        Mark_Img = R.drawable.restaurant;

        itemArrayList = new ArrayList<>();

        readExcel();

        for(Item data : itemArrayList){
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(data.x, data.y), 15));

            options = new MarkerOptions();
            options.icon(BitmapDescriptorFactory.fromResource(Mark_Img));
            options.position(new LatLng(data.x, data.y));       //좌표
            options.title(data.getName());                      //음식점 이름
            options.snippet(data.getAddress_1());               //음식점 주소

            mMap.addMarker(options);

        }

    }
    public void readExcel(){//파일 읽기 함수
        try {
            InputStream is = getBaseContext().getResources().getAssets().open("data.xls");
            //엑셀파일
            Workbook wb = Workbook.getWorkbook(is);
            //엑셀 파일이 있다면
            if(wb != null){

                Sheet sheet = wb.getSheet(0);//시트 블러오기

                if(sheet != null){

                    int colTotal = sheet.getColumns(); //전체 컬럼
                    int rowIndexStart = 1; //row 인덱스 시작
                    int rowTotal = sheet.getColumn(colTotal-1).length;

                    for(int row = rowIndexStart; row < rowTotal; row++){
                        item = new Item(sheet.getCell(0, row).getContents(),
                                Double.parseDouble(sheet.getCell(1, row).getContents()),
                                Double.parseDouble(sheet.getCell(2, row).getContents()),
                                sheet.getCell(3, row).getContents(),
                                sheet.getCell(4, row).getContents(),
                                sheet.getCell(5, row).getContents(),
                                sheet.getCell(6, row).getContents());

                        itemArrayList.add(item);
                    }

                }
            }
        } catch (IOException | BiffException e) {
            e.printStackTrace();
        }
    }

}