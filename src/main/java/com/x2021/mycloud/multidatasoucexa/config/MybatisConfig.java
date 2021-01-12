package com.x2021.mycloud.multidatasoucexa.config;


import com.mysql.cj.jdbc.MysqlXADataSource;
import com.x2021.mycloud.multidatasoucexa.holder.CustomSqlSessionTemplate;
import com.x2021.mycloud.multidatasoucexa.holder.DynamicDataSource;
import com.x2021.mycloud.multidatasoucexa.type.DatabaseType;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;


@Configuration
@MapperScan(basePackages = "com.x2021.mycloud.multidatasoucexa.mapper", sqlSessionTemplateRef = "sqlSessionTemplate")
public class MybatisConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource db1DataSource() {
        Properties props = new Properties();
        props.put("url", env.getProperty("jdbc.url"));
        props.put("user", env.getProperty("jdbc.username"));
        props.put("password", env.getProperty("jdbc.password"));
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setXaDataSourceClassName(MysqlXADataSource.class.getName());
        ds.setUniqueResourceName("db1DataSource");
        ds.setPoolSize(Integer.parseInt(env.getProperty("atomikos.poolSize")));
        ds.setXaProperties(props);
        return ds;
    }

    @Bean
    public DataSource db2DataSource() {
        Properties props = new Properties();
        props.put("url", env.getProperty("jdbc2.url"));
        props.put("user", env.getProperty("jdbc2.username"));
        props.put("password", env.getProperty("jdbc2.password"));
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setXaDataSourceClassName(MysqlXADataSource.class.getName());
        ds.setUniqueResourceName("db2DataSource");
        ds.setPoolSize(Integer.parseInt(env.getProperty("atomikos.poolSize")));
        ds.setXaProperties(props);
        return ds;
    }





    @Bean
    @Primary
    public DynamicDataSource getDataSource(@Qualifier("db1DataSource") DataSource db1DataSource,
            @Qualifier("db2DataSource") DataSource db2DataSource) {

        Map<Object, Object> targetDataSource = new HashMap<>(16);
        targetDataSource.put(DatabaseType.DB1, db1DataSource);
        targetDataSource.put(DatabaseType.DB2, db2DataSource);
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSource);
        dynamicDataSource.setDefaultTargetDataSource(db1DataSource);
        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryDB1(@Qualifier("db1DataSource") DataSource dataSource)
            throws Exception {
        return createSqlSessionFactory(dataSource);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryDB2(@Qualifier("db2DataSource") DataSource dataSource)
            throws Exception {
        return createSqlSessionFactory(dataSource);
    }


    @Bean(name = "sqlSessionTemplate")
    public CustomSqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactoryDB1") SqlSessionFactory sqlSessionFactoryDB1,
            @Qualifier("sqlSessionFactoryDB2") SqlSessionFactory sqlSessionFactoryDB2){
        Map<Object,SqlSessionFactory> sqlSessionFactoryMap = new HashMap<>(16);
        sqlSessionFactoryMap.put(DatabaseType.DB1,  sqlSessionFactoryDB1);
        sqlSessionFactoryMap.put(DatabaseType.DB2, sqlSessionFactoryDB2);
        CustomSqlSessionTemplate customSqlSessionTemplate = new CustomSqlSessionTemplate(sqlSessionFactoryDB1);
        customSqlSessionTemplate.setTargetSqlSessionFactorys(sqlSessionFactoryMap);
        customSqlSessionTemplate.setDefaultTargetSqlSessionFactory(sqlSessionFactoryDB1);
        return customSqlSessionTemplate;
    }

    /**
     * 创建数据源
     */
    private SqlSessionFactory createSqlSessionFactory(DataSource dataSource) throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setVfs(SpringBootVFS.class);
        bean.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session
                .Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(env.getProperty("mybatis.mapperLocations")));
        return bean.getObject();
    }
}
