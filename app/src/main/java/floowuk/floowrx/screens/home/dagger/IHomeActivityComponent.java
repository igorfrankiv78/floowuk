package floowuk.floowrx.screens.home.dagger;

import dagger.Component;
import floowuk.floowrx.app.builder.IRxMvpAppComponent;
import floowuk.floowrx.screens.home.FloowHomeActivity;

@IHomeScope
@Component(modules = HomeModule.class, dependencies = IRxMvpAppComponent.class)
public interface IHomeActivityComponent {
    void inject(FloowHomeActivity homeActivity);
}
