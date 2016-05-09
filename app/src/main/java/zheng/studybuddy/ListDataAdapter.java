package zheng.studybuddy;

/**
 * Created by zheng on 5/8/2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListDataAdapter extends ArrayAdapter {
    List list = new ArrayList();
    public ListDataAdapter(Context context, int resource){
        super(context, resource);
    }

    static class LayoutHandler{

        TextView classname;
        TextView schoolname;

    }

    @Override
    public void add(Object object){
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public Object getItem(int position){
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        LayoutHandler layouthandler;
        if(row == null){

            LayoutInflater layoutinflater =(LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutinflater.inflate(R.layout.display_row, parent, false);
            layouthandler = new LayoutHandler();
            layouthandler.classname = (TextView) row.findViewById(R.id.t_class);
            layouthandler.schoolname = (TextView) row.findViewById(R.id.t_school);
            row.setTag(layouthandler);
        }
        else{
            layouthandler = (LayoutHandler) row.getTag();

        }
        DataProvider dataprovider = (DataProvider) this.getItem(position);
        layouthandler.classname.setText(dataprovider.getClassName());
        layouthandler.schoolname.setText(dataprovider.getSchoolName());
        return row;

    }
}
