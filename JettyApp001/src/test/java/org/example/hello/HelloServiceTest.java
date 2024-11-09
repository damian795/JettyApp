package org.example.hello;

import org.example.lang.Lang;
import org.example.lang.LangRepository;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {
    private final static String WELCOME = "Hello";
    private final static String FALLBACK_ID_WELCOME = "Hola";
    
    @Test
    public void test_prepareGreeting_nullName_returnsGreetingWithFallbackName() throws Exception {
        //given
        var mockRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mockRepository);

        //when
        var result = SUT.prepareGreeting(null, -1);

        //then (wartosc spodziewana, result)
        assertEquals(WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }



    @Test
    public void test_prepareGreeting_name_returnsGreetingWithName() throws Exception {
        //given
        var mockRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mockRepository);
        String name = "test";

        //when
        var result = SUT.prepareGreeting(name, -1);

        //then (wartosc spodziewana, result)
        assertEquals(WELCOME + " " + name + "!", result);
    }

    @Test
    public void test_prepareGreeting_nonExistingLang_returnsGreetingWithFallbackLang() throws Exception {
        //given
        var mockRepository = new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                return Optional.empty();
            }
        };
        var SUT = new HelloService(mockRepository);

        //when
        var result = SUT.prepareGreeting(null, -1);

        //then (wartosc spodziewana, result)
        assertEquals(HelloService.FALLBACK_LANG.getWelcomeMsg() + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_nullLang_returnsGreetingWithFallbackIdLang() throws Exception {
        //given
        var mockRepository = fallbackLangIdRepository();
        var SUT = new HelloService(mockRepository);

        //when
        var result = SUT.prepareGreeting(null, null);

        //then (wartosc spodziewana, result)
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    /*
    @Test
    public void test_prepareGreeting_textLang_returnsGreetingWithFallbackIdLang() throws Exception {
        //given
        var mockRepository = fallbackLangIdRepository();
        var SUT = new HelloService(mockRepository);

        //when
        var result = SUT.prepareGreeting(null, "abc");

        //then (wartosc spodziewana, result)
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }
    */


    private static LangRepository fallbackLangIdRepository() {
        return new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                if (id.equals(HelloService.FALLBACK_LANG.getId())) {
                    return Optional.of(new Lang(null, FALLBACK_ID_WELCOME, null));
                }
                return Optional.empty();
            }
        };
    }

    private static LangRepository alwaysReturningHelloRepository() {
        return new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                return Optional.of(new Lang(null, WELCOME, null));
            }
        };
    }
}
