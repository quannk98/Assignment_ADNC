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
    private Music_Fragment musicFragment;
    private News_Fragment newsFragment;
    private Person_Fragment personFragment;


    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                if (musicFragment == null) {
                    musicFragment = new Music_Fragment();
                }
                return musicFragment;
            case 1:
                if (newsFragment == null) {
                    newsFragment = new News_Fragment();
                }
                return newsFragment;
            case 2:
                if (personFragment == null) {
                    personFragment = new Person_Fragment();
                }
                return personFragment;
            default:
                if (musicFragment == null) {
                    musicFragment = new Music_Fragment();
                }
                return musicFragment;

        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
