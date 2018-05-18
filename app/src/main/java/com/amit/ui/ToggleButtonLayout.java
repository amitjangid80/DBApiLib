package com.amit.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amit.dbapilibrary.R;
import com.amit.utilities.Utils;

import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.List;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * ToggleButtonLayout is a layout used to group related options. Layout and spacing is arranged to
 * convey that certain toggle buttons are part of a group.
 */
public class ToggleButtonLayout extends CardView {

    @Retention(SOURCE)
    @IntDef({MODE_WRAP, MODE_EVEN})
    @interface Mode {
    }

    /**
     * Toggles will wrap content. Default
     */
    public static final int MODE_WRAP = 0;

    /**
     * Toggles will be evenly distributed within view
     */
    public static final int MODE_EVEN = 1;

    private LinearLayout linearLayout;

    private List<Toggle> toggles = new ArrayList<>();
    //customization
    private boolean multipleSelection;
    private boolean allowDeselection = true;
    @ColorInt
    private Integer dividerColor;
    @ColorInt
    private Integer selectedColor;
    @LayoutRes
    private Integer layoutRes;
    @Mode
    private int mode;
    //callbacks
    private OnToggledListener listener;

    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Toggle toggle = (Toggle) v.getTag(R.id.tabToggleId);
            boolean currentSelection = toggle.selected;
            // if allowing deselection and currently selected, do nothing. Could invert it, but
            // it makes more sense to me this way
            if (!allowDeselection && currentSelection) {
                //do nothing
            } else {
                setToggled(toggle.getId(), !toggle.selected);
                if (listener != null) {
                    listener.onToggled(toggle, toggle.selected);
                }
            }
        }
    };

    public ToggleButtonLayout(Context context) {
        super(context);
        init(context, null);
    }

    public ToggleButtonLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ToggleButtonLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {

        linearLayout = new LinearLayout(context);
        addView(linearLayout);

        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.ToggleButtonLayout, 0, 0);

        if (a.hasValue(R.styleable.ToggleButtonLayout_multipleSelection))
        {
            multipleSelection = a.getBoolean(R.styleable.ToggleButtonLayout_multipleSelection, false);
        }

        if (a.hasValue(R.styleable.ToggleButtonLayout_allowDeselection))
        {
            allowDeselection = a.getBoolean(R.styleable.ToggleButtonLayout_allowDeselection, true);
        }

        if (a.hasValue(R.styleable.ToggleButtonLayout_dividerColor))
        {
            dividerColor = a.getColor(R.styleable.ToggleButtonLayout_dividerColor, Color.GRAY);
        }

        if (a.hasValue(R.styleable.ToggleButtonLayout_customLayout))
        {
            layoutRes = a.getResourceId(R.styleable.ToggleButtonLayout_customLayout, 0);
        }

        if (a.hasValue(R.styleable.ToggleButtonLayout_toggleMode))
        {
            @Mode
            int mode = a.getInt(R.styleable.ToggleButtonLayout_toggleMode, MODE_WRAP);
            this.mode = mode;
        }

        selectedColor = a.getColor(R.styleable.ToggleButtonLayout_tgSelectedColor,
                Utils.getThemeAttrColor(getContext(), R.attr.colorControlHighlight));

        //make sure this one is last
        if (a.hasValue(R.styleable.ToggleButtonLayout_menu))
        {
            inflateMenu(a.getResourceId(R.styleable.ToggleButtonLayout_menu, 0));
        }

        a.recycle();
    }

    /**
     * Add actions to the layout from the given menu resource id.
     *
     * @param menuId menu resource id
     */
    public void inflateMenu(@MenuRes int menuId)
    {
        @SuppressLint("RestrictedApi")
        Menu menu = new MenuBuilder(getContext());
        new MenuInflater(getContext()).inflate(menuId, menu);

        for (int i = 0; i < menu.size(); i++)
        {
            MenuItem item = menu.getItem(i);
            Toggle toggle = new Toggle(item.getItemId(), item.getIcon(), item.getTitle());
            addToggle(toggle);
        }
    }

    /**
     * Add a toggle to the layout
     *
     * @param toggle the toggle to add
     */
    public void addToggle(@NonNull Toggle toggle)
    {
        toggles.add(toggle);
        ToggleView toggleView = new ToggleView(getContext(), toggle, layoutRes);
        toggleView.setOnClickListener(onClickListener);

        if (dividerColor != null && toggles.size() > 1)
        {
            View divider = new View(getContext());
            divider.setBackgroundColor(dividerColor);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(Utils.dpToPx(getContext(), 1), ViewGroup.LayoutParams.MATCH_PARENT);
            divider.setLayoutParams(params);
            linearLayout.addView(divider);
        }

        if (mode == MODE_EVEN)
        {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
            toggleView.setLayoutParams(params);
        }

        linearLayout.addView(toggleView);
    }

    /**
     * Set the listener for when toggles get selected and deselected
     *
     * @param listener listener
     */
    public void OnToggleSelectedListener(OnToggledListener listener)
    {
        this.listener = listener;
    }

    /**
     * Set if we are going to allow multiple selection or not. This will also call {@link #reset()}
     * in order to prevent strange behaviour switching between multiple and single selection
     *
     * @param multipleSelection true if allowing multiple selection, false otherwise
     */
    public void setMultipleSelection(boolean multipleSelection)
    {
        this.multipleSelection = multipleSelection;
        reset();
    }

    /**
     * Allow selected items to be de-selected. Defaults to true.
     * @param allowDeselection allow de-selection
     */
    public void setAllowDeselection(boolean allowDeselection)
    {
        this.allowDeselection = allowDeselection;
    }

    /**
     * Reset all toggles to unselected
     */
    public void reset()
    {
        for (Toggle toggle : toggles)
        {
            toggle.selected = false;
            toggleState(toggle);
        }
    }

    /**
     * Manually set the toggled state of the specified toggle. This will not notify any {@link OnToggledListener}
     * @param toggleId the id of the toggle
     * @param toggled true if should be toggled on, false otherwise
     */
    public void setToggled(int toggleId, boolean toggled)
    {
        for (Toggle toggle : toggles)
        {
            if (toggle.getId() == toggleId)
            {
                toggle.selected = toggled;
                toggleState(toggle);

                if (!multipleSelection)
                {
                    for (Toggle otherToggle : toggles)
                    {
                        if (otherToggle != toggle && otherToggle.selected)
                        {
                            otherToggle.selected = false;
                            toggleState(otherToggle);
                            break;
                        }
                    }
                }

                break;
            }
        }
    }

    /**
     * Get a list of selected toggles
     * @return the selected toggles
     */
    @NonNull
    public List<Toggle> getSelectedToggles()
    {
        List<Toggle> selectedToggles = new ArrayList<>();

        for (Toggle toggle : toggles)
        {
            if (toggle.selected)
            {
                selectedToggles.add(toggle);
            }
        }

        return selectedToggles;
    }

    private void toggleState(Toggle toggle)
    {
        View v = linearLayout.findViewById(toggle.getId());
        v.setSelected(toggle.selected);

        if (toggle.selected)
        {
            v.setBackground(new ColorDrawable(selectedColor));
        }
        else
        {
            v.setBackground(null);
        }
    }

    /**
     * Default view for Toggle
     */
    @SuppressLint("ViewConstructor")
    static class ToggleView extends FrameLayout
    {
        Toggle toggle;
        TextView textView;
        ImageView imageView;

        public ToggleView(Context context, Toggle toggle, @Nullable @LayoutRes Integer layoutRes)
        {
            super(context);
            setId(toggle.getId());

            if (layoutRes != null)
            {
                inflate(context, layoutRes, this);
                textView = findViewById(android.R.id.text1);
                imageView = findViewById(android.R.id.icon);
            }
            else
            {
                textView = new TextView(context);
                imageView = new ImageView(context);

                addView(imageView);
                addView(textView);

                int eightDp = Utils.dpToPx(getContext(), 8);
                setPadding(eightDp, eightDp, eightDp, eightDp);
            }

            this.toggle = toggle;
            setTag(R.id.tabToggleId, toggle);
            textView.setText(toggle.getTitle());

            if (toggle.getIcon() != null)
            {
                imageView.setImageDrawable(toggle.getIcon());
            }

            setForeground(Utils.getThemeAttrDrawable(getContext(), R.attr.selectableItemBackground));
        }
    }

    /**
     * Listener for when a toggle is toggled
     */
    public interface OnToggledListener
    {
        /**
         * The toggle has been toggled
         * @param toggle the toggle
         * @param selected true if selected, false if unselected
         */
        void onToggled(Toggle toggle, boolean selected);
    }
}
