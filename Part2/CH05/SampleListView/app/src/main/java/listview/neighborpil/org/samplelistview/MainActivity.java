package listview.neighborpil.org.samplelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    ListView listView;
    SingerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        adapter = new SingerAdapter();
        adapter.addItem(new SingerItem("소녀시대", "010-1000-1000", 20, R.drawable.singer));
        adapter.addItem(new SingerItem("소녀시대1", "010-1001-1000", 21, R.drawable.singer2));
        adapter.addItem(new SingerItem("소녀시대2", "010-1002-1000", 22, R.drawable.singer3));
        adapter.addItem(new SingerItem("소녀시대3", "010-1003-1000", 23, R.drawable.singer4));

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SingerItem item = (SingerItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "선택 : " + item.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }

    class SingerAdapter extends BaseAdapter {

        ArrayList<SingerItem> items = new ArrayList<>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(SingerItem item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SingerItemView view = new SingerItemView(getApplicationContext());
            SingerItem item = items.get(position);
            view.setName(item.getName());
            view.setMobile(item.getMobile());
            view.setAge(item.getAge());
            view.setImage(item.getResId());
            return view;
        }
    }

}
