package example;

import aoplogging.InoutLoggerInterceptor;
import aoplogging.InoutLogging;
import com.google.inject.*;
import com.google.inject.matcher.Matchers;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Injector inj = Guice.createInjector(new Module(){
            @Override
            public void configure(Binder binder){
                binder.bindInterceptor(Matchers.annotatedWith(InoutLogging.class), Matchers.any(), InoutLoggerInterceptor.getInstance());
            }
        });

        inj.getInstance(TestLoggerService.class).concatTestStruct(new TestStruct(1,"struct1"), new TestStruct(2,"struct2"));
    }
}
