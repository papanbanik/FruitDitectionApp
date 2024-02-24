package com.example.myapplication3.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication3.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class AboutFragment extends Fragment implements OnMapReadyCallback {

    private VideoView videoview;
    private GoogleMap gMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);

        // Find the VideoView within the inflated layout
        videoview = rootView.findViewById(R.id.videoview);

        // Access package name through the hosting Activity
        String packageName = requireActivity().getPackageName();

        // Construct the URI for the video resource
        Uri uri= Uri.parse("android.resource://" + packageName + "/" + R.raw.myvideo);

        // Set the video URI and start playing
        videoview.setVideoURI(uri);
        MediaController mediaController=new MediaController(requireContext());
        videoview.setMediaController(mediaController);
        videoview.start();

        // Initialize MapView


        return rootView;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap = googleMap;

        LatLng chittagong = new LatLng(22.3569, 91.7832); // Chittagong's latitude and longitude
        gMap.addMarker(new MarkerOptions().position(chittagong).title("Chittagong"));
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(chittagong, 12)); // Zoom level adjusted to show Chittagong
    }


}
