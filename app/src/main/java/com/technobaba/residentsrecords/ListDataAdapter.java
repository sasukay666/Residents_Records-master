package com.technobaba.residentsrecords;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListDataAdapter extends ArrayAdapter{

    List list = new ArrayList();
    public ListDataAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public void add(DataProvider object) {
        list.add(object);
        super.add(object);
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        LayoutHandler layoutHandler;

        if(row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout, parent, false);

            layoutHandler = new LayoutHandler();

            layoutHandler.ID=(TextView)row.findViewById(R.id.textid);
            layoutHandler.NAME=(TextView)row.findViewById(R.id.textname);
            layoutHandler.GENDER=(TextView)row.findViewById(R.id.textgender);
            layoutHandler.AGE=(TextView)row.findViewById(R.id.textage);
            layoutHandler.MOB=(TextView)row.findViewById(R.id.textmob);
            layoutHandler.ADDRESS=(TextView)row.findViewById(R.id.textaddress);
            layoutHandler.DETAILS=(TextView)row.findViewById(R.id.textdetails);
            layoutHandler.OTHER=(TextView)row.findViewById(R.id.textother);

            layoutHandler.ID.setText("ID");
            layoutHandler.NAME.setText("Name");
            layoutHandler.GENDER.setText("Gender");
            layoutHandler.AGE.setText("Age");
            layoutHandler.MOB.setText("Phone");
            layoutHandler.ADDRESS.setText("Address");
            layoutHandler.DETAILS.setText("Details");
            layoutHandler.OTHER.setText("Other");

            row.setTag(layoutHandler);
        }
        else{
            layoutHandler=(LayoutHandler)row.getTag();
        }

        DataProvider dataProvider=(DataProvider)this.getItem(position);

        layoutHandler.ID.setText(Integer.toString(dataProvider.getId()));
        layoutHandler.NAME.setText(dataProvider.getName());
        layoutHandler.GENDER.setText(dataProvider.getGender());
        layoutHandler.AGE.setText(Integer.toString(dataProvider.getAge()));
        layoutHandler.MOB.setText(dataProvider.getMobile());
        layoutHandler.ADDRESS.setText(dataProvider.getAddress());
        layoutHandler.DETAILS.setText(dataProvider.getDetails());
        layoutHandler.OTHER.setText(dataProvider.getOther());

        return row;
    }

    static class LayoutHandler{
        TextView ID,NAME,GENDER,AGE,MOB,ADDRESS,DETAILS,OTHER;
    }
}