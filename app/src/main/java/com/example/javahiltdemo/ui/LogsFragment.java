package com.example.javahiltdemo.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javahiltdemo.LogApplication;
import com.example.javahiltdemo.R;
import com.example.javahiltdemo.callback.LogCallback;
import com.example.javahiltdemo.data.Log;
import com.example.javahiltdemo.data.LoggerLocalDataSource;
import com.example.javahiltdemo.util.DateFormatter;

import org.w3c.dom.Text;

import java.util.List;

public class LogsFragment extends Fragment {
    private LoggerLocalDataSource logger;
    private DateFormatter dateFormatter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_logs, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);
    }

    @Override
    public void onAttach(Context context){
       super.onAttach(context);
       populateFields(context);
    }

    private void populateFields(Context context) {
        logger = ((LogApplication)context.getApplicationContext()).serviceLocator.loggerLocalDataSource;
        dateFormatter = ((LogApplication)context.getApplicationContext()).serviceLocator.provideDateFormatter();
    }

    @Override
    public void onResume() {
        super.onResume();

        logger.getAllLogs(new LogCallback() {
            @Override
            public void LogCallback(List<Log> logs) {
                recyclerView.setAdapter(new LogsViewAdapter(logs, dateFormatter));
            }
        });
    }
}

class LogsViewAdapter extends RecyclerView.Adapter<LogsViewAdapter.LogsViewHolder> {
    private List<Log> logsDataSet;
    private DateFormatter daterFormatter;

    public class LogsViewHolder extends RecyclerView.ViewHolder {
        TextView itemView;
        public LogsViewHolder(@NonNull TextView itemView) {
            super(itemView);
            itemView = itemView;
        }
    }

    public LogsViewAdapter(List<Log> logs, DateFormatter formatter){
        logsDataSet = logs;
        daterFormatter = formatter;
    }
    @NonNull
    @Override
    public LogsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LogsViewHolder(((TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.text_row_item, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull LogsViewHolder holder, int position) {
        Log log = logsDataSet.get(position);
        holder.itemView.setText(log.message + "\n\t" + daterFormatter.formatDate(log.timeStamp));
    }

    @Override
    public int getItemCount() {
        return logsDataSet.size();
    }


}
