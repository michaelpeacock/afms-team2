package com.example.omegasupreme.sampleuilayout;

/**
 * Created by OmegaSupreme on 12/18/2015.
 */
    import android.app.Fragment;
    import android.graphics.Color;
    import android.os.Bundle;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.support.v4.app.FragmentActivity;
    import android.os.Bundle;

    import com.google.android.gms.maps.CameraUpdateFactory;
    import com.google.android.gms.maps.GoogleMap;
    import com.google.android.gms.maps.MapFragment;
    import com.google.android.gms.maps.OnMapReadyCallback;
    //import com.google.android.gms.maps.SupportMapFragment;
    import com.google.android.gms.maps.model.BitmapDescriptorFactory;
    import com.google.android.gms.maps.model.LatLng;

    import com.google.android.gms.maps.model.MarkerOptions;
    import com.google.android.gms.maps.model.TileOverlayOptions;
    import com.google.maps.android.heatmaps.Gradient;
    import com.google.maps.android.heatmaps.HeatmapTileProvider;
    import com.google.maps.android.heatmaps.WeightedLatLng;
    import com.google.maps.android.kml.KmlContainer;
    import com.google.maps.android.kml.KmlLayer;
    import com.google.maps.android.kml.KmlPlacemark;
    import com.google.maps.android.kml.KmlPoint;


    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.List;
    import java.util.Vector;

