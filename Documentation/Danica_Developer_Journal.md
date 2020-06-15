## Date: 15/06/2020

**Time Taken**: 2 hour

**Done**: Splash screen

---

### Useful Links

[How to Make Splash Screen in Android](https://hackernoon.com/how-to-make-splash-screen-in-android-e8333yoq)

[Android Hide Title Bar and Full Screen Example](https://www.javatpoint.com/android-hide-title-bar-example)

### Step

1. Create a new `activity` for `splash activity`

2. Change launching `activity` with `splash activity`:

   Go to  `manifest.xml`, change the `<intent-filter>...</intent-filter>` to splash activity
  
        ```java
        <activity android:name=".SplashActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity android:name=".MainActivity">
        </activity>
        ```
3. Design the `splash activiy`

4. Add a timer that will trigger next activity

    ```java
    public class SplashActivity extends AppCompatActivity {
    
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash_screen);
          
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
            }, 2000); //means 2 seconds
        }
    }
    ```

5. Hide Title Bar and Full Screen, add the code to `onCreate` function
    
    ```java
    //will hide the title
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    //hide the title bar
    getSupportActionBar().hide(); 
    //show the activity in full screen
    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); 
    ```
    
### Can Be Improved

1. The transition animation of the two activities

## Date: 16/06/2020

**Time Taken**: 2 hour

**Done**: Splash screen

---

### Useful Links
