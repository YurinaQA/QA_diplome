package ru.iteco.fmhandroid.ui;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

public class RecyclerViewMatcher {

    private final int recyclerViewId;

    public RecyclerViewMatcher(int recyclerViewId) {
        this.recyclerViewId = recyclerViewId;
    }

    public Matcher<View> atPositionOnView(final int position, final int targetViewId) {

        return new BoundedMatcher<View, View>(View.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("RecyclerView with id: " + recyclerViewId + " at position: " + position);
            }

            @Override
            protected boolean matchesSafely(View view) {
                RecyclerView recyclerView = view.getRootView().findViewById(recyclerViewId);
                if (recyclerView == null || recyclerView.getId() != recyclerViewId) {
                    return false;
                }
                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                if (viewHolder == null) {
                    // item not displayed yet
                    return false;
                }
                View targetView = viewHolder.itemView.findViewById(targetViewId);
                return view == targetView && view.isShown() && isDisplayed().matches(view);
            }
        };
    }
}

