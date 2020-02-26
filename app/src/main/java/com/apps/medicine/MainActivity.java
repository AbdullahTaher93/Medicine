package com.apps.medicine;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    ImageView m1;
    TextView textView;
    List<String> items=new ArrayList<>();
    ListView listView;
    Button open;
    private  int PICK_IMAGE_REQUEST=1;
    Uri imgUrl;

    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m1=findViewById(R.id.imageView);
        textView=findViewById(R.id.text);
        listView=findViewById(R.id.list);
        open=findViewById(R.id.open);

     //  databaseReference= FirebaseDatabase.getInstance().getReference("Medicine");
      // String ID =databaseReference.push().getKey();
     //  AddNewMedicine addNewMedicine=new AddNewMedicine(ID,"Omeprazole","يستعمل أوميبرازول (Omeprazole) لمعالجة الحالات التي يتم فيها إفراز كميات مفرطة من الأحماض في المعدة. فهو يقوم بتثبيط عمل جهاز اٍنزيماتي محدد موجود في داخل الخلايا المِعَدِيّة (في المعدة) التي تفرز الحامض، وبذلك يمنع إنتاج الحامض. يستعمل أوميبرازول لمعالجة: حرقة الفؤاد (Heartburn – الحَرَقة في المعدة)، التهاب المعدة الحاد (Gastritis)، التهاب الاثناعشريّ (التهاب العفج - Duodenitis)، اٍلتهاب المريء (Esophagitis) جرّاء رجوع محتوى المعدة، القرحة الهضمية (peptic ulcer) وحالات أخرى تتميز بإفراز مفرط للحامض (مثل متلازمة زولينغر اٍليسون - Zollinger Ellison Syndrome). كما يتم استعمال أوميبرازول، أحيانا، لفترة مؤقتة وكجزء من علاج مدمج، للقضاء على المَلْوِيَّة البَوّابِيّة (Helicobacter pylori – نوع من الجراثيم في المعدة).","","","","","");
     //   databaseReference.child(ID).setValue(addNewMedicine);
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String text= (String) listView.getItemAtPosition(position);
               ShowMedicine.wordsearch=text;
               Intent GoToShowMedicine=new Intent(MainActivity.this,ShowMedicine.class);
               startActivity(GoToShowMedicine);
           }
       });

       open.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               OpenFileChooser();

           }
       });

    }

    private void OpenFileChooser() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);

    }
    public void texfromimge(View view) throws IOException {
        Bitmap bitmap=  MediaStore.Images.Media.getBitmap(this.getContentResolver(), imgUrl);
        TextRecognizer textRecgnizer=new TextRecognizer.Builder(getApplicationContext()).build();
        if(!textRecgnizer.isOperational()){

        }else{
            Frame frame=new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> item=textRecgnizer.detect(frame);

            for (int i=0;i<  item.size();i++){
                TextBlock myitem=item.valueAt(i);
                String sb="";
                for (int j=0;j<myitem.getValue().length();j++)
                {
                    if((myitem.getValue().charAt(j)>='A'&&myitem.getValue().charAt(j)<='Z')||myitem.getValue().charAt(j)>='a'&&myitem.getValue().charAt(j)<='z'||(myitem.getValue().charAt(j)>='0'&&myitem.getValue().charAt(j)<='9')){
                        sb=sb+myitem.getValue().charAt(j);
                    }
                    else sb=sb+" ";
                }

                items.add(sb);
                //  sb.append("\n");

            }
            // textView.setText(sb.toString());
            ArrayAdapter<String> adapter = new ArrayAdapter(this,
                    android.R.layout.simple_list_item_multiple_choice,
                    items);
            listView.setAdapter(adapter);
        }
        //final TextView txt = (TextView) findViewById(R.id.txt);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1  && resultCode==RESULT_OK && data!=null){
            imgUrl=data.getData();
            Picasso.get().load(imgUrl).into(m1);
        }else{
            Log.d("", "onActivityResult: ");
        }
    }
}
