package com.example.tacademy.airbnb103.test;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.tacademy.airbnb103.R;
import com.example.tacademy.airbnb103.Util.Log;
import com.example.tacademy.airbnb103.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListActivity extends BaseActivity {

    @BindView(R.id.listView)
    ListView listView;

    MyAdapter myAdapter;
    // 임시 데이터
    String tmpDate[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_test);
        ButterKnife.bind(this);

        myAdapter = new MyAdapter();
        // 리스트에 아답터를 연결하면 작동 시작!
        listView.setAdapter(myAdapter);
        Log.getInstance().log("리스트뷰 객체 주소:" + listView.toString());
    }

    // 반복되는 cell의 구성 component를 최초 cell 생성 시 획득하여 설정하는 class
    class ViewHolder {
        @BindView(R.id.cell_title)
        TextView cell_title;

        public ViewHolder(View view) {
            // cell view를 binding한다.
            ButterKnife.bind(this, view);
        }
    }

    // 리스트뷰에 세팅되는 view를 cell이라고 통상 지칭한다. (ios 나온 용어)
    // BaseAdapter는 리스트뷰에 데이터를 관리하고 cell뷰를 컨트롤하는 클래스의 슈퍼
    class MyAdapter extends BaseAdapter {

        //리스트뷰에 표현한 데이터의 총수
        @Override
        public int getCount() {
            if (tmpDate == null) return 0;
            return tmpDate.length;
        }

        // cell에 대응되는 1개의 데이터를 획득하는 메소드
        @Override
        public String getItem(int position) {
            return tmpDate[position];
        }

        // 아이템의 아이디, 잘 사용안함
        @Override
        public long getItemId(int position) {
            return 0;
        }

        // 셀 1개를 만드는 메소드
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                // 최초 화면을 구성할 때 최대로 필요한 수만큼 여기가 작동됨.
                convertView =
                        ListActivity.this.getLayoutInflater().inflate(
                                R.layout.custom_cell_test,
                                parent,
                                false
                        );
                // cell의 구성원을 담을 그릇 생성
                holder = new ViewHolder(convertView);
                // cell의 내부 구성원 setting(xml -> java)
               // holder.cell_title = (TextView) convertView.findViewById(cell_title);
                // 그릇을 view에 설정
                convertView.setTag(holder);

                Log.getInstance().log("셀 생성" + position);
            } else {
                // 이제 로테이션시킬 양이 모두 채워졌다. 로테이션 시작의 의미
                // 재사용시 cell의 구성을 담는 그릇을 획득
                holder = (ViewHolder) convertView.getTag();
            }

            //TextView cell_title =
            //      (TextView) convertView.findViewById(cell_title);
            // 데이터 설정
            holder.cell_title.setText(getItem(position));

            return convertView;
        }
    }
}
