# java-generator
Generator Controller &amp; Service &amp; Entities using Postgres Table


####  Basic Config

```java

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
public class GeneratorTest {

    @Autowired
    DataSource dataSource;

    @Test
    @Ignore
    public void test() throws Exception {

        String rootPackage = this.getClass().getPackage().getName();

        String projectRoot = "/your/project/root";

        JavaGenerator javaGenerator = JavaGeneratorBuilder.builder()
                .connection(dataSource.getConnection())
                .packageName(rootPackage)
                .projectRoot(projectRoot)
                .build();

    }
}
```

#### Generate Dto Objects

```java

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
public class GeneratorTest {

    @Autowired
    DataSource dataSource;

    @Test
    @Ignore
    public void test() throws Exception {

        String rootPackage = this.getClass().getPackage().getName();

        String projectRoot = "/your/project/root";

        JavaGenerator javaGenerator = JavaGeneratorBuilder.builder()
                .connection(dataSource.getConnection())
                .packageName(rootPackage)
                .projectRoot(projectRoot)
                .build();

        javaGenerator.makeDto(User.class, "Dto");
    }
}

```

#### Generate Dto with validation

```java


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
public class GeneratorTest {

    @Autowired
    DataSource dataSource;

    @Test
    @Ignore
    public void test() throws Exception {

        String rootPackage = this.getClass().getPackage().getName();

        String projectRoot = "/your/project/root";

        JavaGenerator javaGenerator = JavaGeneratorBuilder.builder()
                .connection(dataSource.getConnection())
                .packageName(rootPackage)
                .projectRoot(projectRoot)
                .build();

        javaGenerator.makeDtoWithValidation(User.class, "Dto", "users");
    }
}


```