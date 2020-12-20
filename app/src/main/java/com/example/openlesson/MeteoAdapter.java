package com.example.openlesson;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.internal.$Gson$Preconditions;

import java.util.List;

public class MeteoAdapter extends RecyclerView.Adapter<MeteoAdapter.ViewHolder> {
        private List<Meteo> meteos;

    public MeteoAdapter(List<Meteo> meteos) {
        this.meteos = meteos;
    }

    @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meteo_item, parent, false);
        return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Meteo meteo=meteos.get(position);
            holder.date.setText(meteo.getDate());
            holder.tod.setText(meteo.getTod());
            holder.pressure.setText(meteo.getPressure());
            holder.temp.setText(meteo.getTemp());
            holder.feel.setText(meteo.getFeel());
            holder.humidity.setText(meteo.getHumidity());
            holder.wind.setText(meteo.getWind());
            holder.cloud.setText(meteo.getCloud());
        }

        @Override
        public int getItemCount() {
            return meteos.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            public TextView date;
            public TextView tod;
            public TextView pressure;
            public TextView temp;
            public TextView feel;
            public TextView humidity;
            public TextView wind;
            public TextView cloud;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                date=itemView.findViewById(R.id.date);
                tod=itemView.findViewById(R.id.tod);
                pressure=itemView.findViewById(R.id.pressure);
                temp=itemView.findViewById(R.id.temp);
                feel=itemView.findViewById(R.id.feel);
                humidity=itemView.findViewById(R.id.humidity);
                wind=itemView.findViewById(R.id.wind);
                cloud=itemView.findViewById(R.id.cloud);
            }
        }
}
