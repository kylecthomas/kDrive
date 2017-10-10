package com.kdrive.kyle.kdrive;

/**
 * Created by Kyle on 9/1/2017.
 */
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.tamir7.contacts.Contact;
import com.github.tamir7.contacts.PhoneNumber;

import java.util.List;


class ContactAdapter extends ArrayAdapter<Contact>{

    //public ContactAdapter(@NonNull Context context, @LayoutRes Contact[] myContacts) {
    public ContactAdapter(Context context, List<Contact> myContacts) {
        super(context, R.layout.contacts_list_item, myContacts);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        //TODO: adapt for use with all contacts, not those with only phone numbers
        LayoutInflater contactInflater = LayoutInflater.from(getContext());
        View contactView = contactInflater.inflate(R.layout.contacts_list_item, parent, false);

        Contact singleContact = getItem(position);
        TextView contactName = (TextView) contactView.findViewById(R.id.contactName);
        TextView contactNumberMain = (TextView) contactView.findViewById(R.id.contactNumber);

        contactName.setText(singleContact.getDisplayName());
        contactNumberMain.setText(singleContact.getPhoneNumbers().get(0).getNumber());

        return contactView;

    }
}
