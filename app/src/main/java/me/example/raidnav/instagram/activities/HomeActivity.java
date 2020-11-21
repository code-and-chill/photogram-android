package me.example.raidnav.instagram.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.Objects;

import me.example.raidnav.instagram.R;
import me.example.raidnav.instagram.activities.fragments.CameraFragment;
import me.example.raidnav.instagram.activities.fragments.HomeFragment;
import me.example.raidnav.instagram.activities.fragments.MessagingFragment;
import me.example.raidnav.instagram.others.SectionPagerAdapter;

public class HomeActivity extends AppCompatActivity {

  @Override
  protected void onStart() {
    super.onStart();
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    // load images
    int defaultImage = R.drawable.place_holder_img;
    DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
        .showImageOnLoading(defaultImage)
        .showImageForEmptyUri(defaultImage)
        .showImageOnFail(defaultImage)
        .considerExifParams(true)
        .cacheOnDisk(true)
        .cacheInMemory(true)
        .cacheOnDisk(true).resetViewBeforeLoading(true)
        .imageScaleType(ImageScaleType.EXACTLY)
        .displayer(new FadeInBitmapDisplayer(300)).build();

    ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(HomeActivity.this)
        .defaultDisplayImageOptions(defaultOptions)
        .memoryCache(new WeakMemoryCache())
        .diskCacheSize(100 * 1024 * 1024).build();

    ImageLoader.getInstance().init(configuration);

    // bottom navigation view
    BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
//    BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationView);
//    BottomNavigationViewHelper.enableNavigation(mContext,this,bottomNavigationView);
    Menu menu = bottomNavigationView.getMenu();
    MenuItem menuItem = menu.getItem(0);
    menuItem.setChecked(true);

    // view pager
    SectionPagerAdapter adapter =
        new SectionPagerAdapter(this.getSupportFragmentManager(), 0);
    adapter.addFragment(new CameraFragment()); //index 0
    adapter.addFragment(new HomeFragment());  //index 1
    adapter.addFragment(new MessagingFragment()); //index 2
    ViewPager viewPager = findViewById(R.id.viewPager);
    viewPager.setAdapter(adapter);

    TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
    tabLayout.setupWithViewPager(viewPager);

    Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.ic_action_camera);
    Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.instagram_logo);
    Objects.requireNonNull(tabLayout.getTabAt(2)).setIcon(R.drawable.ic_action_message);
    viewPager.setCurrentItem(1, false);

  }
}
