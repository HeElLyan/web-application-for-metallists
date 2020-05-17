//package ru.he.config;
//
//import freemarker.template.Template;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.context.annotation.*;
//import org.springframework.context.support.ReloadableResourceBundleMessageSource;
//import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.Database;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
//import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.validation.DefaultMessageCodesResolver;
//import org.springframework.validation.MessageCodesResolver;
//import org.springframework.validation.Validator;
//import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
//import org.springframework.web.multipart.MultipartResolver;
//import org.springframework.web.multipart.commons.CommonsMultipartResolver;
//import org.springframework.web.servlet.LocaleResolver;
//import org.springframework.web.servlet.ViewResolver;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.i18n.CookieLocaleResolver;
//import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
//import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
//import ru.he.mail.MailComponent;
//import ru.he.mail.PreparedMailComponent;
////import springfox.documentation.builders.PathSelectors;
////import springfox.documentation.builders.RequestHandlerSelectors;
////import springfox.documentation.spi.DocumentationType;
////import springfox.documentation.spring.web.plugins.Docket;
////import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import javax.persistence.EntityManagerFactory;
//import javax.servlet.MultipartConfigElement;
//import javax.servlet.ServletContext;
//import javax.sql.DataSource;
//import java.io.IOException;
//import java.util.Locale;
//import java.util.Objects;
//import java.util.Properties;
//
//@Configuration
//@EnableWebMvc
//@PropertySource("classpath:application.properties")
//@EnableTransactionManagement
//@ComponentScan(basePackages = "ru.he")
////@EnableSwagger2
//@EnableJpaRepositories(basePackages = "ru.he")
//public class ApplicationContextConfig implements WebMvcConfigurer {
//
//    private Environment environment;
//
//
//    @Autowired
//    public ApplicationContextConfig(Environment environment) {
//        this.environment = environment;
//    }
//
//    @Override
//    public Validator getValidator() {
//        return getLocaleValidator();
//    }
//
//    @Bean
//    public LocalValidatorFactoryBean getLocaleValidator() {
//        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
//        bean.setValidationMessageSource(messageSource());
//        return bean;
//    }
//
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/WEB-INF/**").addResourceLocations("/WEB-INF/");
//        registry.addResourceHandler("/res/**").addResourceLocations("/res/");
//    }
//
//
//    @Bean
//    public ViewResolver viewResolver() {
//        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
//        viewResolver.setCache(true);
//        viewResolver.setContentType("text/html; charset=utf-8");
//        viewResolver.setPrefix("");
//        viewResolver.setSuffix(".ftl");
//        return viewResolver;
//    }
//
//    @Bean
//    public freemarker.template.Configuration configuration() {
//        freemarker.template.Configuration configuration = freemarkerConfig().getConfiguration();
//        configuration.setEncoding(new Locale("ru"), "utf-8");
//        return configuration;
//    }
//
//    @Bean
//    public FreeMarkerConfigurer freemarkerConfig() {
//        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
//        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/ftl");
//        freeMarkerConfigurer.setDefaultEncoding("UTF-8");
//        return freeMarkerConfigurer;
//    }
//
////    @Bean(name = "passwordEncoder")
////    @Profile({"mvc", "rest"})
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public JdbcTemplate jdbcTemplate() {
//        return new JdbcTemplate(driverManagerDataSource());
//    }
//
//    @Bean
//    public DataSource driverManagerDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty("db.driver")));
//        dataSource.setUrl(environment.getProperty("db.url"));
//        dataSource.setUsername(environment.getProperty("db.user"));
//        dataSource.setPassword(environment.getProperty("db.password"));
//        return dataSource;
//    }
//
//    @Bean
//    public JavaMailSender javaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        Properties mailProperties = new Properties();
//        mailProperties.put("mail.smtp.auth", environment.getProperty("mail.smtp.auth"));
//        mailProperties.put("mail.smtp.starttls.enable", environment.getProperty("mail.smtp.starttls.enable"));
//        mailProperties.put("mail.smtp.starttls.required", environment.getProperty("mail.smtp.starttls.required"));
//        mailProperties.put("mail.smtp.socketFactory.port", environment.getProperty("mail.smtp.socketFactory.port"));
//        mailProperties.put("mail.smtp.debug", environment.getProperty("mail.smtp.debug"));
//        mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        mailProperties.put("mail.smtp.socketFactory.fallback",
//                environment.getProperty("mail.smtp.socketFactory.fallback"));
//        mailSender.setJavaMailProperties(mailProperties);
//        mailSender.setHost(environment.getProperty("mail.host"));
//        mailSender.setPort(Integer.parseInt(environment.getProperty("mail.port")));
//        mailSender.setProtocol(environment.getProperty("mail.protocol"));
//        mailSender.setUsername(environment.getProperty("mail.username"));
//        mailSender.setPassword(environment.getProperty("mail.password"));
//        return mailSender;
//    }
//
//    @Bean
//    public MultipartConfigElement multipartConfigElement() {
//        return new MultipartConfigElement("");
//    }
//
//    @Bean
//    public MultipartResolver multipartResolver() {
//        org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver =
//                new org.springframework.web.multipart.commons.CommonsMultipartResolver();
//        multipartResolver.setMaxUploadSize(2000000000);
//        return multipartResolver;
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
//        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
//        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
//        entityManagerFactory.setDataSource(driverManagerDataSource());
//        entityManagerFactory.setPackagesToScan("ru.he.models.entities");
//        entityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter);
//        entityManagerFactory.setJpaProperties(additionalProperties());
//        return entityManagerFactory;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory);
//        return transactionManager;
//    }
//
//
//    private Properties additionalProperties() {
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.hbm2ddl.auto", "create");
//        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
//        properties.setProperty("hibernate.show_sql", "true");
//        return properties;
//    }
//
//
////    @Bean
////    public Docket api() {
////        return new Docket(DocumentationType.SWAGGER_2)
////                .select()
////                .apis(RequestHandlerSelectors.basePackage("ru.he"))
////                .paths(PathSelectors.any())
////                .build();
////    }
//
//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
//        jdbcTokenRepository.setDataSource(driverManagerDataSource());
//        return jdbcTokenRepository;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localeChangeInterceptor());
//    }
//
//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor() {
//        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
//        localeChangeInterceptor.setParamName("lang");
//        return localeChangeInterceptor;
//    }
//
//    // этот бин определяет текущую локализацию исходя из кук
//    @Bean
//    public LocaleResolver localeResolver() {
//        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
//        cookieLocaleResolver.setCookieName("localeInfo");
//        cookieLocaleResolver.setCookieMaxAge(60 * 60 * 24 * 365);
//        return cookieLocaleResolver;
//    }
//
//
//    @Bean
//    public MessageSource messageSource() {
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setBasename("classpath:messages/messages");
//        messageSource.setDefaultEncoding("UTF-8");
//        return messageSource;
//    }
//
//
//    @Override
//    public MessageCodesResolver getMessageCodesResolver() {
//        DefaultMessageCodesResolver codesResolver = new DefaultMessageCodesResolver();
//        codesResolver.setMessageCodeFormatter(DefaultMessageCodesResolver.Format.POSTFIX_ERROR_CODE);
//        return codesResolver;
//    }
//
//    @Bean
//    public ServletContextFactory servletContextFactory() {
//        return new ServletContextFactory();
//    }
//
//    @Bean
//    public freemarker.template.Configuration freemarkerConfiguration() {
//        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_29);
//        try {
//            ServletContext servletContext = servletContextFactory().getObject();
//            configuration.setServletContextForTemplateLoading(servletContext, "");
//        } catch (Exception e) {
//            throw new IllegalStateException(e);
//        }
//        configuration.setDefaultEncoding("UTF-8");
//        return configuration;
//    }
//
//    @Bean
//    public FreeMarkerViewResolver freeMarkerViewResolver() {
//        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
//        resolver.setSuffix(".ftl");
//        resolver.setContentType("text/html; charset=UTF-8");
//        resolver.setOrder(0);
//        return resolver;
//    }
//
//
//    @Bean
//    public FreeMarkerConfigurer freemarkerConfigurer() {
//        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
//        freeMarkerConfigurer.setDefaultEncoding("UTF-8");
//        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/ftl/");
//        return freeMarkerConfigurer;
//    }
//
//    @Bean
//    public Properties fileService() {
//        Properties properties = new Properties();
//        properties.put("files.storage", environment.getProperty("files.storage"));
//        return properties;
//    }
//
//
//    @Bean
//    public Properties jpaProperties() {
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.dialect",
//                "org.hibernate.dialect.MySQL57Dialect");
//        properties.setProperty("hibernate.show_sql",
//                "true");
//        properties.setProperty("hibernate.hbm2ddl.auto", "update");
//        return properties;
//    }
//
//
//
//    @Bean
//    public Properties mailProperties() {
//        Properties properties = new Properties();
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        properties.put("mail.smtp.port", "587");
//        return properties;
//    }
//
//    @Bean
//    public MailComponent mailComponent() {
//        PreparedMailComponent mailComponent = new PreparedMailComponent();
//        mailComponent.setSenderMailName("elvinkaska@gmail.com");
//        mailComponent.setSenderMailPassword("Wolfimov1203651");
//        mailComponent.setMailProperties(mailProperties());
//        return mailComponent;
//    }
//
//    @Bean
//    public Properties confirmationMailProperties() {
//        Properties properties = new Properties();
//        properties.put("subject", "Подтверждение почты");
//        return properties;
//    }
//
//    @Bean
//    public Template mailForConfirmationTemplate() {
//        try {
//            return freemarkerConfiguration().getTemplate("WEB-INF/ftl/confirmMail.ftl");
//        } catch (IOException e) {
//            throw new IllegalStateException(e);
//        }
//    }
//
//    @Bean
//    public Template mailForInvitationConfirmationTemplate() {
//        try {
//            return freemarkerConfiguration().getTemplate("WEB-INF/ftl/confirmMailToInvitation.ftl");
//        } catch (IOException e) {
//            throw new IllegalStateException(e);
//        }
//    }
//
//    @Bean
//    public Template mailForNotificationTemplate() {
//        try {
//            return freemarkerConfiguration().getTemplate("WEB-INF/ftl/notificationMail.ftl");
//        } catch (IOException e) {
//            throw new IllegalStateException(e);
//        }
//    }
//}
