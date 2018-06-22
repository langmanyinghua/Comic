package com.mmm.comic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mmm.comic.APP;
import com.mmm.comic.R;
import com.mmm.comic.activity.HomeActivity;
import com.mmm.comic.base.CommonAdapter;
import com.mmm.comic.base.ViewHolder;
import com.mmm.comic.bean.ComicBean;

import java.util.List;

/**
 * Created by panlaixing on 2018/6/19.
 */
public class BookBoxItemFragment extends Fragment {
    private ListView listView;
    private CommonAdapter commonAdapter;
    private List<ComicBean> comicList = null;
    private String title;

    public static BookBoxItemFragment newInstance(String title) {
        Bundle argz = new Bundle();
        argz.putString("title", title);
        BookBoxItemFragment fragment = new BookBoxItemFragment();
        fragment.setArguments(argz);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookbox_item, null);
//        listView = view.findViewById(R.id.bookbox_listview);
        if (getArguments() != null) {
            title = getArguments().getString("title");
        }
        ImageView bookbox_item_icon = view.findViewById(R.id.bookbox_item_icon);
        TextView bookbox_item_title = view.findViewById(R.id.bookbox_item_title);
        TextView bookbox_item_subtitle = view.findViewById(R.id.bookbox_item_subtitle);
        TextView bookbox_item_button = view.findViewById(R.id.bookbox_item_button);

        if ("历史".equals(title)) {
            bookbox_item_icon.setImageResource(R.drawable.mkz_default_historynull);
            bookbox_item_title.setText(getContext().getString(R.string.mkz_bookshelf_please_look_comic));
            bookbox_item_subtitle.setText(getContext().getString(R.string.mkz_bookshelf_history_empty_tip));
            bookbox_item_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((HomeActivity) getActivity()).switchIndex();
                }
            });
        } else if ("收藏".equals(title)) {
            bookbox_item_icon.setImageResource(R.drawable.mkz_default_collnull);
            bookbox_item_title.setText(getContext().getString(R.string.mkz_load_data_favorite_empty));
            bookbox_item_subtitle.setText(getContext().getString(R.string.mkz_load_data_favorite_empty_tip));

            bookbox_item_button.setVisibility(View.INVISIBLE);
        } else if ("缓存".equals(title)) {
            bookbox_item_icon.setImageResource(R.drawable.mkz_default_downnull);
            bookbox_item_title.setText(getContext().getString(R.string.mkz_cache_empty_tip));
            bookbox_item_subtitle.setText(getContext().getString(R.string.mkz_please_cache_tip));

            bookbox_item_button.setVisibility(View.INVISIBLE);
        } else if ("订购".equals(title)) {
            bookbox_item_icon.setImageResource(R.drawable.mkz_default_downnull);
            bookbox_item_title.setText(getContext().getString(R.string.mkz_load_data_buy_list_empty));
            bookbox_item_subtitle.setText(getContext().getString(R.string.mkz_please_buy_comic));

            bookbox_item_button.setVisibility(View.INVISIBLE);
        }
        return view;
    }

    /**
     * 绑定数据
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        comicList = APP.application.comicList;
//        listView.setAdapter(commonAdapter = new CommonAdapter<ComicBean>(getContext(), comicList, R.layout.fragment_index_man_item) {
//            @Override
//            public void convert(ViewHolder helper, ComicBean item) {
//                Glide.with(getActivity()).load(item.getThumb()).into((ImageView) helper.getView(R.id.image));
//                helper.setText(R.id.name,item.getTitle());
//                helper.setText(R.id.feature,item.getDetail());
//                helper.setText(R.id.author,item.getAuthor());
//
//                LinearLayout label_layout = helper.getView(R.id.label_layout);
//                label_layout.removeAllViews();
//                TextView tag = new TextView(getContext());
//                tag.setTextSize(7);
//                tag.setBackgroundResource(R.drawable.shape_tag);
//                tag.setText(item.getTag());
//                label_layout.addView(tag);
//            }
//        });
    }
}
