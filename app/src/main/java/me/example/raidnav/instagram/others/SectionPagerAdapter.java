package me.example.raidnav.instagram.others;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionPagerAdapter extends FragmentPagerAdapter {

  private final List<Fragment> fragments = new ArrayList<>();

  public SectionPagerAdapter(@NonNull FragmentManager fm, int behavior) {
    super(fm, behavior);
  }

  @NonNull
  @Override
  public Fragment getItem(int position) {
    return fragments.get(position);
  }

  @Override
  public int getCount() {
    return fragments.size();
  }

  public void addFragment(Fragment fragment) {
    fragments.add(fragment);
  }
}
