package com.example.tacademy.airbnb103.test;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.tacademy.airbnb103.R;
import com.example.tacademy.airbnb103.Util.Alert;
import com.example.tacademy.airbnb103.Util.ImageProc;
import com.example.tacademy.airbnb103.Util.Log;
import com.example.tacademy.airbnb103.Util.Net;
import com.example.tacademy.airbnb103.consts.E;
import com.example.tacademy.airbnb103.model.user.EplVo;
import com.example.tacademy.airbnb103.ui.base.BaseActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;

public class ListActivity extends BaseActivity {

    @BindView(R.id.listView)
    ListView listView;

    MyAdapter myAdapter;
    // 임시 데이터
    //String tmpDate[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
    // 서버로부터 전송받은 데이터를 파싱해서 담은 컬렉션
    List<EplVo> arr = new ArrayList<EplVo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_test);
        ButterKnife.bind(this);

        myAdapter = new MyAdapter();
        // 리스트에 아답터를 연결하면 작동 시작!
        listView.setAdapter(myAdapter);
        Log.getInstance().log("리스트뷰 객체 주소:" + listView.toString());

        // eplList 요청
        Log.getInstance().log("==============");
        RequestQueue requestQueue = Net.getInstance().getRequestQueue(this);
        requestQueue.add(new StringRequest(Request.Method.GET, E.NET.USE_DOMAIN + E.NET.API_GET_EPLLIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // 성공
                        Log.getInstance().log("응답" + response);
                        // 파싱하기
                        Gson gson = new Gson();
                        EplVo[] array = gson.fromJson(response, EplVo[].class);

                        Collections.addAll(arr, array);

                        // 리스트뷰를 갱신해라
                        myAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // 실패
                        Alert.getInstance().warnAlert(
                                ListActivity.this,
                                "알림",
                                "네트워크 장애가 있습니다.~" + error.getLocalizedMessage(),
                                "종료",
                                new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.dismissWithAnimation();
                                        // 앱 종료
                                        finish();
                                    }
                                });
                    }
                }));
        
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.getInstance().log("onItemClick");
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.getInstance().log("setOnItemLongClickListener");
                return false;
            }
        });

        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.getInstance().log("OnItemSelectedListener");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.getInstance().log("onNothingSelected");

            }
        });
    }

    // 반복되는 cell의 구성 component를 최초 cell 생성 시 획득하여 설정하는 class
    class ViewHolder {
        @BindView(R.id.ranking)
        TextView ranking;

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.count)
        TextView count;

        @BindView(R.id.winpoint)
        TextView winpoint;

        @BindView(R.id.win)
        TextView win;

        @BindView(R.id.draw)
        TextView draw;

        @BindView(R.id.lose)
        TextView lose;

        @BindView(R.id.score)
        TextView score;

        @BindView(R.id.mscore)
        TextView mscore;

        @BindView(R.id.gap)
        TextView gap;

        @BindView(R.id.symbol)
        CircleImageView symbol;

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
            if (arr == null) return 0;
            return arr.size();
        }

        // cell에 대응되는 1개의 데이터를 획득하는 메소드
        @Override
        public EplVo getItem(int position) {
            return arr.get(position);//tmpDate[position];
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

            // 순위 세팅
            holder.ranking.setText("" + getItem(position).getRanking());
            // 팀명 세팅
            holder.name.setText(getItem(position).getName());
            // 각종 수치 8개 세팅
            holder.count.setText("" + getItem(position).getCount());
            holder.winpoint.setText("" + getItem(position).getWinpoint());
            holder.win.setText("" + getItem(position).getWin());
            holder.draw.setText("" + getItem(position).getDraw());
            holder.lose.setText("" + getItem(position).getLose());
            holder.score.setText("" + getItem(position).getScore());
            holder.mscore.setText("" + getItem(position).getMscore());
            holder.gap.setText("" + getItem(position).getGap());
            // 이미지 세팅
            ImageProc.getInstance().drawImage(E.NET.USE_DOMAIN + getItem(position).getSymbol(), holder.symbol);
            return convertView;
        }
    }
}
