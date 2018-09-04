package floowuk.floowrx.services.dagger;

import dagger.Component;
import floowuk.floowrx.app.builder.IRxMvpAppComponent;
import floowuk.floowrx.services.FloowServiceLocator;
/*** Created by igorfrankiv on 01/03/2018.*/

@IServiceScope
@Component(modules = ServiceModule.class, dependencies = IRxMvpAppComponent.class)
public interface IServiceComponent {
    void inject(FloowServiceLocator floowServiceLocator);
}