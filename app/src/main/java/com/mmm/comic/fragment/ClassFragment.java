package com.mmm.comic.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.mmm.comic.R;

/**
 * Created by Administrator on 2017/2/8.
 */
public class ClassFragment extends Fragment {

    private RelativeLayout aboutme_rl;
    private RelativeLayout feedback_rl;
    private RelativeLayout share_rl;
    private RelativeLayout exit_rl;
//
//    public ChioseChildGalleryAdapter chioseChildGalleryAdapter;
//    private ButtomDialog buttomDialog;
//    public MainActivity activity;
//    private Gallery gallery;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class, null);
//        aboutme_rl = (RelativeLayout) view.findViewById(R.id.aboutme_rl);
//        feedback_rl = (RelativeLayout) view.findViewById(R.id.feedback_rl);
//        share_rl = (RelativeLayout) view.findViewById(R.id.share_rl);
//        exit_rl = (RelativeLayout) view.findViewById(R.id.exit_rl);
//        gallery = (Gallery) view.findViewById(R.id.gallery);
//        return view;
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        activity = (MainActivity) getActivity();
//        chioseChildGalleryAdapter = new ChioseChildGalleryAdapter(getContext(), Constant.user.getBaby());
//        setGallery();
//        setCurrGallery();
//        initEvent();
    }

    private void initEvent() {
        aboutme_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //意见反馈
        feedback_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }





}
