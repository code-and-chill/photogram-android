package me.example.raidnav.instagram.activities.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import me.example.raidnav.instagram.R;

public class CameraFragment extends Fragment {

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_camera, container, false);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    ImageView camera = Objects.requireNonNull(getView()).findViewById(R.id.launch_camera);
//    ImageView close = getView().findViewById(R.id.photo_close);

    StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());

    camera.setOnClickListener(v -> {
    }); // TODO: add action
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
  }
}
