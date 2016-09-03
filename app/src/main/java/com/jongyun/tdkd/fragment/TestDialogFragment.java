package com.jongyun.tdkd.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jongyun.tdkd.history.R;

public class TestDialogFragment extends DialogFragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.test_dialog_fragment, container);
        //fragment 안에서
        getDialog().getWindow().setBackgroundDrawable(ContextCompat.getDrawable(getDialog().getContext(),R.drawable.rectangle));
        return view;
    }

}