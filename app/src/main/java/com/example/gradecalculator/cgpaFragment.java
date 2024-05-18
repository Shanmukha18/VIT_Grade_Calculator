package com.example.gradecalculator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link cgpaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class cgpaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public cgpaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment cgpaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static cgpaFragment newInstance(String param1, String param2) {
        cgpaFragment fragment = new cgpaFragment();
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
        View view = inflater.inflate(R.layout.fragment_cgpa, container, false);

        Button btn_submit = view.findViewById(R.id.btn_cgpa_submit);
        TextView tv_show_cgpa = view.findViewById(R.id.tv_showCGPA);
        EditText et_last_credits = view.findViewById(R.id.et_last_credits);
        EditText et_last_cgpa = view.findViewById(R.id.et_last_cgpa);
        et_last_cgpa.setFilters(new InputFilter[]{ new MinMaxFilter("1.0", "10.0")});
        EditText et_new_credits = view.findViewById(R.id.et_new_credits);
        EditText et_new_gpa = view.findViewById(R.id.et_new_cgpa);
        et_new_gpa.setFilters(new InputFilter[]{ new MinMaxFilter("1.0", "10.0")});

        btn_submit.setOnClickListener(v -> {
            double lastcredits = 0.0;
            double lastcgpa = 0.0;
            double newcredits = 0.0;
            double newgpa = 0.0;
            if(!et_last_credits.getText().toString().equals("") && !et_last_cgpa.getText().toString().equals("")
            && !et_new_credits.getText().toString().equals("") && !et_new_gpa.getText().toString().equals("")){
                lastcredits = Double.parseDouble(et_last_credits.getText().toString());
                lastcgpa = Double.parseDouble(et_last_cgpa.getText().toString());
                newcredits = Double.parseDouble(et_new_credits.getText().toString());
                newgpa = Double.parseDouble(et_new_gpa.getText().toString());
            }
            double numerator = (lastcredits * lastcgpa) + (newcredits * newgpa);
            double denominator = lastcredits + newcredits;
            if(denominator == 0) {
                tv_show_cgpa.setText("Invalid");
                tv_show_cgpa.setVisibility(View.VISIBLE);
            }else{
                double answer = numerator/denominator;
                answer = (double) (Math.round(answer * 100)) /100;
                if(answer <= 10.0){
                    tv_show_cgpa.setText(String.valueOf(answer));
                    tv_show_cgpa.setVisibility(View.VISIBLE);
                }else{
                    tv_show_cgpa.setText("Invalid");
                    tv_show_cgpa.setVisibility(View.VISIBLE);
                }
            }
        });

        return view;
    }
}