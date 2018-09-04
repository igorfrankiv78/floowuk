package flowigor.ie.floowuk.services.dagger2;

import dagger.Component;
import floowuk.floowrx.app.builder.IRxMvpAppComponent;
import floowuk.floowrx.services.dagger.IServiceComponent;
import floowuk.floowrx.services.dagger.IServiceScope;
import floowuk.floowrx.services.dagger.ServiceModule;

/*** Created by igorfrankiv on 28/03/2018.*/
@IServiceScope
@Component(modules = ServiceModule.class, dependencies = IRxMvpAppComponent.class)
public interface ITestServiceComponent extends IServiceComponent {
    void inject( FloowServiceLocatorTest floowServiceLocatorTest );
}
