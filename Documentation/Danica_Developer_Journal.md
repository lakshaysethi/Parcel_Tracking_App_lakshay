## Date: 15/06/2020

**Time Taken**: 2 hours

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

5. Hide Title Bar and Full Screen, add the code to `onCreate` function (`requestFeature()` must be called before adding content)
    
    ```java
    //add before ContentView(...)
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

**Time Taken**: 2 hours

**Done**: Clour theme design, Login UI

---

### Useful Links

[Material Design Color Tool](https://material.io/resources/color/#!/?view.left=0&view.right=0&primary.color=ffc75f&secondary.color=E57373)

[Material Theming](https://material.io/design/material-theming/overview.html)

[ColorSpace](https://mycolor.space/?hex=%23FFC75F&sub=1)

## Date: 17/06/2020

**Time Taken**: 2 hours

**Done**: Signup UI

---

### Useful Links

[TabLayout Tutorial With Example In Android Studio](https://abhiandroid.com/materialdesign/tablayout-example-android-studio.html#Example_2_of_TabLayout_Using_ViewPager)

[Create swipe views with tabs using ViewPager](https://developer.android.com/guide/navigation/navigation-swipe-view#java)

### Create TabLayout Setps

1. In `activity.xml`

    ```java
    <androidx.viewpager.widget.ViewPager
        xmlns:android="http://schemas.android.com/apk/res/android"
        android:id="@+id/pager"
        android:layout_weight="1"
        android:layout_width="match_parent"
        android:layout_height="0dp">
    
        <com.google.android.material.tabs.TabLayout
            android:id="@+id/tab_layout"
            android:background="@color/colorPrimaryLight"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />
    
    </androidx.viewpager.widget.ViewPager>
    ```
    
2. In `activity.java` setup pager

3. Create fragment

## Date: 17/06/2020

**Time Taken**: 1 hours

**Done**: Administrator main UI, Driver main UI, Receiver main UI

---

### Useful Links

[How to set title for action bar in android?](https://www.tutorialspoint.com/how-to-set-title-for-action-bar-in-android)

### Can Be Improved

1. Add `notification button` in action bar

2. Add `profile button` in action bar

## Date: 17/06/2020

**Time Taken**: 4 hours

**Done**: Create Recyclerview and Cardview

---

### Useful Links

[Create a List with RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview)

[Android RecyclerView, Android CardView Example Tutorial](https://www.journaldev.com/10024/android-recyclerview-android-cardview-example-tutorial)

[Create a Card-Based Layout](https://developer.android.com/guide/topics/ui/layout/cardview)

### Step

1. Open the build.gradle file for app module  

2. Add the support library to the dependencies section
    
    ```java
    dependencies {
        ...
        implementation 'androidx.recyclerview:recyclerview:1.0.0'
    }
    ```

3. Add RecyclerView to layout

    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <!-- A RecyclerView with some commonly used attributes -->
     <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/my_recycler_view"
        android:scrollbars="vertical"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
    ```
4. Add CardView to a new layout (design the card content structure)

    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <androidx.cardview.widget.CardView
    ...>
    
        <LinearLayout
        ...>
    
            <TextView
                ...>
    
            <TextView
                ...>
    
        </LinearLayout>
    
    </androidx.cardview.widget.CardView>
    ```

5. Go to activity.java, obtain a handle to the object, connect it to a layout manager, and attach an adapter for the data to be displayed.

6. Add a list adapter in activity.java

## Date: 18/06/2020

**Time Taken**: 4 hours

**Done**: Create 

---

### Useful Links