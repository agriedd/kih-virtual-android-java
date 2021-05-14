package com.komodoindotech.kihvirtual.ui.form;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputLayout;
import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.models.Pendaftaran;
import com.komodoindotech.kihvirtual.ui.pendaftaran.PendaftaranViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FormInfoDataDiriFragment extends Fragment {

    private static final int MINIMUM_UMUR = 0;
    public static final String KEY = "data_diri";
    private static final String IS_STATE_STORED = "stored";

    private View root;
    private MaterialToolbar toolbar;
    private AppCompatEditText nama, umur, alamat, hamil_ke, pendidikan_istri, pendidikan_suami,
            pekerjaan_istri, pekerjaan_suami, haid_terakhir, usia_anak_terakhir,
            lama_menikah;
    private TextInputLayout nama_layout, umur_layout, alamat_layout, hamil_ke_layout, pendidikan_istri_layout, pendidikan_suami_layout,
            pekerjaan_istri_layout, pekerjaan_suami_layout, haid_terakhir_layout, usia_anak_terakhir_layout,
            lama_menikah_layout;

    private Map<String, String> inputErrors;
    private PendaftaranViewModel pendaftaranViewModel;
    private Pendaftaran pendaftaranObject;

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
        pendaftaranViewModel = new ViewModelProvider(requireActivity()).get(PendaftaranViewModel.class);
        initView();
        initToolbar();
        setInputEvent();
        pendaftaranViewModel.getInputErrors().observe(requireActivity(), stringMapMap -> {

        });
        return root;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(IS_STATE_STORED, true);
    }

    private void setDefaultValues() {

        Pendaftaran pendaftaran = pendaftaranViewModel.getPendaftaranObject().getValue();

        if(pendaftaran != null){
            nama.setText(pendaftaran.nama);
            if(pendaftaran.umur != null)
                umur.setText(String.valueOf(pendaftaran.umur));
            alamat.setText(pendaftaran.alamat);
            if(pendaftaran.hamil_ke != null)
                hamil_ke.setText(String.valueOf(pendaftaran.hamil_ke));
            pendidikan_istri.setText(pendaftaran.pendidikan_istri);
            pendidikan_suami.setText(pendaftaran.pendidikan_suami);
            pekerjaan_istri.setText(pendaftaran.pekerjaan_istri);
            pekerjaan_suami.setText(pendaftaran.pekerjaan_suami);

            try {
                haid_terakhir.setText(new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(pendaftaran.haid_terakhir));
            } catch (Exception ignored){}
            if(pendaftaran.usia_anak_terakhir != null)
                usia_anak_terakhir.setText(String.valueOf(pendaftaran.usia_anak_terakhir));
            if(pendaftaran.lama_menikah != null)
                lama_menikah.setText(String.valueOf(pendaftaran.lama_menikah));

        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_only_close, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_exit_activity) {
            requireActivity().onBackPressed();
        }
        return true;
    }

    private void setInputEvent() {

        inputErrors = new HashMap<>();
        pendaftaranViewModel.setInputError(KEY, inputErrors);
        pendaftaranObject = new Pendaftaran();

        haid_terakhir.setOnClickListener(v -> {
            MaterialDatePicker.Builder<Long> builder = MaterialDatePicker
                    .Builder
                    .datePicker()
                    .setTitleText("Pilih tanggal");

            CalendarConstraints.Builder calendarConstraintsBuilder1 = new CalendarConstraints.Builder();
            builder.setCalendarConstraints(calendarConstraintsBuilder1.build());

            if(selected_date != null) builder.setSelection(selected_date);

            MaterialDatePicker<Long> materialDatePicker = builder.build();

            materialDatePicker.show(requireActivity().getSupportFragmentManager(), "Tanggal");
            materialDatePicker.addOnPositiveButtonClickListener(selection -> {
                Date date = new Date(selection);
                selected_date = selection;
                String dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(date);
                haid_terakhir.setText(dateFormat);
            });
            materialDatePicker.addOnNegativeButtonClickListener(dialog -> {
                selected_date = null;
                haid_terakhir.setText(null);
            });
        });

        nama.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().trim().length() <= 0){
                    String message = "Nama wajib diisi";
                    nama_layout.setError(message);
                    inputErrors.put("nama", message);
                } else {
                    nama_layout.setError(null);
                    inputErrors.remove("nama");
                }
                pendaftaranObject.setNama(s.toString());
                pendaftaranViewModel.setInputError(KEY, inputErrors);
                pendaftaranViewModel.setPendaftaranObject(pendaftaranObject);
            }
        });
        umur.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                try {
                    int value = Integer.parseInt(s.toString());
                    if(value <= MINIMUM_UMUR){
                        String message = "Umur tidak boleh kurang dari "+MINIMUM_UMUR+" tahun";
                        umur_layout.setError(message);
                        inputErrors.put("umur", message);
                    } else {
                        umur_layout.setError(null);
                        inputErrors.remove("umur");
                    }
                    pendaftaranObject.setUmur(value);
                } catch (Exception e) {
                    String message = "Perhatikan inputan umur";
                    umur_layout.setError(message);
                    inputErrors.put("umur", message);
                }
                pendaftaranViewModel.setInputError(KEY, inputErrors);
                pendaftaranViewModel.setPendaftaranObject(pendaftaranObject);
            }
        });
        alamat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String value = s.toString();
                if(value.trim().length() <= 0){
                    String message = "Alamat wajib diisi";
                    alamat_layout.setError(message);
                    inputErrors.put("alamat", message);
                } else {
                    alamat_layout.setError(null);
                    inputErrors.remove("alamat");
                }
                pendaftaranObject.setAlamat(s.toString());
                pendaftaranViewModel.setInputError(KEY, inputErrors);
                pendaftaranViewModel.setPendaftaranObject(pendaftaranObject);
            }
        });
        hamil_ke.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    int value = Integer.parseInt(s.toString().trim());
                    if(value < 1){
                        String message = "Perhatikan inputan";
                        hamil_ke_layout.setError(message);
                        inputErrors.put("hamil_ke", message);
                    } else {
                        hamil_ke_layout.setError(null);
                        inputErrors.remove("hamil_ke");
                    }
                    pendaftaranObject.setHamil_ke(value);
                } catch (Exception e){
                    String message = "Perhatikan inputan";
                    hamil_ke_layout.setError(message);
                    inputErrors.put("hamil_ke", message);
                }
                pendaftaranViewModel.setInputError(KEY, inputErrors);
                pendaftaranViewModel.setPendaftaranObject(pendaftaranObject);
            }
        });
        pendidikan_istri.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().trim().length() <= 0){
                    String message = "Pendidikan Ibu wajib diisi";
                    pendidikan_istri_layout.setError(message);
                    inputErrors.put("pendidikan_istri", message);
                } else {
                    pendidikan_istri_layout.setError(null);
                    inputErrors.remove("pendidikan_istri");
                }
                pendaftaranObject.setPendidikan_istri(s.toString());
                pendaftaranViewModel.setInputError(KEY, inputErrors);
                pendaftaranViewModel.setPendaftaranObject(pendaftaranObject);
            }
        });
        pendidikan_suami.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().trim().length() <= 0){
                    String message = "Pendidikan Suami wajib diisi";
                    pendidikan_suami_layout.setError(message);
                    inputErrors.put("pendidikan_suami", message);
                } else {
                    pendidikan_suami_layout.setError(null);
                    inputErrors.remove("pendidikan_suami");
                }
                pendaftaranObject.setPendidikan_suami(s.toString());
                pendaftaranViewModel.setInputError(KEY, inputErrors);
                pendaftaranViewModel.setPendaftaranObject(pendaftaranObject);
            }
        });
        pekerjaan_istri.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().trim().length() <= 0){
                    String message = "Pekerjaan Ibu wajib diisi";
                    pekerjaan_istri_layout.setError(message);
                    inputErrors.put("pekerjaan_istri", message);
                } else {
                    pekerjaan_istri_layout.setError(null);
                    inputErrors.remove("pekerjaan_istri");
                }
                pendaftaranObject.setPekerjaan_istri(s.toString());
                pendaftaranViewModel.setInputError(KEY, inputErrors);
                pendaftaranViewModel.setPendaftaranObject(pendaftaranObject);
            }
        });
        pekerjaan_suami.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().trim().length() <= 0){
                    String message = "Pekerjaan Suami wajib diisi";
                    pekerjaan_suami_layout.setError(message);
                    inputErrors.put("pekerjaan_suami", message);
                } else {
                    pekerjaan_suami_layout.setError(null);
                    inputErrors.remove("pekerjaan_suami");
                }
                pendaftaranObject.setPekerjaan_suami(s.toString());
                pendaftaranViewModel.setInputError(KEY, inputErrors);
                pendaftaranViewModel.setPendaftaranObject(pendaftaranObject);
            }
        });
        haid_terakhir.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().trim().length() <= 0){
                    String message = "Tanggal haid terakhir wajib diisi";
                    haid_terakhir_layout.setError(message);
                    inputErrors.put("haid_terakhir", message);
                } else {
                    haid_terakhir_layout.setError(null);
                    inputErrors.remove("haid_terakhir");
                }
                pendaftaranObject.setHaid_terakhir(selected_date);
                pendaftaranViewModel.setInputError(KEY, inputErrors);
                pendaftaranViewModel.setPendaftaranObject(pendaftaranObject);
            }
        });
        usia_anak_terakhir.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    int value = Integer.parseInt(s.toString().trim());
                    if(value < 0){
                        String message = "Perhatikan inputan";
                        usia_anak_terakhir_layout.setError(message);
                        inputErrors.put("usia_anak_terakhir", message);
                    } else {
                        usia_anak_terakhir_layout.setError(null);
                        inputErrors.remove("usia_anak_terakhir");
                    }
                    pendaftaranObject.setUsia_anak_terakhir(value);
                } catch (Exception e){
                    String message = "Perhatikan inputan";
                    usia_anak_terakhir_layout.setError(message);
                    inputErrors.put("usia_anak_terakhir", message);
                }
                pendaftaranViewModel.setInputError(KEY, inputErrors);
                pendaftaranViewModel.setPendaftaranObject(pendaftaranObject);
            }
        });
        lama_menikah.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    int value = Integer.parseInt(s.toString().trim());
                    if(value < 0 || s.length() <= 0){
                        String message = "Perhatikan inputan";
                        lama_menikah_layout.setError(message);
                        inputErrors.put("lama_menikah", message);
                    } else {
                        lama_menikah_layout.setError(null);
                        inputErrors.remove("lama_menikah");
                    }
                    pendaftaranObject.setLama_menikah(value);
                } catch (Exception e){
                    String message = "Perhatikan inputan";
                    lama_menikah_layout.setError(message);
                    inputErrors.put("lama_menikah", message);
                }
                pendaftaranViewModel.setInputError(KEY, inputErrors);
                pendaftaranViewModel.setPendaftaranObject(pendaftaranObject);
            }
        });
    }

    private void initToolbar() {
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        assert appCompatActivity != null;
        appCompatActivity.setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
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