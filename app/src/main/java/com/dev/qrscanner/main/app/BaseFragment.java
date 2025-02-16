package com.dev.qrscanner.main.app;

import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BaseFragment extends Fragment {

    public void navigate(int destination){
       try {
           View v = getView();
           if(v != null){
               NavController navController = Navigation.findNavController(getView());
               navController.navigate(destination);
           }
       }catch (Exception ignored){

        }
    }

    public void showToast(String message){
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();

    }
}
