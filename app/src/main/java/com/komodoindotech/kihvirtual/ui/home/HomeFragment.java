package com.komodoindotech.kihvirtual.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.komodoindotech.kihvirtual.KesimpulanActivity;
import com.komodoindotech.kihvirtual.PendaftaranActivity;
import com.komodoindotech.kihvirtual.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "homefragment";
    private static final int RC_SIGN_IN = 301;
    private ListArticleViewModel listArticleViewModel;
    private SignInButton btnLoginGoogle;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private View root;
    private ShapeableImageView sesi_img;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView last_cached;
    private LinearLayout last_cached_container;
    private ExtendedFloatingActionButton daftar_button;


    @SuppressLint({"SetTextI18n", "SimpleDateFormat"})
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        listArticleViewModel = new ViewModelProvider(this).get(ListArticleViewModel.class);

        root = inflater.inflate(R.layout.fragment_home, container, false);
        swipeRefreshLayout = root.findViewById(R.id.refresh_home);
        last_cached = root.findViewById(R.id.info_last_update_home);
        last_cached_container = root.findViewById(R.id.container_info_last_update_home);

        listArticleViewModel.getLoadingMutableLiveData().observe(getViewLifecycleOwner(), aBoolean -> {
            if(aBoolean){
                swipeRefreshLayout.setRefreshing(true);
            } else {
                if(swipeRefreshLayout.isRefreshing()){
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
        homeViewModel.getText().observe(getViewLifecycleOwner(), s -> {

        });
        listArticleViewModel.getLastCachedMutableLiveData().observe(getViewLifecycleOwner(), aLong -> {
            if(aLong == null || aLong.equals(0L)){
                last_cached_container.setVisibility(View.GONE);
            } else {
                last_cached_container.setVisibility(View.VISIBLE);
                try {
                    last_cached.setText(
                            "Terakhir diupdate: "
                                    + new SimpleDateFormat("dd-MM-yyyy HH:mm")
                                    .format(new Date( aLong ))
                    );
                } catch (Exception e){
                    Log.d(TAG, "onCreateView: "+e.getMessage());
                }
            }
        });

        mAuth = FirebaseAuth.getInstance();

        btnLoginGoogle = root.findViewById(R.id.sign_in_button);
        btnLoginGoogle.setOnClickListener(this);
        sesi_img = root.findViewById(R.id.sesi_img);
        sesi_img.setOnClickListener(this);
        daftar_button = root.findViewById(R.id.btn_daftar);
        daftar_button.setOnClickListener(this);

        requestLoginGoogle();

        loadFragmentArticle();

        swipeRefreshLayout.setOnRefreshListener(() -> listArticleViewModel.refresh());

        return root;
    }

    private void loadFragmentArticle() {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_artikel, ListArticleFragment.newInstance());
        transaction.commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if(currentUser != null){
            setLoginUser(currentUser);
        } else {}
    }

    private void setLoginUser(FirebaseUser currentUser) {
        root.findViewById(R.id.container_sudah_login).setVisibility(View.VISIBLE);
        root.findViewById(R.id.container_belum_login).setVisibility(View.GONE);
        ((TextView) root.findViewById(R.id.sesi_email)).setText(currentUser.getEmail());
        ((TextView) root.findViewById(R.id.sesi_name)).setText(currentUser.getDisplayName());
        Glide.with(requireContext()).load(currentUser.getPhotoUrl()).into(sesi_img);
    }

    @Override
    public void onClick(View v) {
        if(v == btnLoginGoogle){
            signIn();
        } else if(v == sesi_img){
            logOut();
        } else if(v == daftar_button){
            /**
             * developement
             *
             */

            startActivity(new Intent(getContext(), KesimpulanActivity.class));
//            startActivity(new Intent(getContext(), PendaftaranActivity.class));
        }
    }

    private void requestLoginGoogle() {
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    private void logOut(){
        FirebaseAuth.getInstance().signOut();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Log.d(TAG, "onActivityResult: "+RC_SIGN_IN);
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            firebaseAuthWithGoogle(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getMessage());
            Snackbar.make(root.findViewById(R.id.container), "Tidak dapat login menggunakan akun google.", Snackbar.LENGTH_LONG).show();
            updateUI(null);
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        Log.d(TAG, "signInWithCredential:"+user.getEmail());
                        updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        updateUI(null);
                    }
                });
    }

    private void updateUIGoogleSignIn(GoogleSignInAccount account) {
        Log.d(TAG, "updateUIGoogleSignIn: success");
        if(account != null){
            Log.d(TAG, "updateUIGoogleSignIn: " + account.getEmail());
        }
    }
}