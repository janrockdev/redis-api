# REDIS-API

Java Spring API wrapper for RedisDB.

### Desription:
Simple Java Spring API wrapper for RedisDB. Some projects require a persistent store, cache or simple DB service for React/Axios frontend.

### Dependencies:
```shell
brew install redis
brew services start redis
# optional Medis (Cool RedisDB GUI)
brew install medis
```

### Configuration:
```shell
vi resources/application.properties
#adjust application port or connection details for RedisDB
```

### Compile Code:
```shell
mvn clean package
```

### Run Code:
```shell
# adjust version
java -jar target/redis-api-0.0.1-SNAPSHOT.jar
```