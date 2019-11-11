package br.com.kbmg.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MessagesConfig {

    @Bean                 
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localResolver=new SessionLocaleResolver();
        localResolver.setDefaultLocale(new Locale("pt", "BR"));
        return localResolver;
    }

    @Bean(name = "messageConfig")
    public MessageSource messageResource() {
        ResourceBundleMessageSource messageBundleResrc=new ResourceBundleMessageSource();
        messageBundleResrc.setBasename("messages");
        return messageBundleResrc;
    }

}
