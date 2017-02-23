package br.com.curiousguy.aerocar.feature.carlist;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import br.com.curiousguy.aerocar.R;
import br.com.curiousguy.aerocar.databinding.ItemCarListBinding;
import br.com.curiousguy.aerocar.feature.carlist.carlistitem.CarListItemViewModelImpl;
import br.com.curiousguy.aerocar.model.WorkSession;

public class CarListAdapter extends BaseAdapter {

    List<WorkSession> sessions;

    public CarListAdapter(List<WorkSession> sessions) {
        if(sessions == null) {
            throw new NullPointerException("Empty sessions list");
        }

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

        ItemCarListBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_car_list, parent, false);
        binding.setViewModel(new CarListItemViewModelImpl((WorkSession) this.getItem(position)));

        return binding.getRoot();
    }
}
