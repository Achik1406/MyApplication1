package ftmk.bitp3453.helloclass.RestAPI;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {

    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new GetJokesActivityFrag();
            case 1 :
                return  new GetUniversityFrag();

            default:
                return new GetJokesActivityFrag();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}