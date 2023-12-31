package com.example.travel_logger;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.travel_logger.databinding.ActivityMapsBinding;
import com.example.travel_logger.databinding.FragmentMainPopupFragmentBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.example.travel_logger.databinding.ActivityMapsBinding;
import android.content.Intent;
import android.widget.EditText;
import android.provider.MediaStore;
import android.content.pm.PackageManager;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Environment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;




public class MainPopupFragment extends BottomSheetDialogFragment {

    private FragmentMainPopupFragmentBinding binding;
    private EditText editTextInput;
    private int REQUEST_IMAGE_CAPTURE = 1;
    private static final String IMAGE_FILE_NAME = "captured_image.jpg";

    public MainPopupFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMainPopupFragmentBinding.inflate(inflater, container, false);

        editTextInput = binding.editTextInput;
        View view = binding.getRoot();

        Button button1 = binding.button1;
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                raiseDiary();
            }
        });

        Button button2 = binding.button2;
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                raiseMusic();
            }
        });
        Button button3 = binding.button3;
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                raiseWeather();
            }
        });
        Button button4 = binding.button4;
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                raiseCamera();
            }
        });

        return view;
    }

    private void raiseDiary() {
        // fill in this
        editTextInput.setVisibility(View.VISIBLE);
    }
    private void raiseCamera() {
        // fill in this
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    private void raiseWeather() {
        // fill in this
    }
    private void raiseMusic() {
        // fill in this
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
            saveBitmapToFile(imageBitmap, IMAGE_FILE_NAME);
        }
    }

    private void saveBitmapToFile(Bitmap bitmap, String fileName) {
        File root = getContext().getExternalFilesDir(null);
        File file = new File(root, fileName);

        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}