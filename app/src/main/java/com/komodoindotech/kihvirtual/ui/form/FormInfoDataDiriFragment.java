package com.komodoindotech.kihvirtual.ui.form;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputLayout;
import com.komodoindotech.kihvirtual.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormInfoDataDiriFragment extends Fragment {

    private View root;
    private Toolbar toolbar;
    private AppCompatEditText nama, umur, alamat, hamil_ke, pendidikan_istri, pendidikan_suami,
            pekerjaan_istri, pekerjaan_suami, haid_terakhir, usia_anak_terakhir,
            lama_menikah;
    private TextInputLayout nama_layout, umur_layout, alamat_layout, hamil_ke_layout, pendidikan_istri_layout, pendidikan_suami_layout,
            pekerjaan_istri_layout, pekerjaan_suami_layout, haid_terakhir_layout, usia_anak_terakhir_layout,
            lama_menikah_layout;

    private Long selected_date;

    public FormInfoDataDiriFragment() {}

    public static FormInfoDataDiriFragment newInstance() {
        return new FormInfoDataDiriFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_form_info_data_diri, container, false);
        initView();
        initToolbar();
        setInputEvent();
        return root;
    }

    private void setInputEvent() {
        haid_terakhir.setOnClickListener(v -> {
            MaterialDatePicker.Builder builder = MaterialDatePicker
                    .Builder
                    .datePicker()
                    .setTitleText("Pilih tanggal");

            CalendarConstraints.Builder calendarConstraintsBuilder1 = new CalendarConstraints.Builder();
            builder.setCalendarConstraints(calendarConstraintsBuilder1.build());

            if(selected_date != null) builder.setSelection(selected_date);

            MaterialDatePicker<Long> materialDatePicker = builder.build();

            materialDatePicker.show(getActivity().getSupportFragmentManager(), "Tanggal");
            materialDatePicker.addOnPositiveButtonClickListener(selection -> {
                Date date = new Date(selection);
                selected_date = selection;
                String dateFormat = new SimpleDateFormat("dd-MM-yyyy").format(date);
                haid_terakhir.setText(dateFormat);
            });
            materialDatePicker.addOnNegativeButtonClickListener(dialog -> {
                selected_date = null;
                haid_terakhir.setText(null);
            });
        });
    }

    private void initToolbar() {
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
    }

    private void initView() {
        toolbar = root.findViewById(R.id.toolbar_form_data_diri);

        nama = root.findViewById(R.id.nama);
        umur = root.findViewById(R.id.umur);
        alamat = root.findViewById(R.id.alamat);
        hamil_ke = root.findViewById(R.id.hamil_ke);
        pendidikan_istri = root.findViewById(R.id.pendidikan_istri);
        pendidikan_suami = root.findViewById(R.id.pendidikan_suami);
        pekerjaan_istri = root.findViewById(R.id.pekerjaan_istri);
        pekerjaan_suami = root.findViewById(R.id.pekerjaan_suami);
        haid_terakhir = root.findViewById(R.id.haid_terakhir);
        usia_anak_terakhir = root.findViewById(R.id.usia_anak_terakhir);
        lama_menikah = root.findViewById(R.id.lama_menikah);

        nama_layout = root.findViewById(R.id.nama_layout);
        umur_layout = root.findViewById(R.id.umur_layout);
        alamat_layout = root.findViewById(R.id.alamat_layout);
        hamil_ke_layout = root.findViewById(R.id.hamil_ke_layout);
        pendidikan_istri_layout = root.findViewById(R.id.pendidikan_istri_layout);
        pendidikan_suami_layout = root.findViewById(R.id.pendidikan_suami_layout);
        pekerjaan_istri_layout = root.findViewById(R.id.pekerjaan_istri_layout);
        pekerjaan_suami_layout = root.findViewById(R.id.pekerjaan_suami_layout);
        haid_terakhir_layout = root.findViewById(R.id.haid_terakhir_layout);
        usia_anak_terakhir_layout = root.findViewById(R.id.usia_anak_terakhir_layout);
        lama_menikah_layout = root.findViewById(R.id.lama_menikah_layout);

    }
}