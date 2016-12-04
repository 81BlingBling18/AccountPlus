package com.happycoding.uniquehust.accountplus.details;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.happycoding.uniquehust.accountplus.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OutcomeGraphFragment extends Fragment {

    public OutcomeGraphFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.graph_fragment_outcome, container, false);
        return view;
    }

}
