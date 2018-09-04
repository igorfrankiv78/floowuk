package flowigor.ie.floowuk.screen.home.dagger2;

import dagger.Component;
import floowuk.floowrx.app.builder.IRxMvpAppComponent;
import floowuk.floowrx.screens.home.dagger.HomeModule;
import floowuk.floowrx.screens.home.dagger.IHomeScope;
import floowuk.floowrx.screens.home.dagger.IHomeActivityComponent;
/*** Created by igorfrankiv on 28/03/2018. ***/

@IHomeScope
@Component(modules = HomeModule.class, dependencies = IRxMvpAppComponent.class)
public interface ITestHomeActivityComponent extends IHomeActivityComponent {
    void inject( FloowHomeActivityTest floowHomeActivityTest );
}
