import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.generated.Public;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.util.postgres.PostgresDSL;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.Properties;

public class T {
    @Test
    public void test() {
        Properties properties = new Properties();

        properties.put("dataSourceClassName", "org.postgresql.ds.PGSimpleDataSource");
        properties.put("dataSource.url", "jdbc:postgresql://" + System.getProperty("host") + ":" + System.getProperty("port") + "/test");
        properties.put("dataSource.user", "test");
        properties.put("dataSource.password", "test");

        DataSource source  = new HikariDataSource(new HikariConfig(properties));
        DSLContext context = new DefaultDSLContext(source, SQLDialect.POSTGRES);

        // --- working ---

        System.out.println(context
                .select(PostgresDSL.array(Public.PUBLIC.T.ID1))
                .from(Public.PUBLIC.T)
                .fetch());

        System.out.println(context
                .select(PostgresDSL.array(context.select(Public.PUBLIC.T.ID1).from(Public.PUBLIC.T)))
                .fetch());

        System.out.println(context
                .select(PostgresDSL.array(Public.PUBLIC.T.ID3))
                .from(Public.PUBLIC.T)
                .fetch());

        System.out.println(context
                .select(PostgresDSL.array(context.select(Public.PUBLIC.T.ID3).from(Public.PUBLIC.T)))
                .fetch());

        System.out.println(context
                .select(PostgresDSL.array(Public.PUBLIC.T.ID4))
                .from(Public.PUBLIC.T)
                .fetch());

        System.out.println(context
                .select(PostgresDSL.array(context.select(Public.PUBLIC.T.ID4).from(Public.PUBLIC.T)))
                .fetch());

        // --- not working ---

        System.out.println(context
                .select(PostgresDSL.array(Public.PUBLIC.T.ID2))
                .from(Public.PUBLIC.T)
                .fetch());

        System.out.println(context
                .select(PostgresDSL.array(context.select(Public.PUBLIC.T.ID2).from(Public.PUBLIC.T)))
                .fetch());
    }
}
