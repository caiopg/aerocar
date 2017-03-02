package br.com.curiousguy.aerocar.feature.worksessionlist;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import br.com.curiousguy.aerocar.R;
import br.com.curiousguy.aerocar.databinding.ItemWorkSessionListBinding;
import br.com.curiousguy.aerocar.feature.worksessionlist.worksessionlistitem.WorkSessionListItemViewModelImpl;
import br.com.curiousguy.aerocar.model.WorkSession;

public class WorkSessionListAdapter extends BaseAdapter {

    List<WorkSession> sessions;
    Context context;

    public WorkSessionListAdapter(Context context, List<WorkSession> sessions) {
        if(sessions == null) {
            throw new NullPointerException("Empty sessions list");
        }

        this.context = context;
        this.sessions = sessions;
    }

    @Override
    public int getCount() {
        return sessions.size();
    }

    @Override
    public Object getItem(int position) {
        return sessions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) parent.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ItemWorkSessionListBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_work_session_list, parent, false);

        WorkSession workSession = (WorkSession) this.getItem(position);
        WorkSessionListItemViewModelImpl itemViewModel = new WorkSessionListItemViewModelImpl(context, workSession);
        binding.setViewModel(itemViewModel);

        return binding.getRoot();
    }
}
