package bokkoa.backend.apirest.apirest.auth;

public class JwtConfig {
    
    public static final String SECRET_KEY = "secret.key.121323";

    public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\r\n"+
    "MIIEpQIBAAKCAQEA03WCFRBzgGjjmwl7O7YhF2JMoNb9XQjSD1gseVejsql28RXX\r\n"+
    "bMDGJsHMh+Qz2Q7eR9D8aULAfscLYsGWRfwcJBmVwNo+0S9/tOXJnFQIMgNGqFP4\r\n"+
    "Quu1iu7wEXXr4EeQY4iBc5+iG6aEevS9JaGG8VQrgbfSNHM3NCtzI4gKHp+lAo26\r\n"+
    "gdZjUfWyZxRmalGJ4/c2UZwZeykC/IyWBQTkogdWvxtPua0XilltUHtdx7MVYag6\r\n"+
    "9z9cJB9wqlGpOv1oHWVCel6GVPb25gRRKvGDHrxpoBKQOVoqn5Eg3xYiK8pXnvUr\r\n"+
    "y/BQ26NoGCSbfiOntHOOLIFDjn+wP6KAKCkfcwIDAQABAoIBAD6BQY5QapPObVjq\r\n"+
    "mI6x614qJFR2uDXD4bO/cIX9rdml1g61UhRHd0sFhhu1DM5gYrv/vlyu6o+O7oKm\r\n"+
    "jnDpcLgLCAUVffP/glRKiCm5fSKrbTtcdsnxOpxyvGus26hT/w4sz3cJ/LUHp6lg\r\n"+
    "pnh1Ipo7MxEI8Q2n8O1u2n3qPHOIWxAeqPMJfBxSb2P290slILkCle/ZtVwDxloI\r\n"+
    "OI9IC4LD/1xVFtOqhVlDNqy8H03cUxGua1p5cyiWKQnZtv2rKG07o3pfFI6dcdPX\r\n"+
    "tf91Ll0ySuy2oa0Voc/9t6cMcTaPQuSnw9QyYrbmVlhDYrN6u3lV3iFg9XM8YjR/\r\n"+
    "CQvsGWECgYEA/Hj7VmTxzVa1EVbyfFgWVFyfVCZ57t8Qzu+y/KSFgwBVeiTyVb4V\r\n"+
    "PwgHQ+Hh44tcYjJQ4Q6/V3zVuHe1BShBbDYX3Y9IQf10+aRFge+NHWE2tTI2zd+a\r\n"+
    "ed4TAMcb6pI4naJeYLnrgfqJK+wRL91Qn3gRRA3O1g8OTCe/xEcHKg8CgYEA1mnV\r\n"+
    "TCUbXCEDeWLuwX/2cLQSYwIr+30oFTWEnoLLrIy3RzdOaY9Rh19EeNQAIXU4gTS2\r\n"+
    "l0RBCmNmB+j+ocFoVQUdSEtqpTYgdQUlzxWbt7HHWuXUYU3CPgdToXv8pTEBnIt8\r\n"+
    "nwnm52Om+/dMhc8Oxw8Qu+JX120hE/I7rTK7qF0CgYEAl49Q4iYq+edpPwWA/6Ap\r\n"+
    "l28avntyizEhd/hw7DUfaOrUZUS0rUA0pOf+5IM/BUftp40o/FHMD4sDCX/jDTuD\r\n"+
    "OqCmZvzSlbq/c/VnPH8/qTlojBGe0KvAohPsOHVOjtxEXGzSVyKE/DwYCGPoVWF4\r\n"+
    "OqLrSn03g0z6ekEKX5t784UCgYEAgOmSt7xbrdGfkBz+ZdK07eAmaebFYOhH8DCE\r\n"+
    "MvV61ZypGSVUfL0RjK2nsmnsRjF/Lpft7Ba6cC+BCeJ5LfHl87Amge/uB7T6YMEe\r\n"+
    "kVpxjElUDf++6F3jGn43NxEfvoYvbNqkq849SQOuQzHVjWIHhYE2U3Br1fcj+yUZ\r\n"+
    "4jFtiHUCgYEAp27AWW3O0FqHTlyXylKivLaNHvJxmzHMlwk1jNg1Fmc/O/JX7wAD\r\n"+
    "pszw5zCPWpep9JYB7zg5Nc1NdQgDW43shUO2GTQMCPp4Yrivp5Jsn7HhwSLJx2V/\r\n"+
    "0jPJekS61X9dgDTZn51nRz7KAnG87NPH+cOGuS+WrxV60S13flBVFpY=\r\n"+
    "-----END RSA PRIVATE KEY-----";


    public static final String RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----\r\n"+
    "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA03WCFRBzgGjjmwl7O7Yh\r\n"+
    "F2JMoNb9XQjSD1gseVejsql28RXXbMDGJsHMh+Qz2Q7eR9D8aULAfscLYsGWRfwc\r\n"+
    "JBmVwNo+0S9/tOXJnFQIMgNGqFP4Quu1iu7wEXXr4EeQY4iBc5+iG6aEevS9JaGG\r\n"+
    "8VQrgbfSNHM3NCtzI4gKHp+lAo26gdZjUfWyZxRmalGJ4/c2UZwZeykC/IyWBQTk\r\n"+
    "ogdWvxtPua0XilltUHtdx7MVYag69z9cJB9wqlGpOv1oHWVCel6GVPb25gRRKvGD\r\n"+
    "HrxpoBKQOVoqn5Eg3xYiK8pXnvUry/BQ26NoGCSbfiOntHOOLIFDjn+wP6KAKCkf\r\n"+
    "cwIDAQAB\r\n"+
    "-----END PUBLIC KEY-----";
}
