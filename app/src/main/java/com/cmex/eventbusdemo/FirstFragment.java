package com.cmex.eventbusdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.cmex.eventbusdemo.databinding.FragmentFirstBinding;
import com.cmex.myeventbus.HanEventBus;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });


        HanEventBus.getDefault().register(this);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e("EventBus", this.getClass().getSimpleName() + " hide = " + hidden);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("EventBus", this.getClass().getSimpleName() + " show");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        HanEventBus.getDefault().unRegister(this);
        Log.e("EventBus", this.getClass().getSimpleName() + " paused");
    }

    public void OnBusEvent(Object data){
        Log.e("hanhan", this.getClass().getSimpleName() + " : OnBusEvent data = " + data.toString());
    }

}