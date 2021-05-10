package com.komodoindotech.kihvirtual.adapters;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;
import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.json.PendaftaranObject;
import com.komodoindotech.kihvirtual.json.TanggalObject;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AdapterRecyclerRiwayatImunisasiTTR extends RecyclerView.Adapter<AdapterRecyclerRiwayatImunisasiTTR.viewHolder> {

    private Context mContext;
    private List<TanggalObject> tanggalObjects;

    public AdapterRecyclerRiwayatImunisasiTTR(Context mContext, List<TanggalObject> tanggalObjects) {
        this.mContext = mContext;
        this.tanggalObjects = tanggalObjects;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.component_card_tanggal_form, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.bind(mContext, tanggalObjects.get(position), position);
    }

    @Override
    public int getItemCount() {
        return tanggalObjects.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        TextInputLayout inputLayout;
        AppCompatEditText editText;
        AppCompatImageButton clearButton;
        Date selected_date;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            inputLayout = itemView.findViewById(R.id.input_layout_tanggal);
            editText = itemView.findViewById(R.id.input_tanggal);
            clearButton = itemView.findViewById(R.id.button_clear);
        }

        public void bind(Context mContext, TanggalObject pendaftaranObject, int position) {
            inputLayout.setHint(pendaftaranObject.getLabel());
            editText.setText(pendaftaranObject.getValue());
            editText.setOnClickListener(v -> {
                MaterialDatePicker.Builder builder = MaterialDatePicker
                        .Builder
                        .datePicker()
                        .setTitleText("Pilih tanggal");

                CalendarConstraints.Builder calendarConstraintsBuilder1 = new CalendarConstraints.Builder();
                builder.setCalendarConstraints(calendarConstraintsBuilder1.build());

                if(selected_date != null) builder.setSelection(selected_date.getTime());

                MaterialDatePicker<Long> materialDatePicker = builder.build();

                AppCompatActivity appCompatActivity = (AppCompatActivity) mContext;
                materialDatePicker.show(appCompatActivity.getSupportFragmentManager(), "Tanggal");
                materialDatePicker.addOnPositiveButtonClickListener(selection -> {
                    Date date = new Date(selection);
                    selected_date = date;
                    String dateFormat = new SimpleDateFormat("dd-MM-yyyy").format(date);
                    editText.setText(dateFormat);
                    clearButton.setVisibility(View.VISIBLE);
                });
            });
            clearButton.setOnClickListener(v -> {
                editText.setText(null);
                clearButton.setVisibility(View.GONE);
                selected_date = null;
            });
        }
    }

}
