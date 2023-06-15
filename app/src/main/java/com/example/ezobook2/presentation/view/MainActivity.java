package com.example.ezobook2.presentation.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ezobook2.R;
import com.example.ezobook2.presentation.viewmodel.AuthorViewModel;

public class MainActivity extends AppCompatActivity {
    Button fetchAuthorBtn;
    TextView authorName, nameLabel;

    ImageView authorImage;
    AuthorViewModel authorViewModel;

    Boolean isVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        authorViewModel = new ViewModelProvider(this).get(AuthorViewModel.class);

        initUI();

        fetchAuthorBtn.setOnClickListener(v -> {
            authorViewModel.getAuthorDetails();
        });

        authorViewModel.getAuthorLiveData().observe(this, author -> {
            setVisibility();
            authorName.setText(author.getAuthor());
            Glide.with(MainActivity.this)
                    .load(author.getUrl())
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(R.drawable.loading_image_placeholder)
                    .error(R.drawable.image_error_placeholder)
                    .into(authorImage);
        });
    }

    private void setVisibility() {
        if (isVisible) {
            authorName.setVisibility(View.VISIBLE);
            nameLabel.setVisibility(View.VISIBLE);
            authorImage.setVisibility(View.VISIBLE);
            isVisible = false;
        }
    }

    private void initUI() {
        fetchAuthorBtn = findViewById(R.id.btn_show_author_detail);
        authorName = findViewById(R.id.txt_author_name);
        nameLabel = findViewById(R.id.txt_label_author_name);
        authorImage = findViewById(R.id.iv_auth_profile);
        isVisible = authorName.getVisibility() != View.VISIBLE;
    }
}