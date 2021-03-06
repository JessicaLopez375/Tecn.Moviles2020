package com.iua.jessicalopez.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iua.jessicalopez.Modelo.MovieVo;
import com.iua.jessicalopez.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDetails extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ImageView imageMovie;
    TextView textView;
    TextView textSinopsis;
    MovieVo movieDetails;
    Context context;


    public FragmentDetails(Context context) {
        this.context = context;
        // Required empty public constructor
    }
    public FragmentDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment detailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDetails newInstance(String param1, String param2) {
        FragmentDetails fragment = new FragmentDetails();
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
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_detail, container, false);

        imageMovie = vista.findViewById(R.id.imageMovieDetail);
        textView = vista.findViewById(R.id.titleMovieDetail);
        textSinopsis = vista.findViewById(R.id.textSinopsis);

        initValues();
        return vista;
    }


    public void initValues() {

        Glide.with(context)
                .load(movieDetails.getFoto())
                .into(imageMovie);

        //imageMovie.setImageResource(Integer.parseInt(movieDetails.getFoto()));

        textView.setText(movieDetails.getName());

        textSinopsis.setText(movieDetails.getDescription());


    }

    public void putExtra(MovieVo movieVo) {
        this.movieDetails = movieVo;
    }
}
