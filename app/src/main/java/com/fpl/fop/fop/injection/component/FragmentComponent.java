package com.fpl.fop.fop.injection.component;

import dagger.Subcomponent;

import com.fpl.fop.fop.features.home.HomeActivity;
import com.fpl.fop.fop.injection.PerFragment;
import com.fpl.fop.fop.injection.module.FragmentModule;
import com.fpl.fop.fop.mvrx.core.BaseFragment;

/**
 * This component inject dependencies to all Fragments across the application
 */
@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(BaseFragment BaseFragment);
}
