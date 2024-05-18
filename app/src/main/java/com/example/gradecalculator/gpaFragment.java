package com.example.gradecalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link gpaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class gpaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public gpaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment gpaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static gpaFragment newInstance(String param1, String param2) {
        gpaFragment fragment = new gpaFragment();
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
        View view = inflater.inflate(R.layout.fragment_gpa, container, false);
        Button btn_submit = view.findViewById(R.id.btn_gpa_submit);
        TextView tv_showgpa = view.findViewById(R.id.tv_showGPA);

        btn_submit.setOnClickListener(v -> {
            String gpa = finalgpa();
            tv_showgpa.setText(gpa);
            tv_showgpa.setVisibility(View.VISIBLE);
        });

        return view;
    }

    private double creditSpinnerValue(Spinner a) {
        double value;
        String shannu = a.getSelectedItem().toString();
        value = Double.parseDouble(shannu);
        return value;
    }

    private double gradeSpinnerValue(Spinner b) {
        double value;
        String vishnu = b.getSelectedItem().toString();
        value = gradeValue(vishnu);
        return value;
    }

    private double gradeValue(String grade) {
        double gradeValue;
        switch (grade) {
            case "S":
                gradeValue = 10;
                break;
            case "A":
                gradeValue = 9;
                break;
            case "B":
                gradeValue = 8;
                break;
            case "C":
                gradeValue = 7;
                break;
            case "D":
                gradeValue = 6;
                break;
            case "E":
                gradeValue = 5;
                break;
            default: gradeValue = 0;
        }
        return gradeValue;
    }

    private String finalgpa() throws ArithmeticException{
        double num = 0.0;
        double creditSum = 0.0;
        double[] arrayGrades = {
                gradeSpinnerValue(getView().findViewById(R.id.sp_Grades1)), gradeSpinnerValue(getView().findViewById(R.id.sp_Grades2)),
                gradeSpinnerValue(getView().findViewById(R.id.sp_Grades3)), gradeSpinnerValue(getView().findViewById(R.id.sp_Grades4)),
                gradeSpinnerValue(getView().findViewById(R.id.sp_Grades5)), gradeSpinnerValue(getView().findViewById(R.id.sp_Grades6)),
                gradeSpinnerValue(getView().findViewById(R.id.sp_Grades7)), gradeSpinnerValue(getView().findViewById(R.id.sp_Grades8)),
                gradeSpinnerValue(getView().findViewById(R.id.sp_Grades9)), gradeSpinnerValue(getView().findViewById(R.id.sp_Grades10)),
                gradeSpinnerValue(getView().findViewById(R.id.sp_Grades11)), gradeSpinnerValue(getView().findViewById(R.id.sp_Grades12)),
        };

        double[] arrayCredits = {
                creditSpinnerValue(getView().findViewById(R.id.sp_Credits1)), creditSpinnerValue(getView().findViewById(R.id.sp_Credits2)),
                creditSpinnerValue(getView().findViewById(R.id.sp_Credits3)), creditSpinnerValue(getView().findViewById(R.id.sp_Credits4)),
                creditSpinnerValue(getView().findViewById(R.id.sp_Credits5)), creditSpinnerValue(getView().findViewById(R.id.sp_Credits6)),
                creditSpinnerValue(getView().findViewById(R.id.sp_Credits7)), creditSpinnerValue(getView().findViewById(R.id.sp_Credits8)),
                creditSpinnerValue(getView().findViewById(R.id.sp_Credits9)), creditSpinnerValue(getView().findViewById(R.id.sp_Credits10)),
                creditSpinnerValue(getView().findViewById(R.id.sp_Credits11)), creditSpinnerValue(getView().findViewById(R.id.sp_Credits12)),
        };

        for (int i = 0; i < arrayGrades.length; i++) {
            double m = arrayGrades[i];
            double n = arrayCredits[i];
            creditSum = creditSum + n;
            double result = m * n;
            num = num + result;
        }
        if(creditSum == 0){
            return "Invalid";
        }else{
            return String.format("%.2f",num / creditSum);
        }
    }

}