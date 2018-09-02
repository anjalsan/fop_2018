package com.fpl.fop.fop.common.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import com.fpl.fop.fop.common.injection.module.ApplicationTestModule;
import com.fpl.fop.fop.injection.component.AppComponent;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends AppComponent {
}
