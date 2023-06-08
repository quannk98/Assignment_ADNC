package quannkph29999.fpoly.assignment.ScreenNews;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import quannkph29999.fpoly.assignment.Fragment.Music_Fragment;
import quannkph29999.fpoly.assignment.Fragment.News_Fragment;
import quannkph29999.fpoly.assignment.Fragment.Person_Fragment;

public class NewsViewPager2Adapter extends FragmentStateAdapter {


    public NewsViewPager2Adapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ThoiSuFragment();
            case 1:
                return new BongDaFragment();
            case 2:
                return new DuLichFragment();
            default:
                return new ThoiSuFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }


}
