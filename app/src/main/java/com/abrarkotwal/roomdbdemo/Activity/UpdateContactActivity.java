package com.abrarkotwal.roomdbdemo.Activity;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.abrarkotwal.roomdbdemo.DB.AppDatabase;
import com.abrarkotwal.roomdbdemo.DB.ContactDAO;
import com.abrarkotwal.roomdbdemo.Models.Contact;
import com.abrarkotwal.roomdbdemo.R;


public class UpdateContactActivity extends AppCompatActivity {

    public static String EXTRA_CONTACT_ID = "contact_id";
    private TextView mCreatedTimeTextView;
    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    private EditText mPhoneNumberEditText;
    private Button mUpdateButton;
    private Toolbar mToolbar;
    private ContactDAO mContactDAO;
    private Contact CONTACT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        mContactDAO = Room.databaseBuilder(this, AppDatabase.class, "db-contacts")
                .allowMainThreadQueries()
                .build()
                .getContactDAO();

        mFirstNameEditText      = findViewById(R.id.firstNameEditText);
        mLastNameEditText       = findViewById(R.id.lastNameEditText);
        mPhoneNumberEditText    = findViewById(R.id.phoneNumberEditText);
        mUpdateButton           = findViewById(R.id.updateButton);
        mCreatedTimeTextView    = findViewById(R.id.createdTimeTextView);
        mToolbar                = findViewById(R.id.toolbar);

        CONTACT     = mContactDAO.getContactWithId(getIntent().getStringExtra(EXTRA_CONTACT_ID));

        initViews();
    }

    private void initViews() {
        setSupportActionBar(mToolbar);

        mFirstNameEditText.setText(CONTACT.getFirstName());
        mLastNameEditText.setText(CONTACT.getLastName());
        mPhoneNumberEditText.setText(CONTACT.getPhoneNumber());
        mCreatedTimeTextView.setText(CONTACT.getCreatedDate().toString());

        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = mFirstNameEditText.getText().toString();
                String lastName = mLastNameEditText.getText().toString();
                String phoneNumber = mPhoneNumberEditText.getText().toString();

                if (firstName.length() == 0 || lastName.length() == 0 || phoneNumber.length() == 0) {
                    Toast.makeText(UpdateContactActivity.this, "Please make sure all details are correct", Toast.LENGTH_SHORT).show();
                    return;
                }

                //CONTACT.setFirstName(firstName);
                //CONTACT.setLastName(lastName);
                //CONTACT.setPhoneNumber(phoneNumber);

                //Update to database
                //mContactDAO.update(CONTACT);

                mContactDAO.updateContactDetail(firstName,lastName,phoneNumber);
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_update_options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete: {
                mContactDAO.delete(CONTACT);
                setResult(RESULT_OK);
                finish();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
