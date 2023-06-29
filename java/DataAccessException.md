# Jdbc
JdbcTemplate 를 사용하지 않고 Jdbc 를 직접 사용했을 경우 예외가 발생했을 시 `SQLException` 예외가 발생한다. `SQLException` 은 생성될 때, vendorCode(데이터베이스 벤더별 예외 코드)를 가지며, vendeorCode 를 읽기 위해서는 getErrorCode() 메서드를 사용한다.

예를 들어, 키값 중복 예외가 발생하면 벤더마다 고유코드값이 다르기 때문에 `SQLException` 에 담겨있는 venderCode 는 벤더마다 다른 값을 가진다. 

**e.g)**
1. `SQLException` catch
2. `SQLException` 객체의 getErrorCode() 를 통해 직접 벤더마다 다른 에러코드를 읽는다.
3. 에러코드에 맞는 예외를 확인하고 전환시킨다.

```java
public Customer insert(Customer customer) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("insert into customers(customer_id, name, email, created_at)" +
                        " values (UUID_TO_BIN(?), ?, ?, ?)");
                ) {
            statement.setBytes(1, customer.getCustomerId().toString().getBytes());
            statement.setString(2, customer.getName());
            statement.setString(3, customer.getEmail());
            statement.setTimestamp(4, Timestamp.valueOf(customer.getCreatedAt()));

            int executeUpdate = statement.executeUpdate();
            if (executeUpdate != 1) {
                throw new RuntimeException("Nothing was inserted");
            }

            return customer;
					// 체크 예외인 SQLException 을 잡는다.
        } catch (SQLException e) {
            logger.error("Got error while closing connection", e);

						// 여기서 e 의 errorCode() 를 읽어서 원하는 예외로 바꾸기 위해서는
						// 벤더마다 다른 예외 코드를 확인해야 한다.
            throw new RuntimeException(e); 
        }
    }
```

# DataAccessException
스프링의 JdbcTemplate 은 `SQLException` 의 에러 코드를 DB 별로 매핑해서 그에 해당하는 의미 있는 `DataAccessException` 의 서브클래스 중 하나로 전환해서 던져준다.
스프링은 매핑하는 작업을 간소화시키기 위헤. DB 별로 에러 코드를  매핑해주는 파일을 기본적으로 가지고 있다.

**e.g)**
1. Oracle DB 에서 `SQLException` 의 venderCode 가 900인 에러상황이  발생 
2. 스프링은 Oracle 매핑 파일을 읽어서 900 에러에 해당하는 예외로 매핑시켜준다.  이 예시의 경우 `DataAccessException` 의 서브클래스인 `BadSqlGrammarException` 예외로 매핑된다.
3. 결과적으로 `SQLException` → `BadSqlGrammarException` 예외 전환이 일어난다.

이런식으로 사용할 수 있다.

```java

    void testInsert() {
        try {
            customerNamedJdbcRepository.insert(customer);
        } catch (BadSqlGrammarException e) {
            int errorCode = e.getSQLException().getErrorCode();
            //org.springframework.jdbc.BadSqlGrammarException
            //BadSqlGrammarException Got errorCode = 1054
            System.out.println(e.getClass().getCanonicalName());
            System.out.println("BadSqlGrammarException Got errorCode = " + errorCode);
        }
				...
    }
```

여기서 catch 로 잡는 예외는 모두`DataAccessException` 의 서브 클래스들이다.

장점:

- 런타임 예외로 전환되기 때문에 예외처리 신경쓰지 않아도 된다. 원할 경우 thorws 를 통해 예외를 암시해줄 수 있다.
- DB가 달라져도 같은 종류의 에러라면 동일한 예외를 받을 수 있다.  나는 catch 로 `BadSqlGrammarException` 만 잡으면 어떤 DB 에 상관없이 동일한 에러를 처리할 수 있다.
- 즉, 데이터 액세스 기술에 독립적인 추상화된 예외를 사용하는 것이다.
