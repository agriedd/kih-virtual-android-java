package com.komodoindotech.kihvirtual.ui.puskesmas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.adapters.AdapterRecyclerTelepon;
import com.komodoindotech.kihvirtual.json.PuskesmasObject;

public class MenuPuskesmasDialogFragment extends BottomSheetDialogFragment {

    private final PuskesmasObject puskesmasObject;
    private TextView nama, alamat, label_lokasi;
    private CardView lokasi;
    private RecyclerView telepon;

    public MenuPuskesmasDialogFragment(PuskesmasObject puskesmasObject) {
        this.puskesmasObject = puskesmasObject;
    }

    public static MenuPuskesmasDialogFragment newInstance(PuskesmasObject puskesmasObject) {
        return new MenuPuskesmasDialogFragment(puskesmasObject);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_menu_puskesmas_dialog, container, false);
        nama = root.findViewById(R.id.nama);
        alamat = root.findViewById(R.id.alamat);

        lokasi = root.findViewById(R.id.lokasi);
        label_lokasi = root.findViewById(R.id.lokasi_label);
        telepon = root.findViewById(R.id.kontak);

        bindView();

        return root;
    }

    private void bindView() {
        nama.setText(puskesmasObject.getNama());
        alamat.setText(puskesmasObject.getAlamat());
        if(puskesmasObject.getLokasi() != null){
            lokasi.setOnClickListener(v -> {
                Uri gmmIntentUri = Uri.parse("google.streetview:cbll="+ puskesmasObject.getLokasi().getLatitude() +","+ puskesmasObject.getLokasi().getLongitude());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            });
        } else {
            label_lokasi.setText("Temukan di peta (?)");
            lokasi.setOnClickListener(v -> {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + puskesmasObject.getNama());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            });
        }
        if (puskesmasObject.getTelepon() != null && puskesmasObject.getTelepon().size() > 0){
            telepon.setVisibility(View.VISIBLE);
            AdapterRecyclerTelepon adapterRecyclerTelepon = new AdapterRecyclerTelepon(requireContext(), puskesmasObject.getTelepon());
            telepon.setNestedScrollingEnabled(false);
            telepon.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
            telepon.setHasFixedSize(true);
            telepon.setAdapter(adapterRecyclerTelepon);
        }
    }
}