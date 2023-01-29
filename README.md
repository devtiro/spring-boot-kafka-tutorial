# Create a Topic
```
docker exec broker \
kafka-topics --bootstrap-server broker:9092 \
             --create \
             --topic "customer.visit"
```

# Command Line Consumer
```
docker exec --interactive --tty broker \
kafka-console-consumer --bootstrap-server broker:9092 \
                       --topic "customer.visit" \
                       --from-beginning
```

# Command Line Producer
```
docker exec --interactive --tty broker \
   kafka-console-producer --bootstrap-server broker:9092 \
                          --topic "customer.visit"
```

# Jackson Dependencies
```
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-databind</artifactId>
</dependency>
<dependency>
  <groupId>com.fasterxml.jackson.datatype</groupId>
  <artifactId>jackson-datatype-jsr310</artifactId>
</dependency>
```

