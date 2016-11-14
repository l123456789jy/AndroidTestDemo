package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.administrator.myapplication.model.Container;
import com.example.administrator.myapplication.model.Generator;
import com.example.administrator.myapplication.model.Main;
import java.util.Random;

/**
 * 泛型测试
 */
public class GenericityActivity extends AppCompatActivity implements Generator<String> {
    String[] fruits = new String[] { "Apple", "Banana", "Pear" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genericity);
        generTest();
    }


    private void generTest() {

        //在类上定义泛型
        Container<String, String> c1 = new Container<String, String>("name", "findingsea");
        Container<String, Integer> c2 = new Container<String, Integer>("age", 24);

        //接口使用泛型  implements Generator<String>
        next();
        //类的方法接受泛型.
        Main.out("a");
        Main.outMultiple(1, "aaa");

    }


    @Override public String next() {
        Random rand = new Random();
        return fruits[rand.nextInt(3)];
    }
}
