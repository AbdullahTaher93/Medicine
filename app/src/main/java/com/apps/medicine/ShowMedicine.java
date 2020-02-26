package com.apps.medicine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowMedicine extends AppCompatActivity {
public static String wordsearch;
    DatabaseReference databaseReference;
TextView textView,medicineName,medicineDes,medicineStat,medicineWarnings,medicineSideEffects,medicineTradeName,medicineDrugInteraction;

    AddNewMedicine addNewMedicine;
    List<AddNewMedicine> addNewMedicineslist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_medicine);
        textView=findViewById(R.id.text);
        textView.setText(wordsearch);
         addNewMedicine=new AddNewMedicine();
         addNewMedicineslist=new ArrayList<>();
         databaseReference= FirebaseDatabase.getInstance().getReference("Medicine");
            medicineName =findViewById(R.id.medicineName) ;
            medicineDes = findViewById(R.id.medicineDes);
            medicineStat = findViewById(R.id.medicineStat);;
            medicineWarnings =findViewById(R.id.medicineWarnings) ;
            medicineSideEffects = findViewById(R.id.medicineSideEffects);
            medicineTradeName = findViewById(R.id.medicineTradeName);
            medicineDrugInteraction =findViewById(R.id.medicineDrugInteraction) ;






    }
    public void showdata(View view){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                addNewMedicineslist.clear();
                for (DataSnapshot post:dataSnapshot.getChildren()){
                    addNewMedicine=post.getValue(AddNewMedicine.class);
                   // Log.d("", "print daata: "+addNewMedicine.MedicineName);
                    addNewMedicineslist.add(addNewMedicine);
                }
               int index=Search();
                if (index!=-1)
                {
                    medicineName.setText(addNewMedicineslist.get(index).MedicineName) ;
                    medicineDes.setText(addNewMedicineslist.get(index).MedicineDes) ;
                    medicineStat.setText(addNewMedicineslist.get(index).MedicineStat) ;
                    medicineWarnings.setText(addNewMedicineslist.get(index).MedicineWarnings) ;
                    medicineSideEffects.setText(addNewMedicineslist.get(index).MedicineSideEffects) ;
                    medicineTradeName.setText(addNewMedicineslist.get(index).MedicineTradeName) ;
                    medicineDrugInteraction.setText(addNewMedicineslist.get(index).MedicineDrugInteraction) ;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public int  Search(){
       String[] token=wordsearch.split(" ");


    int index=-1;
        for (int j=0;j<=token.length;j++){
            for (int i=0;i<addNewMedicineslist.size();i++)
            {
                Log.d("", "Search: "+token[j]+"   "+addNewMedicineslist.get(i).MedicineName);
              if(token[j].equalsIgnoreCase(addNewMedicineslist.get(i).MedicineName)){
                  index=i;
                  break;
              }

            }
            if (index!=-1)
                break;
       }
        return index;
    }


}