public class Fragment1 extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment1,container, false);
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment1);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.



        MapFragment mapFragment = (MapFragment) this.getFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        return view;
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        configureInitialMapState(mMap);

        // Add a marker in Sydney and move the camera
        LatLng alaska = new LatLng(63, -151);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(alaska));
    }

    public void configureInitialMapState(GoogleMap googleMap)
    {
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        try {

            KmlLayer layer = new KmlLayer(googleMap, R.raw.kmlfile, getActivity());

//            KmlLayer three_hour_analysis = new KmlLayer(googleMap, R.raw.threehouranalysis, getActivity());
//            three_hour_analysis.addLayerToMap();

            addHeatMap(googleMap);

            //TEST
            /*KmlUtil kmlutil = new KmlUtil();
            Kml my_file = kmlutil.convertToKMLStream(0.1275, 51.5072, "Not London", "Test,Test,Test,Test");
            String s = kmlutil.toString(my_file);
            InputStream is = kmlutil.toInputStream(s);
            KmlLayer layer = new KmlLayer(googleMap, is, getApplicationContext());
            layer.addLayerToMap();*/

            //layer.addLayerToMap();
            for (KmlContainer container : layer.getContainers())
            {
                for(KmlPlacemark placemark : container.getPlacemarks()) {


                    KmlPoint point = (KmlPoint)(placemark.getGeometry());
                    LatLng latlng = point.getGeometryObject();


                    if (placemark.hasProperty("name") &&
                            placemark.hasProperty("description")){
                        String location_name = placemark.getProperty("name");
                        String information = placemark.getProperty("description");

                        List<String> items = Arrays.asList(information.split(","));
                        String parsed_information = "";
                        for (String s : items)
                        {
                            parsed_information = parsed_information + s;
                            parsed_information = parsed_information + "\n";
                        }

                        googleMap.addMarker(new MarkerOptions().position(latlng).
                                title(location_name).
                                snippet(parsed_information).
                                icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                    }
                }

            }
        }
        catch (org.xmlpull.v1.XmlPullParserException e)
        {
        }
        catch (java.io.IOException e)
        {
        }

        Measurements measurement = new Measurements(1, 10.0, 10.2, 32.2, 10.0,
                11.2, 12.2, 23.2, 4.4,
                3,2, 1000, 2.3, 12.4,
                4.5, 11.2, 43.2, 10.4,
                "test", 10.2, 22.2, 23.2,
                "test", 43.3, 45.2,
                9.2, 23.2, 45.4,
                32.2, 11.0, 61.22, 149.85);

        Vector<LatLng> coordinates = new Vector<LatLng>();

        for (int i = 0; i < 10; i++) {
            LatLng latlng = new LatLng(measurement.getLatitude(), measurement.getLongitude());
            coordinates.add(latlng);
            String name = "Recorded at " + measurement.getLatitude()+10 + " Lat, " + measurement.getLongitude()+10 + " Lon";
            String description = "Temperature: " + measurement.getTemperature() + "\nWind Speed" + measurement.getWind_speed() +
                    "\nBarometric Pressure:" + measurement.getBarometric_pressure();

            googleMap.addMarker(new MarkerOptions().position(latlng).title(name).snippet(description));
        }

        googleMap.setInfoWindowAdapter(new PopupAdapter(LayoutInflater.from(getActivity())));
    }


    private void addHeatMap(GoogleMap googleMap) {
        List<WeightedLatLng> list = null;

        list = readItems();

        int[] colors = {
                Color.rgb (0, 0, 255), //blue
                Color.rgb(34,139, 34), //green
                Color.rgb(255, 255, 0), //yellow
                Color.rgb(255,140,0), //orange
                Color.rgb(255, 0, 0) //red
        };

        float[] startPoints = {
                0.6f, 0.7f, 0.8f, 0.9f, 1f
        };

        Gradient gradient = new Gradient(colors, startPoints);

        HeatmapTileProvider provider = new HeatmapTileProvider.Builder().weightedData(list).gradient(gradient).build(
        );
        googleMap.addTileOverlay(new TileOverlayOptions().tileProvider(provider));
    }

    /**
     * Read the data (locations of police stations) from raw resources.
     *
     *
     */


    private ArrayList<WeightedLatLng> readItems() {

        ArrayList latlon = new ArrayList<LatLng>();

        for (int i = 0; i < 10; i++)
        {


            Measurements measurement = new Measurements(1, 10.0, 10.2, 32.2, 10.0,
                    11.2, 12.2, 23.2, 4.4,
                    3, 2, 1000, 2.3, 12.4,
                    4.5, 11.2, 43.2, 10.4,
                    "test", 10.2, 22.2, 23.2,
                    "test", 59-i, 45.2,
                    9.2, 23.2, 45.4,
                    32.2, 11.0, 61.14+(i*.02), -149.50+(i*.02));

            Measurements measurement2 = new Measurements(1, 10.0, 10.2, 32.2, 10.0,
                    11.2, 12.2, 23.2, 4.4,
                    3, 2, 1000, 2.3, 12.4,
                    4.5, 11.2, 43.2, 10.4,
                    "test", 10.2, 22.2, 23.2,
                    "test", 55-i, 45.2,
                    9.2, 23.2, 45.4,
                    32.2, 11.0, 61.22+(i*.02), -149.25+(i*0.02));

            Measurements measurement3 = new Measurements(1, 10.0, 10.2, 32.2, 10.0,
                    11.2, 12.2, 23.2, 4.4,
                    3, 2, 1000, 2.3, 12.4,
                    4.5, 11.2, 43.2, 10.4,
                    "test", 10.2, 22.2, 23.2,
                    "test", 48-i, 45.2,
                    9.2, 23.2, 45.4,
                    32.2, 11.0, 61.50+(i*0.02), -149.85+(i*0.02));

            Measurements measurement4 = new Measurements(1, 10.0, 10.2, 32.2, 10.0,
                    11.2, 12.2, 23.2, 4.4,
                    3, 2, 1000, 2.3, 12.4,
                    4.5, 11.2, 43.2, 10.4,
                    "test", 10.2, 22.2, 23.2,
                    "test", 52-i, 45.2,
                    9.2, 23.2, 45.4,
                    32.2, 11.0, 61.44+(i*0.02), -149.35+(i*0.02));



            int temperature1 = (int) measurement.getTemperature();

            int temperature2 = (int) measurement2.getTemperature();

            int temperature3 = (int) measurement3.getTemperature();

            int temperature4 = (int) measurement4.getTemperature();

            WeightedLatLng latlng1 = new WeightedLatLng(new LatLng(measurement.getLatitude(), measurement.getLongitude())
                    , measurement.getTemperature());
            WeightedLatLng latlng2 = new WeightedLatLng(new LatLng(measurement2.getLatitude(), measurement2.getLongitude(
            )), measurement2.getTemperature());
            WeightedLatLng latlng3 = new WeightedLatLng(new LatLng(measurement3.getLatitude(), measurement3.getLongitude(
            )), measurement3.getTemperature());
            WeightedLatLng latlng4 = new WeightedLatLng(new LatLng(measurement4.getLatitude(), measurement4.getLongitude(
            )), measurement4.getTemperature());

            latlon.add(latlng1);
            latlon.add(latlng2);
            latlon.add(latlng3);
            latlon.add(latlng4);
        }

        return latlon;

    }

}