package example.android.contextmenusample;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.TextView;

public class ContextMenuSampleActivity extends Activity {

	List<String> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_context_menu_sample);

		list = new ArrayList<String>();
		list.add("選択1");
		list.add("選択2");
		list.add("選択3");

		ListAdapter adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_checked, list);

		ListView listview = (ListView) findViewById(R.id.lv_item);
		listview.setAdapter(adapter);

		listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

		listview.setMultiChoiceModeListener(new ListViewChoiceListener());
	}

	class ListViewChoiceListener implements MultiChoiceModeListener {

		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			ListView listview = (ListView) findViewById(R.id.lv_item);
			TextView textView = (TextView) findViewById(R.id.tv_message);

			SparseBooleanArray list = listview.getCheckedItemPositions();
			String selectedItem = "(選択されたリストアイテム：";
			for (int i = 0; i < list.size(); i++) {
				selectedItem += " " + listview.getItemAtPosition(list.keyAt(i));
			}
			selectedItem += ")";

			textView.setText("コンテキストメニューで選択：" + item.getTitle() + selectedItem);
			mode.finish();
			return true;
		}

		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			MenuInflater inflater = mode.getMenuInflater();
			inflater.inflate(R.menu.activity_context_menu_sample, menu);
			return true;
		}

		@Override
		public void onDestroyActionMode(ActionMode mode) {
		}

		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			return false;
		}

		@Override
		public void onItemCheckedStateChanged(ActionMode mode, int position, long id,
				boolean checked) {
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_context_menu_sample, menu);
		return true;
	}

}
