package comstudy.myeaseanim;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import com.example.library.MyObjectAnimator;
import com.example.library.TimeInterpolator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bottom = findViewById(R.id.bottom);

//        ObjectAnimator animator  = ObjectAnimator.ofFloat(bottom,"ScaleX",1f,2f,3f,1f);
//        animator.setInterpolator(new LinearInterpolator());
//        animator.setDuration(3000);
//        animator.setRepeatCount(2);
//        animator.start();

        MyObjectAnimator objectAnimator = MyObjectAnimator.ofFloat(bottom, "ScaleX", 1.0f, 2.0f);
        objectAnimator.setDuration(3000);
        objectAnimator.setInterpolator(new TimeInterpolator());
        objectAnimator.start();


    }
}
