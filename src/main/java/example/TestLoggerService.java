package example;

import aoplogging.InoutLogging;

/**
 * User: Vanwin
 * Date: 14-10-21
 */
@InoutLogging
public class TestLoggerService {
    public TestStruct concatTestStruct(TestStruct testStruct1, TestStruct testStruct2){
        return new TestStruct(testStruct1.getId()+testStruct2.getId(), testStruct2.getName()+testStruct2.getName());
    }
}
