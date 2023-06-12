package quannkph29999.fpoly.assignment.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

import quannkph29999.fpoly.assignment.Fragment.Music_Fragment;
import quannkph29999.fpoly.assignment.Fragment.News_Fragment;
import quannkph29999.fpoly.assignment.Fragment.Person_Fragment;

public class ViewPager2Adapter extends FragmentStateAdapter {


    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:

                return new Music_Fragment();
            case 1:
                return new News_Fragment();
            case 2:

                return new Person_Fragment();
            default:
                return new Music_Fragment();

        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
