package com.happycoding.uniquehust.accountplus.details;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.happycoding.uniquehust.accountplus.R;
import com.happycoding.uniquehust.accountplus.global.TypeKeyValue;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IncomeTypeSelectFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link IncomeTypeSelectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IncomeTypeSelectFragment extends Fragment {

    public static class MessageEvent2 {
        public String type;
        public int drawableId;
        public boolean isIncome;
        public MessageEvent2(String type, int drawableId, boolean isIncome) {
            this.type = type;
            this.isIncome = isIncome;
            this.drawableId = drawableId;
        }
    }

    @OnClick({R.id.btn16, R.id.btn17, R.id.btn18})
    public void onTypeButtonClick(View v) {
        String type = ((Button)v).getText().toString();
        int id = 0;
        for (int i = 0; i < 17; i++) {
            if (type.equals(getResources().getString(R.string.type_bag + i))) {
                id = R.string.type_bag + i;
                Log.d("miaomiaomiao", "onTypeButtonClick: " + id);
                break;
            }
        }
        int drawableId = TypeKeyValue.typeIdMap.get(id);
        EventBus.getDefault().post(new MessageEvent2(type, drawableId, true));
    }
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public IncomeTypeSelectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountTypeSelectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IncomeTypeSelectFragment newInstance(String param1, String param2) {
        IncomeTypeSelectFragment fragment = new IncomeTypeSelectFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_income_type_select, container, false);
        ButterKnife.bind(this, view);
        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
