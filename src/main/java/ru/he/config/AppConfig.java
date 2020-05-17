package ru.he.config;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import ru.he.mail.MailComponent;
import ru.he.mail.PreparedMailComponent;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@org.springframework.context.annotation.Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "ru.he")
@EnableAspectJAutoProxy
@EnableJpaRepositories(basePackages = "ru.he.repositoriesJpa")
@EnableTransactionManagement
@EnableWebSecurity
@EnableJdbcHttpSession
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer{

//    @Autowired
//    private Environment environment;
//
//    @Bean
//    public JdbcTemplate jdbcTemplate() {
//        return new JdbcTemplate(driverManagerDataSource());
//    }
//
//    @Bean
//    public DataSource driverManagerDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(environment.getProperty("db.driver"));
//        dataSource.setUrl(environment.getProperty("db.url"));
//        dataSource.setUsername(environment.getProperty("db.user"));
//        dataSource.setPassword(environment.getProperty("db.password"));
//        return dataSource;
//    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry){
//        resourceHandlerRegistry.addResourceHandler()
//    }

    @Bean
    public EmbeddedDatabase dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2).addScript("org/springframework/session/jdbc/schema-h2.sql").build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ServletContextFactory servletContextFactory() {
        return new ServletContextFactory();
    }

    @Bean
    public Configuration freemarkerConfiguration() {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        try {
            ServletContext servletContext = servletContextFactory().getObject();
            configuration.setServletContextForTemplateLoading(servletContext, "");
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        configuration.setDefaultEncoding("UTF-8");
        return configuration;
    }

    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setSuffix(".ftl");
        resolver.setContentType("text/html; charset=UTF-8");
        resolver.setOrder(0);
        return resolver;
    }


    @Bean
    public FreeMarkerConfigurer freemarkerConfigurer() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setDefaultEncoding("UTF-8");
        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/ftl/");
        return freeMarkerConfigurer;
    }

    @Autowired
    private Environment environment;

    @Bean
    public Properties fileService() {
        Properties properties = new Properties();
        properties.put("files.storage", environment.getProperty("files.storage"));
        return properties;
    }

    @Autowired
    private DataSource dataSource;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
                new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setDataSource(dataSource);
        //посмотрет models  и подключит через entity
        entityManagerFactoryBean.setPackagesToScan("ru.he.models.entities");

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);

        entityManagerFactoryBean.setJpaVendorAdapter(adapter);
        entityManagerFactoryBean.setJpaProperties(jpaProperties());

        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public Properties jpaProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect",
                "org.hibernate.dialect.MySQL57Dialect");
        properties.setProperty("hibernate.show_sql",
                "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        return properties;
    }



    @Bean
    public Properties mailProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        return properties;
    }

    @Bean
    public MailComponent mailComponent() {
        PreparedMailComponent mailComponent = new PreparedMailComponent();
        mailComponent.setSenderMailName("elvinkaska@gmail.com");
        mailComponent.setSenderMailPassword("Wolfimov1203651");
        mailComponent.setMailProperties(mailProperties());
        return mailComponent;
    }

    @Bean
    public Properties confirmationMailProperties() {
        Properties properties = new Properties();
        properties.put("subject", "Подтверждение почты");
        return properties;
    }

    @Bean
    public Template mailForConfirmationTemplate() {
        try {
            return freemarkerConfiguration().getTemplate("WEB-INF/ftl/confirmMail.ftl");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Bean
    public Template mailForInvitationConfirmationTemplate() {
        try {
            return freemarkerConfiguration().getTemplate("WEB-INF/ftl/confirmMailToInvitation.ftl");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Bean
    public Template mailForNotificationTemplate() {
        try {
            return freemarkerConfiguration().getTemplate("WEB-INF/ftl/notificationMail.ftl");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/html/");
        viewResolver.setSuffix(".html");
        viewResolver.setOrder(1);
        return viewResolver;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver(){
        return new CommonsMultipartResolver();
    }

}
